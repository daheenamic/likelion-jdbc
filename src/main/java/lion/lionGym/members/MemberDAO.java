package lion.lionGym.members;

import lion.lionGym.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    // 리스트
    public List<MemberDTO> list() {
        List<MemberDTO> memberList = new ArrayList<>();
        String sql = " SELECT ID, NAME, PHONE, TRAINER_ID, SESSION, REG_DATE "
                   + " FROM MEMBERS ";

        try (
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while(rs.next()) {
                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setId(rs.getInt("ID"));
                memberDTO.setName(rs.getString("NAME"));
                memberDTO.setPhone(rs.getString("PHONE"));
                memberDTO.setTrainerId(rs.getInt("TRAINER_ID"));
                memberDTO.setSession(rs.getInt("SESSION"));
                memberDTO.setRegDate(rs.getDate("REG_DATE"));

                memberList.add(memberDTO);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return memberList;
    }

    // 조회
    public MemberDTO view(int id) {
        MemberDTO memberDTO = new MemberDTO();
        String sql = " SELECT ID, NAME, PHONE, TRAINER_ID, SESSION, REG_DATE "
                   + " FROM MEMBERS "
                   + " WHERE ID = ? ";

        ResultSet rs = null;

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                memberDTO.setId(rs.getInt("ID"));
                memberDTO.setName(rs.getString("NAME"));
                memberDTO.setPhone(rs.getString("PHONE"));
                memberDTO.setTrainerId(rs.getInt("TRAINER_ID"));
                memberDTO.setSession(rs.getInt("SESSION"));
                memberDTO.setRegDate(rs.getDate("REG_DATE"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBUtil.close(rs);
        }

        return memberDTO;
    }

    // 등록
    public int insert(MemberDTO memberDTO) {
        int result = 0;
        String sql = "INSERT INTO MEMBER(NAME, PHONE) VALUES(?, ?)";

        try (
                Connection conn = lion.jdbc.DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setString(1, memberDTO.getName());
            ps.setString(2, memberDTO.getPhone());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    // 수정
    public int update(MemberDTO memberDTO) {
        int result = 0;
        String sql = "UPDATE MEMBER SET TRAINER_ID = ?, SESSION = ?"
                   + "WHERE ID = ?" ;

        try (
                Connection conn = lion.jdbc.DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setInt(1, memberDTO.getTrainerId());
            ps.setInt(2, memberDTO.getSession());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    // 삭제
    public int delete(MemberDTO memberDTO) {
        int result = 0;
        String sql = "DELETE FROM MEMBER WHERE ID = ?";

        try (
                Connection conn = lion.jdbc.DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, memberDTO.getId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
