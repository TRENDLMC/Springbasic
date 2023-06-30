package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	
	private int amount;
	private int PageNum;
	
	public Criteria() {
		this(1,10);
	}
	public Criteria(int Pagenum,int amount) {
		this.PageNum=Pagenum;
		this.amount=amount;
	}
}
