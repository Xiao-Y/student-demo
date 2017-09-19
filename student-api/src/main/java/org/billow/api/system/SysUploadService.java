package org.billow.api.system;

import org.billow.api.base.BaseService;
import org.billow.model.expand.SysUploadDto;
import org.billow.model.expand.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 数据字典接口<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-09-15 14:12:02
 */
public interface SysUploadService extends BaseService<SysUploadDto> {

    /**
     * 文件类型：file-共享文件，workflowZip-工作流程文件
     */
    public final static String FILE_TYPE_FILE = "file";
    /**
     * 文件类型：file-共享文件，workflowZip-工作流程文件
     */
    public final static String FILE_TYPE_WORKFLOW_ZIP = "workflowZip";


    /**
     * 保存上传文件信息
     *
     * @param loginUser 创建人
     * @param path      文件路径
     * @param file      上传的文件
     * @param fileType  文件类型：file-共享文件，workflowZip-工作流程文件
     */
    String saveUpoad(UserDto loginUser, String path, MultipartFile file, String fileType) throws IOException;

    /**
     * 删除文件
     *
     * @param path     文件路径
     * @param id       文件id
     * @param fileName 文件名称
     */
    void deleteFile(String path, String id, String fileName) throws IOException;
}