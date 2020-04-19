package com.shiliao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiliao.domain.NotesDetails;
import com.shiliao.domain.PageResult;
import com.shiliao.domain.User;
import com.shiliao.mapper.NotesDetailsMapper;
import com.shiliao.mapper.NotesMapper;
import com.shiliao.mapper.UserDetailsMapper;
import com.shiliao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class NotesDetailsService {

    @Autowired
    private NotesDetailsMapper notesDetailsMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Autowired
    private NotesMapper notesMapper;

    //添加评论
    @Transactional
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

    //删除评论
    @Transactional
    public PageResult deleteDetail(Long id,Long detailsUserid,Long uid) {
        //查询用户是否有管理员权限
        User user = this.userMapper.selectByPrimaryKey(uid);
        if (detailsUserid.equals(uid)||user.getUtype()){

        }

        //查询是否是本人的评论
        NotesDetails notesDetails = new NotesDetails();
        notesDetails.setId(id);
        notesDetails.setNdvalid(false);
        this.notesDetailsMapper.updateByPrimaryKeySelective(notesDetails);
        return PageResult.ok();
    }

    //找到通知
    public PageResult<NotesDetails> findNotice(Long uid) {

        //找到对应未读评论下的主键id 对应的帖子id,内容 用户id 评论日期
        List<NotesDetails> notesDetails = this.notesDetailsMapper.selectUnreadDetails(uid);
        if (!CollectionUtils.isEmpty(notesDetails)) {
            //开启分页，固定显示10条信息
            PageHelper.startPage(1,10);
            //获取并设置对应评论下的用户名和帖子名
            notesDetails.forEach(notesdetail -> {
                String udname = this.userDetailsMapper.selectNameByUid(notesdetail.getUid());
                notesdetail.setUdnames(udname);
                String ntitle = this.notesMapper.selectNtitleByUid(notesdetail.getNid());
                notesdetail.setNtitle(ntitle);
            });

            PageInfo<NotesDetails> pageInfo = new PageInfo<>(notesDetails);
            PageResult<NotesDetails> pageResult = new PageResult<NotesDetails>();
            pageResult.setItems(pageInfo.getList());
            pageResult.setTotal(pageInfo.getTotal());
            pageResult.setFlag(true);
            pageResult.setMsg("有未读评论");
            return pageResult;
        }else {
            return PageResult.error();
        }
    }

    //设置已读
    public PageResult setRead(String ids) {
        //将数据分割组成成一个数组
        List<String> ids1 = Arrays.asList(ids.split(","));
        for (String id : ids1) {
            long id1 = Long.parseLong(id);
            NotesDetails notesDetails = new NotesDetails();
            notesDetails.setNdread(true);
            notesDetails.setId(id1);
            this.notesDetailsMapper.updateByPrimaryKeySelective(notesDetails);
        }
        return PageResult.ok();
    }
}
