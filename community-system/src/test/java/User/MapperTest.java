package User;

import org.gulanthys.system.SystemApplication;
import org.gulanthys.system.entity.User;
import org.gulanthys.system.mapper.MenuMapper;
import org.gulanthys.system.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = SystemApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class MapperTest {
    @Resource
    private MenuMapper menuMapper;

    @Test
    public void TestSelectPermsByUserId() {
        List<String> list = menuMapper.selectPermsByUserId(100000001);
        System.out.println(list);
    }

    @Resource
    private UserService userService;

    @Test
    public void TestBCryptPasswordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("admin"));
    }

    @Test
    public void Test() {
        User user = userService.getById(100000001);
        System.out.println(user);
    }
}
