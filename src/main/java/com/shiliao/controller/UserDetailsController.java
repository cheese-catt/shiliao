package com.shiliao.controller;

import com.shiliao.domain.PageResult;
import com.shiliao.domain.UserDetails;
import com.shiliao.mapper.UserDetailsMapper;
import com.shiliao.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userDetails")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 修改用户的个人信息
     * @param userDetails
     * @return
     */
    public PageResult updateUserDetails(@RequestBody UserDetails userDetails){

        try {
            if (userDetails!=null){
               return this.userDetailsService.updateUserDetails(userDetails);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.error();
        }
        return PageResult.error();
    }
}
