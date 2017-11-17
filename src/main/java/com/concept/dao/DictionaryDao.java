package com.concept.dao;

import java.util.List;

import com.concept.bean.Dictionary;

public interface DictionaryDao {

    void addDictionaryDatabase(Dictionary dictionary);

    Dictionary toUpdateAndDelete(String id);

    void updateDictionaryDatabase(Dictionary dictionary);

    void deleteDictionaryDatabase(String id);

    List<Dictionary> selectAll();

}
