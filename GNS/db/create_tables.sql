-- GNS 스키마 생성
CREATE SCHEMA IF NOT EXISTS gns;

-- AdminMapper에서 사용하는 테이블 생성
CREATE TABLE IF NOT EXISTS gns.por_popup (
    pop_id VARCHAR(20) PRIMARY KEY,
    pop_type VARCHAR(20),
    pop_date TIMESTAMP,
    pop_fr_date DATE,
    pop_to_date DATE,
    pop_title VARCHAR(200),
    pop_cont TEXT,
    pop_width INT,
    pop_height INT,
    user_id VARCHAR(50),
    pop_yn CHAR(1)
);

-- MemberMapper에서 사용하는 테이블 생성
CREATE TABLE IF NOT EXISTS gns.por_user (
    user_id VARCHAR(50) PRIMARY KEY,
    user_nm VARCHAR(100),
    user_pw VARCHAR(200),
    user_mail VARCHAR(100),
    tel1 VARCHAR(5),
    tel2 VARCHAR(5),
    tel3 VARCHAR(5),
    mobile1 VARCHAR(5),
    mobile2 VARCHAR(5),
    mobile3 VARCHAR(5),
    ll_id VARCHAR(50),
    user_auth_id VARCHAR(10),
    user_stat VARCHAR(10) DEFAULT '00',
    insrt_date TIMESTAMP,
    updt_date TIMESTAMP,
    login_fail_time VARCHAR(20) DEFAULT '0',
    login_fail_cnt INT DEFAULT 0,
    login_fail_ip VARCHAR(50),
    pw_updt_date TIMESTAMP,
    init_yn CHAR(1) DEFAULT 'Y',
    ucheck1 VARCHAR(10),
    ucheck2 VARCHAR(10),
    ucheck3 VARCHAR(10),
    salt VARCHAR(100),
    mail_yn CHAR(1) DEFAULT 'N',
    mail_code VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS gns.por_login (
    user_id VARCHAR(50),
    login_date TIMESTAMP,
    PRIMARY KEY (user_id, login_date)
);

CREATE TABLE IF NOT EXISTS gns.por_login_check (
    user_id VARCHAR(50) PRIMARY KEY,
    session_id VARCHAR(100),
    login_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS gns.tb_login_info (
    id VARCHAR(50),
    ip VARCHAR(50),
    gubun VARCHAR(20),
    login_date TIMESTAMP,
    logout_date TIMESTAMP,
    logfail_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS gns.tb_privacy_info (
    logtim TIMESTAMP,
    seq INT,
    sys_id VARCHAR(20),
    user_id VARCHAR(50),
    user_nm VARCHAR(100),
    org_nm VARCHAR(100),
    user_ip VARCHAR(50),
    cust_id VARCHAR(50),
    deal_dtm2 TIMESTAMP,
    rtrv_menu VARCHAR(100),
    rtrv_type_nm VARCHAR(100),
    multi_log_type VARCHAR(20),
    multi_log_param VARCHAR(200),
    rtrv_caus_nm VARCHAR(200),
    PRIMARY KEY (seq)
);

CREATE TABLE IF NOT EXISTS gns.tb_auth_info (
    user_id VARCHAR(50),
    user_approval_id VARCHAR(50),
    user_auth_id VARCHAR(10),
    gubun VARCHAR(20),
    event_code VARCHAR(20),
    auth_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS gns.por_mail_approval (
    user_ip VARCHAR(50),
    user_id VARCHAR(50),
    user_nm VARCHAR(100),
    user_mail VARCHAR(100),
    send_date TIMESTAMP,
    send VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS gns.por_mail_content (
    mail_cd VARCHAR(10) PRIMARY KEY,
    host VARCHAR(100),
    port VARCHAR(10),
    admin_mail VARCHAR(100),
    subject VARCHAR(200),
    content TEXT
);

CREATE TABLE IF NOT EXISTS gns.user_coki_info (
    coki_seq INT PRIMARY KEY,
    user_id VARCHAR(50),
    user_agent TEXT,
    coki_salt VARCHAR(100),
    coki_key VARCHAR(100),
    coki_saltEn VARCHAR(100),
    coki_keyEn VARCHAR(100),
    reg_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS gns.code_info (
    cd_id VARCHAR(50) PRIMARY KEY,
    cd_val TEXT
);

CREATE TABLE IF NOT EXISTS gns.por_stat (
    menu_id VARCHAR(20) PRIMARY KEY,
    menu_lvl1_nm VARCHAR(100),
    menu_lvl2_nm VARCHAR(100),
    menu_lvl3_nm VARCHAR(100),
    menu_cnt INT DEFAULT 0,
    use_yn CHAR(1) DEFAULT 'Y'
);

CREATE TABLE IF NOT EXISTS gns.por_stat_log (
    user_id VARCHAR(50),
    menu_id VARCHAR(20),
    in_date TIMESTAMP,
    PRIMARY KEY (user_id, menu_id, in_date)
);

CREATE TABLE IF NOT EXISTS gns.por_copy_t (
    no INT PRIMARY KEY,
    copy_no VARCHAR(10)
);

-- 필요한 기초 데이터 삽입
INSERT INTO gns.code_info (cd_id, cd_val) VALUES ('spublickey', 'default_public_key') ON CONFLICT (cd_id) DO NOTHING;
INSERT INTO gns.code_info (cd_id, cd_val) VALUES ('sprivatekey', 'default_private_key') ON CONFLICT (cd_id) DO NOTHING;

-- por_mail_content 기본 데이터
INSERT INTO gns.por_mail_content (mail_cd, host, port, admin_mail, subject, content) 
VALUES ('03', 'smtp.example.com', '25', 'admin@example.com', '회원인증 메일', '인증코드: {code}') 
ON CONFLICT (mail_cd) DO NOTHING;

-- por_copy_t에 1~31 숫자 추가 (날짜용)
DO $$
BEGIN
    FOR i IN 0..31 LOOP
        INSERT INTO gns.por_copy_t (no, copy_no) VALUES (i, LPAD(i::text, 2, '0')) ON CONFLICT (no) DO NOTHING;
    END LOOP;
END $$;

-- 관리자 계정 생성
INSERT INTO gns.por_user (
    user_id, user_nm, user_pw, user_mail, 
    user_auth_id, user_stat, insrt_date, updt_date, pw_updt_date, init_yn
) VALUES (
    'admin', '관리자', encode(digest('admin1234', 'sha256'), 'hex'), 'admin@example.com', 
    '40', '00', NOW(), NOW(), NOW(), 'N'
) ON CONFLICT (user_id) DO NOTHING;
