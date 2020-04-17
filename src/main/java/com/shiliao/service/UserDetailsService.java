package com.shiliao.service;

import com.shiliao.domain.PageResult;
import com.shiliao.domain.UserDetails;
import com.shiliao.mapper.UserDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    @Autowired
    UserDetailsMapper userDetailsMapper;

    public PageResult updateUserDetails(UserDetails userDetails) {
       this.userDetailsMapper.updateByPrimaryKeySelective(userDetails);
       PageResult pageResult = new PageResult();
      return   PageResult.ok().add("userDetails",userDetails);
    }
}
