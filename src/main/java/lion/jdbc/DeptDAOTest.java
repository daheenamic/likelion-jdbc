package lion.jdbc;

public class DeptDAOTest {
    public static void main(String[] args) {
        DeptDAO dao = new DeptDAO();
        dao.insertDept();
        dao.updateDept();
        dao.deleteDept(50);
    }
}
