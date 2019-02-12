package com.service;

import com.common.StringUtils;
import com.dao.MemberDao;
import com.dao.MemberMapper;
import com.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberMapper mapper;

    @Autowired
    private MemberDao dao;


    public List<MemberAttribute> queryAllMembers(QueryData qo, Pagination page) {
        return dao.queryAllMembers(qo,page);
    }

    public int queryAllMembersTotal(QueryData qo, Pagination page) {
        return dao.queryAllMembersTotal(qo,page);
    }

    public int delMemberWithIds(List<String> delList) {
        return mapper.delMemberWithIds(delList);
    }

    public int insertMember(Member member) {
        return mapper.insertSelective(member);
    }

    public int updateMember(Member member) {
        return mapper.updateByPrimaryKeySelective(member);
    }

    public String checkMemberCodeIsAlreadyExist(String memberCode) {
        Member member =  mapper.checkMemberCodeIsAlreadyExist(memberCode);
        if(member != null){
            return "1";
        }else {
            return "0";
        }
    }

	public Member queryMemberByCode(String memberCode) {
		return mapper.checkMemberCodeIsAlreadyExist(memberCode);
	}
}
