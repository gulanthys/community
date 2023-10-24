import jakarta.annotation.Resource;
import org.gulanthys.system.SystemApplication;
import org.gulanthys.system.entity.User;
import org.gulanthys.system.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SystemApplication.class)
@RunWith(SpringRunner.class)
public final class TestMapper {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testUserMapper() {
        for (User user : userMapper.selectList(null)) {
            System.out.println(user);
        }
    }

    @Test
    public void testPassword() {
        String encode = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("admin");
        System.out.println(encode);
        boolean encoding = PasswordEncoderFactories.createDelegatingPasswordEncoder().matches("admin", "{bcrypt}$2a$10$VMdyrzm5oagY6SQm6AnGbOWCi4h0r2hyqJxyJrtgSO/7maEvsNSM6");
        System.out.println(encoding);
    }
}
