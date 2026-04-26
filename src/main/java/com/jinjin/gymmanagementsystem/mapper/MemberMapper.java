package com.jinjin.gymmanagementsystem.mapper;

import com.jinjin.gymmanagementsystem.pojo.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author shenjin
 * @date 2026/4/25
 */
@Mapper
public interface MemberMapper {
    Integer selectMemberCount();

    Member selectMemberByMemberAccount(Integer memberAccount);

    Boolean insertMember(Member member);

    List<Member> findAll();

    Boolean updateMemberByMemberAccount(Member member);

    Boolean deleteMemberByMemberAccount(Integer memberAccount);

    Member selectMemberByAccountAndPassword(Member member);
}
