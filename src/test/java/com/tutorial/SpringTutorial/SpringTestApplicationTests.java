package com.tutorial.SpringTutorial;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.springTutorial.contoller.test.UnitTestController;
import com.tutorial.springTutorial.dao.member.MemberRepository;
import com.tutorial.springTutorial.exception.member.MemberNotFoundException;
import com.tutorial.springTutorial.model.member.MemberAccount;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@WebMvcTest(UnitTestController.class)
public class SpringTestApplicationTests {
	@Autowired
	private MockMvc mockMvc;
//	
	@Autowired
	  private UnitTestController controller;


	@Test
	public void testCtrller()throws Exception{
		assertThat(controller).isNotNull();
		
	}


//	@LocalServerPort
//	private int port;
//
//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	@Test
//	public void greetingShouldReturnDefaultMessage() throws Exception {
//		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/test",
//				String.class)).contains("Hello, World");
//	}
	
	
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/test")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, World")));
	}



	@Test
	public void createGetAPI() throws Exception {
//		String uri = "/memberApi/{id}";
//		MvcResult result = this.mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
//		int status = result.getResponse().getStatus();
//		System.out.println("HIHIHIHIH:" +status);
//		Assert.assertEquals("錯誤",200,status);
		  this.mockMvc.perform( MockMvcRequestBuilders
			      .get("/memberApi/{id}", 3)
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3));
	}


	

	@Test
	public void createPostAPI() throws Exception 
	{
		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setPassword("eclipsetest33312345678");
		memberAccount.setEmail("eclipsetest33@gmail.com");
		memberAccount.setCellphone("330912345789");
		memberAccount.setAddress("33eclipse台北市");
		this.mockMvc.perform( MockMvcRequestBuilders
	      .post("/memberApi")
	      .content(asJsonString(memberAccount))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

	}
	

	@Test
	public void whenDerivedExceptionThrown_thenAssertionSucceds() {
	    Exception exception = assertThrows(MemberNotFoundException.class, () -> {
	    	MemberAccount memberAccount = new MemberAccount();
			memberAccount.setId(1);
			memberAccount.setPassword("eclipseUpdatetest33312345678");
			memberAccount.setEmail("eclipseUpdatetest33@gmail.com");
			memberAccount.setCellphone("updagte330912345789");
			memberAccount.setAddress("update33eclipse台北市");
			this.mockMvc.perform( MockMvcRequestBuilders
		      .put("/memberApi")
		      .content(asJsonString(memberAccount))
		      .contentType(MediaType.APPLICATION_JSON)
		      .accept(MediaType.APPLICATION_JSON))
			  .andDo(print());
			 
	    });

	    String expectedMessage = "1";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}

	 
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}



	
	

}
	
