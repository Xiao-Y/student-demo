package org.billow.controller.activiti;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.apache.log4j.Logger;
import org.billow.api.workflow.WorkFlowService;
import org.billow.model.custom.JsonResult;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * 流程部署
 *
 * @author liuyongtao
 * @date 2017年9月9日 下午12:34:13
 */
@Controller
@RequestMapping("/sysActDeploy")
public class SysActDeployController {

    private static final Logger LOGGER = Logger.getLogger(SysActDeployController.class);

    @Autowired
    private WorkFlowService workFlowService;

    @Autowired(required = false)
    private RepositoryService repositoryService;

    /**
     * 查询流程部署列表
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param request
     * @return
     * @date 2017年9月9日 下午12:36:07
     */
    @RequestMapping("/queryDeployList")
    public ModelAndView queryDeployList(HttpServletRequest request) {
        PageInfo<Deployment> pages = workFlowService.queryDeployList();
        ModelAndView av = new ModelAndView();
        av.addObject("page", pages);
        av.setViewName(PagePathCst.BASEPATH_ACTIVITI_DEPLOY + "/actDeploy");
        return av;
    }

    /**
     * 打开文件上传页面
     *
     * @return
     */
    @RequestMapping("/actFileDeploy")
    public String actFileDeploy() {
        return PagePathCst.BASEPATH_ACTIVITI_DEPLOY + "/actFileDeploy";
    }

    @ResponseBody
    @RequestMapping("/saveFileDeploy")
    public JsonResult saveFiledeploy(@RequestParam("zipFile") MultipartFile zipFile, @RequestParam("deployName") String deployName) {
        JsonResult json = new JsonResult();
        try {
            InputStream in = zipFile.getInputStream();
            ZipInputStream zipInputStream = new ZipInputStream(in);
            // 创建发布配置对象
            DeploymentBuilder builder = repositoryService.createDeployment();
            // 设置发布信息
            builder.name(deployName)// 添加部署规则的显示别名
                    .addZipInputStream(zipInputStream);
            json.setSuccess(true);
            json.setMessage(MessageTipsCst.UPLOAD_SUCCESS);
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMessage(MessageTipsCst.UPLOAD_FAILURE);
            e.printStackTrace();
            LOGGER.error(e);
        }
        return json;
    }
}
