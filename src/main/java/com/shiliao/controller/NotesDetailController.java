package com.shiliao.controller;

import com.shiliao.domain.NotesDetails;
import com.shiliao.domain.PageResult;
import com.shiliao.service.NotesDetailsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notesDetail")
public class NotesDetailController {


    @Autowired
    private NotesDetailsService notesDetailsService;

    /**
     * 添加评论
     * @param notesDetails
     * @return
     */
    @RequestMapping("insertDetails")
    public PageResult insertDetail(@RequestBody NotesDetails notesDetails){
        if (!StringUtils.isEmpty(notesDetails.getNddetails())){
            return this.notesDetailsService.insertDetail(notesDetails);
        }
      return PageResult.error();
    }

    @RequestMapping("deleteDetails")
    public PageResult deleteDetail(Long id){
        if (id !=null){
            return this.notesDetailsService.deleteDetail(id);
        }
        return PageResult.error();
    }
}
