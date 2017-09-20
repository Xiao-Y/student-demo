package org.billow.service.system;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.apache.log4j.Logger;
import org.billow.api.system.SysUploadService;
import org.billow.dao.SysUploadDao;
import org.billow.model.expand.SysUploadDto;
import org.billow.dao.base.BaseDao;
import org.billow.model.expand.UserDto;
import org.billow.service.base.BaseServiceImpl;
import org.billow.utils.StringUtils;
import org.billow.utils.ToolsUtils;
import org.billow.utils.date.DateTime;
import org.billow.utils.generator.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * 数据字典实现类<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-09-15 14:12:02
 */
@Service
public class SysUploadServiceImpl extends BaseServiceImpl<SysUploadDto> implements SysUploadService {
    private static final Logger LOGGER = Logger.getLogger(SysUploadService.class);
    @Resource
    private SysUploadDao sysUploadDao;

    @Autowired(required = false)
    private RepositoryService repositoryService;

    @Resource
    @Override
    public void setBaseDao(BaseDao<SysUploadDto> baseDao) {
        super.baseDao = this.sysUploadDao;
    }

    @Override
    public String saveUpoad(UserDto loginUser, String path, MultipartFile file, String fileType) throws IOException {
        String id = UUID.generate();
        String originalFilename = file.getOriginalFilename();
        Deployment deploy = null;
        if (SysUploadService.FILE_TYPE_WORKFLOW_ZIP.equals(fileType)) {
            InputStream in = file.getInputStream();
            ZipInputStream zipInputStream = new ZipInputStream(in);
            // 创建发布配置对象
            DeploymentBuilder builder = repositoryService.createDeployment();
            // 设置发布信息
            builder.name(originalFilename)// 添加部署规则的显示别名
                    .addZipInputStream(zipInputStream);
            deploy = builder.deploy();
        }
        if(deploy != null){
            id = deploy.getId();
        }
        String suff = getFileSuff(originalFilename);
        String newFileName = id + suff;
        File targetFile = new File(path, newFileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        file.transferTo(targetFile);

        SysUploadDto dto = new SysUploadDto();
        dto.setId(id);
        dto.setFileName(originalFilename);
        dto.setFileType(file.getContentType());
        dto.setFileSize(file.getSize());
        dto.setNewFileName(newFileName);
        dto.setCreateTime(new Date());
        dto.setCreateCode(loginUser.getUserName());
        sysUploadDao.insert(dto);
        return this.returnResult(dto);
    }

    @Override
    public void deleteFile(String path, String id, String fileName) throws IOException {
        SysUploadDto dto = new SysUploadDto();
        dto.setId(id);
        sysUploadDao.deleteByPrimaryKey(dto);
        String suff = getFileSuff(fileName);
        File targetFile = new File(path, id + suff);
        if (!targetFile.exists()) {
            LOGGER.error("文件：" + id + "不存在！");
        } else {
            targetFile.delete();
        }
    }

    @Override
    public List<SysUploadDto> selectAll(SysUploadDto sysUploadDto) {
        List<SysUploadDto> list = super.selectAll(sysUploadDto);
        if (ToolsUtils.isNotEmpty(list)) {
            for (SysUploadDto d : list) {
                d.setFileSizeF(this.fileBigDecimalSize(d.getFileSize()));
            }
        }
        return list;
    }

    /**
     * 获取文件后缀名
     *
     * @param originalFilename
     * @return
     */
    private String getFileSuff(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
    }

    /**
     * 返回的操作的结果
     *
     * @param dto
     * @return
     */
    private String returnResult(SysUploadDto dto) {
        String id = dto.getId();
        String fileName = dto.getFileName();
        String fileSize = fileBigDecimalSize(dto.getFileSize());
        String fileType = dto.getFileType();
        String createCode = dto.getCreateCode();
        String createTime = new DateTime(dto.getCreateTime(), DateTime.YEAR_TO_SECOND).toString();
        return "success:" + "," + id + "," + fileName + "," + fileSize + "," + fileType
                + "," + createCode + "," + createTime;
    }

    /**
     * 计算文件单位的转化
     *
     * @param fileLength 文件大小，单位：B
     * @return
     */
    private String fileBigDecimalSize(Long fileLength) {
        String fileSize = "0B";
        BigDecimal temp = new BigDecimal(1024);
        BigDecimal size = new BigDecimal(fileLength);
        if (size.compareTo(temp) >= 0) {
            size = size.divide(temp, 2, BigDecimal.ROUND_HALF_UP);
            fileSize = size + "KB";
        } else {
            fileSize = size + "B";
        }
        if (size.compareTo(temp) >= 0) {
            size = size.divide(temp, 2, BigDecimal.ROUND_HALF_UP);
            fileSize = size + "M";
        }
        if (size.compareTo(temp) >= 0) {
            size = size.divide(temp, 2, BigDecimal.ROUND_HALF_UP);
            fileSize = size + "G";
        }
        return fileSize;
    }
}