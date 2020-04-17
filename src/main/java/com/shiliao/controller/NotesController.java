package com.shiliao.controller;

import com.shiliao.domain.Notes;
import com.shiliao.domain.PageResult;
import com.shiliao.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    /**
     * 找到所有帖子，并进行分页
     * @param page
     * @param key
     * @param rows
     * @param desc
     * @param sortBy
     * @return
     */
    @PostMapping("findAll")
    public PageResult<Notes> findAll(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page,
                                     @RequestParam(value = "key",required = false)String key,
                                     @RequestParam(value = "rows",required = false,defaultValue = "8")Integer rows,
                                     @RequestParam(value = "desc",required = false)Boolean desc,
                                     @RequestParam(value = "sortBy",required = false)String sortBy,
                                     @RequestParam(value = "Ncategory",required = false)String Ncategory,
                                     @RequestParam(value = "Narea",required = false)Integer Narea){

        PageResult<Notes> result=this.notesService.findNotesByPage(key,page,rows,desc,sortBy,Ncategory,Narea);
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
     * @param Nid
     * @return
     */
    @PostMapping("deleteNotes")
    public PageResult<Notes> deleteNotes(Long Nid){
        PageResult<Notes> result = this.notesService.deleteNotes(Nid);
        return result;
    }

    /**
     * 更新帖子
     * @param notes
     * @return
     */
    @PostMapping
    public PageResult<Notes> updateNotes(@RequestBody Notes notes){
        if (notes.getNid()==null){
            return PageResult.error();
        }
        PageResult<Notes> result = this.notesService.updateNotes(notes);
        return  result;
    }


}
