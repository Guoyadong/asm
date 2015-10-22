package com.renew.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.renew.config.GlobalSetting;
import lombok.extern.log4j.Log4j;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.renew.updateserver.entity.Version;
import com.renew.updateserver.service.VersionService;
import com.renew.utils.SpringContextHelper;
  
/** 
 *  
 * @author Administrator 
 * 文件上传 
 * 具体步骤： 
 * 1）获得磁盘文件条目工厂 DiskFileItemFactory 要导包 
 * 2） 利用 request 获取 真实路径 ，供临时文件存储，和 最终文件存储 ，这两个存储位置可不同，也可相同 
 * 3）对 DiskFileItemFactory 对象设置一些 属性 
 * 4）高水平的API文件上传处理  ServletFileUpload upload = new ServletFileUpload(factory); 
 * 目的是调用 parseRequest（request）方法  获得 FileItem 集合list ， 
 *      
 * 5）在 FileItem 对象中 获取信息，   遍历， 判断 表单提交过来的信息 是否是 普通文本信息  另做处理 
 * 6） 
 *    第一种. 用第三方 提供的  item.write( new File(path,filename) );  直接写到磁盘上 
 *    第二种. 手动处理   
 * 
 */  
@Log4j
public class FileUpLoad extends HttpServlet {  
  
    private VersionService versionService =  SpringContextHelper.getBean(VersionService.class);
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
       
        request.setCharacterEncoding("utf-8");  //设置编码  
        
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        File project_root = new File(this.getServletContext().getRealPath("/"));
        File download_dir = new File(GlobalSetting.AppUploadDir);
        if(!download_dir.exists())
        {
            download_dir.mkdirs();
            log.info("创建版本存储目录: "+ download_dir.getAbsolutePath());
        }
        
        factory.setRepository(download_dir);  
        factory.setSizeThreshold(1024*1024);
        factory.setFileCleaningTracker(FileCleanerCleanup.getFileCleaningTracker(request.getSession().getServletContext()));
          
        ServletFileUpload upload = new ServletFileUpload(factory);  
        long timestamp =0;  
        String filename ="";
        response.setContentType("text/html; charset=utf-8");
        try {  
            List<FileItem> list = upload.parseRequest(request);
              
            for(FileItem item : list)  
            {  
                String name = item.getFieldName();  
                if(item.isFormField())  
                {               
                    String value = item.getString() ;  
                    request.setAttribute(name, value);  
                }  
                else  
                {  
                    String value = item.getName() ;
                    if(value != "")
                    {
                        int start = value.lastIndexOf("\\");  
                        filename = value.substring(start+1);  
                        timestamp = System.currentTimeMillis();
                        File subDir = new File(download_dir,""+timestamp);
                        subDir.mkdir();
                        File version_file = new File(subDir,filename);
                        if(!version_file.exists())
                        {
                            version_file.createNewFile();
                            log.info("创建版本上传文件："+ version_file.getAbsolutePath());
                        }
                        item.write(version_file);    
                    }else
                    {
                        response.getWriter().write("{\"value\":\"" + timestamp+"/"+filename +"\",\"result\":\"RESULT_SKIP\"}");
                    }
                }  
            } 
            response.getWriter().write("{\"value\":\"" + timestamp+"/"+filename +"\",\"result\":\"RESULT_OK\"}");
        }  
        catch (Exception e) {
            log.error("上传文件”" +filename+"“失败：" +e.getMessage());
            response.getWriter().write("{\"value\":" + e.getMessage() +",\"result\":\"RESULT_ERROR\"}");
        }finally
        {
        }
    }    
}  
