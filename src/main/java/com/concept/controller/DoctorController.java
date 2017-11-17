package com.concept.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.concept.bean.Message;
import com.concept.service.DoctorService;
import com.concept.util.Page;
import com.mongodb.DBObject;

@Controller
public class DoctorController {
    private static final int String = 0;

    @Autowired
    private DoctorService doctorService;

    // private Patients patient;
    private List<DBObject> message;

    // 健康表单
    @RequestMapping("report")
    public ModelAndView report(ModelAndView mav, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("_id", new ObjectId(request.getParameter("_id")));
        DBObject object = this.doctorService.report(map);
        mav.addObject("healthDBObject", object);
        mav.addObject("id", new ObjectId(request.getParameter("_id")));
        mav.addObject("entity", request.getParameter("entity"));
        mav.setViewName("healthReport");
        return mav;
    }

    // 保存健康表单
    @RequestMapping("saveHealth")
    public ModelAndView saveHealth(ModelAndView mav, HttpServletRequest request) {
        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        String dateNowStr = date.format(d);
        String timeNowStr = time.format(d);

        map1.put("_id", new ObjectId(request.getParameter("_id")));
        map2.put("state", "0");
        map2.put("date", dateNowStr);
        map2.put("time", timeNowStr);
        map2.put("onclusion", request.getParameter("onclusion"));
        this.doctorService.saveHealth(map1, map2);
        return new ModelAndView("forward:report.do");
    }

    // 提交健康表单
    @RequestMapping("present")
    public ModelAndView present(ModelAndView mav, HttpServletRequest request) {
        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        String dateNowStr = date.format(d);
        String timeNowStr = time.format(d);
        map2.put("date", dateNowStr);
        map2.put("time", timeNowStr);
        map1.put("_id", new ObjectId(request.getParameter("_id")));
        map2.put("state", "1");
        map2.put("onclusion", request.getParameter("onclusion"));
        this.doctorService.present(map1, map2);
        return new ModelAndView("forward:report.do");
    }

    // 未完成健康表单
    @RequestMapping("unfinishReport")
    public ModelAndView unfinishReport(ModelAndView mav, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("_id", new ObjectId(request.getParameter("_id")));
        DBObject object = this.doctorService.report(map);
        mav.addObject("healthDBObject", object);
        mav.addObject("id", new ObjectId(request.getParameter("_id")));
        mav.addObject("state", request.getParameter("state"));
        mav.setViewName("healthReport2");
        return mav;
    }

    // 未完成保存健康表单
    @RequestMapping("unfinishSaveHealth")
    public ModelAndView unfinishSaveHealth(ModelAndView mav, HttpServletRequest request) {
        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        String dateNowStr = date.format(d);
        String timeNowStr = time.format(d);

        map1.put("_id", new ObjectId(request.getParameter("_id")));
        map2.put("state", "0");
        map2.put("date", dateNowStr);
        map2.put("time", timeNowStr);
        map2.put("onclusion", request.getParameter("onclusion"));
        this.doctorService.saveHealth(map1, map2);
        return new ModelAndView("forward:unfinishReport.do");
    }

    // 未完成提交健康表单
    @RequestMapping("unfinishPresent")
    public ModelAndView unfinishPresent(ModelAndView mav, HttpServletRequest request) {
        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        String dateNowStr = date.format(d);
        String timeNowStr = time.format(d);
        map2.put("date", dateNowStr);
        map2.put("time", timeNowStr);
        map1.put("_id", new ObjectId(request.getParameter("_id")));
        map2.put("state", "1");
        map2.put("onclusion", request.getParameter("onclusion"));
        this.doctorService.present(map1, map2);
        return new ModelAndView("forward:unfinishReport.do");
    }

    // 内容为空界面
    @RequestMapping("empty")
    public String empty() {
        return "empty";
    }

    // 内容加载界面
    @RequestMapping("load")
    public String load() {
        return "load";
    }

    // 内容找不到界面
    @RequestMapping("notFound")
    public String notFound() {
        return "notFound";
    }

    // 查询已完成、未完成状态消息
    @RequestMapping("message")
    public ModelAndView getMessage(ModelAndView mav, String state, String value, String initalState,
            String currentPage, String vague) {

        if (state == null || "" == state) {
            state = "0";
        }
        if (value == null || value == "name") {
            value = "name";
        } else if (value == "symptom") {
            value = "symptom";
        } else if (value == "sdate") {
            value = "sdate";
        }
        List<String> list = new ArrayList<String>();
        list.add(value);
        Map<String, Object> map = new HashMap<String, Object>();
        if (initalState == null || Integer.parseInt(initalState) == 0) {
            initalState = "0";
        }
        if (Integer.parseInt(initalState) == 1) {
            initalState = "1";
        }
        if (Integer.parseInt(initalState) == 2) {
            initalState = "2";
        }
        if (Integer.parseInt(initalState) == 3) {
            initalState = "3";
        }
        if (initalState != "0") {
            map.put("initalState", initalState);
        }
        if (vague == null) {
            vague = "null";
        }
        if (vague != "null") {
            map.put("vague", vague);
        }
        map.put("state", state);
        int count = new Long(this.doctorService.count(map)).intValue();
        Page<Message> page = new Page<Message>(currentPage, count, "10");
        message = this.doctorService.getMessage(map, list, page);
        // System.out.println(message);
        mav.addObject("page", page);
        mav.addObject("message", message);
        mav.addObject("count", count);
        mav.addObject("value", value);
        mav.addObject("vague", vague);
        mav.addObject("initalState", initalState);
        mav.addObject("state", state);
        mav.setViewName("unfinish");
        return mav;
    }

    // //查看未完成报告单信息
    // @RequestMapping("unfinishMess")
    // public ModelAndView healthReport(ModelAndView mv,String id){
    // Map<String,Object> map = new HashMap<String, Object>();
    // map.put("uid",id);
    // DBObject object= this.doctorService.healthReport(map);
    // System.out.println(object);
    // mv.addObject("map",(Map<String,Object>)object);
    //
    // mv.setViewName("healthReport");
    // return mv;
    // }

}
