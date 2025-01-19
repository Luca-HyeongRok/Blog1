package shop.mtcoding.blog.core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import shop.mtcoding.blog.core.error.ex.Exception401;
import shop.mtcoding.blog.user.User;

import java.net.http.HttpResponse;

public class Logininterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        //1. 나머지는 다 2임 System.out.println("프리핸들러 동작 ㄱ ====================================================================");
        if(sessionUser == null){
            //response.sendRedirect("/login-form"); 옛버전
            throw new Exception401("인증되지 않았습니다.");
        }
        return true;
    }
}
