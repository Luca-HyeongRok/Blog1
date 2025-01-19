package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.core.error.ex.Exception404;

import java.util.List;

@Repository
public class BoardRepository {
    /*IOC 컨테이너에 넣었는지 확인용
    public BoardRepository() {
        //System.out.println("BoardRepository 생성자");
    }
    */
    @Autowired
    private EntityManager em; // em에서 가져온다.

    @Transactional // 트랜잭션이 아직 안걸려있다. 동시에 오는 걸 막기위해 그래서 @써주기
    public void save(Board board) {
        em.persist(board);
    }

    public List<Board> findAll() {
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);
        List<Board> boardList = query.getResultList();
        return boardList;
    }

    public Board findById(int id) {
        Query query = em.createQuery("select b from Board b join fetch b.user U where b.id =:id", Board.class);
        query.setParameter("id", id); // id를 모르니까 넣어주는
        try{
            Board board = (Board) query.getSingleResult(); // 형변환 1건이니까
            return board;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception404("게시글 id를 찾을 수 없습니다.");
        }
    }
    @Transactional
    public void deleteFromId(int id) {
        Query query = em.createNativeQuery("delete from board_tb where id = ?", Board.class);
        query.setParameter(1, id);
        query.executeUpdate();
    }

    @Transactional
    public void updateById(String title, String content, int id) {
        Query query = em.createNativeQuery("update board_tb set title = ?, content = ? where id = ?");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, id);
        query.executeUpdate();
    }

}

