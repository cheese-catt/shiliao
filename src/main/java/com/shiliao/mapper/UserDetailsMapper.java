package com.shiliao.mapper;

import com.shiliao.domain.UserDetails;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UserDetailsMapper extends Mapper<UserDetails> {

    @Select("select * from u_details where uid =#{uid} ")
    UserDetails selectAllByUid(Long uid);

    @Select("select udnames from u_details where uid = #{uid} ")
    String selectNameByUid(Long uid);
}
