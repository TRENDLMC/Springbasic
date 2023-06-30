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
		//before硫붿꽌�뱶�뒗 junit�쓣 �떎�뻾�븯湲곗쟾�뿉 �씠嫄� �젣�씪癒쇱� �떎�뻾�빐�씪 �씪�뒗�쓽誘�
		this.mockMvc =MockMvcBuilders.webAppContextSetup(ctx).build();
		//媛��긽�쓽 mvc 媛��긽�쓽 url怨� �뙆�씪誘명꽣瑜� 留뚮뱾�뼱�꽌 釉뚮씪�슦���뿉�꽌 �궗�슜�븯�뒗寃껋쿂�읆 controller瑜� �떆�뿕�빐蹂쇱닔�엲�쓬
	}
	
	@Test
	public void testList()throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				//�꽌踰꾩뿉�꽌 get諛⑹떇�쑝濡� ��嫄� 遺덈윭���씪
				.andReturn()
				.getModelAndView()
				//由ы꽩�떆�궓 媛믪쓣 紐⑤뜽 酉곕줈 媛��졇�샂 �솗�씤�븯湲곗쐞�빐
				.getModelMap());
				
	}
	
	@Test
	public void testRegister()throws Exception {
	
		String resultPage=mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title","새로운 제목")
				.param("content","새로운 내용")
				.param("writer","user01")
				//�깉湲��쓣 �벑濡앺븷�뻹 媛믪쓣 �꽔�뼱�빞�븯�뒗寃� �쓣 param�쑝濡� �꽔�쓬
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
				.param("title", "�닔�젙�븿")
				.param("content", "�닔�젙�궡�슜")
				.param("writer","�닔�젙�궗�엺"))
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
	
	


