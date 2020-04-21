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
     * @param model
     * @return
     */
    @PostMapping("login")
    public PageResult findUser(User user , Model model){
        PageResult result = new PageResult();

        if (user.getUname()!=null &&user.getUpwd()!=null){
       User user1 = this.userService.findUser(user);
       //找到对应的数据之后，将其存入session域
       if (user1 == null){
           result.setFlag(false);
           result.setMsg("帐号或密码错误");
           return result;

       }else {
            List<User> users = Arrays.asList(user1);
            result.setItems(users);
            result.setFlag(true);

           model.addAttribute("user",user1);
            return  result;
        }}
        result.setFlag(false);
        result.setMsg("帐号或密码错误");

        return result;
    }

    /**
     * 注册
     * @param user
     * @param model
     * @return
     */
    @PostMapping("regist")
    public PageResult queryUserbyId(User user ,Model model){
        PageResult result = new PageResult();
        if (user!=null) {
            this.userService.insertUser(user);
            result.setFlag(true);

            return result;
        }

        result.setMsg("注册失败，请稍后重试");
        return result;
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
    public PageResult updateUser(@RequestBody User user){
        try {
            this.userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.error();
        }
        return PageResult.ok();
    }
}
