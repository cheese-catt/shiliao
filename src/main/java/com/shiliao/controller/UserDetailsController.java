package com.shiliao.controller;

import com.shiliao.domain.PageResult;
import com.shiliao.domain.User;
import com.shiliao.domain.UserDetails;
import com.shiliao.mapper.UserDetailsMapper;
import com.shiliao.service.UserDetailsService;
import com.shiliao.service.UserService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("userDetails")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    /**
     * 修改用户的个人信息
     * @param user
     * @return
     */
    @RequestMapping("update")
    public PageResult updateUserDetails(UserDetails user, HttpSession session){

        try {
            if (user!=null) {
                 this.userDetailsService.updateUserDetails(user,session);
                return PageResult.ok().add("user",user);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.error();
        }
        return PageResult.error();
    }

    /**
     * 头像上传
     * @param file
     * @return
     */
    @RequestMapping("imageUpload")
    public Map imageUpload(@RequestParam("udimage") MultipartFile file) {
        return this.userDetailsService.upload(file);
    }


    /**
     * 通过uid找用户详细信息
     * @param uid
     * @return
     */
    @RequestMapping("findUserDetails")
    public PageResult findUserDetails(Long uid){
        try {
            return this.userDetailsService.findUserDetails(uid);
        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.error();
        }
    }
}
