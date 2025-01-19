package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

//Test 시 꼭 이름에 Test라고 명시해줄 것
@DataJpaTest
@Import(BoardRepository.class)
public class BoardRepositoryTest {

    @Autowired  //IOC에서 가져오기
    private BoardRepository boardRepository;
    @Autowired
    private EntityManager em;
    /*
    Given : 매개변수를 강제로 만든다
    When : 여기서 테스트 한다.
    Eye : 눈에 보이는 것 like sout 같은 것
     */
    @Test // Test를 할 수 있다. 실행시 문제 없으면 save가 됐다는 것
    public void save_test(){
        //given 
        String title = "제목1";
        String content = "내용1";
        
        //when
        //boardRepository.save(title, content);
        
        //eye
        
    }

//    @Test
//    public void findAll_test(){
//        boardRepository.findAll();
//        // 됐는지 안됐는지 확인하려면
//        //given X
//        //when
//        List<Board> boardList = boardRepository.findAll();
//        //eye sout
//        System.out.println("사이즈 : " + boardList.size());
//
//        //값 보고 싶으면?
//        for (Board board : boardList) {
//            System.out.println(board.getTitle());
//            System.out.println(board.getContent());
//        }
//    }
@Test
public void findAll_test(){
    //given

    //when
    System.out.println("1. 첫번째 조회");
    List<Board> boardList = boardRepository.findAll();
    System.out.println("1번 userId : " + boardList.get(0).getUser().getId());
    System.out.println("=============");


    //eye
    //싸이즈 부터 보자
    System.out.println("2. 레이지 로딩");
    System.out.println("2번 title: " + boardList.get(0).getUser().getUsername());
    System.out.println("3번 title: " + boardList.get(1).getUser().getUsername());
}

    @Test
    public void findById_test(){
        //given
        int id = 1; // 6치면 오류남 그래서 try catch
        //when
        Board board = boardRepository.findById(id);
        // eye
        System.out.println(board.getId());
        System.out.println(board.getTitle());
        System.out.println(board.getContent());

        // Then
//        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");
//        Assertions.assertThat(board.getContent()).isEqualTo("내용1");
    }

    @Test
    public void deleteFromId_test(){
        //given
        int id = 1;

        //when
        boardRepository.deleteFromId(id);
    }
    @Test
    public void update_test(){
        //given
        int id = 1;
        String title = "제목1변경";
        String content = "내용1변경";

        //when
        boardRepository.updateById(title, content, id);

        //eye
        Board board = boardRepository.findById(id);

        //then
        Assertions.assertThat(board.getTitle()).isEqualTo("제목1변경");

    }

    //조회된 애 수정 되는지 궁금한니까 하는거
    //트랜잭션 끝나면 플러쉬를 하는데 여기서는 트랜잭션 못거니까
    @Test
    public void updateByIdV2_test() {
        // given 조회 먼저함
        int id = 1;
        Board board = boardRepository.findById(id);

        //when
        board.setTitle("제목10");
        board.setContent("내용10");

        //트랜잭션이 종료되면 flush(); 느낌 내려고 함
        em.flush();
    }
//조회안하고 DB안들어가서 쿼리자체가

    @Test
    public void updateByIdV3_test() {
        // given 조회 먼저함
        int id = 1;
        Board board =new Board();

        //when
        board.setTitle("제목10");
        board.setContent("내용10");

        //트랜잭션이 종료되면 flush();
        em.flush();
    }
}
