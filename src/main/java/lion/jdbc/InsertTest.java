package lion.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertTest {
    public static void main(String[] args) throws Exception{
        // 0. 드라이버 로딩. (MySQL이 드라이버를 메모리에 올려준다.)
        // Class.forName("com.mysql.cj.jdbc.Driver"); // 생략 가능. 주석처리 해도 실행 됨.

        String url = "jdbc:mysql://localhost:3306/liondb"; // DBMS 마다 다르다
        String user = "root";
        String password = "root1234";

        // 1. 접속 - Connection
        Connection conn = DriverManager.getConnection(url, user, password);

        // 2. 쿼리 작성 - Statement vs PreparedStatement
        // SQL 문장은 실행 전에 DBMS가 알아들을 수 있도록 파싱(번역)된다.
        // Statement를 사용하면 값이 포함된 SQL이 그대로 실행된다.
        //   예) INSERT INTO STUDENTS (NAME) VALUES ('kim');
        //       INSERT INTO STUDENTS (NAME) VALUES ('park');
        //       INSERT INTO STUDENTS (NAME) VALUES ('lee');
        //   -> DBMS는 이 3개를 각각 다른 쿼리로 인식하고 매번 새로 파싱한다.
        //
        // PreparedStatement를 사용하면 값 자리를 ? 로 표시한 SQL을 미리 파싱해둔다.
        //   예) INSERT INTO STUDENTS (NAME) VALUES (?);
        //   -> 이후 실행 시 ? 자리에 값만 바꿔 넣으므로 같은 쿼리로 재사용된다.
        String sql = "INSERT INTO DEPT(DEPTNO, DNAME, LOC) VALUES(?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        // 반드시 물음표(?)에 값을 채워줘야 한다. - 쿼리를 완성해야 한다.
        ps.setInt(1, 50); // 1번째 물음표에 50이라는 값을 넣는다.
        ps.setString(2, "LION"); // 2번째 물음표에 "LION"이라는 값을 넣는다.
        ps.setString(3, "SEOUL"); // 3번째 물음표에 "SEOUL"이라는 값을 넣는다.

        // 3. 실행
        // ps.executeQuery(); // 리턴 타입이 ResultSet - SELECT
        int resultCount = ps.executeUpdate(); // 리턴 타입이 int - INSERT, UPDATE, DELETE

        if (resultCount > 0) {
            System.out.println("INSERT SUCCESS !!");
        } else {
            System.out.println("INSERT FAIL !!");
        }

        ps.close();
        conn.close();
    }
}