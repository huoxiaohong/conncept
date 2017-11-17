package com.concept.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.concept.bean.Articles;
import com.concept.bean.Patient;
import com.concept.service.PatientService;
import com.concept.util.Page;
import com.mongodb.DBObject;
import com.ygj.concept.dao.ConceptDao;

@Service
public class PatientServiceImpl implements PatientService {
    ConceptDao cd = new ConceptDao();

    // 插入新闻
    public void addpage(Map<String, Object> parmMap) {

        cd.insert("article", parmMap);
    }

    // 分页查询
    public List<DBObject> selectArticle(Map<String, Object> map, Page<Articles> page, List<String> lists) {

        return cd.find("article", map, null, lists, -1, page.getStartRecord(), page.getPageSize());
    }

    // 查询患者列表总数
    public Long count(Map<String, Object> map) {
        return cd.count("report", map);
    }

    public List<DBObject> showPatient(Page<Patient> page, Map<String, Object> map, List<String> list2) {
        return cd.find("report", map, null, list2, -1, page.getStartRecord(), page.getPageSize());
    }

    // 查询新闻总条数
    public Long countArt(Map<String, Object> map) {
        return cd.count("article", map);
    }

    public Object selectById(Map<String, Object> parmMap) {
        return cd.findOne("article", parmMap);
    }

    public Object selectOne(Map<String, Object> map) {
        ConceptDao cd = new ConceptDao();
        return cd.findOne("article", map);
    }

    public void pushpage(Map<String, Object> parmMap1, Map<String, Object> parmMapt) {
        cd.update("article", parmMap1, parmMapt);
    }

    public void pullpage(Map<String, Object> parmMap, Map<String, Object> parmMapt) {
        cd.update("article", parmMap, parmMapt);
    }

}
