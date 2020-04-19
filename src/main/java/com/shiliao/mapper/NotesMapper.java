package com.shiliao.mapper;

import com.shiliao.domain.Notes;
import com.shiliao.domain.NotesDetails;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface NotesMapper extends Mapper<Notes> {

    @Select("select ntitle from notes where  nid = #{nid}")
    String selectNtitleByUid( @Param("nid") Long nid);
}
