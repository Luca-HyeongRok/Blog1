package shop.mtcoding.blog.board;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardRequest {

    @Data
    public static class UpdateDTO {
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
    }

    @Data
    public static class SaveDTO {
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
        //포린 키를 받아야 하는데 클라이언트는 모름 세션에서 꺼내와야 한다 세션에 id 있어서 퍼시스트 할 거다. 보드 오브젝트로 해야함
        // what is the 퍼시스트 ? 영속화하는 것이다. 영속객체 즉 저장된 데이터를 가져오겠다는 것이다.

        // 빌더로 생성자를 만들어줬기 때문에 순서 상관없이 가능
        public Board toEntity(User sessionUser) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(sessionUser)
                    .build();
        }
    }
}
