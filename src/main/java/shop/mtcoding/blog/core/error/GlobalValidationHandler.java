package shop.mtcoding.blog.core.error;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import shop.mtcoding.blog.core.error.ex.Exception400;

@Aspect
@Component
public class GlobalValidationHandler {
    //특정한 어노테이션 전에 발동될 수 있게
    // 메서드나 매개변수 이후에 하기 힘들기 때문
    // 사용할 때 풀 내임 적어야 하는데
    //지금 getMapping실행되면 될 수 있게
//    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping)")
//    public void hello() {
//        System.out.println("aop hello1 호출됨");
//        // 언제 발동될 것인지
//    @Before("@annotation(shop.mtcoding.blog.core.Hello)")
//    public void hello() {
//        System.out.println("aop hello1 호출됨");
//        // 언제 발동될 것인지
//    }
//    @Around("@annotation(shop.mtcoding.blog.core.Hello)")
//    public Object hello(ProceedingJoinPoint jp) throws Throwable {
//        System.out.println("aop hello1 Before호출됨");
//        // 언제 발동될 것인지
//        Object proceed = jp.proceed(); //@Hello 어노테이션 붙은 함수 호출 "/user/login-form"
//        System.out.println(proceed);
//        System.out.println("aop hello1 After호출됨");
//        return proceed; // 왜 리턴하냐 ? DS이 받아서 처리를 해야하기 때문이다.
//        //메서드 실행시간 성능 체크를 위해
//    }
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void vaildCheck(JoinPoint jp){
        // 메서드 매개변수 알려준다.
        // postMapping의 매개 변수 전부 가져온다.
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            // 만약에 arg에 Errors 가 있다면 두 개 중 타입이 Errors가 있다면 instanceof 타입 검사 후 다운 캐스팅
            // 만약에 arg에 Errors 가 없다면 벨리데이션 검사 안하고 있으면 검사하고
            if(arg instanceof Errors) {
                Errors errors = (Errors) arg;
                // 에서 있는지 검사
                if(errors.hasErrors()) {
                    for (FieldError error : errors.getFieldErrors()) {
                        throw new Exception400(error.getDefaultMessage() + " : " + error.getField());
                    }
                }
            }

        }
    }
}
