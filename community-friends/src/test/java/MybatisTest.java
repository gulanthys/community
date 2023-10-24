import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.yanty.friends.FriendsApplication;
import com.yanty.friends.entity.User;
import com.yanty.friends.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FriendsApplication.class)
public class MybatisTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {

    }


}