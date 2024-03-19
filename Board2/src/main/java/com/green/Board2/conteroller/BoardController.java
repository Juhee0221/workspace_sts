package com.green.Board2.conteroller;

import com.green.Board2.VO.BoardVO;
import com.green.Board2.VO.CommentVO;
import com.green.Board2.VO.MemberVO;
import com.green.Board2.VO.SearchVO;
import com.green.Board2.service.BoardService;
import com.green.Board2.service.CommentService;
import com.green.Board2.service.CommentServiceImpl;
import com.green.Board2.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Resource(name = "commentService")
    private CommentService commentService;

    @Resource(name = "boardService")
    private BoardService boardService;

    //목록조회, 글쓰기, 상세조회, 수정, 삭제

    //게시글 목록 페이지
    //getMapping, postMapping 둘다 접속가능.

    @RequestMapping("/list")
    //커맨트객체는 html에 값을 넘겨주지 않아도 자동으로 값을 넘겨줌.
    //변수명이 넘어가는것이 아닌 클래스명에서 앞글자만 소문자로 바꿔진채로 넘어감.
    public String boardList(SearchVO searchVO, Model model){
        //전체데이터 수

        int totalDateCnt = boardService.selectBoardCnt();
        searchVO.setTotalDateCnt(totalDateCnt);

        //페이지 정보 세팅
        searchVO.setPageInfo();

        System.out.println(searchVO);
        List<BoardVO> list = boardService.selectBoard(searchVO);

        model.addAttribute("boardList", list);
        return "list";
    }

    //글쓰기 화면
    @GetMapping("/write")
    public String boardWrite(){

        return "write";
    }

    //글쓴 화면 내용 저장.
    @PostMapping("/write")
    public String boardWriteAll(BoardVO boardVO, HttpSession session){
        //세션에 저장된 로그인한 유저의 아이디로 조회
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        //boardVO에 작성자 데이터 저장
        boardVO.setWriter(loginInfo.getMemberId());

        boardService.insertBoard(boardVO);

        return "redirect:/board/list";
    }

    //글쓴 화면 상세내용
    @GetMapping("/select")
    public String boardSelectAll(BoardVO boardVO, Model model){
        //보더
        BoardVO board = boardService.selectBoardAll(boardVO.getBoardNum());
        boardService.updateReadCnt(boardVO.getBoardNum());

        model.addAttribute("board", board);

//        댓글
        List<CommentVO> commentList = commentService.selectComment(boardVO.getBoardNum());
        model.addAttribute("commentList",commentList);

        return "select";
    }

    //입력한 댓글 저장하는 메소드.
    @PostMapping("/select")
    public String commentInsert(CommentVO commentVO,HttpSession session){

        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        commentVO.setCommentWriter(loginInfo.getMemberId());
        commentService.insertComment(commentVO);

        return "redirect:/board/select?boardNum=" + commentVO.getBoardNum();
    }

    //글쓴 내용 삭제
    @GetMapping("/delete")
    public String boardDelete(@RequestParam(name = "boardNum") int boardNum){

        boardService.deleteBoard(boardNum);

        return "redirect:/board/list";
    }

    //글쓴 내용 수정
    @GetMapping("/update")
    public String boardUpdate(BoardVO boardVO, Model model){

        BoardVO board = boardService.selectBoardAll(boardVO.getBoardNum());
        model.addAttribute("board", board);

        return "update";
    }

    //게시글 수정
    @PostMapping("/update")
    public String boardUpdateAll(BoardVO boardVO){

        boardService.updateBoard(boardVO);

        return "redirect:/board/select?boardNum="+ boardVO.getBoardNum();
    }

}
