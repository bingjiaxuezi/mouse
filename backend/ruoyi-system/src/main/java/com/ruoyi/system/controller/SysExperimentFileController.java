package com.ruoyi.system.controller;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysExperimentFile;
import com.ruoyi.system.service.ISysExperimentFileService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.file.MinioUtils;
import org.springframework.util.StreamUtils;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * 实验文件管理Controller
 * 
 * @author lczj
 * @date 2025-09-08
 */
@Controller
@RequestMapping("/system/SysExperimentFile")
public class SysExperimentFileController extends BaseController
{
    private String prefix = "system/SysExperimentFile";

    @Autowired
    private ISysExperimentFileService sysExperimentFileService;
    
    @Autowired
    private MinioUtils minioUtils;

    @RequiresPermissions("system:SysExperimentFile:view")
    @GetMapping()
    public String SysExperimentFile(@RequestParam(value = "experimentId", required = false) Long experimentId, ModelMap mmap)
    {
        if (experimentId != null) {
            mmap.put("experimentId", experimentId);
        }
        return prefix + "/SysExperimentFile";
    }

    /**
     * 查询实验文件管理列表
     */
    @RequiresPermissions("system:SysExperimentFile:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysExperimentFile sysExperimentFile)
    {
        startPage();
        List<SysExperimentFile> list = sysExperimentFileService.selectSysExperimentFileList(sysExperimentFile);
        return getDataTable(list);
    }

    /**
     * 导出实验文件管理列表
     */
    @RequiresPermissions("system:SysExperimentFile:export")
    @Log(title = "实验文件管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysExperimentFile sysExperimentFile)
    {
        List<SysExperimentFile> list = sysExperimentFileService.selectSysExperimentFileList(sysExperimentFile);
        ExcelUtil<SysExperimentFile> util = new ExcelUtil<SysExperimentFile>(SysExperimentFile.class);
        return util.exportExcel(list, "实验文件管理数据");
    }

    /**
     * 新增实验文件管理
     */
    @RequiresPermissions("system:SysExperimentFile:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存实验文件管理
     */
    @RequiresPermissions("system:SysExperimentFile:add")
    @Log(title = "实验文件管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysExperimentFile sysExperimentFile)
    {
        return toAjax(sysExperimentFileService.insertSysExperimentFile(sysExperimentFile));
    }

    /**
     * 修改实验文件管理
     */
    @RequiresPermissions("system:SysExperimentFile:edit")
    @GetMapping("/edit/{fileId}")
    public String edit(@PathVariable("fileId") Long fileId, ModelMap mmap)
    {
        SysExperimentFile sysExperimentFile = sysExperimentFileService.selectSysExperimentFileByFileId(fileId);
        mmap.put("sysExperimentFile", sysExperimentFile);
        return prefix + "/edit";
    }

    /**
     * 修改保存实验文件管理
     */
    @RequiresPermissions("system:SysExperimentFile:edit")
    @Log(title = "实验文件管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysExperimentFile sysExperimentFile)
    {
        return toAjax(sysExperimentFileService.updateSysExperimentFile(sysExperimentFile));
    }

    /**
     * 删除实验文件管理
     */
    @RequiresPermissions("system:SysExperimentFile:remove")
    @Log(title = "实验文件管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysExperimentFileService.deleteSysExperimentFileByFileIds(ids));
    }

    /**
     * 文件上传接口
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult upload(MultipartFile file, String experimentId)
    {
        return uploadFileInternal(file, experimentId);
    }
    
    /**
     * 文件上传接口 - 兼容前端调用
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file, String experimentId)
    {
        return uploadFileInternal(file, experimentId);
    }
    
    /**
     * 文件上传内部实现
     */
    private AjaxResult uploadFileInternal(MultipartFile file, String experimentId)
    {
        try
        {
            if (file.isEmpty())
            {
                return AjaxResult.error("上传文件不能为空");
            }
            
            if (StringUtils.isEmpty(experimentId))
            {
                return AjaxResult.error("实验ID不能为空");
            }

            // 获取文件信息
            String originalFilename = file.getOriginalFilename();
            String fileExtension = getFileExtension(originalFilename);
            String contentType = file.getContentType();
            
            // 生成存储路径
            String bucketName = "experiment-files";
            String objectName = "experiment_" + experimentId + "/" + System.currentTimeMillis() + "_" + originalFilename;
            
            // 确保存储桶存在
            minioUtils.createBucketIfNotExists(bucketName);
            
            // 使用自定义的文件名上传到MinIO
            String fileUrl = minioUtils.uploadFile(file.getInputStream(), objectName, contentType, bucketName);
            
            // 返回上传结果
            Map<String, Object> result = new HashMap<>();
            result.put("fileName", originalFilename);
            result.put("originalFileName", originalFilename);
            result.put("filePath", fileUrl);
            result.put("bucketName", bucketName);
            result.put("objectName", objectName);
            result.put("fileType", getFileType(fileExtension));
            result.put("fileSize", file.getSize());
            result.put("fileUrl", fileUrl);
            
            return AjaxResult.success("上传成功", result);
        }
        catch (Exception e)
        {
            logger.error("文件上传失败", e);
            return AjaxResult.error("文件上传失败: " + e.getMessage());
        }
    }

    /**
     * 文件预览接口
     */
    @GetMapping("/preview/{id}")
    public void previewFile(@PathVariable Long id, HttpServletResponse response)
    {
        try
        {
            SysExperimentFile file = sysExperimentFileService.selectSysExperimentFileByFileId(id);
            if (file == null)
            {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            
            // 从MinIO获取文件流
            String bucketName = file.getBucketName();
            String objectName = file.getObjectName();
            
            if (StringUtils.isEmpty(bucketName) || StringUtils.isEmpty(objectName))
            {
                // 兼容旧数据：尝试从filePath中解析bucket与object
                String pathUrl = file.getFilePath();
                String[] parsed = parseBucketAndObjectFromUrl(pathUrl);
                if (parsed != null) {
                    bucketName = parsed[0];
                    objectName = parsed[1];
                }
                if (StringUtils.isEmpty(bucketName) || StringUtils.isEmpty(objectName))
                {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
            }
            
            try (InputStream inputStream = minioUtils.getFileStream(objectName, bucketName))
            {
                response.setContentType(getContentType(file.getFileType()));
                String fname = file.getFileOriginalName();
                if (StringUtils.isEmpty(fname) && StringUtils.isNotEmpty(objectName)) {
                    int idx = objectName.lastIndexOf('/') + 1;
                    fname = idx > 0 ? objectName.substring(idx) : objectName;
                }
                String encoded = URLEncoder.encode(fname, "UTF-8").replaceAll("\\+", "%20");
                response.setHeader("Content-Disposition", "inline; filename=\"" + fname + "\"; filename*=UTF-8''" + encoded);
                
                // 将文件流写入响应
                StreamUtils.copy(inputStream, response.getOutputStream());
                response.flushBuffer();
            }
        }
        catch (Exception e)
        {
            logger.error("文件预览失败", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 文件下载接口
     */
    @GetMapping("/download/{id}")
    public void downloadFile(@PathVariable Long id, HttpServletResponse response)
    {
        try
        {
            SysExperimentFile file = sysExperimentFileService.selectSysExperimentFileByFileId(id);
            if (file == null)
            {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            
            // 从MinIO获取文件流
            String bucketName = file.getBucketName();
            String objectName = file.getObjectName();
            
            if (StringUtils.isEmpty(bucketName) || StringUtils.isEmpty(objectName))
            {
                // 兼容旧数据：尝试从filePath中解析bucket与object
                String pathUrl = file.getFilePath();
                String[] parsed = parseBucketAndObjectFromUrl(pathUrl);
                if (parsed != null) {
                    bucketName = parsed[0];
                    objectName = parsed[1];
                }
                if (StringUtils.isEmpty(bucketName) || StringUtils.isEmpty(objectName))
                {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
            }
            
            try (InputStream inputStream = minioUtils.getFileStream(objectName, bucketName))
            {
                response.setContentType("application/octet-stream");
                String fname = file.getFileOriginalName();
                if (StringUtils.isEmpty(fname) && StringUtils.isNotEmpty(objectName)) {
                    int idx = objectName.lastIndexOf('/') + 1;
                    fname = idx > 0 ? objectName.substring(idx) : objectName;
                }
                String encoded = URLEncoder.encode(fname, "UTF-8").replaceAll("\\+", "%20");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fname + "\"; filename*=UTF-8''" + encoded);
                
                // 将文件流写入响应
                StreamUtils.copy(inputStream, response.getOutputStream());
                response.flushBuffer();
            }
        }
        catch (Exception e)
        {
            logger.error("文件下载失败", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    // 解析形如 http(s)://host:port/bucket/object 的URL，提取bucket与object
    private String[] parseBucketAndObjectFromUrl(String url) {
        if (StringUtils.isEmpty(url)) return null;
        try {
            String path = url;
            int schemeIdx = path.indexOf("://");
            if (schemeIdx > -1) {
                path = path.substring(schemeIdx + 3);
                int firstSlash = path.indexOf('/');
                path = firstSlash > -1 ? path.substring(firstSlash + 1) : path;
            }
            // 现在 path 形如 bucket/dir/file.ext
            int slash = path.indexOf('/');
            if (slash == -1) return null;
            String bucket = path.substring(0, slash);
            String object = path.substring(slash + 1);
            if (StringUtils.isEmpty(bucket) || StringUtils.isEmpty(object)) return null;
            return new String[]{bucket, object};
        } catch (Exception ignore) {
            return null;
        }
    }

    /**
     * 删除文件（包括MinIO中的文件）
     */
    @PostMapping("/deleteWithMinio")
    @ResponseBody
    public AjaxResult deleteFileWithMinio(Long id)
    {
        try
        {
            SysExperimentFile file = sysExperimentFileService.selectSysExperimentFileByFileId(id);
            if (file == null)
            {
                return AjaxResult.error("文件不存在");
            }
            
            // 从MinIO删除文件
            String bucketName = file.getBucketName();
            String objectName = file.getObjectName();
            
            if (StringUtils.isNotEmpty(bucketName) && StringUtils.isNotEmpty(objectName))
            {
                try
                {
                    minioUtils.deleteFile(objectName, bucketName);
                    logger.info("成功从MinIO删除文件: {}/{}", bucketName, objectName);
                }
                catch (Exception minioException)
                {
                    logger.warn("从MinIO删除文件失败，但继续删除数据库记录: {}", minioException.getMessage());
                }
            }
            
            // 删除数据库记录
            int result = sysExperimentFileService.deleteSysExperimentFileByFileId(id);
            
            return toAjax(result);
        }
        catch (Exception e)
        {
            logger.error("删除文件失败", e);
            return AjaxResult.error("删除文件失败: " + e.getMessage());
        }
    }

    /**
      * 获取文件扩展名
      */
     private String getFileExtension(String fileName)
     {
         if (StringUtils.isEmpty(fileName))
         {
             return "";
         }
         int lastDotIndex = fileName.lastIndexOf('.');
         if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1)
         {
             return fileName.substring(lastDotIndex + 1).toLowerCase();
         }
         return "";
     }


    /**
     * 根据文件扩展名判断文件类型
     */
    private String getFileType(String extension)
    {
        if (StringUtils.isEmpty(extension))
        {
            return "其他";
        }
        
        extension = extension.toLowerCase();
        if (extension.matches("jpg|jpeg|png|gif|bmp|webp"))
        {
            return "图片";
        }
        else if (extension.matches("mp4|avi|mov|wmv|flv|mkv"))
        {
            return "视频";
        }
        else if (extension.matches("pdf|doc|docx|xls|xlsx|ppt|pptx"))
        {
            return "文档";
        }
        else
        {
            return "其他";
        }
    }

    /**
     * 根据文件类型获取Content-Type
     */
    private String getContentType(String fileType)
    {
        switch (fileType)
        {
            case "图片":
                return "image/*";
            case "视频":
                return "video/*";
            case "文档":
                return "application/pdf";
            default:
                return "application/octet-stream";
        }
    }
}
