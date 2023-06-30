package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public BoardVO get(Long bno) {
		log.info("get............");
		return mapper.read(bno);
	}
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("getList............");
		return mapper.getListWithPaging(cri);
		
	}
	@Override
	public boolean modify(BoardVO board) {

		log.info("modify............");
		return mapper.update(board)==1;
	}
	@Override
	public void register(BoardVO board) {
		log.info("resgister......"+board);
		mapper.insertSelectKey(board);	
	}
	@Override
	public boolean remove(Long bno) {
		log.info("remove............");
		return mapper.delete(bno)==1;
	}
	
	
	

}
