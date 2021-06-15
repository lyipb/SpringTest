package com.tutorial.springTutorial.contoller.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.tutorial.springTutorial.dao.member.MemberRepository;
import com.tutorial.springTutorial.exception.member.MemberNotFoundException;
import com.tutorial.springTutorial.model.member.MemberAccountEntity;


@Controller
@RequestMapping("/member")
public class Member2Controller {
	//透過 @RequestMapping 指定從/會被對應到此addMemberPage()方法
//	@Autowired
//	MemberAccount memberAccount;
//	
//	@Autowired
//	MemberService memberService;
//	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	DataSource dataSource;
	 

	@GetMapping("/addMemberPage")
    public String addMemberPage(){

		List<MemberAccountEntity> memberAccountEntity = new ArrayList<MemberAccountEntity>();
		memberAccountEntity = memberRepository.findAll();

		for(int i=0;i<memberAccountEntity.size();i++){
			System.out.println(memberAccountEntity.get(i).getId());
		}
        return "addMemberPage"; 
    }
	
	@GetMapping("/login")
    public String login(Model model){
		model.addAttribute("memberAccountEntity", new MemberAccountEntity());

        return "login";
    }
	
	@PostMapping("/doLogin")
    public String doLogin(@ModelAttribute MemberAccountEntity memberAccountEntity, HttpSession session){
		String email = memberAccountEntity.getEmail();
		String password = memberAccountEntity.getPassword();
		List<MemberAccountEntity> MemberAccountJPAList = new ArrayList<MemberAccountEntity>();
		MemberAccountJPAList = memberRepository.findCheckMemberAccount(email, password);
		System.out.println(MemberAccountJPAList.size());
		MemberAccountEntity memberAccount = new  MemberAccountEntity();
		memberAccount.setPassword(password);
		memberAccount.setEmail(email);;

		if(MemberAccountJPAList.size()==0){
			return "login";
		}
		else{
			session.setAttribute("uid", memberAccount);
			System.out.println("HIHIHI");
	        return "welcome";
		}

    }
	
	
	@GetMapping("/memberList")
    public String memberListPage(Model model){
		List<MemberAccountEntity>  memberAccountList = new ArrayList<MemberAccountEntity>();
		memberAccountList = memberRepository.findAll();
		model.addAttribute("memberAccountList", memberAccountList);
        return "memberListPage";
    }   

}
