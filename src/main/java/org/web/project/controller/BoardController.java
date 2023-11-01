package org.web.project.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web.project.domain.BoardVO;
import org.web.project.service.BoardService;

@Controller
@Log4j
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public void list(Model model){
        model.addAttribute("list", boardService.getList());
    }

    @PostMapping("/register")
    public String register(BoardVO boardVO, RedirectAttributes rttr){
        boardService.register(boardVO);
        rttr.addFlashAttribute("result", boardVO.getBno());
        return "redirect:/board/list";
    }
}
