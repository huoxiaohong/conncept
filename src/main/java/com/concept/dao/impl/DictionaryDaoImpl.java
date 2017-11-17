package com.concept.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.concept.bean.Dictionary;
import com.concept.dao.DictionaryDao;

@Repository
public class DictionaryDaoImpl implements DictionaryDao {

    public void addDictionaryDatabase(Dictionary dictionary) {
        System.out.println("添加成功");
    }

    public Dictionary toUpdateAndDelete(String id) {

        Dictionary dictionary = new Dictionary(1, "A", "高血压", "收到了口封上发的快乐");
        return dictionary;
    }

    public void updateDictionaryDatabase(Dictionary dictionary) {
        System.out.println("修改成功");
    }

    public void deleteDictionaryDatabase(String id) {
        System.out.println("删除成功");
    }

    public List<Dictionary> selectAll() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        Dictionary library = new Dictionary(1, "A", "血压：", "收缩压-舒张压：数值，有单位，有值范围，由危急值。。。。");
        Dictionary library1 = new Dictionary(2, "B", "血压：", "收缩压-舒张压：数值，有单位，有值范围，由危急值。。。。");
        Dictionary library2 = new Dictionary(3, "C", "血压：", "收缩压-舒张压：数值，有单位，有值范围，由危急值。。。。");
        Dictionary library3 = new Dictionary(4, "D", "血压：", "收缩压-舒张压：数值，有单位，有值范围，由危急值。。。。");
        Dictionary library4 = new Dictionary(5, "E", "血压：", "收缩压-舒张压：数值，有单位，有值范围，由危急值。。。。");
        Dictionary library5 = new Dictionary(6, "F", "血压：", "收缩压-舒张压：数值，有单位，有值范围，由危急值。。。。");
        Dictionary library6 = new Dictionary(7, "G", "血压：", "收缩压-舒张压：数值，有单位，有值范围，由危急值。。。。");
        Dictionary library7 = new Dictionary(8, "H", "血压：", "收缩压-舒张压：数值，有单位，有值范围，由危急值。。。。");
        list.add(library);
        list.add(library1);
        list.add(library2);
        list.add(library3);
        list.add(library4);
        list.add(library5);
        list.add(library6);
        list.add(library7);
        return list;

    }

}
