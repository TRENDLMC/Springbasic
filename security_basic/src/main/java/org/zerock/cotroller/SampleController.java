package org.zerock.cotroller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/sample/*")
@Controller
public class SampleController {

	@GetMapping("/all")
	public void doAll(Authentication auth) {
		log.info("do all can access everybody");
		log.info("all:"+auth);
	}
	
	@GetMapping("/member")
	public void doMember(Authentication auth) {
		log.info("loginde member");
		log.info("member:"+auth);
		
	}
	
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("admin only");
	}
}
