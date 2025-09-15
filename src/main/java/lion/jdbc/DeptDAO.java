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

    public DeptDTO getDept(int deptNo) {

        String sql = "SELECT DEPTNO, DNAME, LOC FROM DEPT WHERE DEPTNO = ?";
        DeptDTO deptDTO = new DeptDTO();
        ResultSet rs = null;

        try (
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, deptNo);
            rs = ps.executeQuery();

            if (rs.next()) {
                deptDTO.setDeptNo(rs.getInt("DEPTNO"));
                deptDTO.setDname(rs.getString("DNAME"));
                deptDTO.setLoc(rs.getString("Loc"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBUtil.close(rs);
        }

        // 데이터가 없으면 null을 보내는게 좋음.
        // 빈 객체를 보내게 되면 null인지 체크하고 객체 안의 필드값도 조회해야 하기 때문이다.
        // 어차피 다시 쓸 객체가 아니기 때문에 그냥 null로 보내면 좋다.
        return deptDTO;
    }
}