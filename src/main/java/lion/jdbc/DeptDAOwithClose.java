package lion.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeptDAOwithClose {
    public int insert(DeptDTO deptDTO) {
        int result = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("INSERT INTO~");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt);
        }

        return result;
    }
}
