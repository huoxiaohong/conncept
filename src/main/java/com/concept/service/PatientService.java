package com.concept.service;

import java.util.List;
import java.util.Map;

import com.concept.bean.Articles;
import com.concept.bean.Patient;
import com.concept.util.Page;
import com.mongodb.DBObject;

public interface PatientService {

    void addpage(Map<String, Object> parmMap);

    Long count(Map<String, Object> map);

    List<DBObject> showPatient(Page<Patient> page, Map<String, Object> map, List<String> list2);

    Long countArt(Map<String, Object> map);

    List<DBObject> selectArticle(Map<String, Object> map, Page<Articles> page, List<String> lists);

    Object selectById(Map<String, Object> parmMap);

    Object selectOne(Map<String, Object> map);

    void pushpage(Map<String, Object> parmMap1, Map<String, Object> parmMapt);

    void pullpage(Map<String, Object> parmMap, Map<String, Object> parmMapt);

}
