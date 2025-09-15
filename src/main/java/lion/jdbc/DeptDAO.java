package lion.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptDAO {

    public int insertDept(DeptDTO deptDto) {
        String sql = "INSERT INTO DEPT(DEPTNO, DNAME, LOC) VALUES(?, ?, ?)";
        int result = 0;

        try (
            Connection conn = DBUtil.getConnection();
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
        String sql = "UPDATE DEPT SET DNAME = ? WHERE DEPTNO = ?";
        int result = 0;

        try (
            Connection conn = DBUtil.getConnection();
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
        String sql = "DELETE FROM DEPT WHERE DEPTNO = ?";
        int result = 0;

        try (
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, deptDto.getDeptNo());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<DeptDTO> getDeptList() {

        String sql = "SELECT DEPTNO, DNAME, LOC FROM DEPT";
        List<DeptDTO> deptList = new ArrayList<>();

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            while(rs.next()) {
                DeptDTO deptDTO = new DeptDTO();
                deptDTO.setDeptNo(rs.getInt("DEPTNO"));
                deptDTO.setDname(rs.getString("DNAME"));
                deptDTO.setLoc(rs.getString("LOC"));

                deptList.add(deptDTO);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return deptList;
    }
}