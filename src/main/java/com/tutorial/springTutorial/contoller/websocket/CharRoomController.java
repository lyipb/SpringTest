package com.tutorial.springTutorial.contoller.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.springTutorial.model.websocket.ChatClientModel;
import com.tutorial.springTutorial.model.websocket.ServerResponseModel;



@Controller
public class CharRoomController {
	
	@MessageMapping("/messageControl")
	@SendTo("queue/responses")
		public ServerResponseModel said(ChatClientModel responseMessage) throws InterruptedException{
		

			return new ServerResponseModel("歡迎來到, " + responseMessage.getName());
			
					
		}
	
	@GetMapping("/chatroom")
	public String getChatRoomIndexPag3() {
		return "index";
	}
}


