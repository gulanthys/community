
import com.alibaba.fastjson.JSONObject;
import com.yanty.friends.FriendsApplication;
import com.yanty.friends.entity.PrivateMessage;
import com.yanty.friends.mapper.PrivateMessageMapper;
import com.yanty.friends.ws.pojo.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FriendsApplication.class)
public class MybatisTest {

    @Autowired
    private PrivateMessageMapper privateMessageMapper;



    @Test
    public void testSelect() {
//        Message message = new Message();
//        message.setReceiverId("1");
//        message.setContent("恭喜你的作品被点赞！");
//        String s = JSONObject.toJSONString(message);
//        System.out.println(s);

        privateMessageMapper.createTable();

    }


}