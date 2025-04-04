package com.ktds.egov.vo.member;

/**
 * 암호화된 개인정보를 저장하기 위한 VO 클래스
 */
public class EncryptedMemberVo extends MemberVo {
    
    private String encrypted_user_nm;    // 암호화된 이름
    private String encrypted_user_mail;  // 암호화된 이메일
    private String encrypted_tel1;       // 암호화된 전화번호1
    private String encrypted_tel2;       // 암호화된 전화번호2
    private String encrypted_tel3;       // 암호화된 전화번호3
    private String encrypted_mobile1;    // 암호화된 휴대전화번호1
    private String encrypted_mobile2;    // 암호화된 휴대전화번호2
    private String encrypted_mobile3;    // 암호화된 휴대전화번호3
    
    public String getEncrypted_user_nm() {
        return encrypted_user_nm;
    }
    
    public void setEncrypted_user_nm(String encrypted_user_nm) {
        this.encrypted_user_nm = encrypted_user_nm;
    }
    
    public String getEncrypted_user_mail() {
        return encrypted_user_mail;
    }
    
    public void setEncrypted_user_mail(String encrypted_user_mail) {
        this.encrypted_user_mail = encrypted_user_mail;
    }
    
    public String getEncrypted_tel1() {
        return encrypted_tel1;
    }
    
    public void setEncrypted_tel1(String encrypted_tel1) {
        this.encrypted_tel1 = encrypted_tel1;
    }
    
    public String getEncrypted_tel2() {
        return encrypted_tel2;
    }
    
    public void setEncrypted_tel2(String encrypted_tel2) {
        this.encrypted_tel2 = encrypted_tel2;
    }
    
    public String getEncrypted_tel3() {
        return encrypted_tel3;
    }
    
    public void setEncrypted_tel3(String encrypted_tel3) {
        this.encrypted_tel3 = encrypted_tel3;
    }
    
    public String getEncrypted_mobile1() {
        return encrypted_mobile1;
    }
    
    public void setEncrypted_mobile1(String encrypted_mobile1) {
        this.encrypted_mobile1 = encrypted_mobile1;
    }
    
    public String getEncrypted_mobile2() {
        return encrypted_mobile2;
    }
    
    public void setEncrypted_mobile2(String encrypted_mobile2) {
        this.encrypted_mobile2 = encrypted_mobile2;
    }
    
    public String getEncrypted_mobile3() {
        return encrypted_mobile3;
    }
    
    public void setEncrypted_mobile3(String encrypted_mobile3) {
        this.encrypted_mobile3 = encrypted_mobile3;
    }
}
