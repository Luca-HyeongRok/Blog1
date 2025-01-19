package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog.core.Hello;

@RequiredArgsConstructor
@Controller
public class UserController {

//    @Autowired
//    private UserRepository userRepository;
//    //일요일마지막에 추가
//    @Autowired
//    private HttpS
//    ession session;
    private final UserRepository userRepository;
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form"; //인증이 안필요한 거에는 안 붙인다.
    }

    @Hello
    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @PostMapping("/join")
    public String join(@Valid UserRequest.JoinDTO joinDTO, Errors errors) {
        userService.회원가입(joinDTO);
        return "redirect:/login-form";
    }
    //일요일마지막에 추가
    @PostMapping("/login")
    public String login(@Valid  UserRequest.LoginDto loginDTO, Errors errors) {
        User sessionUser = userService.로그인(loginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
