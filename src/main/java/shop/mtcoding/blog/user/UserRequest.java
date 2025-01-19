package shop.mtcoding.blog.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class UserRequest {
    @Data
    public static class JoinDTO {
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
        @NotEmpty
        private String email;

        //Entity로 만들어준다. DTO를 객체로 변환
        public User toEntity() {
            return User.builder().username(username).password(password).email(email).build();
        }
    }

    @Data
    public static class LoginDto {
        @NotEmpty
        @Size(min = 2, max = 4)
        private String username;
        @NotEmpty
        private String password;
        //asdfasdfasdfasdf
        //오늘 인증
        //asdfasdfasdfa
    }

}
