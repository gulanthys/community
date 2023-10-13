package User;

import org.gulanthys.user.entity.User;
import org.gulanthys.user.service.UserService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
public class MapperTest {
    @Resource
    private UserService userService;

    @Test
    public void TestBCryptPasswordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("admin"));
    }

    @Test
    public void Test() {
        User user = userService.getById("100000001");
        System.out.println(user);
    }
}
