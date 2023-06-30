package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

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
		board.setTitle("새 제목");
		board.setContent("새 내용");
		board.setWriter("newbie");
		mapper.insert(board);
		
		log.info(board);
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO board=new BoardVO();
		board.setTitle("�깉濡쒖옉�꽦�븯�뒗 湲� select key");
		board.setContent("�깉濡쒖옉�꽦�븯�뒗 �궡�슜 select key");
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
		board.setContent("�닔�젙�븳 �궡�슜");
		board.setTitle("�닔�젙�븳 �젣紐�");
		board.setWriter("user00");
		
		int count=mapper.update(board);
		log.info("update count:"+count);
	}
	
	@Test
	public void testPagin() {
		Criteria cri=new Criteria();
		cri.setPageNum(5);
		List<BoardVO> list=mapper.getListWithPaging(cri);
		list.forEach(board->log.info(board));
	}
	
	@Test
	public void testSearch() {
		Criteria cri=new Criteria();
		cri.setKeyword("새로");
		cri.setType("T");
		
		List<BoardVO> list=mapper.getListWithPaging(cri);
		
		list.forEach(BoardVO->log.info(BoardVO));
		
	}
	

}
