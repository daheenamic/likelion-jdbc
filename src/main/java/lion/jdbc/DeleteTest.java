package lion.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteTest {
    public static void main(String[] args) {
        int result = deleteDept(50);
        System.out.println(result + "건의 데이터가 삭제되었습니다.");
    }

    public static int deleteDept(int deptNo) {
        String url = "jdbc:mysql://localhost:3306/liondb";
        String user = "root";
        String password = "root1234";

        String sql = "DELETE FROM DEPT WHERE DEPTNO = ?";
        int result = 0;

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, deptNo);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
