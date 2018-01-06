package org.billow.controller.activiti;

import com.github.pagehelper.PageInfo;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.apache.log4j.Logger;
import org.billow.api.system.SysUploadService;
import org.billow.api.workflow.WorkFlowService;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.SysUploadDto;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.billow.utils.downLoad.DownLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.InputStream;

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
    @Autowired
    private SysUploadService sysUploadService;

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

    /**
     * 下载部署文件zip
     *
     * @param request
     */
    @RequestMapping("/loadDeployZip/{deployId}")
    public void loadDeployZip(HttpServletRequest request, HttpServletResponse response,
                              @PathVariable("deployId") String deployId) throws Exception {
        try {
            HttpSession session = request.getSession();
            String path = session.getServletContext().getRealPath("upload");
            SysUploadDto dto = new SysUploadDto();
            dto.setId(deployId);
            SysUploadDto sysUploadDto = sysUploadService.selectByPrimaryKey(dto);
            if (sysUploadDto == null) {
                throw new RuntimeException("暂时没有zip文件...");
            }
            String newFileName = sysUploadDto.getNewFileName();
            String fileName = sysUploadDto.getFileName();
            //读取文件
            InputStream in = new FileInputStream(path + "/" + newFileName);
            DownLoad.downLoad(fileName, in, response);
        } catch (Exception e) {
            response.setContentType("text/html;charset=UTF-8");
            response.getOutputStream().write("暂时没有zip文件...".getBytes("UTF-8"));
            e.printStackTrace();
            LOGGER.error("下载部署文件zip失败：deployId=" + deployId, e);
        }
    }

    /**
     * 删除流程部署
     *
     * @param deployId 部署Id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteDeploy/{deployId}")
    public JsonResult deleteDeploy(@PathVariable("deployId") String deployId) {
        JsonResult json = new JsonResult();
        try {
            workFlowService.deleteDeploy(deployId, false);
            json.setType(MessageTipsCst.TYPE_SUCCES);
            json.setMessage(MessageTipsCst.DELETE_SUCCESS);
        } catch (Exception e) {
            json.setType(MessageTipsCst.TYPE_ERROR);
            json.setMessage(MessageTipsCst.DELETE_FAILURE);
            e.printStackTrace();
            LOGGER.error(e);
        }
        return json;
    }

    /**
     * 删除流程部署
     *
     * @param deployId 部署Id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteDeployAll/{deployId}")
    public JsonResult deleteDeployAll(@PathVariable("deployId") String deployId) {
        JsonResult json = new JsonResult();
        try {
            workFlowService.deleteDeploy(deployId, true);
            json.setType(MessageTipsCst.TYPE_SUCCES);
            json.setMessage(MessageTipsCst.DELETE_SUCCESS);
        } catch (Exception e) {
            json.setType(MessageTipsCst.TYPE_ERROR);
            json.setMessage(MessageTipsCst.DELETE_FAILURE);
            e.printStackTrace();
            LOGGER.error(e);
        }
        return json;
    }
}
