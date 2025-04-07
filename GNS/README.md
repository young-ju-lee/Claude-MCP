# GNS 프로젝트 소스구조 및 아키텍처

## 프로젝트 개요
GNS(KT DS Framework)는 Spring 기반의 웹 애플리케이션 프로젝트로, Spring MVC 패턴을 활용한 웹 서비스를 제공합니다. Spring Security를 통한 인증 및 권한 관리와 MyBatis를 통한 데이터베이스 접근을 지원합니다.

## 기술 스택
- **Java 1.6**
- **Spring Framework 3.2.0.RELEASE**
- **Spring Security 3.1.3.RELEASE**
- **MyBatis 3.1.1**
- **JSP/Servlet**
- **PostgreSQL 및 Oracle DB 지원**
- **JUnit 테스트 프레임워크**
- **Maven 기반 빌드 시스템**

## 프로젝트 구조

### 폴더 구조
```
GNS/
├── .classpath, .project, .settings/      # 이클립스 프로젝트 설정 파일
├── Jenkinsfile                          # Jenkins CI/CD 파이프라인 설정
├── lib/                                # 외부 라이브러리 파일 디렉토리
├── pom.xml                             # Maven 프로젝트 설정 파일
├── src/                                # 소스 코드
│   └── main/
│       ├── java/                        # Java 소스 파일
│       │   └── com/
│       │       └── ktds/
│       │           ├── egov/            # 전자정부 프레임워크 관련 소스
│       │           └── framework/       # 프레임워크 핵심 소스
│       ├── resources/                   # 리소스 파일
│       │   ├── classes/                 # 클래스 리소스
│       │   ├── conf/                    # 설정 파일
│       │   ├── sqlMap/                  # MyBatis SQL 맵핑 파일
│       │   ├── uml/                     # UML 다이어그램
│       │   └── views/                   # 뷰 템플릿
│       └── webapp/                      # 웹 애플리케이션 파일
│           ├── WEB-INF/                 # 웹 애플리케이션 설정
│           │   ├── conf/                # 웹 애플리케이션 설정 파일
│           │   ├── jsp/                 # JSP 뷰 파일
│           │   ├── lib/                 # 웹 애플리케이션 라이브러리
│           │   ├── tld/                 # Tag Library Descriptor 파일
│           │   └── web.xml              # 웹 애플리케이션 배포 설명자
│           ├── css/                     # CSS 파일
│           ├── js/                      # JavaScript 파일
│           ├── images/                  # 이미지 파일
│           └── index.html               # 기본 인덱스 페이지
└── target/                             # 빌드 결과물
```

## 아키텍처 개요

### 레이어드 아키텍처
GNS 프로젝트는 전형적인 3-계층 아키텍처를 따릅니다:

1. **프레젠테이션 레이어**
   - Controller 클래스 (MVC 패턴의 C)
   - JSP 뷰 템플릿 (MVC 패턴의 V)
   - HTML, CSS, JavaScript를 통한 프론트엔드 구현

2. **비즈니스 레이어**
   - Service 인터페이스 및 구현체
   - 비즈니스 로직 처리
   - 트랜잭션 관리

3. **데이터 액세스 레이어**
   - DAO (Data Access Object) 인터페이스 및 구현체
   - MyBatis를 통한 SQL 매핑
   - 데이터베이스 연동 로직

### Spring MVC 패턴
- **DispatcherServlet**: 모든 HTTP 요청의 진입점
- **Controller**: 요청 처리 및 모델 데이터 준비
- **View Resolver**: 뷰 이름을 실제 JSP 파일로 매핑
- **JSP 뷰**: 모델 데이터를 사용하여 HTML 응답 생성

### 보안 아키텍처
- Spring Security를 사용하여 인증 및 권한 부여 관리
- HTTP 요청에 대한 보안 필터 체인
- 사용자 인증 및 세션 관리

### 데이터 액세스
- MyBatis를 사용한 객체-관계 매핑
- XML 기반 SQL 매핑 파일
- 데이터 액세스 객체(DAO) 패턴

### 설정 관리
- XML 기반 Spring 설정
- 속성 파일을 통한 환경 설정
- 개발, 테스트, 운영 환경 분리

## 빌드 및 배포
프로젝트는 Maven을 사용하여 빌드되며, Jenkins CI/CD 파이프라인을 통해 배포됩니다. 최종 산출물은 WAR 파일로 생성되어 웹 서버(예: Apache Tomcat)에 배포됩니다.

```bash
# 빌드 명령어
mvn clean package
```

## 주요 설정 파일
- **pom.xml**: Maven 프로젝트 설정 및 의존성 관리
- **web.xml**: 웹 애플리케이션 설정
- **applicationContext.xml**: Spring 애플리케이션 컨텍스트 설정
- **servlet-context.xml**: Spring MVC 설정
- **security-context.xml**: Spring Security 설정
- **mybatis-config.xml**: MyBatis 설정