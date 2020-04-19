package com.shiliao.service;

import com.shiliao.domain.NotesDetails;
import com.shiliao.domain.PageResult;
import com.shiliao.mapper.NotesDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class NotesDetailsService {

    @Autowired
    private NotesDetailsMapper notesDetailsMapper;

    //添加评论
    public PageResult insertDetail(NotesDetails notesDetails) {

        //设置未读并且可显示
        notesDetails.setNdread(false);
        notesDetails.setNdvalid(true);

        //找到帖子下楼层的最大值
        List<Long> order = this.notesDetailsMapper.selectOrderByUid(notesDetails.getUid());
        int max = max(order)+1;
        //将最高的楼层赋值进去
        notesDetails.setNdorder(max);
        this.notesDetailsMapper.insertSelective(notesDetails);
        return PageResult.ok().add("notesDetails",notesDetails);
    }

    //找最大值
    public  int max(List array){
        int max=0;
        int i=0;
        for(i=0;i<array.size();i++){

            if(Integer.parseInt(array.get(i).toString())>max){
                max=Integer.parseInt(array.get(i).toString());
            }
        }
        return max;
    }

    public PageResult deleteDetail(Long id) {
        NotesDetails notesDetails = new NotesDetails();
        notesDetails.setId(id);
        notesDetails.setNdvalid(false);
        this.notesDetailsMapper.updateByPrimaryKeySelective(notesDetails);
        return PageResult.ok();
    }
}
