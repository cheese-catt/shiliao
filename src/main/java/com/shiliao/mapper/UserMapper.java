package com.shiliao.mapper;



import com.shiliao.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    @Select("select * from users where uname = #{uname}")
    User findUname(String uname);

    @Select("select * from users where umail = #{umail}")
    User findUmail(String umail);

    @Select("select * from users where uname=#{uname} and upwd =#{upwd}")
    User selectByUnameAndPwd(@Param("uname") String uname, @Param("upwd") String upwd);

    @Update("UPDATE users SET upwd = #{user.upwd} WHERE Umail = #{user.umail}")
    void updateUserByUmail(@Param("user") User user);
}
