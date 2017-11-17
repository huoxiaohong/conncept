package com.concept.service;

import java.util.List;

import com.concept.bean.Dictionary;

public interface DictionaryService {

    void addDictionaryDatabase(Dictionary dictionary);

    Dictionary toUpdateAndDelete(String id);

    void updateDictionaryDatabase(Dictionary dictionary);

    void deleteDictionaryDatabase(String id);

    List<Dictionary> selectAll();

}
