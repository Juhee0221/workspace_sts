package com.green.board.controller;

import com.green.board.service.BoardServiceImpl;
import com.green.board.vo.BoardVO;
import jakarta.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.lang.model.element.Name;
import java.util.List;

@Controller
public class BoardController {
    @Resource(name = "BoardService")
    private BoardServiceImpl boardService;

//     첫화면
    @GetMapping("/")
    public String boardList(Model model){
        List<BoardVO> list = boardService.selectBoardList();

        model.addAttribute("boardList", list);
        return "board_list";
    }

    // 글 작성 화면
    @GetMapping("/boardWriteFrom")
    public String boardWrite(){
        return "board_write_from";
    }

    @PostMapping("/boardWriteFrom")
    public String boardWrite(BoardVO boardVO){
        boardService.insertBoard(boardVO);

        return "redirect:/";
    }

    //상세페이지
    @GetMapping("/boardDetail")
    public String boardDetail (BoardVO boardVO ,Model model){

        BoardVO board = boardService.selectBoard(boardVO.getBoardNum());
        model.addAttribute("boardInfo", board);

        return "board_detail";
    }

    @GetMapping("/boardDelete")
    public String boardDelete(@RequestParam(name = "boardNo") int boardNo){

        boardService.deleteBoard(boardNo);

        return "redirect:/";
    }

    @GetMapping("/boardUpdate")
    public String boardUpdate(BoardVO boardVO,Model model){
        BoardVO board = boardService.selectBoard(boardVO.getBoardNum());
        model.addAttribute("update", board);

        return "update_form";
    }

    //게시글수정
    @PostMapping("/boardUpdate")
    public String boardUpdate(BoardVO boardVO){

         //게시글 수정
         boardService.updateBoard(boardVO);

        return "redirect:/boardDetail?boardNum=" + boardVO.getBoardNum();
    }



}
