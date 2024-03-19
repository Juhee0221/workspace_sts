package com.green.Board2.conteroller;

import com.green.Board2.VO.CommentVO;
import com.green.Board2.VO.MemberVO;
import com.green.Board2.service.CommentService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Resource(name = "commentService")
    private CommentService commentService;



}
