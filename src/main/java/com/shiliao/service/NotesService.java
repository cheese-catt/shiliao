package com.shiliao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiliao.domain.Notes;
import com.shiliao.domain.PageResult;
import com.shiliao.domain.UserDetails;
import com.shiliao.mapper.NotesMapper;
import com.shiliao.mapper.UserDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class NotesService {

    @Autowired
    private NotesMapper notesMapper;

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    //找所有帖子
    public PageResult<Notes> findNotesByPage(String key, Integer page, Integer rows, Boolean desc,String sortBy,String Ncategory,Integer Narea) {

        //初始化example对象
        Example example = new Example(Notes.class);
        Example.Criteria criteria = example.createCriteria();

        
        criteria.andEqualTo("nvalid",true);
        if (Ncategory!=null){
            criteria.andEqualTo("ncategory",Ncategory);
        }
        if (Narea!=null){
            criteria.andEqualTo("narea",Narea);
        }else {
            criteria.andEqualTo("narea",0);
        }
        //根据name模糊查询，或者首字母查询
        if (StringUtils.isNotBlank(key)){
            criteria.andLike("ntitle","%" + key + "%");
        }


        //添加分页条件
        PageHelper.startPage(page,rows);

        //添加排序条件
        if (StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy+" "+ (desc?"desc":"aSC"));
        }
        List<Notes> notes = this.notesMapper.selectByExample(example);
        notes.forEach(note->{
            Example example1 = new Example(UserDetails.class);
            example1.createCriteria().andEqualTo("uid",note.getNuid());
           note.setUdnames(this.userDetailsMapper.selectOneByExample(example1).getUdnames());
        });

        //包装成PageInfo
        PageInfo<Notes> pageInfo = new PageInfo<Notes>(notes);
        int total = this.notesMapper.selectCountByExample(example);

        //包装成分页结果集返回
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList(),true);

    }

    //添加帖子
    public PageResult<Notes> addNotes(Notes notes) {
        notes.setNdate(new Date());

        this.notesMapper.insertSelective(notes);
        return PageResult.ok().add("notes",notes);
    }

    //删除帖子
    public PageResult<Notes> deleteNotes(Long id){
        Notes notes = new Notes();
        notes.setNid(id);
        notes.setNvalid(false);
        int i = this.notesMapper.updateByPrimaryKeySelective(notes);

        return PageResult.ok();
    }

    //更新帖子
    public PageResult<Notes> updateNotes(Notes notes) {
        this.notesMapper.updateByPrimaryKeySelective(notes);
        return PageResult.ok().add("notes",notes);
    }

    //找到当前用户下的帖子
    public PageResult findNotesByUser(Integer page,Integer rows,Long uid) {

        //初始化example对象
        Example example = new Example(Notes.class);
        Example.Criteria criteria = example.createCriteria();

        //显示存在的数据,和对应的用户下的帖子
        criteria.andEqualTo("nvalid",true).andEqualTo("nuid",uid);
        //添加排序条件
            example.setOrderByClause("ndate"+" "+ "desc");

        //添加分页条件
        PageHelper.startPage(page,rows);

        //找到用户对应的帖子
        List<Notes> notes = this.notesMapper.selectByExample(example);


        //把帖子都包装进去
        PageInfo<Notes> pageInfo = new PageInfo<Notes>(notes);

        return new PageResult<>(pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList(),true);
        /*
        PageResult result = new PageResult();
        result.setItems(notes);
        result.setFlag(true);
        result.setMsg("返回成功");
        long l = Long.parseLong(count.toString());
        result.setTotal(l);
        return result;
                //查找总数
        Integer count =(Integer) this.notesMapper.selectCount(record);
        */
    }


}
