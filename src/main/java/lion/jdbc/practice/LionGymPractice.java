package lion.jdbc.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LionGymPractice {
    public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://localhost:3306/liongym";
        String user = "root";
        String pw = "root1234";

        Connection conn = DriverManager.getConnection(url, user, pw);

        String sql = "INSERT INTO MEMBERS(NAME, PHONE, TRAINER_ID, SESSION)"
                   + "VALUES (?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        String trainerId = "1";

        ps.setString(1, "정다희");
        ps.setString(2, "010-0000-0000");
        ps.setString(3, trainerId);
        ps.setInt(4, 10);

        int resultCnt = ps.executeUpdate();

        if (resultCnt > 0) {
            System.out.println("INSERT SUCCESS !!");
        } else {
            System.out.println("INSERT FAIL !!");
        }

        String sql2 = "SELECT * FROM MEMBERS WHERE TRAINER_ID = ?";
        PreparedStatement ps2 = conn.prepareStatement(sql2);
        ps2.setString(1, trainerId);

        ResultSet rs = ps2.executeQuery();

        while(rs.next()) {
            System.out.println("====================");
            System.out.println("ID: " + rs.getInt("ID"));
            System.out.println("NAME: " + rs.getString("NAME"));
            System.out.println("PHONE: " + rs.getString("PHONE"));
            System.out.println("TRAINER_ID: " + rs.getString("TRAINER_ID"));
            System.out.println("SESSION: " + rs.getInt("SESSION"));
            System.out.println();
        }

        ps.close();
        conn.close();
    }
}
