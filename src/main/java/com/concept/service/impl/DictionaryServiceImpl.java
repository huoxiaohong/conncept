package com.concept.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concept.bean.Dictionary;
import com.concept.dao.DictionaryDao;
import com.concept.service.DictionaryService;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryDao dictionaryDao;

    public void addDictionaryDatabase(Dictionary dictionary) {
        this.dictionaryDao.addDictionaryDatabase(dictionary);
    }

    public Dictionary toUpdateAndDelete(String id) {
        return this.dictionaryDao.toUpdateAndDelete(id);
    }

    public void updateDictionaryDatabase(Dictionary dictionary) {
        this.dictionaryDao.updateDictionaryDatabase(dictionary);
    }

    public void deleteDictionaryDatabase(String id) {
        this.dictionaryDao.deleteDictionaryDatabase(id);
    }

    public List<Dictionary> selectAll() {
        return this.dictionaryDao.selectAll();
    }

}
