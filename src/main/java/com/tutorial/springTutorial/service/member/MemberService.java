package com.tutorial.springTutorial.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.springTutorial.dao.member.MemberDao;
import com.tutorial.springTutorial.model.member.MemberAccount;

@Service
public class MemberService {
	@Autowired
	MemberDao memberDao;
	public void addMember(MemberAccount memberAccount){
		memberDao.addMember(memberAccount);
	}
}
