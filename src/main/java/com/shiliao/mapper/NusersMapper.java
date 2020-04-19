package com.shiliao.mapper;

import com.shiliao.domain.Nusers;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface NusersMapper extends Mapper<Nusers> {

    @Select("SELECT a.`id`,`Nids`,a.`Nlike` FROM n_users a WHERE a.`Uid`=#{uid}")
    Nusers selectByuid(Long uid);
}
