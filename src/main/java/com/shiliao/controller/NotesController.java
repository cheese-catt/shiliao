package com.shiliao.controller;

import com.shiliao.domain.Notes;
import com.shiliao.domain.PageResult;
import com.shiliao.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @GetMapping("findAll")
    public PageResult<Notes> findAll(@RequestParam(value = "page",required = false,defaultValue = "1")Integer page,
                                     @RequestParam(value = "key",required = false)String key,
                                     @RequestParam(value = "rows",required = false,defaultValue = "8")Integer rows,
                                     @RequestParam(value = "desc",required = false)Boolean desc,
                                     @RequestParam(value = "sortBy",required = false)String sortBy){

        PageResult<Notes> result=this.notesService.findNotesByPage(key,page,rows,desc,sortBy);
        if (CollectionUtils.isEmpty(result.getItems())){
            return null;
        }
        return result;

    }

}
