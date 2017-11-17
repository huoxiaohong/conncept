package com.ygj.concept.dao;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ConceptDao {

    private static String url;

    private static String dbName;

    private static MdbExecutor exe;

    private static MongoClient mongoClient;

    static {

        try {

            InputStream is = ConceptDao.class.getResourceAsStream("/mongo.properties");

            Properties properties = new Properties();

            properties.load(is);

            url = properties.getProperty("url");

            dbName = properties.getProperty("dbName");

            exe = MongoDBFactory.newInstance(url);

            mongoClient = new MongoClient(url);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // 把 map集合的数据插到数据库中
    public void insert(String tableName, Map<String, Object> map) {

        DBObject obj = new BasicDBObject(map);

        exe.insert(dbName, tableName, obj);

    }

    // 更新最后一次数据
    public void update(String tableName, Map<String, Object> map, List<String> avgList,
                       Map<String, Object>... argus) {

        DBObject find = findOne(tableName, map);
        Map<String, Object> object = new HashMap<String, Object>();
        DecimalFormat df = new DecimalFormat("######0.0");
        for (int i = 0; i < argus.length; i++) {
            Set<String> keys = argus[i].keySet();
            for (String key : keys) {
                Object o = argus[i].get(key);
                Object obj = find.get(key);
                List<Object> list = (List<Object>) obj;
                list.remove(list.size() - 1);
                list.add(o);
                object.put(key, list);
                for (String avgLi : avgList) {
                    if (key == avgLi) {
                        Object object1 = object.get(avgLi);
                        List<Double> lis = (List<Double>) object1;
                        Double sum = 0.0;
                        Double count = 0.0;
                        for (Double li : lis) {
                            sum += li;
                            count++;
                        }
                        Double avg = sum / count;
                        df.format(avg);
                        object.put(avgLi + "avg", avg);
                    }
                }
            }
        }
        update(tableName, map, object);
    }

    /*
     * argus参数格式{key:value}
     */
    public void insert(String tableName, Map<String, Object> map, List<String> avgList,
                       Map<String, Object>... argus) {
        DBObject find = findOne(tableName, map);
        DecimalFormat df = new DecimalFormat("######0.0");
        if (find != null) {
            Map<String, Object> obj = new HashMap<String, Object>();
            for (int i = 0; i < argus.length; i++) {
                Set<String> keys = argus[i].keySet();
                for (String key : keys) {
                    Object o = find.get(key);
                    List<Object> list = (List<Object>) o;
                    list.add(argus[i].get(key));
                    obj.put(key, list);

                    for (String avgLi : avgList) {
                        if (key == avgLi) {
                            Object object = obj.get(avgLi);
                            List<Double> lis = (List<Double>) object;
                            Double sum = 0.0d;
                            Double count = 0.0d;
                            for (Double li : lis) {
                                sum += li;
                                count++;
                            }
                            Double avg = sum / count;
                            df.format(avg);
                            obj.put(avgLi + "avg", avg);
                        }
                    }
                }
            }
            update(tableName, map, obj);

        } else {
            Map<String, Object> insertMap = new HashMap<String, Object>();
            Set<String> key1 = map.keySet();
            for (String k1 : key1) {
                insertMap.put(k1, map.get(k1));
            }
            for (int i = 0; i < argus.length; i++) {
                List<Object> list = new ArrayList<Object>();
                Set<String> key2 = argus[i].keySet();
                for (String k2 : key2) {
                    list.add(argus[i].get(k2));
                    insertMap.put(k2, list);
                    for (String avg : avgList) {
                        if (k2 == avg) {
                            insertMap.put(avg + "avg", argus[i].get(k2));
                        }
                    }
                }
            }

            insert(tableName, insertMap);

        }
    }

    /*
     * 方法同下
     * 
     * @param app参数类型为{key1:value1,key2:value2}
     */
    public void insert(String tableName, Map<String, Object> map, String key, Map<String, Object> addMap) {

        DBObject find = findOne(tableName, map);
        if (find != null) {
            Object o = find.get(key);
            List<Object> list = (List<Object>) o;
            list.add(addMap);
            Map<String, Object> obj = new HashMap<String, Object>();
            obj.put(key, list);
            update(tableName, map, obj);

        } else {
            Map<String, Object> insertMap = new HashMap<String, Object>();
            Set<String> key1 = map.keySet();
            for (String k1 : key1) {
                insertMap.put(k1, map.get(k1));
            }
            List<Object> list = new ArrayList<Object>();
            list.add(addMap);
            insertMap.put(key, list);
            insert(tableName, insertMap);

        }

    }

    /*
     * @param
     * tableName：表名，map：查询条件，appMap：插入的值参数格式为{key:{key1:value1,key2:value2},key0:{key1:value1,
     * key2:value2}}
     * 
     * 插入到数据库中的格式为：{key:[{key1:value1,key2:value2},{key1:value3,key2:value4}]}
     */
    public void insert(String tableName, Map<String, Object> map, Map<String, Object> addMap) {

        DBObject find = findOne(tableName, map);
        Set<String> list = addMap.keySet();
        Map<String, Object> obj = new HashMap<String, Object>();
        if (find != null) {
            for (String li : list) {
                Object o = find.get(li);
                List<Object> lis = (List<Object>) o;
                System.out.println(li);
                lis.add(addMap.get(li));
                obj.put(li, lis);
            }
            update(tableName, map, obj);

        } else {
            Map<String, Object> insertMap = new HashMap<String, Object>();
            Set<String> key1 = map.keySet();
            for (String k1 : key1) {
                insertMap.put(k1, map.get(k1));
            }
            for (String li : list) {

                List<Object> lis = new ArrayList<Object>();
                lis.add(addMap.get(li));
                insertMap.put(li, lis);
            }
            insert(tableName, insertMap);

        }

    }

    public void insert(String tableName, Map<String, Object> map, List<String> list,
                       Map<String, Object> addMap) {

        DBObject find = findOne(tableName, map);
        Map<String, Object> obj = new HashMap<String, Object>();
        if (find != null) {
            for (String li : list) {
                Object o = find.get(li);
                List<Object> lis = (List<Object>) o;
                lis.add(addMap.get(li));
                obj.put(li, lis);
            }
            update(tableName, map, obj);

        } else {
            Map<String, Object> insertMap = new HashMap<String, Object>();
            Set<String> key1 = map.keySet();
            for (String k1 : key1) {
                insertMap.put(k1, map.get(k1));
            }
            for (String li : list) {

                List<Object> lis = new ArrayList<Object>();
                lis.add(addMap.get(li));
                insertMap.put(li, lis);
            }
            insert(tableName, insertMap);

        }

    }

    // 在dbName数据库、tableName表中查询所有数据条数
    public Long count(String tableName) {
        return count(tableName, null);
    }

    // 在dbName数据库、tableName表中查询符合map要求的条数
    public Long count(String tableName, Map<String, Object> map) {
        Long count = null;

        if (map == null) {

            DB db = mongoClient.getDB(dbName);
            DBCollection collection = db.getCollection(tableName);
            count = collection.count();
        } else {

            DBObject q = new BasicDBObject(map);

            count = exe.count(dbName, tableName, q);
        }

        return count;
    }

    // 查询符合map要求的一条数据
    public DBObject findOne(String tableName, Map<String, Object> map) {
        return findOne(tableName, map, null);
    }

    // 查询符合map要求的一条数据并返回list集合的字段
    public DBObject findOne(String tableName, Map<String, Object> map, List<String> list) {

        DB db = mongoClient.getDB(dbName);
        DBCollection collection = db.getCollection(tableName);
        DBObject findOne = null;

        DBObject q = new BasicDBObject(map);

        if (list == null) {
            findOne = collection.findOne(q);
        } else {

            DBObject keys = new BasicDBObject();

            for (String li : list) {
                keys.put(li, 1);
            }

            findOne = collection.findOne(q, keys);
        }
        return findOne;

    }

    // article保存回显
    public List<DBObject> find(String tableName, Map<String, Object> map) {
        return find(tableName, map, null, null, 1, 0, Integer.MAX_VALUE);
    }

    public List<DBObject> find(String tableName, Map<String, Object> map, List<String> sortFieldName) {
        return find(tableName, map, null, sortFieldName, -1, 0, Integer.MAX_VALUE);
    }

    // article查询方法
    public List<DBObject> find(String tableName, Map<String, Object> map, List<String> sortFieldName,
                               int begin, int size) {
        return find(tableName, map, null, sortFieldName, 1, begin, size);
    }

    // 查询基础方法
    public List<DBObject> find(String tableName, Map<String, Object> map, List<String> list,
                               List<String> sortFieldName, int sort, int begin, int size) {

        DB db = mongoClient.getDB(dbName);

        DBCollection collection = db.getCollection(tableName);

        DBObject query = null;

        if (map == null) {
            query = new BasicDBObject();
        } else {
            query = new BasicDBObject(map);
        }
        DBCursor find = null;
        if (sortFieldName != null) {
            DBObject sortField = new BasicDBObject();
            for (String sortName : sortFieldName) {

                sortField.put(sortName, sort);
            }
            if (list == null) {
                find = collection.find(query).sort(sortField).skip(begin).limit(size);
            } else {
                DBObject keys = new BasicDBObject();
                for (String li : list) {
                    keys.put(li, 1);
                }
                find = collection.find(query, keys).sort(sortField).skip(begin).limit(size);
            }
        } else {
            if (list == null) {
                find = collection.find(query).skip(begin).limit(size);
            } else {
                BasicDBObject keys = new BasicDBObject();

                for (String li : list) {
                    keys.put(li, 1);
                }
                find = collection.find(query, keys).skip(begin).limit(size);
            }
        }
        List<DBObject> listAll = new ArrayList<DBObject>();

        while (find.hasNext()) {
            DBObject obj = find.next();
            listAll.add(obj);
        }
        return listAll;
    }

    // 查找符合map1要求的数据更新map2中的数据
    public void update(String tableName, Map<String, Object> map1, Map<String, Object> map2) {
        DBObject q = null;
        if (map1 != null || map1.isEmpty()) {
            q = new BasicDBObject(map1);
        } else {
            q = new BasicDBObject();
        }

        DBObject o = new BasicDBObject();

        Set<String> keys = map2.keySet();

        for (String key : keys) {

            o.put(key, map2.get(key));
        }
        Set<String> keyQuery = map1.keySet();
        //System.out.println(keyQuery.size());
        if (keyQuery.size() == 1) {
            for (String kq : keyQuery) {
                if (kq == "_id") {
                    String[] split = map1.toString().split("=");
                    String sString = split[0] + ": new ObjectId('" + split[1].replace("}", "'") + ")}";
                    String mql = "db." + tableName + ".update(" + sString + ",{$set:" + o + "},false,true)";
                    exe.excuteMQL(dbName, mql);
                } else {
                    exe.update(dbName, tableName, q, o);
                }
            }
        }

    }

    @Test
    public void Test() {

        ConceptDao cdi = new ConceptDao();

        Map<String, Object> map1 = new HashMap<String, Object>();

        // map1.put("uid", "7");
        // map1.put("sdate", "2017-07-28");
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("stime", "23:45");
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("value", "80");
        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("weight", 110);

        Map<String, Object> map2 = new HashMap<String, Object>();
        // System.out.println(map3);

        List<String> list = new ArrayList<String>();
        list.add("weight");
        // list.add("h");
        map2.put("weight", map3);
        // map2.put("h", map3);

        // update("weight", map1, list,map3, map5);
        // insert("weight", map1, list, map3, map5);
        // map1.put("sampleNo", "1416");
        // map1.put("weight", 70.02);
        map1.put("_id", new ObjectId("5971563b658b1507b2d9e0cb"));
        update("report", map1, map5);
        List<DBObject> find = find("report", map1);
        System.out.println(find);

    }

}
