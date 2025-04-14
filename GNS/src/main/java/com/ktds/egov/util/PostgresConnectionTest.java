package com.ktds.egov.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * PostgreSQL 연결 테스트 유틸리티
 */
public class PostgresConnectionTest {
    
    // PostgreSQL 연결 정보
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dldudwn1!";
    
    /**
     * 메인 메소드
     */
    public static void main(String[] args) {
        try {
            testConnection();
            checkGnsSchema();
        } catch (Exception e) {
            System.err.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 데이터베이스 연결을 테스트합니다.
     */
    public static void testConnection() {
        System.out.println("===== PostgreSQL 연결 테스트 =====");
        
        try {
            // JDBC 드라이버 로드
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL JDBC 드라이버 로드 성공");
            
            // 데이터베이스 연결
            System.out.println("PostgreSQL 서버에 연결 시도 중...");
            try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                System.out.println("PostgreSQL 연결 성공!");
                
                // 버전 정보 출력
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT version()")) {
                    if (rs.next()) {
                        System.out.println("PostgreSQL 버전: " + rs.getString(1));
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC 드라이버를 찾을 수 없습니다.");
            System.err.println("오류: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("PostgreSQL 연결 실패!");
            System.err.println("오류: " + e.getMessage());
        }
    }
    
    /**
     * gns 스키마와 테이블을 확인합니다.
     */
    public static void checkGnsSchema() {
        System.out.println("\n===== GNS 스키마 확인 =====");
        
        try {
            // 데이터베이스 연결
            try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                // 스키마 확인
                boolean schemaExists = false;
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT schema_name FROM information_schema.schemata WHERE schema_name = 'gns'")) {
                    schemaExists = rs.next();
                }
                
                if (schemaExists) {
                    System.out.println("gns 스키마가 존재합니다.");
                    
                    // 테이블 목록 확인
                    try (Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'gns' ORDER BY table_name")) {
                        
                        System.out.println("\ngns 스키마의 테이블 목록:");
                        int count = 0;
                        
                        while (rs.next()) {
                            count++;
                            System.out.println(count + ". " + rs.getString("table_name"));
                        }
                        
                        if (count > 0) {
                            System.out.println("\n총 " + count + "개의 테이블이 있습니다.");
                        } else {
                            System.out.println("gns 스키마에 테이블이 없습니다.");
                            System.out.println("테이블을 생성하려면 PostgresTableCreator 클래스를 실행하세요.");
                        }
                    }
                } else {
                    System.out.println("gns 스키마가 존재하지 않습니다.");
                    System.out.println("스키마와 테이블을 생성하려면 PostgresTableCreator 클래스를 실행하세요.");
                }
            }
        } catch (SQLException e) {
            System.err.println("데이터베이스 조회 실패!");
            System.err.println("오류: " + e.getMessage());
        }
    }
}
