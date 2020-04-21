package com.shiliao.service;

import com.shiliao.domain.Nusers;
import com.shiliao.domain.User;
import com.shiliao.domain.UserDetails;
import com.shiliao.mapper.NusersMapper;
import com.shiliao.mapper.UserDetailsMapper;
import com.shiliao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Autowired
    private NusersMapper nusersMapper;

    //登录找用户
    public User findUser(User user) {

        User user1 = this.userMapper.selectOne(user);
        if (user1==null){
            return null;
        }
        //找出对应的用户详细信息
        UserDetails details = this.userDetailsMapper.selectAllByUid(user1.getUid());
        //把详细信息设置到User中
        user1.setUserDetails(details);
        return user1;
    }

    //注册
    @Transactional
    public void insertUser(User user) {
        //往User表插入当前用户数据
        user.setUdate(new Date());
        user.setUtype(false);
        user.setUstate(false);
        this.userMapper.insertSelective(user);
        //往Nusers插入当前用户数据
        Nusers nusers = new Nusers();
        nusers.setUid(user.getUid());
        this.nusersMapper.insertSelective(nusers);
        //往用户个人信息表插入数据
        UserDetails userDetails = new UserDetails();

        userDetails.setUdnames(UUID.randomUUID().toString()+"未命名");
        userDetails.setUid(user.getUid());
        this.userDetailsMapper.insertSelective(userDetails);
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

    @Transactional
    public void updateUser(User user) {
        user.setUname(null);
        this.userMapper.updateByPrimaryKeySelective(user);
    }
}
