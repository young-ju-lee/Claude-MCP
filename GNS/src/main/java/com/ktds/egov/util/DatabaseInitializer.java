package com.ktds.egov.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 데이터베이스 연결 테스트 및 테이블 생성을 담당하는 유틸리티 클래스
 */
public class DatabaseInitializer {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "dldudwn1!";
    private static final String SQL_SCRIPT_PATH = "./db/create_tables.sql";

    /**
     * 데이터베이스 연결을 테스트합니다.
     * @return 연결 성공 여부
     */
    public static boolean testConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
                System.out.println("PostgreSQL 연결 테스트 성공!");
                return true;
            }
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC 드라이버를 찾을 수 없습니다: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            System.err.println("PostgreSQL 연결 실패: " + e.getMessage());
            return false;
        }
    }

    /**
     * SQL 스크립트 파일을 읽어 테이블을 생성합니다.
     * @return 테이블 생성 성공 여부
     */
    public static boolean createTables() {
        if (!testConnection()) {
            return false;
        }

        try {
            String sqlScript = readSqlScript(SQL_SCRIPT_PATH);
            String[] sqlStatements = sqlScript.split(";");

            try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement()) {
                
                for (String sql : sqlStatements) {
                    if (sql.trim().isEmpty()) {
                        continue;
                    }
                    stmt.execute(sql);
                }
                System.out.println("스키마 및 테이블 생성 완료!");
                return true;
            }
        } catch (IOException e) {
            System.err.println("SQL 스크립트 파일 읽기 실패: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            System.err.println("SQL 실행 중 오류 발생: " + e.getMessage());
            return false;
        }
    }

    /**
     * SQL 스크립트 파일을 읽어 문자열로 반환합니다.
     * @param filePath SQL 스크립트 파일 경로
     * @return SQL 스크립트 문자열
     * @throws IOException 파일 읽기 실패 시 발생
     */
    private static String readSqlScript(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 주석 제거
                if (line.trim().startsWith("--")) {
                    continue;
                }
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * 메인 메서드 - 데이터베이스 연결 테스트 및 테이블 생성을 실행합니다.
     * @param args 명령행 인수 (사용하지 않음)
     */
    public static void main(String[] args) {
        if (testConnection()) {
            System.out.println("PostgreSQL 연결 테스트 성공!");
            
            if (createTables()) {
                System.out.println("GNS 스키마 및 테이블 생성 완료!");
            } else {
                System.err.println("GNS 스키마 및 테이블 생성 실패!");
            }
        } else {
            System.err.println("PostgreSQL 연결 테스트 실패!");
        }
    }
}
