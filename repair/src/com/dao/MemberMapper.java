package com.dao;

import com.model.Member;

import java.util.List;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer memberId);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer memberId);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    int delMemberWithIds(List<String> delList);

    Member checkMemberCodeIsAlreadyExist(String memberCode);
}