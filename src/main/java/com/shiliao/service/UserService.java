package com.shiliao.service;

import com.shiliao.domain.User;
import com.shiliao.mapper.CategoryMapper;
import com.shiliao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    //登录找用户
    public User findUser(User user) {
        User record = new User();
        record.setUname(user.getUname());
        record.setUpwd(user.getUpwd());
        List<User> users = this.userMapper.select(record);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }else{
            return null;
        }
    }

    //注册
    @Transactional
    public void insertUser(User user) {
        user.setUdate(new Date());
        user.setUtype(false);
        user.setUstate(false);
        this.userMapper.insertSelective(user);
    }

    //获取验证码
    private String getCheckCode() {
        String base = "0123456789ABCDEFGabcdefg";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<=4;i++){
            //产生0到size-1的随机值
            int index = r.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            //将c放入到StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }

    public void getCode(HttpServletResponse response,HttpServletRequest request) throws IOException {
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        //在内存中创建一个长80，宽30的图片，默认黑色背景
        //参数一：长
        //参数二：宽
        //参数三：颜色
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //获取画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        //填充图片
        g.fillRect(0,0, width,height);

        //产生4个随机验证码，12Ey
        String checkCode = getCheckCode();
        //将验证码放入HttpSession中
        request.getSession().setAttribute("CHECKCODE_SERVER",checkCode);

        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体的小大
        g.setFont(new Font("黑体",Font.BOLD,24));
        //向图片上写入验证码
        g.drawString(checkCode,15,25);

        ImageIO.write(image, "PNG", response.getOutputStream());
    }
}