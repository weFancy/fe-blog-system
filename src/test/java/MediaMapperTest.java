import com.fe.blog.bean.Media;
import com.fe.blog.mapper.media.MediaMapper;
import com.fe.blog.utils.MybatisSession;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

public class MediaMapperTest {

    @Test
    void test1(){
        SqlSession sqlSession = MybatisSession.getSession();
        MediaMapper mediaMapper = sqlSession.getMapper(MediaMapper.class);
        System.out.println(mediaMapper);
        Media media =new Media(22,"","");
        //mediaMapper.deleteImage(media.getMediaId());
    }
}
