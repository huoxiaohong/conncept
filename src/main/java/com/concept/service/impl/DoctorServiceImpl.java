package com.concept.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.concept.bean.Message;
import com.concept.service.DoctorService;
import com.concept.util.Page;
import com.mongodb.DBObject;
import com.ygj.concept.dao.ConceptDao;

@Service
public class DoctorServiceImpl implements DoctorService {

    ConceptDao cd = new ConceptDao();

    public Long count(Map<String, Object> map) {
        return cd.count("report", map);
    }

    public List<DBObject> getMessage(Map<String, Object> map, List<String> list, Page<Message> page) {

        return cd.find("report", map, null, list, -1, page.getStartRecord(), page.getPageSize());
    }

    @Override
    public DBObject healthReport(Map<String, Object> map) {

        return cd.findOne("report", map);
    }

    public DBObject report(Map<String, Object> map) {
        return cd.findOne("report", map);
    }

    // 保存
    public void saveHealth(Map<String, Object> map1, Map<String, Object> map2) {

        cd.update("report", map1, map2);
    }

    // 提交
    public void present(Map<String, Object> map1, Map<String, Object> map2) {

        cd.update("report", map1, map2);
        // System.out.println(cd.findOne("report", map1));
    }

}
