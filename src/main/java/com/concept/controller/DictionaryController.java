package com.concept.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.concept.bean.Dictionary;
import com.concept.service.DictionaryService;

@Controller
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    // 查询并跳转
    @RequestMapping("jump")
    public ModelAndView showlist(ModelAndView mv, HttpServletRequest request) {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list = this.dictionaryService.selectAll();
        mv.addObject("list", list);
        mv.setViewName("library");
        mv.addObject("basepath",
                request.getScheme() + "://" + request.getHeader("Host") + request.getContextPath());// 获取路径
        return mv;
    }

    // 跳转添加字典表
    @RequestMapping("toAddDictionaryLibrary")
    public String add() {

        return "addDictionaryLibrary";
    }

    // 提交添加
    @RequestMapping("addDictionaryDatabase")
    public String addDictionaryDatabase(Dictionary dictionary) {
        this.dictionaryService.addDictionaryDatabase(dictionary);
        return "forward:jump.do";
    }

    // 跳转修改删除页
    @RequestMapping("toUpdateAndDelete")
    public ModelAndView updateAndDelete(ModelAndView mav, String id) {
        Dictionary dictionary = this.dictionaryService.toUpdateAndDelete(id);
        mav.addObject("dictionary", dictionary);
        mav.setViewName("updateAndDelete");
        return mav;
    }

    // 提交修改
    @RequestMapping("updateDictionaryDatabase")
    public String updateDictionaryDatabase(Dictionary dictionary) {
        this.dictionaryService.updateDictionaryDatabase(dictionary);
        return "forward:jump.do";
    }

    // 删除
    @RequestMapping("deleteDictionaryDatabase")
    public String deleteDictionaryDatabase(String id) {
        this.dictionaryService.deleteDictionaryDatabase(id);
        return "forward:jump.do";
    }

}
