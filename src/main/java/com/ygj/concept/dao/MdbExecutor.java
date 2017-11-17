package com.ygj.concept.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.Bytes;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class MdbExecutor {
    private MdbHandler handler;

    transient private MongoClient m;

    private static final String MDB_ADMIN = "admin";

    private static final int DEFAULT_MAX_FIND = 120;

    private static final long DEFAULT_CONNECT_INTERVAL = 60000L;

    private long connectInterval = DEFAULT_CONNECT_INTERVAL;

    private int maxFind = DEFAULT_MAX_FIND;

    private int findCount = 0;

    private String adminName = MDB_ADMIN;

    transient private Object lock = new Object();

    public MdbExecutor(MdbHandler handler) {
        this.handler = handler;
    }

    public void findMongoDB() {
        if (null == m) {
            synchronized (lock) {
                if (null == m) {
                    try {
                        m = new MongoClient(this.handler.getHost(), this.handler.getPort());
                        // 设置连接不超时
                        m.addOption(Bytes.QUERYOPTION_NOTIMEOUT);
                        m.addOption(Bytes.QUERYOPTION_AWAITDATA);
                        findCount = 0;
                    } catch (Exception e) {
                        e.printStackTrace();
                        m = null;
                        try {
                            Thread.sleep(connectInterval);
                            if (findCount++ < maxFind) {
                                findMongoDB();
                            }
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public CommandResult excuteMQL(String dbname, String mql, Object... args) {
        findMongoDB();
        if (null != this.m) {
            DB db = this.m.getDB(dbname);
            return db.doEval(mql, args);
        }
        return null;
    }

    public WriteResult insert(String dbName, String tableName, List<DBObject> list) {
        findMongoDB();
        if (null != this.m) {
            DB db = this.m.getDB(dbName);
            DBCollection tb = db.getCollection(tableName);

            WriteResult wr = tb.insert(list);
            return wr;
        }
        return null;
    }

    public WriteResult insert(String dbName, String tableName, DBObject obj) {
        findMongoDB();
        if (null != this.m) {
            DB db = this.m.getDB(dbName);
            DBCollection tb = db.getCollection(tableName);
            return tb.insert(obj);
        }
        return null;
    }

    public WriteResult update(String dbName, String tableName, DBObject q, DBObject o) {
        findMongoDB();
        if (null != this.m) {
            DB db = this.m.getDB(dbName);
            DBCollection tb = db.getCollection(tableName);
            return tb.update(q, o);
        }
        return null;
    }

    public WriteResult update(String dbName, String tableName, DBObject q, DBObject o, boolean upsert,
            boolean multi) {
        findMongoDB();
        if (null != this.m) {
            DB db = this.m.getDB(dbName);
            DBCollection tb = db.getCollection(tableName);
            return tb.update(q, o, upsert, multi);
        }
        return null;
    }

    public void remove(String dbName, String tableName, Set<DBObject> list) {
        findMongoDB();
        if (null != this.m) {
            DB db = this.m.getDB(dbName);
            DBCollection tb = db.getCollection(tableName);
            for (DBObject o : list) {
                try {
                    tb.remove(o);
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }

    public WriteResult remove(String dbName, String tableName, DBObject dbo) {
        WriteResult wr = null;
        findMongoDB();
        if (null != this.m) {
            DB db = this.m.getDB(dbName);
            DBCollection tb = db.getCollection(tableName);
            try {
                wr = tb.remove(dbo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return wr;
    }

    public DBCursor getCursor(String dbName, String tableName, DBObject query) {
        return find(dbName, tableName, query, null);
    }

    public DBCursor find(String dbName, String tableName, DBObject query, DBObject keys) {
        findMongoDB();
        if (null != this.m) {
            DB db = this.m.getDB(dbName);
            DBCollection tb = db.getCollection(tableName);

            if (keys == null) {
                return tb.find(query);
            } else {
                return tb.find(query, keys);
            }
        }
        return null;
    }

    public DBCursor find(String dbName, String tableName, DBObject query) {
        return find(dbName, tableName, 0, Integer.MAX_VALUE, null, 1, query, null);
    }

    public DBCursor find(String dbName, String tableName, int begin, int size, String sortFieldName,
            int sort, DBObject query, DBObject keys) {
        findMongoDB();
        if (null != this.m) {
            DB db = this.m.getDB(dbName);
            DBCollection tb = db.getCollection(tableName);

            if (sortFieldName != null) {
                DBObject sortField = new BasicDBObject();
                sortField.put(sortFieldName, sort);
                if (keys == null) {
                    return tb.find(query).sort(sortField).skip(begin).limit(size);
                } else {
                    return tb.find(query, keys).sort(sortField).skip(begin).limit(size);
                }
            } else {
                if (keys == null) {
                    return tb.find(query).skip(begin).limit(size);
                } else {
                    return tb.find(query, keys).skip(begin).limit(size);
                }
            }
        }
        return null;
    }

    public Long count(String dbName, String tableName, DBObject query) {
        findMongoDB();
        if (null != this.m) {
            DB db = this.m.getDB(dbName);
            DBCollection tb = db.getCollection(tableName);
            return tb.count(query);
        }
        return null;
    }

    public DBCursor find(String dbName, String tableName, int begin, int size, String sortFieldName,
            int sort, DBObject query) {
        return find(dbName, tableName, begin, size, sortFieldName, sort, query, null);
    }

    // 多字段排序
    public DBCursor find(String dbName, String tableName, int begin, int size, DBObject query, DBObject sort) {
        findMongoDB();
        if (null != this.m) {
            DB db = this.m.getDB(dbName);
            DBCollection tb = db.getCollection(tableName);
            return tb.find(query).sort(sort).skip(begin).limit(size);
        }
        return null;
    }

    public DBObject findOne(String dbName, String tableName, DBObject query, DBObject keys) {
        findMongoDB();
        if (null != this.m) {
            DB db = this.m.getDB(dbName);
            DBCollection tb = db.getCollection(tableName);

            if (keys == null) {
                return tb.findOne(query);
            } else {
                return tb.findOne(query, keys);
            }

        }
        return null;
    }

    public Iterator<DBObject> aggregate(String dbName, String tableName, DBObject firstOp,
            DBObject... additionalOps) {
        findMongoDB();
        if (null != this.m) {
            DB db = this.m.getDB(dbName);
            DBCollection tb = db.getCollection(tableName);
            AggregationOutput out = tb.aggregate(firstOp, additionalOps);
            // if (out.getCommandResult().ok()) {
            return out.results().iterator();
            // }
        }
        return null;
    }

    public DBObject findOne(String dbName, String tableName, DBObject query) {
        return findOne(dbName, tableName, query, null);
    }

    public Mongo getM() {
        findMongoDB();
        return m;
    }

    public void setM(MongoClient m) {
        this.m = m;
    }

    public long getConnectInterval() {
        return connectInterval;
    }

    public void setConnectInterval(long connectInterval) {
        this.connectInterval = connectInterval;
    }

    public int getMaxFind() {
        return maxFind;
    }

    public void setMaxFind(int maxFind) {
        this.maxFind = maxFind;
    }

    public int getFindCount() {
        return findCount;
    }

    public void setFindCount(int findCount) {
        this.findCount = findCount;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public static void main(String[] args) {
        try {
            MongoClient mc = new MongoClient("127.0.0.1");
            DB db = mc.getDB("waitqueue_2013");
            List<String> names = mc.getDatabaseNames();
            for (String s : names) {
                System.out.println(s);
            }
            DBCursor c = db.getCollection("queue").find();
            System.out.println(c.count() + " " + c.numSeen());
            while (c.hasNext()) {
                System.out.println("1" + c.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
