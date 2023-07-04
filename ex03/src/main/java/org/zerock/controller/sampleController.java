package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Ticket;
import org.zerock.domain.sampleVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class sampleController {

	@GetMapping(value="/getText",produces = "text/plain; charset=utf-8")
	public String getTest() {
		
		log.info("MIME TYPE"+MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
	}
	@GetMapping(value = "/getSample",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE})
	public sampleVO getSample() {
		return new sampleVO(112,"스타","로드");
	}
	@GetMapping(value = "/getSample2")
	public sampleVO  getSample2() {
		return new sampleVO(113,"로켓","라쿤");
	}
	
	@GetMapping(value = "/getList")
	public List<sampleVO> getList(){
		return IntStream.range(1, 10).mapToObj(i-> new sampleVO(i,i+"Frist",i+"Last"))
				.collect(Collectors.toList());
	}
	@GetMapping(value = "/getMap")
	public Map<String ,sampleVO> getMap(){
		Map<String,sampleVO> map=new HashMap<>();
		map.put("First", new sampleVO(111,"그루트","주니어"));
		map.put("First1", new sampleVO(111,"그루트","주니어"));
		map.put("First2", new sampleVO(111,"그루트","주니어"));
		map.put("First3", new sampleVO(111,"그루트","주니어"));
		map.put("First4", new sampleVO(111,"그루트","주니어"));
		map.put("First5", new sampleVO(111,"그루트","주니어"));
		map.put("First6", new sampleVO(111,"그루트","주니어"));
		map.put("First7", new sampleVO(111,"그루트","주니어"));
		return map; 
	}
	
	@GetMapping(value = "/check",params = {"height","weight"})
	public ResponseEntity<sampleVO> check(Double height,Double weight){
		sampleVO vo=new sampleVO(0,""+height,""+weight);
		
		ResponseEntity<sampleVO> result=null;
		if(height<150) {
			result= ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);	
		}else {
			result= ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return result;
	}
	

	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat ,@PathVariable("pid") Integer pid) {
		return new String[] {"cathegory:"+cat , "product:"+pid};
		
	}
	
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("convert........ticket"+ticket);
		
		return ticket;
	}
	
} 
