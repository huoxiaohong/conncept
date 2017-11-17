package com.concept.uploadsg;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Properties;

public class FileHandler {
    public static final String KEY_CUSTOM_FILE_INDEX_DBNAME = "cfg.string.z-resmgr.file_index_dbname";

    public static final String KEY_CUSTOM_FILE_DBNAME = "cfg.string.z-resmgr.file_dbname";

    private static final String KEY_CUSTOM_ENABLE_FILE_INDEX = "cfg.string.z-resmgr.enable_file_index";

    // private DBAccess dbaData = null;
    // private DBAccess dbaIndex = null;
    private boolean isEnableFileIndex = false;

    String service = null;

    public FileHandler() {
        InputStream inputstr = FileHandler.class.getResourceAsStream("/upload.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputstr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        service = properties.getProperty("service");
        final String DB_UPLOAD_FILE = "service";
        // final String DB_UPLOAD_INDEX =
        // Configure.getValueByProp(KEY_CUSTOM_FILE_INDEX_DBNAME);
        // this.dbaData = DBManager.fetchDBAccess(DB_UPLOAD_FILE);
        // if (null == dbaData) {
        // throw new NullPointerException("The dbaData is null!");
        // }

        // String enableFileIndex =
        // Configure.getValueByProp(KEY_CUSTOM_ENABLE_FILE_INDEX);
        // if ("true".equalsIgnoreCase(enableFileIndex)) {
        // this.dbaIndex = DBManager.fetchDBAccess(DB_UPLOAD_INDEX);
        // if (null == dbaIndex) {
        // throw new NullPointerException("The dbaIndex is null!");
        // }
        // isEnableFileIndex = true;
        // }
    }

    public boolean create(UploadFile newFile, InputStream in) {
        boolean ret = false;

        try {
            if (null != in) {
                long count = Files.copy(in, Paths.get(newFile.getAbsolutePath()),
                        StandardCopyOption.REPLACE_EXISTING);
                ret = 0 < count;

            }
            // if (isEnableFileIndex)
            // ret = this.dbaIndex.create(newFile) > 0;

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }

        // 1. 将输入流转换成字节流
        // byte[] bs = getContent(in);
        // 2. 封装一个数据对象（字段有id和byte[]）
        // FileStream stream = new FileStream();
        // stream.setIndexFileId(newFile.getId());

        // 存储一个物理文件
        // FileOutputStream fos = null;
        // try {

        // fos = new FileOutputStream(newFile.getAbsolutePath());
        // fos.write(bs);
        // fos.flush();
        // ret = true;
        // if (isEnableFileIndex)
        // ret = this.dbaIndex.create(newFile) > 0;
        // } catch (Exception e) {
        // e.printStackTrace();
        // } finally {
        // if (null != fos) {
        // try {
        // fos.close();
        // } catch (IOException e) {
        // com.ztools.exceptions.ExceptionPrinter.printStackTrace(e);
        // }
        // }
        // }

        // 3. 将数据对象的id记录到FileStream对象中
        // if (null != bs)
        // stream.setStream(new String(Base64.encode(bs)));
        // 4. 保存数据对象到数据库
        // ret = this.dbaData.create(stream) > 0;
        // if (ret) {
        // 5. 第4步成功的话保存文件描述对象到数据库（建议是索引库）

        // }
        // } else {
        // 6. 第4步不成功的话，记录错误日志
        // }

        // return this.dbaData.create(newFile) > 0;
        return ret;
    }

    public List<UploadFile> retrieve(UploadFile query, int begin, int end, int size) {
        return null;
    }

    public boolean update(UploadFile updateFile) {
        return false;
    }

    public boolean delete(UploadFile delFile) {
        return false;
    }

    public InputStream read(UploadFile readFile) {
        return null;
    }

    private byte[] getContent(InputStream in) {
        if (null == in) {
            return null;
        }
        int l = 1024;
        byte[] bf = new byte[l];
        byte[] rs = null;
        byte[] tm = null;
        int n = 0;
        int c = 0;
        try {
            while (-1 != (n = in.read(bf, 0, l))) {
                c += n;
                tm = rs;
                rs = new byte[c];
                if (null != tm) {
                    System.arraycopy(tm, 0, rs, 0, c - n);
                    System.arraycopy(bf, 0, rs, c - n, n);
                } else {
                    System.arraycopy(bf, 0, rs, 0, n);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }

        }
        return rs;
    }

}
