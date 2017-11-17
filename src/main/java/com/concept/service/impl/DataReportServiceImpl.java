package com.concept.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.concept.service.DataReportService;
import com.mongodb.DBObject;
import com.ygj.concept.dao.ConceptDao;

@Service
public class DataReportServiceImpl implements DataReportService {

    public List<DBObject> select4() {
        ConceptDao cd = new ConceptDao();
        return cd.find("report_4", null);
    }

    @Override
    public List<DBObject> select3() {
        ConceptDao cd = new ConceptDao();
        return cd.find("report_3", null);
    }

    @Override
    public List<DBObject> select2() {
        ConceptDao cd = new ConceptDao();
        return cd.find("report_2", null);
    }

    @Override
    public List<DBObject> select1() {
        ConceptDao cd = new ConceptDao();
        return cd.find("report_1", null);
    }

}
