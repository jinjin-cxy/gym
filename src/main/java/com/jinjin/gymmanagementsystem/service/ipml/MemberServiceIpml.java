package com.jinjin.gymmanagementsystem.service.ipml;

import com.jinjin.gymmanagementsystem.mapper.MemberMapper;
import com.jinjin.gymmanagementsystem.pojo.Member;
import com.jinjin.gymmanagementsystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shenjin
 * @date 2026/4/25
 */
@Service
public class MemberServiceIpml implements MemberService {
    @Autowired
    private MemberMapper memberMapper;
    
    @Override
    public Integer selectTotalCount() {
        return memberMapper.selectMemberCount();
    }

    @Override
    public Member selectMemberByMemberAccount(Integer memberAccount) {
        return memberMapper.selectMemberByMemberAccount(memberAccount);
    }

    @Override
    public List<Member> selectAllMember() {
        return memberMapper.findAll();
    }

    @Override
    public Boolean insertMember(Member member) {
        return memberMapper.insertMember(member);
    }

    @Override
    public Boolean deleteMemberByMemberAccount(Integer memberAccount) {
        return memberMapper.deleteMemberByMemberAccount(memberAccount);
    }

    @Override
    public Boolean updateMemberByMemberAccount(Member member) {
        return memberMapper.updateMemberByMemberAccount(member);
    }

    @Override
    public Member memberLogin(Member member) {
        return memberMapper.selectMemberByAccountAndPassword(member);
    }
}
