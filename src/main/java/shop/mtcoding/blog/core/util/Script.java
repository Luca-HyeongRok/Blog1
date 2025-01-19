package shop.mtcoding.blog.core.util;

public class Script {
    //스테틱이여서 new안해도 됨
    //이 친구의 역할 문장열 받아서 리턴하는 것
    public static String back(String msg){
        String errMsg = """
                <script>
                    alert('$msg');
                    history.back();
                </script>
                """.replace("$msg", msg);
        return errMsg;
    }

    //메시지 하나 띄우고 화면 이동시켜주는 애
    // 만약 지금 보드 목록페이지에 있어
    //주소에 강제로 로컬 host/save-form으로 때렸어 가려면 로그인 한 후 들어가야 하는데
    // 인증 안되면 그 화면 가면 안된다. 제일 좋은 거는 메시지 주고 href로 로그인 창으로 가는게 좋다
    /*
    페이지 404를 찾을 수 없습니다라는 페이지를 만들 필요 없음
    로그인창
     */
    public static String href(String msg, String url) {
        String errMsg = """
                <script>
                    alert('$msg');
                    location.href = '$url';
                </script>
                """.replace("$msg", msg)
                .replace("$url", url);
        return errMsg;
    }
}
