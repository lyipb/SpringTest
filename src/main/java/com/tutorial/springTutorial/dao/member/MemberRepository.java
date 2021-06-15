package com.tutorial.springTutorial.dao.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tutorial.springTutorial.model.member.MemberAccountEntity;


public interface MemberRepository  extends JpaRepository<MemberAccountEntity, Integer>{
	List<MemberAccountEntity> findAll();
	
	List<MemberAccountEntity> findByEmail(String email);
	
	
	@Query(value="select id,email,password,address,cellphone from member_api_account where EMAIL = ?1 and PASSWORD = ?2 " ,nativeQuery = true)
	List<MemberAccountEntity> findCheckMemberAccount(String email,String password);

}