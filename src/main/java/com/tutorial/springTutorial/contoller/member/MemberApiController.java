package com.tutorial.springTutorial.contoller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.tutorial.springTutorial.dao.member.MemberRepository;
import com.tutorial.springTutorial.exception.member.MemberNotFoundException;
import com.tutorial.springTutorial.model.member.MemberAccountEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/memberApi")
public class MemberApiController {
//	@Autowired
//	MemberAccount memberAccount;
//	
//	 @RequestMapping("/memberApi/memberTest")
//	 public MemberAccount memberTest() {
//		 MemberAccount memberAccount = new MemberAccount();
//		 memberAccount.setAddress("台北市");
//		 memberAccount.setCellphone("09123456789");
//		 memberAccount.setEmail("test@gmail.com");
//		 memberAccount.setId(1);
//		 memberAccount.setPassword("123456789");
//		 return memberAccount;
//	 }
	
	@Autowired
	MemberRepository memberRepository;

	
	@RequestMapping(value="/{id}")
	 public Optional<MemberAccountEntity> read(@PathVariable int id) throws MemberNotFoundException {;
		
		if(!(memberRepository.findById(id)).isPresent()) {
			throw new MemberNotFoundException(String.valueOf(id));
		}
		return memberRepository.findById(id);
	 }
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public Optional<MemberAccountEntity> create(@RequestBody MemberAccountEntity memberaccount) throws MemberNotFoundException {
		memberRepository.saveAndFlush(memberaccount);
		return read(memberaccount.getId());
	 }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public Optional<MemberAccountEntity> update(@RequestBody MemberAccountEntity memberaccount) throws MemberNotFoundException {
		int memId = memberaccount.getId();
		
		if(!(memberRepository.findById(memId)).isPresent()) {
			throw new MemberNotFoundException(String.valueOf(memId));
		}
		memberRepository.saveAndFlush(memberaccount);
		return read(memberaccount.getId());
		
	 }	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	 public void delete(@PathVariable int id) {
		memberRepository.deleteById(id); 
		 
	 }	
	
	@GetMapping("/memberList")
    public ResponseEntity<String> memberListPage(Model model){
		List<MemberAccountEntity>  memberAccountList = new ArrayList<>();
		memberAccountList = memberRepository.findAll();
		model.addAttribute("memberAccountList", memberAccountList);
        return new ResponseEntity("test response data: member/memberListPage", HttpStatus.OK);
    }   
	

    @ExceptionHandler(value = MemberNotFoundException.class)
	public ModelAndView exception(MemberNotFoundException ex,WebRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ex", ex);
		modelAndView.addObject("message","你可以設定一些錯誤訊息");
		modelAndView.setViewName("error");
		return modelAndView;
	}
    
    //
//	@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="Member not found")
//  @ExceptionHandler(value = MemberNotFoundException.class)
//	public ResponseEntity<Object>exception(MemberNotFoundException ex,WebRequest request){
//		return new ResponseEntity<Object>(
//		          "NOT FOUND THE MEMBER ", new HttpHeaders(), HttpStatus.NOT_FOUND);
//	}

}



//curl --header "Content-Type: application/json;charset=UTF-8" \
//--request PUT \\
//--data ‘{\“Id\”:\”1\”, \”email\”: \”test999@gmail.com\”, \”cellphone\”: \”eclipsetest33@gmail.com\”,\”password\”: \”eclipsetest33312345678\”, \”address\”: \”33eclipse台北市\”}’ \
//http://localhost:8080/memberApi/
//
//		memberAccount.setPassword("eclipsetest33312345678");
//		memberAccount.setEmail("eclipsetest33@gmail.com");
//		memberAccount.setCellphone("330912345789");
//		memberAccount.setAddress("33eclipse台北市");




//curl -v -X PUT localhost:8080/memberApi/ -d "\“Id\”:\”1\”, \”email\”: \”test999@gmail.com\”, \”cellphone\”: \”eclipsetest33@gmail.com\”,\”password\”: \”eclipsetest33312345678\”, \”address\”: \”33eclipse台北市\”}" -H "Content-Type:application/json;charset=UTF-8"



