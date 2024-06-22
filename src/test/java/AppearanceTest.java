import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fe.blog.bean.Homepage;
import com.fe.blog.service.HomePageService;
import com.fe.blog.service.Impl.HomePageServiceImpl;
import com.fe.blog.utils.MybatisSession;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AppearanceTest {
    /**
     * 测试Appearance 方法
     */
    @Test
    public void test() throws JsonProcessingException {
        SqlSession sqlSession = MybatisSession.getSession();
        ObjectMapper objectMapper = new ObjectMapper();
        HomePageService homePageService = new HomePageServiceImpl();
        List<Homepage> homepages = homePageService.FindAllHomepage(1);
        String json = objectMapper.writeValueAsString(homepages);
        System.out.println(json);
    }
}
