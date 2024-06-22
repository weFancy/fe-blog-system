package com.fe.blog.mapper.homepage;

import com.fe.blog.bean.Homepage;

import java.util.List;

/**
 * @author Fancy.we
 */
public interface HomePageMapper {

  /**
   * 添加Homepage
   * @return 添加成功行数
   */
  public Integer insertHomepage(Homepage homepage);

  /**
   * 删除Homepage
   * @return 删除成功行数
   */
  public Integer delHomepage(Homepage homepage);

  /**
   * 更新Homepage
   * @return 更新成功行数
   */
  public Integer UpdateHomepage(Homepage homepage);

  /**
   * 查询Homepage
   */
  public List<Homepage> findHomepage(int Homepageid);

}
