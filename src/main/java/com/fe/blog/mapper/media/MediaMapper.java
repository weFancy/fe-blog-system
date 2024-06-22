package com.fe.blog.mapper.media;

import com.fe.blog.bean.Media;

import java.util.List;


/**
 * @author Fancy.we
 */
public interface MediaMapper {
    /**
     * 查询所有图片媒体文件
     * @return  有图片媒体文件集合
     */
    public List<Media> findAllImage();

    /**
     * 查询所有音频媒体文件
     * @return  有音频媒体文件集合
     */
    public List<Media> findAllMusic();


    /**
     * 查询指定名称图片
     */
    public Media findImage(String image);

    /**
     * 查询指定名称图片
     */
    public Media findMusic(String music);

    /**
     * 向媒体库中添加图片文件
     */
    public Integer addImage(Media imageMedia);

    /**
     * 向媒体库中添加音频文件
     */
    public Integer addMusic(Media musicMeida);

    /**
     * 从媒体库中删除指定图片文件
     */
    public Integer deleteImage(String image);

    /**
     * 从媒体库中删除指定音频文件
     */
    public Integer deleteMusic(String music);

}
