package com.ksh.database.vo;

import lombok.Data;

@Data
public class SigPSRuleVO {
	private long id;
	private String type;
	private String risk;
	private String method;
	private String signature;
	private String res_code;
	private String res_header;
	private String res_body;
}
