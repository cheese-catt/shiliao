package com.shiliao.mapper;

import com.shiliao.domain.NotesDetails;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface NotesDetailsMapper extends Mapper<NotesDetails>{

    @Select("select ndorder from notes_details where uid = #{uid}")
    List<Long> selectOrderByUid(Long uid);

    @Select("SELECT a.`id`,a.`Nid`,a.`NDdetails`,a.`uid`,a.`NDdate` FROM notes_details a WHERE  a.`Nid`IN" +
            "( SELECT b.nid FROM notes b WHERE b.`Nuid`= #{uid}) AND a.`NDvalid`=1 AND a.`NDread`=0 ")
    List<NotesDetails> selectUnreadDetails(Long uid);
}
