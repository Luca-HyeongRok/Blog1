package shop.mtcoding.blog.core.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.mtcoding.blog.core.error.ex.*;
import shop.mtcoding.blog.core.util.Script;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //정확하게 표현하려고 runtimeexception이 아닌 exception 400으로
    //유효성 검사 실패 (잘못된 클라이언트의 요청)
    //바디데이터 잘 못 터졌을 때
    @ExceptionHandler(Exception400.class)
    public String ex(Exception400 e) {
        return Script.back(e.getMessage());
    }

    //인증 실패(클라이언트가 인증 없이 요청했거나 인증하다가 실패했거나)
    @ExceptionHandler(Exception401.class)
    public String ex(Exception401 e) {
        return Script.href("인증되지 않았습니다.","/login-form");
    }

    //권한 실패 (인증은 돼있는데 삭제하려는 게시글이 내가 적은게 아니다)
    @ExceptionHandler(Exception403.class)
    public String ex(Exception403 e) {
        return Script.back(e.getMessage());
    }

    //페이지를 찾을 수 없다 하면 안됨  리소스가 DB자원이 될 수 도 있어서
    //서버에서 리소스(자원)를 찾을 수 없을 때
    @ExceptionHandler(Exception404.class)
    public String ex(Exception404 e) {
        return Script.back(e.getMessage());
    }

    //서버에서 심각한 오류가 발생했을 때(에러 알고 있을 때)
    @ExceptionHandler(Exception500.class)
    public String ex(Exception500 e) {
        return Script.back(e.getMessage());
    }

    //서버에서 심각한 오류가 발생했을 때(에러 모를 때)
    @ExceptionHandler(Exception.class)
    public String ex(Exception e) {
        return Script.back(e.getMessage());
    }
}
