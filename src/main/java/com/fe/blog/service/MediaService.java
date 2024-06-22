package com.fe.blog.service;

import com.fe.blog.bean.Media;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fancy.we
 */
public interface MediaService {


    /**
     * 增加媒体
     *
     * @param media,type
     * @return
     */
    boolean add(Media media,String type);

    /**
     * 删除媒体
     */
    boolean delete(ArrayList<String> medias,String type,String filePath);

    /**
     * 查询所有媒体
     */
    List<Media> selectALL(String type);

    /**
     * 查找指定名称媒体文件
     */
    Media findMedia(String media,String type);

    /**
     * 生成新文件名
     */
    String createNewName(String fileName);

    /**
     * 查找target字符在字符串中第nums次出现的位置
     */
    int indexOfChar(String str,String target,int nums);

    /**
     * 返回需要删除的媒体文件名称数组
     */
    ArrayList<String> getParameterArrays(InputStream inputStream);
}
