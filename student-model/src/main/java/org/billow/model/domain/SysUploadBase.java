package org.billow.model.domain;

import java.io.Serializable;
import java.util.Date;

import org.billow.model.base.BaseModel;

/**
 * 数据字典数据库模型<br>
 * <p>
 * 对应的表名：t_upload_log
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-09-15 14:12:01
 */
public class SysUploadBase extends BaseModel implements Serializable {

    public SysUploadBase() {
        super();
    }

    /**
     * 主键构造器
     *
     * @param id 主键
     */
    public SysUploadBase(String id) {
        super();
        this.id = id;
    }

    // 创建时间
    private Date createTime;
    // 文件名
    private String fileName;
    //新文件名
    private String newFileName;
    // 文件类型
    private String fileType;
    // 主键
    private String id;
    // 创建人
    private String createCode;
    // 文件大小
    private Long fileSize;

    /**
     * 创建时间
     *
     * @return
     * @author billow<br>
     * @date: 2017-09-15 14:12:01
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建时间
     *
     * @param createTime
     * @author billow<br>
     * @date: 2017-09-15 14:12:01
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 文件名
     *
     * @return
     * @author billow<br>
     * @date: 2017-09-15 14:12:01
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * 文件名
     *
     * @param fileName
     * @author billow<br>
     * @date: 2017-09-15 14:12:01
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 文件类型
     *
     * @return
     * @author billow<br>
     * @date: 2017-09-15 14:12:01
     */
    public String getFileType() {
        return this.fileType;
    }

    /**
     * 文件类型
     *
     * @param fileType
     * @author billow<br>
     * @date: 2017-09-15 14:12:01
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * 主键
     *
     * @return
     * @author billow<br>
     * @date: 2017-09-15 14:12:01
     */
    public String getId() {
        return this.id;
    }

    /**
     * 主键
     *
     * @param id
     * @author billow<br>
     * @date: 2017-09-15 14:12:01
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 创建人
     *
     * @return
     * @author billow<br>
     * @date: 2017-09-15 14:12:01
     */
    public String getCreateCode() {
        return this.createCode;
    }

    /**
     * 创建人
     *
     * @param createCode
     * @author billow<br>
     * @date: 2017-09-15 14:12:01
     */
    public void setCreateCode(String createCode) {
        this.createCode = createCode;
    }

    /**
     * 文件大小
     *
     * @return
     * @author billow<br>
     * @date: 2017-09-15 14:12:01
     */
    public Long getFileSize() {
        return this.fileSize;
    }

    /**
     * 文件大小
     *
     * @param fileSize
     * @author billow<br>
     * @date: 2017-09-15 14:12:01
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 新文件名
     * @return
     */
    public String getNewFileName() {
        return newFileName;
    }

    /**
     * 新文件名
     * @param newFileName
     */
    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    /**
     * 主键toString 非主键不允许添加
     */
    @Override
    public String toString() {
        return "PK[id = " + id + "]";
    }
}  