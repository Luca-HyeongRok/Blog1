package shop.mtcoding.blog.core.error.ex;

/*
http 상태 코드
10x : 요청을 받았으며 프로세스를 계속 처리중
20x 201 : 요청을 성공적으로 처리. 값은 잘 전달됐다.
30x 다른 걸 돌려줄게 요청 완료를 위해 웹 브라우저에서 추가 작업 조치가 필요
40x 클라이언트의 잘못 요청의 문법이 잘못되었거나 요청을 처리할 수 없음
50x 서버의 잘못 서버가 정상 요청을 처리하지 못함
 */
public class Exception400 extends RuntimeException {
    public Exception400(String message) {
        super(message);
    }
}
