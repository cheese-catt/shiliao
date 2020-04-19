package com.shiliao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiliao.domain.*;
import com.shiliao.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import org.apache.commons.lang.StringUtils;

import java.util.*;

@Service
public class NotesService {

    @Autowired
    private NotesMapper notesMapper;

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Autowired
    private NcategoryMapper ncategoryMapper;

    @Autowired
    private NotesDetailsMapper notesDetailsMapper;

    @Autowired
    private NusersMapper nusersMapper;
    @Autowired
    private UserMapper userMapper;


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
        if (StringUtils.isNotBlank(sortBy)&& desc!=null){
            example.setOrderByClause(sortBy+" "+ (desc?"desc":"aSC"));
        }
        //执行查询，并将帖子对应的具体信息添加上去 ,添加对应的标签名
        List<Notes> notes = this.notesMapper.selectByExample(example);
        for (Notes note : notes) {
            Example example1 = new Example(UserDetails.class);
            example1.createCriteria().andEqualTo("uid", note.getNuid());
            UserDetails userDetails = this.userDetailsMapper.selectOneByExample(example1);

            //添加用户名，头像，性别信息
            note.setUdnames(userDetails.getUdnames());
            note.setUdsex(userDetails.getUdsex());
            note.setUdimages(userDetails.getUdimage());

            //查找出帖子对应的标签
            //设置一个空的list<map>集合
            List<Map<String, String>> categories = new ArrayList<>();
            List<String> cids = Arrays.asList(note.getNcategory().split(","));
            for (String cid : cids) {
                //{1,2,3,4} 通过每个标签id找到对应的标签名 并将标签名设置进去notes返回给前端
                Ncategory ncategory = this.ncategoryMapper.selectByPrimaryKey(cid);
                Map<String, String> map = new HashMap<>();
                map.put(cid.toString(), ncategory.getCategory());
                categories.add(map);
            }
            note.setNcategorys(categories);
        }


        //包装成PageInfo
        PageInfo<Notes> pageInfo = new PageInfo<Notes>(notes);


        //包装成分页结果集返回
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList(),true);

    }

    //添加帖子
    @Transactional
    public PageResult<Notes> addNotes(Notes notes) {
        notes.setNdate(new Date());

        this.notesMapper.insertSelective(notes);
        return PageResult.ok().add("notes",notes);
    }

    //删除帖子
    @Transactional
    public PageResult<Notes> deleteNotes(Long id,Long uid){
        //通过主键查询出帖子的具体信息
        Notes notes1 = this.notesMapper.selectByPrimaryKey(id);
        //通过主键查询用户的信息
        User user = this.userMapper.selectByPrimaryKey(uid);
        if (notes1.getNuid().equals(uid)||user.getUtype()){
            //如果是本用户发的帖子，或者管理员就可以删除
            Notes notes = new Notes();
            notes.setNid(id);
            notes.setNvalid(false);
            int i = this.notesMapper.updateByPrimaryKeySelective(notes);

            return PageResult.ok();
        }
        return PageResult.error();
    }

    //更新帖子
    @Transactional
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

    //找到具体的帖子内容和信息
    public PageResult findNotesAndDetails(Long nid, Long uid,Integer page,Integer rows,Boolean asc) {

        //先查询帖子的主体
        Notes notes = this.notesMapper.selectByPrimaryKey(nid);
        //找出帖子对应的用户信息
        Example example2 =new Example(NotesDetails.class);
        example2.createCriteria().andEqualTo("uid", notes.getNuid());
        UserDetails detail = this.userDetailsMapper.selectByExample(example2).get(0);
        PageResult<Notes>  result= new PageResult<Notes>();
        result.add("noteUserDetail",detail);

        //分页查询评论
        Example example = new Example(NotesDetails.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("nid",nid).andEqualTo("ndvalid",true);
        //设置分页
        PageHelper.startPage(page,rows);
        //设置排序条件
        example.setOrderByClause("ndorder" + " " + (asc ? "aSC" : "desc"));
        //找到需要的评论
        List<NotesDetails> notesDetails = this.notesDetailsMapper.selectByExample(example);
        PageInfo<NotesDetails> pageInfo = new PageInfo<NotesDetails>(notesDetails);
        //将查询到的评论每条添加上对应的用户个人信息
        pageInfo.getList().forEach(userdetail->{
            Example example3 =new Example(NotesDetails.class);
            example3.createCriteria().andEqualTo("uid", userdetail.getUid());
            UserDetails details = this.userDetailsMapper.selectByExample(example3).get(0);
            userdetail.setUserDetails(details);
        });
        //再封装到notes中
        notes.setNotesDetails(pageInfo.getList());
        //将页码数量和评论总数封装到返回的结果集
        result.setTotal(pageInfo.getTotal());
        result.setTotalPage(pageInfo.getPages());

        //查询是否已点赞
        Example example1 = new Example(Nusers.class);
        example1.createCriteria().andEqualTo("uid",uid);
        List<Nusers> nusers1 = this.nusersMapper.selectByExample(example1);
        if (!CollectionUtils.isEmpty(nusers1)) {
            Nusers nusers = nusers1.get(0);

            //得到所有点赞过的帖子 如果为null
            if (StringUtils.isEmpty(nusers.getNlike())) {
                result.add("nlikesFlag", false);
            }else {
                //如果不为null,就进行分割，成为数组
                List<String> likes = Arrays.asList(nusers.getNlike().split(","));
                //如果拥有,就设置flag到结果集
                if (likes.contains(nid.toString())) {
                    result.add("nlikesFlag", true);
                }else {
                    result.add("nlikesFlag", false);
                }
            }
            //查询是否已收藏 如果收藏不为空
            if (!StringUtils.isEmpty(nusers.getNids())) {
                List<String> collectctions = Arrays.asList(nusers.getNids().split(","));
                if (collectctions.contains(nid.toString())) {
                    //如果存在收藏
                    result.add("ncollectFlag", true);
                } else {
                    //如果不存在
                    result.add("ncollectFlag", false);
                }
            }else {
                result.add("ncollectFlag", false);
            }
        }
        //查询是否是当前用户自己的帖子
        //如果是将当前用户下对当前帖子的未读评论设置为已读
        if (notes.getNuid().equals(uid)){
            NotesDetails notesDetails1 = new NotesDetails();
            notesDetails1.setNdread(true);
            Example example4 = new Example(NotesDetails.class);
            example4.createCriteria().andEqualTo("nid",nid);
            this.notesDetailsMapper.updateByExampleSelective(notesDetails1,example4);
        }
        result.setItems(Arrays.asList(notes));
        return result;
    }

    //点赞
    @Transactional
    public PageResult setlike(Long nid,Long uid,Integer likeTimes){
       // 找到当前帖子下的点赞数
        Notes notes = new Notes();
        notes.setNid(nid);
        notes.setNlikeTimes(likeTimes+1);
        this.notesMapper.updateByPrimaryKeySelective(notes);
        //获取点赞的数据
        Nusers nusers = this.nusersMapper.selectByuid(uid);
        String nlike = nusers.getNlike();

        if (!StringUtils.isEmpty(nlike)){
            //如果点赞不为空
            nlike=nlike + "," +nid.toString();
        }else {
            //如果为空
            nlike=nid.toString();
        }
        //将点赞的数据设置好
        nusers.setNlike(nlike);
        //对表进行更新
        this.nusersMapper.updateByPrimaryKeySelective(nusers);
        Integer liketime=likeTimes+1;
        return PageResult.ok().add("likeTimes",liketime);
    }

    @Transactional
    //取消点赞
    public PageResult unlike(Long nid, Long uid, Integer nlikeTimes) {
        // 找到当前帖子下的点赞数
        Notes notes = new Notes();
        notes.setNid(nid);
        int nlikes=nlikeTimes-1;
        notes.setNlikeTimes(nlikes);
        this.notesMapper.updateByPrimaryKeySelective(notes);
        //获取点赞的数据
        Nusers nusers = this.nusersMapper.selectByuid(uid);
        String nlike = nusers.getNlike();
        List<String> likess = Arrays.asList(nlike.split(","));
        List<String> likes = new ArrayList<>(likess);
        String newlike ="";
        Iterator<String> iterator = likes.iterator();
        while (iterator.hasNext()){
          String like = iterator.next();
           if (like.equals(String.valueOf(nid))){
               iterator.remove();
           }
        }
        for (int i = 0; i < likes.size(); i++) {
            if (newlike.equals("")){
                newlike=likes.get(i);
            }else {
                newlike=newlike+","+likes.get(i);
            }
        }
        //将点赞的数据设置好
        nusers.setNlike(newlike);
        //对表进行更新
        this.nusersMapper.updateByPrimaryKeySelective(nusers);
        return PageResult.ok().add("nlikeTimes",newlike);
    }
}
