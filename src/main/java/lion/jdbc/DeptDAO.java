package lion.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeptDAO {

    public int insertDept(DeptDTO deptDto) {
        String url = "jdbc:mysql://localhost:3306/liondb";
        String user = "root";
        String password = "root1234";

        String sql = "INSERT INTO DEPT(DEPTNO, DNAME, LOC) VALUES(?, ?, ?)";
        int result = 0;

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setInt(1, deptDto.getDeptNo());
            ps.setString(2, deptDto.getDname());
            ps.setString(3, deptDto.getLoc());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int updateDept(DeptDTO deptDto) {
        String url = "jdbc:mysql://localhost:3306/liondb";
        String user = "root";
        String password = "root1234";

        String sql = "UPDATE DEPT SET DNAME = ? WHERE DEPTNO = ?";
        int result = 0;

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setString(1, deptDto.getDname());
            ps.setInt(2, deptDto.getDeptNo());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int deleteDept(DeptDTO deptDto) {
        String url = "jdbc:mysql://localhost:3306/liondb";
        String user = "root";
        String password = "root1234";

        String sql = "DELETE FROM DEPT WHERE DEPTNO = ?";
        int result = 0;

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, deptDto.getDeptNo());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}