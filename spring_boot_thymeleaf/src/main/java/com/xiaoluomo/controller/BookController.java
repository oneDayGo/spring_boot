package com.xiaoluomo.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaoluomo.po.Book;
import com.xiaoluomo.po.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class BookController {

    @GetMapping("/books")
    public ModelAndView books(){
        ModelAndView mv = new ModelAndView();

        List<Book> books = new ArrayList<>();

        Book b1 = new Book();
        Book b2 = new Book();

        b1.setId(1);
        b1.setAuthor("罗贯中");
        b1.setName("三国演义");

        b2.setId(2);
        b2.setAuthor("曹雪芹");
        b2.setName("红楼梦");
        books.add(b1);
        books.add(b2);

        mv.addObject("books",books);
        mv.setViewName("books");
        return mv;
    }

    @GetMapping(value = "/user",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User user(){

        User user = new User();
        user.setId(1);
        user.setName("xiaozhe");
        user.setPass("123456");
        user.setTime(new Date());

        return user;



    }


    //文件上传
    @PostMapping("/upload")
    @ResponseBody
    public String  upload(MultipartFile uploadFile, HttpServletRequest req){
        //uploadFile
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        //获取文件保存路径
        String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
        String format = sdf.format(new Date());

        //创建文件对象
        File folder = new File(realPath+format);

        if(!folder.isDirectory()){
            folder.mkdirs();
        }

        //获取文件名
        String oldName = uploadFile.getOriginalFilename();
        //创建新名字
        String newName = UUID.randomUUID().toString()+
                oldName.substring(oldName.lastIndexOf("."),oldName.length());

        try{
            //写入
            uploadFile.transferTo(new File(folder,newName));

            String filePath = req.getScheme()+"://"+req.getServerName()+":"+
                    req.getServerPort()+"/uploadFile/"+format+"/"+newName;

            return filePath;


        }catch (IOException e){
            e.printStackTrace();

        }

        return  "上传失败";




    }

    //多文件上传
    @PostMapping("/uploads")
    @ResponseBody
    public String uploads(MultipartFile[] uploadFiles,HttpServletRequest req){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        //获取文件保存路径
        String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
        String format = sdf.format(new Date());

        List<String> imagesPath = new ArrayList<>();

        //创建文件对象
        File folder = new File(realPath+format);

        if(!folder.isDirectory()){
            folder.mkdirs();
        }

        for(MultipartFile file:uploadFiles){
            String oldName = file.getOriginalFilename();
            //创建新名字
            String newName = UUID.randomUUID().toString()+
                    oldName.substring(oldName.lastIndexOf("."),oldName.length());
            try{
                //写入
                file.transferTo(new File(folder,newName));
                String filePath = req.getScheme()+"://"+req.getServerName()+":"+
                        req.getServerPort()+"/uploadFile/"+format+"/"+newName;
                imagesPath.add(filePath);
            }catch (IOException e){
                e.printStackTrace();

            }

        }

        return imagesPath.toString();


    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        int i = 1/0;
        return "hello";
    }

}
