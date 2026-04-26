package com.jinjin.gymmanagementsystem.controller;

import com.jinjin.gymmanagementsystem.pojo.Admin;
import com.jinjin.gymmanagementsystem.pojo.Member;
import com.jinjin.gymmanagementsystem.service.AdminService;
import com.jinjin.gymmanagementsystem.service.EmployeeService;
import com.jinjin.gymmanagementsystem.service.EquipmentService;
import com.jinjin.gymmanagementsystem.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenjin
 * @date 2026/4/24
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    private static final String SESSION_ADMIN = "admin";
    private static final String SESSION_MEMBER = "member";

    @Autowired
    private AdminService adminService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private EquipmentService equipmentService;

    @RequestMapping("/adminLogin")
    public ResponseEntity<Map<String, Object>> adminLogin(Admin admin, HttpSession session) {
        Admin loginIn = adminService.adminLogin(admin);
        if (loginIn != null) {
            session.setAttribute("admin", loginIn);
            putAdminMainDataToSession(loginIn, session);
            return ResponseEntity.ok(Map.of("message", "登录成功"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "账号或密码错误"));
        }

    }

    @RequestMapping("/userLogin")
    public ResponseEntity<Map<String, Object>> userLogin(Member member, HttpSession session) {
        Member loginIn = memberService.memberLogin(member);
        if (loginIn != null) {
            putMemberDataToSession(loginIn, session);
            return ResponseEntity.ok(Map.of("message", "登录成功", "member", loginIn));
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "账号或密码错误"));
        }
    }

    @RequestMapping("/toAdminMain")
    public ResponseEntity<Map<String, Object>> toAdminMain(HttpSession session) {
        Map<String, Object> body = new HashMap<>();
        body.put("success", true);
        body.put("memberTotal", session.getAttribute("memberTotal"));
        body.put("employeeTotal", session.getAttribute("employeeTotal"));
        body.put("equipmentTotal", session.getAttribute("equipmentTotal"));
        body.put("humanTotal", session.getAttribute("humanTotal"));
        return ResponseEntity.ok(body);
    }

    private void putAdminMainDataToSession(Admin admin, HttpSession session) {
        session.setAttribute(SESSION_ADMIN, admin);
        Integer memberTotal = memberService.selectTotalCount();
        Integer employeeTotal = employeeService.selectTotalCount();
        Integer equipmentTotal = equipmentService.selectTotalCount();
        Integer humanTotal = memberTotal + employeeTotal;
        session.setAttribute("humanTotal", humanTotal);
        session.setAttribute("memberTotal", memberTotal);
        session.setAttribute("employeeTotal", employeeTotal);
        session.setAttribute("equipmentTotal", equipmentTotal);
    }

    private void putMemberDataToSession(Member member, HttpSession session) {
        session.setAttribute(SESSION_MEMBER, member);
        session.setAttribute("memberAccount", member.getMemberAccount());
        session.setAttribute("memberName", member.getMemberName());
        session.setAttribute("memberGender", member.getMemberGender());
        session.setAttribute("memberAge", member.getMemberAge());
        session.setAttribute("memberPhone", member.getMemberPhone());
        session.setAttribute("cardTime", member.getCardTime());
        session.setAttribute("cardClass", member.getCardClass());
        session.setAttribute("cardNextClass", member.getCardNextClass());
    }
}
