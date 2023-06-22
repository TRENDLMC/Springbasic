package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper mapper;
	
	@Test
	public void testGetFile() {
		mapper.getList().forEach(board->log.info(board));
	}
	
	@Test
	public void testInsert() {
		BoardVO board=new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		mapper.insert(board);
		
		log.info(board);
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO board=new BoardVO();
		board.setTitle("새로작성하는 글 select key");
		board.setContent("새로작성하는 내용 select key");
		board.setWriter("newbie");
		
		mapper.insertSelectKey(board);
		log.info(board);
	
	}
	
	@Test
	public void testRead() {
		BoardVO board=mapper.read(7L);
		log.info(board);
	}
	
	@Test
	public void testdelect() {
		log.info("delect count:" +mapper.delete(3L));
	}
	
	@Test
	public void testupdate() {
		BoardVO board=new BoardVO();
		board.setBno(5L);
		board.setContent("수정한 내용");
		board.setTitle("수정한 제목");
		board.setWriter("user00");
		
		int count=mapper.update(board);
		log.info("update count:"+count);
	}
	

}
