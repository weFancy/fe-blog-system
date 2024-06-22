import com.fasterxml.jackson.core.JsonProcessingException;
import com.fe.blog.bean.Blog;
import com.fe.blog.service.BlogService;
import com.fe.blog.service.Impl.BlogServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;


public class FieldMapperTest {
    @Test
    void test() throws JsonProcessingException {
        BlogService blogService = new BlogServiceImpl();
        List<Blog> blogs = blogService.selectByBlogLimit(1, 0, 12);
        System.out.println(blogs);

    }
}

