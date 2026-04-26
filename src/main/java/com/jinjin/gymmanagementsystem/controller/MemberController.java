package com.jinjin.gymmanagementsystem.controller;

import com.jinjin.gymmanagementsystem.pojo.Member;
import com.jinjin.gymmanagementsystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author shenjin
 * @date 2026/4/25
 */
@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/selMemberList")
    public ResponseEntity<Map<String, Object>> getMemberList() {
        List<Member> members = memberService.selectAllMember();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("memberList", members);
        return ResponseEntity.ok(response);
    }

    @RequestMapping("/queryMember/{memberAccount}")
    public ResponseEntity<Map<String, Object>> getMemberByMemberAccount(@PathVariable Integer memberAccount) {
        Member member = memberService.selectMemberByMemberAccount(memberAccount);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("member", member);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addMember")
    public ResponseEntity<Map<String, Object>> addMember(@RequestBody Member member) {
        //会员号随机生成
        Random random = new Random();
        String account1 = "2021";
        for (int i = 0; i < 5; i++) {
            account1 += random.nextInt(10);
        }
        Integer account = Integer.parseInt(account1);

        //初始密码
        String password = "123456";

        //获取当前日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = simpleDateFormat.format(date);

        Integer nextClass = member.getCardClass();

        member.setMemberAccount(account);
        member.setMemberPassword(password);
        member.setCardTime(nowDay);
        member.setCardNextClass(nextClass);

        memberService.insertMember(member);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("member", member);
        response.put("message", "Member added successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteMember/{memberAccount}")
    public ResponseEntity<Map<String, Object>> deleteMember(@PathVariable Integer memberAccount) {
        memberService.deleteMemberByMemberAccount(memberAccount);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Member deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateMember")
    public ResponseEntity<Map<String, Object>> updateMember(@RequestBody Member member) {
        memberService.updateMemberByMemberAccount(member);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("member", member);
        response.put("message", "Member updated successfully");
        return ResponseEntity.ok(response);
    }
}
