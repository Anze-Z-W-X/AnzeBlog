package com.anze.controller;

import com.anze.domain.ResponseResult;
import com.anze.domain.entity.Link;
import com.anze.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    public ResponseResult getLinkList(Integer pageNum,Integer pageSize,String name,String status){
        return linkService.getLinkList(pageNum,pageSize,name,status);
    }

    @PostMapping
    public ResponseResult addLink(@RequestBody Link link){
        return linkService.addLink(link);
    }

    @GetMapping("/{id}")
    public ResponseResult getLinkById(@PathVariable("id")Long id){
        return linkService.getLinkById(id);
    }

    @PutMapping
    public ResponseResult updateLink(@RequestBody Link link){
        return linkService.updateLink(link);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delLink(@PathVariable("id")Long id){
        return linkService.delLink(id);
    }

    @PutMapping("/changeLinkStatus")
    public ResponseResult changeLinkStatus(@RequestBody Link link){
        return linkService.changeLinkStatus(link);
    }
}
