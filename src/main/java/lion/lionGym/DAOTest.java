package lion.lionGym;

import lion.lionGym.members.MemberDAO;
import lion.lionGym.members.MemberDTO;

public class DAOTest {
    public static void main(String[] args) {
        MemberDAO memberDAO = new MemberDAO();
        // 목록 조회
        memberDAO.list();

        // 단건 조회
        memberDAO.view(1);

        // 등록
        MemberDTO insertDTO = new MemberDTO();
        insertDTO.setName("홍길동");
        insertDTO.setPhone("010-1234-1234");
        memberDAO.insert(insertDTO);

        insertDTO.setName("홍길동2");
        insertDTO.setPhone("010-5678-5678");
        memberDAO.insert(insertDTO);

        // 수정
        MemberDTO updateDTO = new MemberDTO();
        updateDTO.setTrainerId(3);
        updateDTO.setSession(10);
        updateDTO.setId(1);
        memberDAO.update(updateDTO);

        // 삭제
        MemberDTO deleteDTO = new MemberDTO();
        deleteDTO.setId(12);
        memberDAO.delete(deleteDTO);
    }
}
