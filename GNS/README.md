# GNS(Global Network Service) 프로젝트

## 1. 개요

GNS(Global Network Service) 프로젝트는 글로벌 네트워크 서비스를 위한 웹 애플리케이션입니다.

## 2. 기술 스택

- Java 1.6
- Spring Framework 3.2.0
- Spring Security 3.1.3
- MyBatis 3.1.1
- PostgreSQL 9.1
- Maven

## 3. 프로젝트 구성

```
GNS/
├── db/                    # 데이터베이스 스크립트
├── lib/                   # 라이브러리
├── src/
│   └── main/
│       ├── java/          # 자바 소스 코드
│       ├── resources/     # 리소스 파일
│       │   ├── classes/   # 클래스 관련 리소스
│       │   ├── conf/      # 설정 파일
│       │   ├── sqlMap/    # MyBatis SQL 매핑 파일
│       │   ├── uml/       # UML 다이어그램
│       │   └── views/     # 뷰 템플릿
│       └── webapp/        # 웹 어플리케이션 파일
└── target/                # 빌드 결과물
```

## 4. 데이터베이스 설정

### PostgreSQL 연결 정보

```properties
jdbc.datasource=jdbc/DataSource
jdbc.driverClass=org.postgresql.Driver
jdbc.url=jdbc:postgresql://localhost:5432/postgres
jdbc.username=postgres
jdbc.password=dldudwn1!
```

### 테이블 생성 방법

1. `com.ktds.egov.util.PostgresTableCreator` 클래스를 실행하여 테이블을 생성할 수 있습니다.

   ```bash
   # 프로젝트 루트 디렉토리에서
   cd GNS
   mvn compile exec:java -Dexec.mainClass="com.ktds.egov.util.PostgresTableCreator"
   ```

## 5. 주요 기능

- 회원 관리
- 팝업 관리
- 통계
- 관리자 페이지

## 6. 설치 및 실행 방법

### 6.1 필수 요구사항

- JDK 1.6 이상
- Maven 3.x
- PostgreSQL 9.1 이상

### 6.2 프로젝트 빌드

```bash
cd GNS
mvn clean package
```

### 6.3 애플리케이션 실행

생성된 WAR 파일을 웹 애플리케이션 서버(Tomcat, JBoss 등)에 배포하여 실행합니다.

## 7. 개발자

- KT DS
