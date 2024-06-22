package com.fe.blog.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fe.blog.bean.Media;
import com.fe.blog.mapper.media.MediaMapper;
import com.fe.blog.service.MediaService;
import com.fe.blog.utils.MybatisSession;
import org.apache.ibatis.session.SqlSession;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Fancy.we
 */
public class MediaServiceImpl implements MediaService {
    private MediaMapper mediaMapper=MybatisSession.getSession().getMapper(MediaMapper.class);

    /**
     * 增加媒体
     *
     * @param media,type
     * @return
     */
    @Override
    public boolean add(Media media,String type) {
        if(type.equals("image")){ return mediaMapper.addImage(media)!=0?true:false; }
        else {return mediaMapper.addMusic(media)!=0?true:false;}
    }


    /**
     * 删除媒体
     *
     * @param medias
     * @param type
     * @param filePath
     * @return
     */
    @Override
    public boolean delete(ArrayList<String> medias,String type,String filePath) {
        //判断media类型,根据类型进行相应操作
        File file=null;
        if(type.equals("image")){
            for (String media : medias) {
                file =new File(filePath+media);
                if (!file.delete()||mediaMapper.deleteImage(media)==0) { return false;}
            }
            return true;
        } else{
            for (String media : medias) {
                file =new File(filePath+media);
                if (!file.delete()||mediaMapper.deleteMusic(media)==0) { return false;}
            }
            return true;
        }
    }


    /**
     * 查询所有媒体
     *
     * @param type
     * @return
     */
    @Override
    public List<Media> selectALL(String type) {
        SqlSession sqlSession = MybatisSession.getSession();
        MediaMapper mediaMapper2=sqlSession.getMapper(MediaMapper.class);
        //判断media类型,根据类型进行相应操作
        if (type.equals("image")) {return mediaMapper2.findAllImage();}
        else {return mediaMapper2.findAllMusic();}
    }

    /**
     * 查找指定名称媒体文件
     * @param media
     * @param type
     * @return
     */
    @Override
    public Media findMedia(String media, String type) {
        SqlSession sqlSession = MybatisSession.getSession();
        MediaMapper mediaMapper2=sqlSession.getMapper(MediaMapper.class);
        if (type.equals("image")){ return mediaMapper2.findImage(media); }
        else {return mediaMapper2.findMusic(media);}
    }

    /**
     * 生成新文件名
     * @param fileName
     * @return
     */
    @Override
    public String createNewName(String fileName) {
        String[] split = fileName.split("\\.");
        char[] chars = split[split.length - 2].toCharArray();
        //判断是否已经重命名过
        if(chars[chars.length-1]!=')'){
            split[split.length-2]+="(1).";
        }else {
            int aChar =Integer.valueOf(String.valueOf(chars[chars.length - 2]))+1;
            chars[chars.length - 2]=String.valueOf(aChar).toCharArray()[0];
            split[split.length - 2]=new String(new StringBuffer(new String(chars)).append("."));
        }
        StringBuffer stringBuffer =new StringBuffer();
        for (String s : split) {
            stringBuffer.append(s);
        }
        return new String(stringBuffer);
    }

    /**
     * 查找target字符在字符串中第nums次出现的位置
     * @param str
     * @param target
     * @param nums
     * @return
     */
    @Override
    public int indexOfChar(String str, String target, int nums) {
        Pattern pattern = Pattern.compile(target);
        Matcher findMatcher = pattern.matcher(str);
        int number = 0;
        while(findMatcher.find()) {
            number++;
            if(number == nums){//当“A”第二次出现时停止
                break;
            }
        }
        int i = findMatcher.start();//“A”第二次出现的位置
        return i;
    }

    /**
     * 返回需要删除的媒体文件名称数组
     * @param inputStream
     * @return
     */
    @Override
    public ArrayList<String> getParameterArrays(InputStream inputStream)  {
        StringBuffer stringBuffer =null;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            stringBuffer=new StringBuffer();
            String s =null;
            while ((s = bufferedReader.readLine())!=null){
                stringBuffer.append(s);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray=JSONArray.parseArray(stringBuffer.toString());
        ArrayList<String> arrayList =new ArrayList<>();
        for (Object o : jsonArray) {
            arrayList.add(((JSONObject)o).getString("value"));
        }
        return arrayList;
    }
}
