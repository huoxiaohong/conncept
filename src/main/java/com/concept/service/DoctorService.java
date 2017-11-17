package com.concept.service;

import java.util.List;
import java.util.Map;

import com.concept.bean.Message;
import com.concept.util.Page;
import com.mongodb.DBObject;

public interface DoctorService {

    List<DBObject> getMessage(Map<String, Object> map, List<String> list, Page<Message> page);

    DBObject healthReport(Map<String, Object> map);

    DBObject report(Map<String, Object> map);

    void saveHealth(Map<String, Object> map1, Map<String, Object> map2);

    void present(Map<String, Object> map1, Map<String, Object> map2);

    Long count(Map<String, Object> map);

}
