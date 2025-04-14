package com.ktds.egov.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

/**
 * PostgreSQL 테이블 생성 유틸리티
 * GNS 스키마 및 관련 테이블들을 생성합니다.
 */
public class PostgresTableCreator {
    
    // PostgreSQL 연결 정보
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dldudwn1!";
    
    /**
     * 메인 메소드
     */
    public static void main(String[] args) {
        try {
            // JDBC 드라이버 로드
            Class.forName("org.postgresql.Driver");
            
            System.out.println("PostgreSQL 서버에 연결 중...");
            
            // 데이터베이스 연결
            try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                System.out.println("연결 성공!");
                
                // SQL 스크립트 파일 읽기
                String sqlScript = readSqlFile("./GNS/db/create_tables.sql");
                
                // 스크립트 실행
                executeSqlScript(conn, sqlScript);
                
                // 테이블 목록 확인
                listTables(conn);
            }
            
            System.out.println("프로그램 완료");
            
        } catch (Exception e) {
            System.err.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * SQL 스크립트 파일을 읽습니다.
     */
    private static String readSqlFile(String filePath) throws Exception {
        System.out.println("SQL 스크립트 파일 읽는 중: " + filePath);
        
        // 파일 존재 여부 확인
        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("파일을 찾을 수 없습니다. 현재 작업 디렉토리: " + Paths.get("").toAbsolutePath());
            
            // 대체 경로 시도
            String altPath = "db/create_tables.sql";
            if (Files.exists(Paths.get(altPath))) {
                filePath = altPath;
                System.out.println("대체 경로에서 파일을 찾았습니다: " + altPath);
            } else {
                throw new Exception("SQL 스크립트 파일을 찾을 수 없습니다.");
            }
        }
        
        return Files.readString(Paths.get(filePath));
    }
    
    /**
     * SQL 스크립트를 실행합니다.
     */
    private static void executeSqlScript(Connection conn, String sqlScript) throws SQLException {
        System.out.println("SQL 스크립트 실행 중...");
        
        // 스크립트를 세미콜론으로 분리
        String[] sqlStatements = sqlScript.split(";");
        
        try (Statement stmt = conn.createStatement()) {
            for (String sql : sqlStatements) {
                // 공백 및 주석 제거
                sql = sql.trim();
                if (!sql.isEmpty() && !sql.startsWith("--")) {
                    try {
                        stmt.execute(sql);
                        System.out.println("SQL 실행 성공: " + sql.substring(0, Math.min(50, sql.length())) + "...");
                    } catch (SQLException e) {
                        System.err.println("SQL 실행 실패: " + sql);
                        System.err.println("오류: " + e.getMessage());
                        // 계속 진행
                    }
                }
            }
        }
        
        System.out.println("SQL 스크립트 실행 완료");
    }
    
    /**
     * 생성된 테이블 목록을 출력합니다.
     */
    private static void listTables(Connection conn) throws SQLException {
        System.out.println("\n--- gns 스키마 테이블 목록 ---");
        
        String query = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'gns' ORDER BY table_name";
        
        try (Statement stmt = conn.createStatement()) {
            var rs = stmt.executeQuery(query);
            int count = 0;
            
            while (rs.next()) {
                System.out.println((++count) + ". " + rs.getString("table_name"));
            }
            
            System.out.println("총 테이블 수: " + count);
        }
    }
}
