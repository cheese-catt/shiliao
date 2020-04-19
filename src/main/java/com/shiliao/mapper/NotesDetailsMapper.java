package com.shiliao.mapper;

import com.shiliao.domain.NotesDetails;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface NotesDetailsMapper extends Mapper<NotesDetails>{

    @Select("select ndorder from notes_details where uid = #{uid}")
    List<Long> selectOrderByUid(Long uid);
}
