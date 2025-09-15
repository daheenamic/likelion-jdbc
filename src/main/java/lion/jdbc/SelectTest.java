package lion.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectTest {
    public static void main(String[] args) {

        // 1. 필요한 객체를 먼저 선언 (try와 finally 블럭이 다르기 때문에)
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT DEPTNO, DNAME, LOC FROM DEPT";

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                System.out.print(rs.getInt("DEPTNO") + "\t");
                System.out.print(rs.getString("DNAME") + "\t");
                System.out.println(rs.getString("LOC"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 2. 선언했으면 닫는 부분 코드를 먼저 작성 (중요함 !!)
            DBUtil.close(conn, ps, rs);
        }
    }
}
