package lion.jdbc;

public class DeptDAOTest {
    public static void main(String[] args) {
        DeptDAO dao = new DeptDAO();
        DeptDTO insertDTO = new DeptDTO();
        int result = 0;

        // INSERT
        insertDTO.setDeptNo(50);
        insertDTO.setDname("LION");
        insertDTO.setLoc("SEOUL");
        result += dao.insertDept(insertDTO);

        insertDTO.setDeptNo(60);
        insertDTO.setDname("LION");
        insertDTO.setLoc("SEOUL");
        result += dao.insertDept(insertDTO);

        insertDTO.setDeptNo(70);
        insertDTO.setDname("LION");
        insertDTO.setLoc("SEOUL");
        result += dao.insertDept(insertDTO);

        System.out.println("SUCCESS INSERT: " + result);

        // UPDATE
        DeptDTO updateDTO = new DeptDTO();
        updateDTO.setDeptNo(50);
        updateDTO.setDname("LIKE");
        result = dao.updateDept(updateDTO);

        System.out.println("SUCCESS UPDATE: " + result);

        // DELETE
        DeptDTO deleteDTO = new DeptDTO();
        deleteDTO.setDeptNo(60);
        dao.deleteDept(deleteDTO);

        System.out.println("SUCCESS DELETE: " + result);
    }
}
