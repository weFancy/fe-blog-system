import com.fe.blog.bean.Blog;
import com.fe.blog.bean.Tag;
import com.fe.blog.mapper.blog.BlogMapper;
import com.fe.blog.service.BlogService;
import com.fe.blog.service.Impl.BlogServiceImpl;
import com.fe.blog.utils.MybatisSession;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;


public class BlogMapperTest {

    @Test
    public void addRelation(){
//        SqlSession sqlSession = MybatisSession.getSession();
//        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
//        HashMap<String, Integer> map = new HashMap<String, Integer>();
//        map.put("blogId", 1);
//        map.put("tagId", 1);
//        mapper.addRelation(map);
//        sqlSession.commit();
//        sqlSession.close();
    }

    @Test
    public void selectTagAll(){
        SqlSession sqlSession = MybatisSession.getSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        List<Tag> tagList = mapper.selectTagAll();
        System.out.println(tagList);
        /*for (Tag tag : tagList) {
            System.out.println(tag);
        }*/
    }
    //@Test
//    public void QueryBlog(){
//        SqlSession sqlSession = MybatisSession.getSession();
//        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
////        List<Blog> blogs = mapper.randomQuery(userId);
////        for (Blog blog : blogs) {
////            System.out.println(blog);
//        }
//    }

    @Test
    public void selectLimitBlog(){
        SqlSession sqlSession = MybatisSession.getSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        List<Blog> blogs = mapper.selectByBlogLimit(2,0,2);
        System.out.println(blogs);
        sqlSession.close();
    }

    @Test
    public void addBlog(){
        SqlSession sqlSession = MybatisSession.getSession();

        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setTitle("java总结");
        blog.setContent("weqqdsagdsdffgdf");
        blog.setDescription("java");
        blog.setType("技术");
        blog.setUserId(1);
        blog.setFieldId(1);
        blog.setAuthor("admin");

        mapper.addBlog(blog);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteBlog(){
        SqlSession sqlSession = MybatisSession.getSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        mapper.deleteBlog(10);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateBlog(){
        SqlSession sqlSession = MybatisSession.getSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setBlogId(1);
        blog.setTitle("java总结");
        blog.setContent("的骄傲看来是刀锋并非u发表");
        blog.setDescription("java");
        blog.setType("技术");
        blog.setDeleted(0);
        blog.setStatus(1);
        blog.setUserId(2);
        blog.setFieldId(1);
        blog.setAuthor("风");

        mapper.updateBlog(blog);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void addTag(){
        SqlSession sqlSession = MybatisSession.getSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        mapper.addTag("java");
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteTag(){
        SqlSession sqlSession = MybatisSession.getSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        mapper.deleteTag("java");
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void selectByTag(){
        SqlSession sqlSession = MybatisSession.getSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
//        Tag java = (Tag) mapper.selectByTag(Collections.singletonList("java"));
//        System.out.println(java);
        List<Tag> tags = mapper.selectByTag(Collections.singletonList("java"));
        for (Tag tag : tags) {
            System.out.println(tag);
        }
    }
    @Test
    void test3(){
        BlogService blogService=new BlogServiceImpl();
        List<Tag> taglist = blogService.selectTagsByBlog(37);
        System.out.println("=========="+taglist);
    }
}
