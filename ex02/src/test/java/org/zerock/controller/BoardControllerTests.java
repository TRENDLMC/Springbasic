package org.zerock.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class BoardControllerTests {
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	
	@Before
	public void setup() {
		//before메서드는 junit을 실행하기전에 이걸 제일먼저 실행해라 라는의미
		this.mockMvc =MockMvcBuilders.webAppContextSetup(ctx).build();
		//가상의 mvc 가상의 url과 파라미터를 만들어서 브라우저에서 사용하는것처럼 controller를 시험해볼수잇음
	}
	
	@Test
	public void testList()throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				//서버에서 get방식으로 저걸 불러와라
				.andReturn()
				.getModelAndView()
				//리턴시킨 값을 모델 뷰로 가져옴 확인하기위해
				.getModelMap());
				
	}
	
	@Test
	public void testRegister()throws Exception {
	
		String resultPage=mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title","컨텍스트 새글 제목")
				.param("content","컨텍스트글 새글내용")
				.param("writer","user01")
				//새글을 등록할떄 값을 넣어야하는것 을 param으로 넣음
				).andReturn()
				.getModelAndView()
				.getViewName();
		log.info(resultPage);
	}
	
	@Test
	public void testGet()throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders
				.get("/board/get")
				.param("bno", "4"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}
	
	@Test
	public void testModfiy()throws Exception{
		String resultPage=mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "1")
				.param("title", "수정함")
				.param("content", "수정내용")
				.param("writer","수정사람"))
				.andReturn()
				.getModelAndView()
				.getViewName();
		log.info(resultPage);
	}
	
	@Test
	public void testRemove()throws Exception{
		String resultPage=mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "14"))
				.andReturn()
				.getModelAndView()
				.getViewName();
		log.info(resultPage);
	}
}
	
	


