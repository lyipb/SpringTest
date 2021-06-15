package com.tutorial.springTutorial.exception.member;

import org.springframework.web.util.NestedServletException;

public class MemberNotFoundException extends NestedServletException{
	
	public MemberNotFoundException(String msg) {
		super(msg);
	}
	
	private static final long serialVersionUID = 1L;

}
