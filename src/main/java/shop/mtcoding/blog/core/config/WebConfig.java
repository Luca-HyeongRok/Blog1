package shop.mtcoding.blog.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import shop.mtcoding.blog.core.interceptor.Logininterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override // 유저나 보드가 들어가면 다 확인해라...
    // + 다음스텝
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new Logininterceptor())
               .addPathPatterns("/api/**");
    }
}
