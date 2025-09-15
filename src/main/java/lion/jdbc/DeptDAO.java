package lion.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeptDAO {
    public int insertDept() {
        int result = 0;
        return result;
    }

    public int updateDept() {
        int result = 0;
        return result;
    }

    public int deleteDept(int deptNo) {
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
