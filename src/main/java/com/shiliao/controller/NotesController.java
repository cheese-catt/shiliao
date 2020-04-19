package com.shiliao.controller;

import com.shiliao.domain.Notes;
import com.shiliao.domain.PageResult;
import com.shiliao.service.NotesService;
import com.shiliao.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 找到所有帖子，并进行分页
     * @param page
     * @param key
     * @param rows
     * @param desc
     * @param sortBy
     * @return
     */
    @GetMapping("findAll")
    public PageResult<Notes> findAll(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page,
                                     @RequestParam(value = "key",required = false)String key,
                                     @RequestParam(value = "rows",required = false,defaultValue = "8")Integer rows,
                                     @RequestParam(value = "desc",required = false,defaultValue = "true")Boolean desc,
                                     @RequestParam(value = "sortBy",required = false)String sortBy,
                                     @RequestParam(value = "ncategory",required = false)String ncategory,
                                     @RequestParam(value = "narea",required = false)Integer narea){

        PageResult<Notes> result=this.notesService.findNotesByPage(key,page,rows,desc,sortBy,ncategory,narea);
        if (CollectionUtils.isEmpty(result.getItems())){
            return null;
        }
        return result;

    }

    /**
     * 添加帖子
     * @param notes
     * @return
     */
    @PostMapping("addNotes")
    public PageResult<Notes> addNotes(@RequestBody Notes notes){
        PageResult<Notes> result = this.notesService.addNotes(notes);
        return result;
    }

    /**
     * 删除帖子
     * @param nid
     * @return
     */
    @PostMapping("deleteNotes")
    public PageResult<Notes> deleteNotes(Long nid){
        PageResult<Notes> result = this.notesService.deleteNotes(nid);
        return result;
    }

    /**
     * 更新帖子
     * @param notes
     * @return
     */
    @PostMapping("updateNotes")
    public PageResult<Notes> updateNotes(@RequestBody Notes notes){
        if (notes.getNid()==null){
            return PageResult.error();
        }
        PageResult<Notes> result = this.notesService.updateNotes(notes);
        return  result;
    }

    /**
     * 找到具体的帖子信息
     * @param nid
     * @param uid
     * @return
     */
    @PostMapping("findNotesAndDetails")
    public PageResult<Notes> findNotesAndDetails(Long nid,Long uid,@RequestParam(value = "page",required = false,defaultValue = "1")Integer page,
                                                 @RequestParam(value = "rows",required = false,defaultValue = "7")Integer rows,
                                                 @RequestParam(value = "asc",required = false,defaultValue = "true")Boolean asc){
        PageResult<Notes> notesDetails=this.notesService.findNotesAndDetails(nid,uid,page,rows,asc);
        return notesDetails;
    }


    /**
     * 图片上传
     * @param file
     * @return
     */
    @RequestMapping("imageUpload")
    public Map imageUpload(@RequestParam("nimage") MultipartFile file) {
        return this.userDetailsService.upload(file);
    }
}
