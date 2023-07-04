package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;
import org.zerock.service.ReplyServiceImpl;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyMapperTests {
	private Long[] bnoArr= {1L,2L,3L,4L,5L,6L};
	
	
	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private ReplyService service;
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i->{
			ReplyVO vo=new ReplyVO();
			
			vo.setBno(bnoArr[i%5]);
			vo.setReply("´ñ±ÛÅ×½ºÆ®"+i);
			vo.setReplyer("replyer"+i);
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testRead() {
		long targerRno=5L;
		
		ReplyVO vo=mapper.read(targerRno);
		
		log.info(vo);
	}
	@Test
	public void testDelete() {
		Long targerRno=1L;
		
		mapper.delete(targerRno);
		
	}
	@Test
	public void testUpdate() {
		Long targerRno=10L;
		ReplyVO vo=mapper.read(targerRno);
		vo.setReply("update reply");
		int count =mapper.update(vo);
		
		log.info("UPDATE COUNT:"+count);
	}
	
	@Test
	public void testList() {
		Criteria cri=new Criteria();
		
		List<ReplyVO> replies=service.getList(cri,bnoArr[0]);
		
		replies.forEach(reply ->log.info(reply));
	}
	
	@Test
	public void testList2() {
		Criteria cri=new Criteria(1,10);
		
		List<ReplyVO> replies=service.getList(cri,1L);
		
		replies.forEach(reply ->log.info(reply));
	}
	
	
}
