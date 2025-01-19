package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.core.error.ex.Exception401;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class UserRepository {
    @Autowired
    private EntityManager em;

    public User findByUsername(String username) {
        Query query = em.createQuery("select u from User u where u.username = :username", User.class);//찾고
        query.setParameter("username", username);
        try {
            User user = (User)query.getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    public User findByUsernameAndPassword(String username, String password) {
        Query query = em.createQuery("select u from User u where u.username = :username and u.password = :password",User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            User user = (User)query.getSingleResult();
            return user;
        } catch (Exception e) {
            throw new Exception401("인증되지 않았습니다.");
        }

    }

    @Transactional
    public void save(User user) {
        //Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        System.out.println("담기기 전" + user.getId());
        em.persist(user); // 알아서 인서트 해주는 거
        System.out.println("담긴 후 " + user.getId());
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setEmail(email);
        //user.setCreatedAt(now);
    }
}
