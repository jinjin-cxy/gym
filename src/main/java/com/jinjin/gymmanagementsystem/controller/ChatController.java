package com.jinjin.gymmanagementsystem.controller;

import com.jinjin.gymmanagementsystem.pojo.Member;
import com.jinjin.gymmanagementsystem.service.ChatService;
import com.jinjin.gymmanagementsystem.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenjin
 * @date 2026/4/26
 */
@RestController
@RequestMapping("/api/chat")

public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private MemberService memberService;

    @PostMapping("/query")
    public Map<String, Object> chat(@RequestBody String content, String model, HttpSession session) {
        Map<String, Object> reponse = new HashMap<>();
        Member member = (Member) session.getAttribute("member");
        if (member != null) {
            System.out.println("会员号: " + member.getMemberAccount());
            System.out.println("会员姓名: " + member.getMemberName());
        }
        System.out.println("===================================");
        
        if (member == null) {
            reponse.put("success", false);
            reponse.put("reply", "请先登录");
            return reponse;
        }
        String enhancedContent = buildEnhancedContent(member, content);
        String reply = chatService.queryChat(enhancedContent, model);
        reponse.put("success", true);
        reponse.put("reply", reply);
        return reponse;
    }

    private String buildEnhancedContent(Member member, String content) {
        if (member == null || member.getMemberAccount() == null) {
            return content;
        }
        Member memberInfo = memberService.selectMemberByMemberAccount(member.getMemberAccount());
        System.out.println(memberInfo.getMemberName());
        return "你好，我是" + memberInfo.getMemberName() + "，" + content;
    }
}
