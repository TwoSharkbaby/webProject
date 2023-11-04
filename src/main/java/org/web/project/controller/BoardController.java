package org.web.project.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web.project.domain.BoardVO;
import org.web.project.domain.Criteria;
import org.web.project.domain.PageDTO;
import org.web.project.service.BoardService;

@Controller
@Log4j
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

//    @GetMapping("/list")
//    public void list(Model model){
//        model.addAttribute("list", boardService.getList());
//    }

    @GetMapping("/list")
    public void list(Criteria cri, Model model){
        model.addAttribute("list", boardService.getList(cri));
        model.addAttribute("pageMaker", new PageDTO(cri, boardService.getTotal(cri)));
    }

    @PostMapping("/register")
    public String register(BoardVO boardVO, RedirectAttributes rttr){
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

    @PostMapping("/modify")
    public String modify(BoardVO boardVO, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){
        log.info(boardVO);
        log.info(cri);
        if (boardService.modify(boardVO)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list" +cri.getListLink();
    }

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno")Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){
        if (boardService.remove(bno)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list" + cri.getListLink();
    }
}
