package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;

@NoArgsConstructor //빈 생성자
@Data // 롬북 게터,세터,투스트링,이퀄스,해쉬코드 메서드를 자동으로 생성
@Table (name = "board_tb") // 테이블 이름 지정
@Entity // 데이터베이스 테이블에 매핑되는 클래스 라는 뜻
public class Board {
    // 프라이머리 키를 자동으로 생성 id++;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id // 프라이머리 키(고유식별자)로 설정하겠다는 뜻
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Board(Timestamp createdAt, String content, String title, Integer id, User user) {
        this.createdAt = createdAt;
        this.content = content;
        this.title = title;
        this.id = id;
        this.user = user;
    }
}
