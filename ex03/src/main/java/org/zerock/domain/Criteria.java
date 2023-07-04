package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	
	private int amount;
	private int PageNum;
	String type;
	String keyword;
	
	
	public Criteria() {
		this(1,10);
	}
	public Criteria(int Pagenum,int amount) {
		this.PageNum=Pagenum;
		this.amount=amount;
	}
	
	public String[] getTypeArr() {
		return type== null? new String[]{}:type.split("");
	}
	
	public String getListLink() {
		UriComponentsBuilder builder=UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.PageNum)
				.queryParam("amount", this.amount)
				.queryParam("keyword", this.keyword)
				.queryParam("type", this.type);
		
		return builder.toUriString();
	}
}
