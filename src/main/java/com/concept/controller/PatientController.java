package com.concept.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.hibernate.validator.internal.xml.PayloadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.concept.bean.Articles;
import com.concept.bean.Patient;
import com.concept.service.PatientService;
import com.concept.util.Page;
import com.mongodb.DBObject;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @RequestMapping("classify")
    public ModelAndView classify(ModelAndView mv, String entity, String value, String initalState,
            String currentPage, String vague) {
        if (null == entity) {
            entity = "1";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        List<DBObject> list = new ArrayList<DBObject>();
        map.put("entity", entity);
        if (vague == null) {
            vague = "null";
        }
        if (vague != "null") {
            map.put("vague", vague);
        }
        // 查询总数
        int count = new Long(this.patientService.count(map)).intValue();
        Page<Patient> page = new Page<Patient>(currentPage, count, "10");

        // 排序
        if (value == null || value == "name") {
            value = "name";
        } else if (value == "symptom") {
            value = "symptom";
        } else if (value == "sdate") {
            value = "sdate";
        }
        List<String> list2 = new ArrayList<String>();
        list2.add(value);
        // 筛选
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

        list = this.patientService.showPatient(page, map, list2);
        mv.addObject("count", count);
        mv.addObject("page", page);
        mv.addObject("listPatient", list);
        mv.addObject("entity", entity);
        mv.addObject("initalState", initalState);
        mv.addObject("value", value);
        mv.addObject("vague", vague);
        mv.setViewName("patientClassify");
        return mv;
    }

    // 跳转健康模板
    @RequestMapping("template")
    public ModelAndView template(ModelAndView mv, HttpServletRequest request) {
        mv.addObject("basepath",
                request.getScheme() + "://" + request.getHeader("Host") + request.getContextPath());// 获取路径
        mv.setViewName("healthTemplate");
        return mv;
    }

    // 根據id查詢資訊，list里面存map集合
    @RequestMapping("articles")
    public ModelAndView selectArticle(ModelAndView mv, String id, String currentPage,
            HttpServletRequest request) {
        mv.addObject("basepath",
                request.getScheme() + "://" + request.getHeader("Host") + request.getContextPath());// 获取路径
        List<DBObject> list = new ArrayList<DBObject>();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("tleid", id);
        int count = new Long(this.patientService.countArt(map)).intValue();
        Page<Articles> page = new Page<Articles>(currentPage, count, "10");
        List<String> lists = new ArrayList<String>();
        lists.add("sdate");
        list = this.patientService.selectArticle(map, page, lists);
        mv.addObject("count", count);

        mv.addObject("page", page);
        mv.addObject("list", list);
        mv.addObject("tleid", id);
        mv.setViewName("articles");
        return mv;
    }

    // 从列表调到具体模板添加内容
    @RequestMapping("temps")
    public ModelAndView temp(ModelAndView mv, int tid, HttpServletRequest request) {
        mv.addObject("basepath",
                request.getScheme() + "://" + request.getHeader("Host") + request.getContextPath());// 获取路径
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> staMap = new HashMap<String, Object>();
        map.put("tleid", tid);
        map.put("tlephoto", "单击此处添加图片");
        map.put("tlephoto1", "单击此处添加图片");
        map.put("tlephoto2", "单击此处添加图片");
        map.put("tletitle", "单击此处添加标题");
        map.put("tletitle1", "单击此处添加副标题");
        map.put("tlecontent", "单击此处添加内容");
        map.put("tlecontent1", "单击此处添加内容");
        map.put("tlecontent2", "单击此处添加内容");
        staMap.put("sta", "add");
        staMap.put("number", "1");
        mv.addObject("stateMap", staMap);
        mv.addObject("parmMap", map);
        mv.setViewName("templates");
        return mv;
    }

    // 保存
    @RequestMapping("addpage")
    public ModelAndView addpage(HttpServletRequest request, ModelAndView mv) {
        mv.addObject("basepath",
                request.getScheme() + "://" + request.getHeader("Host") + request.getContextPath());// 获取路径
        Map<String, Object> parmMap = new HashMap<String, Object>();
        Map<String, Object> staMap = new HashMap<String, Object>();
        String a = request.getParameter("number").replaceAll("<.*?>", "");
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        String dateNowStr = date.format(d);
        String timeNowStr = time.format(d);
        List<String> list = new ArrayList<String>();
        list.add("date");
        parmMap.put("date", dateNowStr);
        parmMap.put("time", timeNowStr);
        if (a.equals("1")) {
            staMap.put("sta", "view");
            staMap.put("number", "2");
            String types = request.getParameter("types");
            parmMap.put("types", types);
            parmMap.put("_id", new ObjectId());
            Enumeration name = request.getParameterNames();
            Object parm = null;
            String val = "";
            while (name.hasMoreElements()) {
                parm = name.nextElement();
                if (null != parm && parm.toString().startsWith("tle")) {
                    val = request.getParameter(parm.toString());
                    parmMap.put(parm.toString(), val.replaceAll("<.*?>", ""));
                }
            }
            String sf = request.getParameter("tlephoto");
            String ss[];
            if (sf != null) {
                ss = sf.split("/");
                StringBuffer url1 = new StringBuffer();
                for (int i = 3; i < ss.length; i++) {
                    url1.append(ss[i].trim() + "/");
                }
                String url = url1.substring(0, url1.length() - 1);
                parmMap.put("url", url.toString());
            }
            String sf1 = request.getParameter("tlephoto1");
            String ss1[];
            if (sf1 != null) {
                ss1 = sf1.split("/");
                StringBuffer url12 = new StringBuffer();
                for (int i = 3; i < ss1.length; i++) {
                    url12.append(ss1[i].trim() + "/");
                }
                String url1 = url12.substring(0, url12.length() - 1);
                parmMap.put("url1", url1.toString());
            }
            String sf2 = request.getParameter("tlephoto2");
            String ss2[];
            if (sf2 != null) {
                ss2 = sf2.split("/");
                StringBuffer url22 = new StringBuffer();
                for (int i = 3; i < ss2.length; i++) {
                    url22.append(ss2[i].trim() + "/");
                }
                String url12 = url22.substring(0, url22.length() - 1);
                parmMap.put("url2", url12.toString());
            }
            this.patientService.addpage(parmMap);
            // System.out.println(parmMap);
            Object parmMap2 = this.patientService.selectById(parmMap);
            mv.addObject("stateMap", staMap);
            mv.addObject("parmMap", parmMap2);

            mv.setViewName("templates");
            return mv;
        } else {
            Map<String, Object> parmMap1 = new HashMap<>();
            parmMap1.put("_id", new ObjectId(request.getParameter("_id")));
            Map<String, Object> parmMapt = new HashMap<>();
            staMap.put("sta", "view");
            staMap.put("number", "2");
            String types = request.getParameter("types");
            parmMapt.put("types", types);
            Enumeration name = request.getParameterNames();
            Object parm = null;
            String val = "";
            while (name.hasMoreElements()) {
                parm = name.nextElement();
                if (null != parm && parm.toString().startsWith("tle")) {
                    val = request.getParameter(parm.toString());
                    parmMapt.put(parm.toString(), val.replaceAll("<.*?>", ""));
                }
            }

            this.patientService.pushpage(parmMap1, parmMapt);
            Map<String, Object> parmMap2 = new HashMap<>();
            parmMap2.put("_id", new ObjectId(request.getParameter("_id")));

            Object parmMap3 = this.patientService.selectById(parmMap2);
            mv.addObject("stateMap", staMap);
            mv.addObject("parmMap", parmMap3);
            mv.setViewName("templates");
            return mv;
        }
    }

    // 可编辑
    @RequestMapping("viewpage")
    public ModelAndView viewpage(HttpServletRequest request, ModelAndView mv) {
        mv.addObject("basepath",
                request.getScheme() + "://" + request.getHeader("Host") + request.getContextPath());// 获取路径
        Map<String, Object> parmMap = new HashMap<String, Object>();
        Map<String, Object> staMap = new HashMap<String, Object>();
        staMap.put("sta", "add");
        staMap.put("number", "2");
        parmMap.put("_id", new ObjectId(request.getParameter("_id")));
        Object parmMap1 = this.patientService.selectById(parmMap);
        mv.addObject("stateMap", staMap);
        mv.addObject("parmMap", parmMap1);
        // System.out.println("====="+parmMap1);
        mv.setViewName("templates");
        return mv;
    }

    // 推送
    @RequestMapping("pushpage")
    public ModelAndView pushpage(ModelAndView mv, String id, String lid, String currentPage) {
        Map<String, Object> parmMap = new HashMap<String, Object>();

        parmMap.put("_id", new ObjectId(lid));
        Object parmMapo = this.patientService.selectById(parmMap);
        // System.out.println(parmMapo);
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        String dateNowStr = date.format(d);
        String timeNowStr = time.format(d);

        Map<String, Object> parmMapt = new HashMap<String, Object>();
        parmMapt.put("tlestate", "1");
        parmMapt.put("date", dateNowStr);
        parmMapt.put("time", timeNowStr);
        this.patientService.pushpage(parmMap, parmMapt);
        List<DBObject> list1 = new ArrayList<DBObject>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tleid", id);
        int count = new Long(this.patientService.countArt(map)).intValue();
        Page<Articles> page = new Page<Articles>(currentPage, count, "10");
        List<String> lists = new ArrayList<String>();
        lists.add("sdate");
        list1 = this.patientService.selectArticle(map, page, lists);
        // System.out.println(list);
        mv.addObject("page", page);
        mv.addObject("count", count);
        mv.addObject("list", list1);
        mv.addObject("tleid", id);
        mv.setViewName("articles");
        return mv;
    }

    // 撤回
    @RequestMapping("pullpage")
    public ModelAndView pullpage(ModelAndView mv, String id, String lid, String currentPage) {
        Map<String, Object> parmMap = new HashMap<String, Object>();

        parmMap.put("_id", new ObjectId(lid));
        Object parmMapo = this.patientService.selectById(parmMap);
        // System.out.println(parmMapo);
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        String dateNowStr = date.format(d);
        String timeNowStr = time.format(d);

        Map<String, Object> parmMapt = new HashMap<String, Object>();
        // System.out.println("========="+parmMapt);
        parmMapt.put("tlestate", "0");
        parmMapt.put("date", dateNowStr);
        parmMapt.put("time", timeNowStr);
        // System.out.println(parmMapt);
        this.patientService.pullpage(parmMap, parmMapt);

        List<DBObject> list1 = new ArrayList<DBObject>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tleid", id);
        int count = new Long(this.patientService.countArt(map)).intValue();
        Page<Articles> page = new Page<Articles>(currentPage, count, "10");
        List<String> lists = new ArrayList<String>();
        lists.add("sdate");
        list1 = this.patientService.selectArticle(map, page, lists);
        // System.out.println(list);
        mv.addObject("page", page);
        mv.addObject("count", count);
        mv.addObject("list", list1);
        mv.addObject("tleid", id);
        mv.setViewName("articles");
        return mv;
    }

    // 修改
    @RequestMapping("update")
    public ModelAndView update(ModelAndView mv, String id, HttpServletRequest request) {
        mv.addObject("basepath",
                request.getScheme() + "://" + request.getHeader("Host") + request.getContextPath());// 获取路径
        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<>();
        map1.put("sta", "add");
        map.put("_id", new ObjectId(id));
        Object obj = this.patientService.selectOne(map);
        Map<String, Object> parmMap = (Map<String, Object>) obj;
        mv.addObject("parmMap", parmMap);
        mv.addObject("stateMap", map1);
        mv.setViewName("templates");
        return mv;
    }

}
