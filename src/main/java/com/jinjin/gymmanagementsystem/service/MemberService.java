package com.jinjin.gymmanagementsystem.service;

import com.jinjin.gymmanagementsystem.pojo.Member;

import java.util.List;

/**
 * @author shenjin
 * @date 2026/4/24
 */
public interface MemberService {
    Integer selectTotalCount();

    Member selectMemberByMemberAccount(Integer memberAccount);

    List<Member> selectAllMember();

    Boolean insertMember(Member member);

    Boolean deleteMemberByMemberAccount(Integer memberAccount);

    Boolean updateMemberByMemberAccount(Member member);

    Member memberLogin(Member member);
}
