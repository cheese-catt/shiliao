package com.shiliao.controller;

import com.shiliao.domain.PageResult;
import com.shiliao.domain.User;
import com.shiliao.service.NotesService;
import com.shiliao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private NotesService notesService;


    /**
     * 登录 并且附带有用户的个人信息
     * @param user
     * @param session
     * @return
     */
    @PostMapping("login")
    public PageResult findUser(User user , HttpSession session){

        try {
            return this.userService.findUser(user,session);
        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.error();
        }

    }

    /**
     * 注册
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("regist")
    public PageResult queryUserbyId(User user ,HttpSession session){

        if (user!=null) {
            this.userService.insertUser(user,session);
           return PageResult.ok();

        }


        return PageResult.error();
    }

    //获取验证码
    @GetMapping("getcode")
    public Object checkCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        this.userService.getCode(response,request);
        return PageResult.ok();
    }

    /**
     * 登出
     * @param session
     * @return
     */
    @GetMapping("logout")
    public PageResult logout(HttpSession session){
        session.removeAttribute("user");
        return PageResult.ok();
    }

    /**
     * 通过用户ID得到用户自己发的帖子
     * @param uid
     * @return
     */
    @PostMapping("notesByUser")
    public PageResult findNotesByUser( Long uid,@RequestParam(value = "page",required = false,defaultValue = "1")Integer page,
                                       @RequestParam(value = "rows",required = false,defaultValue = "8")Integer rows){
        PageResult result =this.notesService.findNotesByUser(page,rows,uid);
        return result;
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    @RequestMapping("update")
    public PageResult updateUser( User user,HttpSession session){
        try {
            this.userService.updateUser(user,session);
        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.error();
        }
        return PageResult.ok();
    }

    /**
     * 邮箱激活
     * @param session
     * @return
     */
    @RequestMapping("setUstate")
    public PageResult setUstate(HttpSession session){
        try {
            return  this.userService.setUstate(session);
        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.error();
        }

    }

    /**
     * 找重名
     * @param uname
     * @return
     */
    @RequestMapping("findUname")
    public PageResult findUname(String uname){
        try {
            return this.userService.findUname(uname);
        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.error();
        }
    }

    /**
     * 找重邮箱
     * @param umail
     * @return
     */
    @RequestMapping("findUmail")
    public PageResult findUmail(String umail){
        try {
            return this.userService.findUmail(umail);
        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.error();
        }
    }

    /**
     * 找回密码邮件
     * @param umail
     * @param model
     * @return
     */
    @RequestMapping("sendPwdMail")
    public PageResult sendForgetPwd(String umail,Model model){
        try {
            return this.userService.sendPwdMail(umail,model);
        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.error();
        }
    }

    /**
     * 重新设置密码
     * @param user
     * @return
     */
    @RequestMapping("findpwd")
    public PageResult updateUserByUmail(User user){
        try {
            return this.userService.updateUserByUmail(user);
        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.error();
        }
    }
}
