package com.shiliao.service;

import com.shiliao.domain.PageResult;
import com.shiliao.domain.UserDetails;
import com.shiliao.mapper.UserDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserDetailsService {

    @Autowired
    UserDetailsMapper userDetailsMapper;

    //更新用户信息
    @Transactional
    public PageResult updateUserDetails(UserDetails userDetails, HttpSession session) {
        Example example = new Example(UserDetails.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uid", userDetails.getUid());

        this.userDetailsMapper.updateByExampleSelective(userDetails,example);

        session.removeAttribute("userDetail");
        session.setAttribute("userDetail",userDetails);

      return   PageResult.ok().add("userDetails",userDetails);
    }

    public Map upload(MultipartFile file){
        String result_msg="";//上传结果信息

        Map<String,Object> root=new HashMap<String, Object>();

        if (file.getSize() / 1000 > 10000){
            result_msg="图片大小不能超过10000KB";
        }
        else{
            //判断上传文件格式
            String fileType = file.getContentType();
            if (fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/jpeg")) {
                // 要上传的目标文件存放的绝对路径
                //用src为保存绝对路径不能改名只能用原名，不用原名会导致ajax上传图片后在前端显示时出现404错误-->原因未知
//                String localPath="F:\\IDEAProject\\imageupload\\src\\main\\resources\\static\\img";
                final String localPath="E:\\workshop\\shiliao\\src\\main\\resources\\static\\images";
                //上传后保存的文件名(需要防止图片重名导致的文件覆盖)
                //获取文件名
                String fileName = file.getOriginalFilename();
                //获取文件后缀名
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                //重新生成文件名
                fileName = UUID.randomUUID()+suffixName;
                if (com.shiliao.util.FileUtils.upload(file, localPath, fileName)) {
                    //文件存放的相对路径(一般存放在数据库用于img标签的src)
                    String relativePath="images/"+fileName;
                    root.put("relativePath",relativePath);//前端根据是否存在该字段来判断上传是否成功
                    result_msg="图片上传成功";
                }
                else{
                    result_msg="图片上传失败";
                }
            }
            else{
                result_msg="图片格式不正确";
            }
        }

        root.put("result_msg",result_msg);

        return root;
    }

    //找到详细信息
    public PageResult findUserDetails(Long uid) {

        Example example = new Example(UserDetails.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uid", uid);
        List<UserDetails> userDetails = this.userDetailsMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(userDetails)){
          return PageResult.ok().add("userDetails",userDetails.get(0)) ;
        }else {
            return PageResult.error();
        }
    }
}
