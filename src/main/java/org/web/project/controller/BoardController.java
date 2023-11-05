package org.web.project.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web.project.domain.BoardAttachVO;
import org.web.project.domain.BoardVO;
import org.web.project.domain.Criteria;
import org.web.project.domain.PageDTO;
import org.web.project.service.BoardService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Log4j
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public void list(Criteria cri, Model model){
        model.addAttribute("list", boardService.getList(cri));
        model.addAttribute("pageMaker", new PageDTO(cri, boardService.getTotal(cri)));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/register")
    public String register(BoardVO boardVO, RedirectAttributes rttr){
        if (boardVO.getAttachList() != null){
            boardVO.getAttachList().forEach(log::info);
        }
        boardService.register(boardVO);
        rttr.addFlashAttribute("result", boardVO.getBno());
        return "redirect:/board/list";
    }

    @GetMapping("/get")
    public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model){
        model.addAttribute("board", boardService.get(bno));
    }


    @GetMapping("/modify")
    public void modify(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model){
        model.addAttribute("board", boardService.get(bno));
    }

    @PreAuthorize("principal.username == #board.writer")
    @PostMapping("/modify")
    public String modify(BoardVO boardVO, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){
        log.info(boardVO);
        log.info(cri);
        if (boardService.modify(boardVO)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list" +cri.getListLink();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/register")
    public void register(){

    }

    @PreAuthorize("principal.username == #writer")
    @PostMapping("/remove")
    public String remove(@RequestParam("bno")Long bno, @ModelAttribute("cri") Criteria cri,
                         RedirectAttributes rttr, String writer){
        List<BoardAttachVO> attachVOList = boardService.getAttachList(bno);
        if (boardService.remove(bno)) {
            deleteFiles(attachVOList);
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list" + cri.getListLink();
    }

    @GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno) {
        log.info("getAttachList12 " + bno);
        return new ResponseEntity<>(boardService.getAttachList(bno), HttpStatus.OK);
    }

    private void deleteFiles(List<BoardAttachVO> attachList) {

        if (attachList == null || attachList.size() == 0) {
            return;
        }

        log.info("delete attach files...................");

        attachList.forEach(attach -> {
            try {
                String path = "C:\\picture\\" + attach.getUploadPath() + "\\" + attach.getUuid() + "_"
                        + attach.getFileName();
                Path file = Paths.get(path);
                Files.deleteIfExists(file);
                if (Files.probeContentType(file).startsWith("image")) {
                    String thumNailPath = "C:\\picture\\" + attach.getUploadPath() + "\\s_" + attach.getUuid() + "_"
                            + attach.getFileName();
                    Path thumNail = Paths.get(thumNailPath);
                    Files.delete(thumNail);
                }

            } catch (Exception e) {
                log.error(e.getMessage());
            }

        });

    }
}
