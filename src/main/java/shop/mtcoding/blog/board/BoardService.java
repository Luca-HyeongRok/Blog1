package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.core.error.ex.Exception403;
import shop.mtcoding.blog.user.User;

import java.util.List;

//repository 에 적었던 걸 여기로 분리
@RequiredArgsConstructor // 생성자 주입
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardResponse.DetailDTO 상세보기(int id, User sessionUser) {
        Board board = boardRepository.findById(id);
        return new BoardResponse.DetailDTO(board, sessionUser); // 화면구성에 필요한 것만 넣어줌.
        // 컨트롤러가서 레퍼지토리가 아니라 서비스로 이동할 수 있게 다 수정
    }

    @Transactional
    public void  게시글쓰기(BoardRequest.SaveDTO saveDTO, User sessionUser) {
        boardRepository.save(saveDTO.toEntity(sessionUser)); // 누가 적었는지 알아야하니까 세션유저를 받아준다.
    }

    @Transactional
    public void 게시글삭제(int id, User sessionUser) {
        Board board = boardRepository.findById(id);
        if (sessionUser.getId() != board.getUser().getId()) {
            throw new Exception403("본인이 작성한 글이 아닙니다.");
        }
        boardRepository.deleteFromId(id);
    }

    public List<Board> 게시글목록보기() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }

    public Board 게시글수정화면가기(int id, User sessionUser) {
        Board board = boardRepository.findById(id);
        if (board.getUser().getId() != sessionUser.getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }
        return board;
    }

    @Transactional
    public void 게시글수정(int id, BoardRequest.UpdateDTO updateDTO, User sessionUser) {
        //조회
        Board board = boardRepository.findById(id);
        //권한체크
        if (board.getUser().getId() != sessionUser.getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }
        board.setTitle(updateDTO.getTitle());
        board.setContent(updateDTO.getContent());
    }
}
