package org.billow.controller.system;

import org.apache.log4j.Logger;
import org.billow.api.system.SysUploadService;
import org.billow.common.login.LoginHelper;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.SysUploadDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 数据字典控制器<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-09-15 14:12:02
 */
@Controller
@RequestMapping("/sysUploadController")
public class SysUploadController {
    private static final Logger LOGGER = Logger.getLogger(SysUploadController.class);
    @Autowired
    private SysUploadService sysUploadService;

    @RequestMapping("/uploadIndex")
    public ModelAndView uploadIndex() {
        List<SysUploadDto> sysUploadDtos = sysUploadService.selectAll(null);
        ModelAndView av = new ModelAndView();
        av.addObject("page", sysUploadDtos);
        av.setViewName(PagePathCst.BASEPATH_SYSTEM + "uploadIndex");
        return av;
    }

    @RequestMapping(value = "/upload")
    public void upload(@RequestParam(value = "file", required = false) MultipartFile file,
                       @RequestParam("fileType") String fileType, HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        try {
            System.out.println("fileType" + fileType);
            HttpSession session = request.getSession();
            UserDto loginUser = LoginHelper.getLoginUser(session);
            String path = session.getServletContext().getRealPath("upload");
            System.out.println(path);
            String json = sysUploadService.saveUpoad(loginUser, path, file,fileType);

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("error:上传文件失败!");
        }
    }

    @ResponseBody
    @RequestMapping("/deleteFile/{fileName}/{id}")
    public JsonResult deleteFile(@PathVariable("fileName") String fileName, @PathVariable("id") String id,
                                 HttpServletRequest request) {
        HttpSession session = request.getSession();
        String path = session.getServletContext().getRealPath("upload");
        JsonResult json = new JsonResult();
        String type = "";
        String message = "";
        try {
            sysUploadService.deleteFile(path, id, fileName);
            message = MessageTipsCst.DELETE_SUCCESS;
            type = MessageTipsCst.TYPE_SUCCES;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e);
            type = MessageTipsCst.TYPE_ERROR;
            message = MessageTipsCst.DELETE_FAILURE;
        }
        json.setMessage(message);
        json.setType(type);
        return json;
    }
}