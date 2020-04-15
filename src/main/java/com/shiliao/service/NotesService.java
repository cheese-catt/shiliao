package com.shiliao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiliao.domain.Notes;
import com.shiliao.domain.PageResult;
import com.shiliao.mapper.NotesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import org.apache.commons.lang.StringUtils;

import java.util.List;

@Service
public class NotesService {

    @Autowired
    private NotesMapper notesMapper;

    public PageResult<Notes> findNotesByPage(String key, Integer page, Integer rows, Boolean desc,String sortBy) {

        //初始化example对象
        Example example = new Example(Notes.class);
        Example.Criteria criteria = example.createCriteria();

        //根据name模糊查询，或者首字母查询
        if (StringUtils.isNotBlank(key)){
            criteria.andLike("Ntitle","%" + key + "%");
        }


        //添加分页条件
        PageHelper.startPage(page,rows);

        //添加排序条件
        if (StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy+" "+ (desc?"desc":"aSC"));
        }
        List<Notes> notes = this.notesMapper.selectByExample(example);

        //包装成PageInfo
        PageInfo<Notes> pageInfo = new PageInfo<Notes>(notes);
        int total = this.notesMapper.selectCountByExample(example);

        //包装成分页结果集返回
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList(),true);

    }
}
