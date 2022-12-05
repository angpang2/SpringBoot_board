package com.its.board.controller;

import com.its.board.dto.BoardDTO;
import com.its.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm() {
        return "boardPages/boardSave";
    }

    @PostMapping("/save")
    public String boardSave(@ModelAttribute BoardDTO boardDTO, Model model){
        System.out.println("boardDTO = " + boardDTO + ", model = " + model);
        Long result = boardService.save(boardDTO);
        if(result != null){
            List<BoardDTO> boardList = boardService.findAll();
            model.addAttribute("boardList",boardList);
            return "boardPages/boardList";
        }
        return "/index";

    }

    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDTO> boardList = boardService.findAll();
        model.addAttribute("boardList",boardList);
        return "boardPages/boardList";
    }


    @GetMapping("/{id}")
    public String boardFind(@PathVariable Long id,Model model){
        BoardDTO boardDTO = boardService.findById(id);
        boardService.updateView(id);
        model.addAttribute("board", boardDTO);
        return "boardPages/boardDetail";
    }


}
