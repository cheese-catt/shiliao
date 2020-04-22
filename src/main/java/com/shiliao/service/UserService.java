package com.shiliao.service;

import com.shiliao.domain.Nusers;
import com.shiliao.domain.PageResult;
import com.shiliao.domain.User;
import com.shiliao.domain.UserDetails;
import com.shiliao.mapper.NusersMapper;
import com.shiliao.mapper.UserDetailsMapper;
import com.shiliao.mapper.UserMapper;
import com.shiliao.util.CodecUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import tk.mybatis.mapper.entity.Example;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @Autowired
    private MailService mailService;

    //登录找用户
    public PageResult findUser(User user, HttpSession session) {

        // 查询
        User record = new User();
        record.setUname(user.getUname());
        User user1 = this.userMapper.selectOne(record);
        if (user1==null){
            return PageResult.error();
        }
        if (!user1.getUpwd().equals(CodecUtils.md5Hex(user.getUpwd(), user1.getSalt()))){
            return PageResult.error();
        }
        user1.setUpwd(user.getUpwd());
        session.setAttribute("user",user1);
        //找出对应的用户详细信息
        UserDetails details = this.userDetailsMapper.selectAllByUid(user1.getUid());
        user.setUserDetails(details);

        //把details设置进去session域
        session.setAttribute("userDetail",details);
        return PageResult.ok().add("user",user1);
    }

    //注册
    @Transactional
    public void insertUser(User user,HttpSession session) {
        //往User表插入当前用户数据
        user.setUdate(new Date());
        user.setUtype(false);
        user.setUstate(false);
        session.setAttribute("regist",user);

        /*
        //生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        //对用户的密码进行加密
        user.setUpwd(CodecUtils.md5Hex(user.getUpwd(), salt));

        this.userMapper.insertSelective(user);

        //往Nusers插入当前用户数据
        Nusers nusers = new Nusers();
        nusers.setUid(user.getUid());
        this.nusersMapper.insertSelective(nusers);
        //往用户个人信息表插入数据
        UserDetails userDetails = new UserDetails();

        userDetails.setUdnames("未命名"+UUID.randomUUID().toString().substring(0,7));
        userDetails.setUid(user.getUid());
        this.userDetailsMapper.insertSelective(userDetails);
        */

        //发送邮箱激活
        String htmlStr = "<a href='http://localhost:8080/pages/index.html' >点击此处进行邮箱激活</a>";

        this.mailService.sendMimeMessge(user.getUmail(),"食疗论坛邮箱激活",htmlStr);
        
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

    //更新user(在用户界面修改密码）
    @Transactional
    public void updateUser(User user,HttpSession session) {

        try {
            //生成盐
            String salt = CodecUtils.generateSalt();
            user.setSalt(salt);
            //对用户的密码进行加密
            user.setUpwd(CodecUtils.md5Hex(user.getUpwd(), salt));

            this.userMapper.updateByPrimaryKeySelective(user);
            session.removeAttribute("user");
            session.setAttribute("user",user);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    //激活邮箱
    @Transactional
    public PageResult setUstate(HttpSession session) {
       User user=(User)session.getAttribute("regist");
       //将用户状态设置为true
       user.setUstate(true);

        //生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        //对用户的密码进行加密
        user.setUpwd(CodecUtils.md5Hex(user.getUpwd(), salt));

        this.userMapper.insertSelective(user);
        //往Nusers插入当前用户数据
        Nusers nusers = new Nusers();
        nusers.setUid(user.getUid());
        this.nusersMapper.insertSelective(nusers);
        //往用户个人信息表插入数据
        UserDetails userDetails = new UserDetails();

        userDetails.setUdnames("未命名"+UUID.randomUUID().toString().substring(0,7));
        userDetails.setUid(user.getUid());
        this.userDetailsMapper.insertSelective(userDetails);

        session.removeAttribute("regist");
        return PageResult.ok();
    }

    //查找是否重名
    public PageResult findUname(String uname) {
        PageResult result = new PageResult();
       User user= this.userMapper.findUname(uname);
       if (user!= null){
           //已存在此用户名
           result.setMsg("已存在此用户名");
           result.setFlag(false);
           return result;
       }
       result.setFlag(true);
       result.setMsg("可以用这个用户名");
        return result;
    }

    //查找是否重邮箱
    public PageResult findUmail(String umail) {
        PageResult result = new PageResult();
        User user= this.userMapper.findUmail(umail);
        if (user!= null){
            //已存在此用户名
            result.setMsg("已存在此邮箱");
            result.setFlag(false);
            return result;
        }
        result.setFlag(true);
        result.setMsg("可以用这个邮箱");
        return result;
    }

    //发送密码找回邮件
    @Transactional
    public PageResult sendPwdMail(String umail,Model model) {
        PageResult result = new PageResult();
        User user= this.userMapper.findUmail(umail);
          if (user!=null){
              //存在此邮箱，将邮箱存入session
              model.addAttribute("umail",user.getUmail());
              //发送邮箱激活
              String htmlStr = "<a href='http://localhost:8080/pages/index.html' >点击此处找回密码</a>";
              this.mailService.sendMimeMessge(user.getUmail(),"食疗论坛密码找回",htmlStr);

              return PageResult.ok().add("umail",umail);
          }
          return PageResult.error();
    }

    //找回密码更新user
    @Transactional
    public PageResult updateUserByUmail(User user) {

        try {
            //生成盐
            String salt = CodecUtils.generateSalt();
            user.setSalt(salt);
            //对用户的密码进行加密
            user.setUpwd(CodecUtils.md5Hex(user.getUpwd(), salt));

            this.userMapper.updateUserByUmail(user);
            return PageResult.ok();

        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.error();
        }
    }
}
