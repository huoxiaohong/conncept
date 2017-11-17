package com.concept.uploadsg;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.velocity.context.Context;

import ztools.vms.actions.AbsAction;

public class Ap extends AbsAction {

    private static final String PROP_IMGHOST = "cfg.string.z-resmgr.imghost";

    private static final String PROP_IMGBASE = "cfg.string.z-resmgr.imgbase";

    private static final String PROP_DOWNURL_PREFIX = "cfg.string.z-resmgr.download_url_prefix";

    private static final String PROP_UPLOAD_DIR = "cfg.string.z-resmgr.upload_directory";

    private static final String REQ_SCOPE = "scope";

    private static final String REQ_TMPUPLOADID = "tmpUploadId";

    private static final String REQ_TARGETFILEID = "tfId";

    private static final String SLASH = "/";

    private FileHandler fileHandler = new FileHandler();

    private ImageHandler imgHandler = new ImageHandler();

    private static Map<String, UploadFile> uploadSessionMap = new HashMap<>();

    @Override
    public void setContext(HttpServletRequest request, Context context) {

        String scope = request.getParameter(REQ_SCOPE);
        String tmpUploadId = request.getParameter(REQ_TMPUPLOADID);
        String targetFileId = request.getParameter(REQ_TARGETFILEID);

        // wrap httpheader
        // initHeaders(request);

        List<UploadFile> files = new ArrayList<>();
        String contextType = request.getHeader("Content-Type");
        String contentRange = request.getHeader("Content-Range");
        if (null != contextType && contextType.contains("multipart/form-data")) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List<FileItem> fileList = upload.parseRequest(request);
                System.out.println("upload file count: " + fileList.size());
                for (FileItem item : fileList) {

                }

                // context.put("responseText", "{\"files\": "
                // + new JSONArray(files).toString() + "}");
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean saveFile(UploadFile file, InputStream in) {

        // if (file instanceof Picture) {
        // imgHandler.create(file, in);
        // } else {
        // fileHandler.create(file, in);
        // }

        return fileHandler.create(file, in);
    }
}
