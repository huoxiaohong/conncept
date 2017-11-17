package com.concept.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.concept.service.DataReportService;
import com.mongodb.DBObject;

@Controller
public class DataReportController {

    @Autowired
    private DataReportService dataReportService;

    // 报表一
    @RequestMapping("input")
    public ModelAndView selectData(ModelAndView mav, HttpSession session) throws JSONException {
        // session.setAttribute("admin", "123456");
        List<DBObject> list1 = new ArrayList<DBObject>();
        list1 = this.dataReportService.select1();
        JSONArray json1 = new JSONArray();
        for (DBObject a1 : list1) {
            JSONObject jo1 = new JSONObject();
            jo1.put("_id", a1.get("_id"));
            jo1.put("Surveyor", a1.get("surveyor"));
            jo1.put("agerange", a1.get("agerange"));
            jo1.put("a1count", a1.get("a1count"));
            jo1.put("flag4", (int) Double.parseDouble(a1.get("flag4") == null ? "0" : a1.get("flag4") + ""));
            jo1.put("flag5", (int) Double.parseDouble(a1.get("flag5") == null ? "0" : a1.get("flag5") + ""));
            jo1.put("flag6", (int) Double.parseDouble(a1.get("flag6") == null ? "0" : a1.get("flag6") + ""));
            jo1.put("flag7", (int) Double.parseDouble(a1.get("flag7") == null ? "0" : a1.get("flag7") + ""));
            jo1.put("flag8", (int) Double.parseDouble(a1.get("flag8") == null ? "0" : a1.get("flag8") + ""));
            jo1.put("flag9", (int) Double.parseDouble(a1.get("flag9") == null ? "0" : a1.get("flag9") + ""));
            json1.put(jo1);
        }
        mav.addObject("json1", json1);
        mav.addObject("list1", list1);
        mav.setViewName("tableReport1");
        return mav;
    }

    // 报表二
    @RequestMapping("organ")
    public ModelAndView organDamage(ModelAndView mav) throws Exception {
        List<DBObject> list2 = new ArrayList<DBObject>();
        list2 = this.dataReportService.select2();
        JSONArray json2 = new JSONArray();
        for (DBObject a2 : list2) {
            JSONObject jo2 = new JSONObject();
            jo2.put("_id", a2.get("_id"));
            jo2.put("Surveyor", a2.get("Surveyor"));
            jo2.put("G1_2_sum", a2.get("G1_2_sum"));
            jo2.put("G1_3_sum", a2.get("G1_3_sum"));
            jo2.put("G1_4_sum", a2.get("G1_4_sum"));
            jo2.put("G1_5_sum", a2.get("G1_5_sum"));
            jo2.put("G1_6_sum", a2.get("G1_6_sum"));
            jo2.put("G1_7_sum", a2.get("G1_7_sum"));
            jo2.put("G2_2_sum", a2.get("G2_2_sum"));
            jo2.put("G2_3_sum", a2.get("G2_3_sum"));
            jo2.put("G2_4_sum", a2.get("G2_4_sum"));
            jo2.put("G3_2_sum", a2.get("G3_2_sum"));
            jo2.put("G3_3_sum", a2.get("G3_3_sum"));
            jo2.put("G4_sum", a2.get("G4_sum"));
            jo2.put("G5_1_sum", a2.get("G5_1_sum"));
            jo2.put("G5_2_sum", a2.get("G5_2_sum"));
            jo2.put("G5_3_sum", a2.get("G5_3_sum"));
            json2.put(jo2);
        }
        mav.addObject("json2", json2);
        mav.addObject("list2", list2);
        mav.setViewName("tableReport2");
        return mav;
    }

    // 报表三
    @RequestMapping("danger")
    public ModelAndView dangerCase(ModelAndView mav) throws Exception {
        List<DBObject> list3 = new ArrayList<DBObject>();
        list3 = this.dataReportService.select3();
        JSONArray json3 = new JSONArray();
        for (DBObject a3 : list3) {
            JSONObject jo3 = new JSONObject();
            jo3.put("_id", a3.get("_id"));
            jo3.put("Surveyor", a3.get("Surveyor"));
            jo3.put("C1_sum", a3.get("C1_sum"));
            jo3.put("C2_sum", a3.get("C2_sum"));
            jo3.put("C3_sum", a3.get("C3_sum"));
            jo3.put("C4_sum", a3.get("C4_sum"));
            jo3.put("C5_sum", a3.get("C5_sum"));
            json3.put(jo3);
        }
        mav.addObject("json3", json3);
        mav.addObject("list3", list3);
        mav.setViewName("tableReport3");
        return mav;
    }

    // 报表四
    @RequestMapping("history")
    public ModelAndView history(ModelAndView mav) throws Exception {
        // Map<String, Object> map4 = new HashMap<String, Object>();
        List<DBObject> list4 = new ArrayList<DBObject>();
        list4 = this.dataReportService.select4();
        JSONArray json = new JSONArray();
        for (DBObject a : list4) {
            JSONObject jo = new JSONObject();
            jo.put("_id", a.get("_id"));
            jo.put("Surveyor", a.get("Surveyor"));
            jo.put("B1_sum", a.get("B1_sum"));
            jo.put("B1b_sum", a.get("B1b_sum"));
            jo.put("B2_sum", a.get("B2_sum"));
            jo.put("B3_sum", a.get("B3_sum"));
            jo.put("B4_sum", a.get("B4_sum"));
            jo.put("B6_sum", a.get("B6_sum"));
            jo.put("B6d_sum", a.get("B6d_sum"));
            jo.put("B7_sum", a.get("B7_sum"));
            jo.put("B7a_sum", a.get("B7a_sum"));
            jo.put("B8_1_sum", a.get("B8_1_sum"));
            jo.put("B8_2_sum", a.get("B8_2_sum"));
            jo.put("B8_3_sum", a.get("B8_3_sum"));
            jo.put("B9_1_sum", a.get("B9_1_sum"));
            jo.put("B9_2_sum", a.get("B9_2_sum"));
            json.put(jo);
        }
        mav.addObject("json4", json);
        mav.addObject("list4", list4);
        mav.setViewName("tableReport4");
        return mav;
    }
}
