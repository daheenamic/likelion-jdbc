package lion.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest {
    public static void main(String[] args) throws Exception {
        // 자바 애플리케이션도 DBMS 입장에서는 하나의 클라이언트다.
        // DB와 통신하기 위해서는 먼저 접속(Connection)이 필요하다.
        // → Connection 객체는 "DB 연결"을 추상화한 객체로, SQL 실행을 위한 출발점 역할을 한다.

        // 문자열로 클래스 이름을 받아서 JVM에 그 클래스를 로드한다.
        // 이렇게 해야 DriverManager.getConnection()이 이 드라이버를 찾아서 MySQL에 연결할 수 있음.
        // 클래스를 찾기 전에 Libraries에 mysql:mysql-connector-java:8.0.33를 찾아서 등록 해줘야 한다.
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/liondb"; // DBMS 마다 다르다
        String user = "root";
        String password = "root1234";
        conn = DriverManager.getConnection(url, user, password);

        if ( conn != null ) {
            System.out.println("성공");
        } else {
            System.out.println("실패");
        }

        conn.close();
    }
}
