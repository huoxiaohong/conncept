package com.ygj.concept.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MongoDBFactory {

    // 客户端缓存
    private static Map<String, Object> mongoClientCache = new HashMap<String, Object>();

    // 单例锁
    private static Object lock = new Object();

    // 数据地址模式
    private static Pattern addressPattern = Pattern.compile(".*:[0-9]*$");

    // 地址验证
    private static boolean isAddress(String str) {
        return (str == null || str.isEmpty()) ? false : addressPattern.matcher(str).matches();
    }

    /**
     * 根据地址获取mongo查询器
     * 
     * @param address 格式"host:port"
     * @return
     */
    public static MdbExecutor singletonInstance(String address) {
        // 验证mongo地址格式是否正确
        if (!isAddress(address))
            return null;
        // System.out.println("1");
        Object res = mongoClientCache.get(address);
        if (res == null) {
            synchronized (lock) {
                if ((res = mongoClientCache.get(address)) == null) {
                    MdbHandler handler = new MdbHandler(address);
                    MdbExecutor _mdbexe = new MdbExecutor(handler);
                    // System.out.println(_mdbexe);
                    // System.out.println("2");
                    mongoClientCache.put(address, _mdbexe);
                    return _mdbexe;
                } else
                    return (MdbExecutor) res;
            }
        }
        return (MdbExecutor) res;
    }

    /**
     * 多例模式，用于多线程
     * 
     * @param address 格式"host:port"
     * @return
     */
    public static MdbExecutor newInstance(String address) {
        // 验证mongo地址格式是否正确
        if (!isAddress(address))
            return null;
        return new MdbExecutor(new MdbHandler(address));
    }

    public static void main(String[] args) {
        // System.out.println(isAddress("192.168.6.119:37008"));
        test();
    }

    public static void test() {
        String address = "192.168.6.119:37008";
        MdbExecutor executor = MongoDBFactory.singletonInstance(address);
        // System.out.println(executor);
        DBObject query = new BasicDBObject();
        query.put("targetType", 2);
        Long cursor = executor.count("personalCenter", "fav", query);
        System.out.println("size->" + cursor);
    }
}
