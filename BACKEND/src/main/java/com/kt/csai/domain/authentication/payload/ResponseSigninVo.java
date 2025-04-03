package com.kt.csai.domain.authentication.payload;

import java.util.Map;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ResponseSigninVo {

	private String userName; // 이름
	private String deptCd; // 소속 팀 코드
	private String deptName; // 소속 팀 명
	private String agencyCd; // 소속 본부 코드
	private String agencyName; // 소속 본부 명
	private String positionCd; // 그룹사 코드 | 본부 코드 | 팀 코드
	private String positionName; // 그룹사 명 | 본부 명 | 팀 명
	private String companyCd; // 회사코드
	private String companyName; // 회사명
	private String mobile; // 휴대폰 번호
	//private String email; // 이메일 주소

	private String resCd; // 응답 코드
	private String errMsg; // 에러 메세지
	private String ossAthnInntNo; // OSS인증고유번호
	
	private String loginId; // ID 
	
	private String isSkip = "false"; // skip

	public ResponseSigninVo() {}
	
	public ResponseSigninVo(Map userInfoMap) {
		this.resCd = "1";
		this.userName = (String) userInfoMap.get("userName");
		this.deptCd = (String) userInfoMap.get("deptCd");
		this.deptName = (String) userInfoMap.get("deptName");
		this.agencyCd = (String) userInfoMap.get("agencyCd");
		this.agencyName = (String) userInfoMap.get("agencyName");
		this.positionCd = (String) userInfoMap.get("positionCd");
		this.positionName = (String) userInfoMap.get("positionName");
		this.companyCd = (String) userInfoMap.get("companyCd");
		this.companyName = (String) userInfoMap.get("companyName");
		this.mobile = (String) userInfoMap.get("mobile");
		//this.email = (String) userInfoMap.get("email");
	}
}