//import com.fe.blog.bean.User;
//import com.fe.blog.mapper.User.UserLogMapper;
//import com.fe.blog.utils.MybatisSession;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.jupiter.api.Test;
//
//public class SqlSessionTest {
//
//    /**
//     * 测试 SqlSession
//     */
//    @Test
//    public void test(){
//        System.out.println("获取成功！"+MybatisSession.getSession());
//    }
//
//    @Test
//    public void Login(){
//        SqlSession sqlSession = MybatisSession.getSession();
//        UserLogMapper mapper = sqlSession.getMapper(UserLogMapper.class);
//        User user = mapper.getUserByAccount("藤树test");
//
//        sqlSession.close();
//
//        if (user.getPassword().equals("123456")){
//            System.out.println("true");
//            return;
//        }
//
//        System.out.println("false");
//    }
//}
