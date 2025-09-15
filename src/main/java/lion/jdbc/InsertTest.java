package lion.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest {
    public static void main(String[] args) throws Exception{
        int result = insertDept(60, "LION", "SEOUT");
        System.out.println(result + "건의 데이터가 입력되었습니다.");
    }

    public static int insertDept(int deptNo, String dname, String loc) {
        // 0. 드라이버 로딩. (MySQL이 드라이버를 메모리에 올려준다.)
        // Class.forName("com.mysql.cj.jdbc.Driver"); // 생략 가능. 주석처리 해도 실행 됨.

        String url = "jdbc:mysql://localhost:3306/liondb"; // DBMS 마다 다르다
        String user = "root";
        String password = "root1234";

        String sql = "INSERT INTO DEPT(DEPTNO, DNAME, LOC) VALUES(?, ?, ?)";
        int result = 0;

        try (
                // 1. 접속 - Connection
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
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
            // 반드시 물음표(?)에 값을 채워줘야 한다. - 쿼리를 완성해야 한다.
            ps.setInt(1, deptNo); // 1번째 물음표에 deptNo값을 넣는다.
            ps.setString(2, dname); // 2번째 물음표에 dname값을 넣는다.
            ps.setString(3, loc); // 3번째 물음표에 loc값을 넣는다.

            // 3. 실행
            // ps.executeQuery(); // 리턴 타입이 ResultSet - SELECT
            result = ps.executeUpdate(); // 리턴 타입이 int - INSERT, UPDATE, DELETE
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
