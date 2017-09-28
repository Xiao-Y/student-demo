package org.billow.controller.activiti.apply.reimbursement;


import com.github.pagehelper.PageInfo;
import org.billow.api.reimbursement.ReimbursementService;
import org.billow.common.login.LoginHelper;
import org.billow.model.expand.LeaveDto;
import org.billow.model.expand.ReimbursementDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.constant.ActivitiCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 报销流程控制器<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-09-27 17:06:48
 */
@Controller
@RequestMapping("/applyReb")
public class ApplyRebController {

    public final static String processDefinitionKey = ActivitiCst.PROCESSDEFINITION_KEY_REB;

    @Autowired
    private ReimbursementService reimbursementService;

    /**
     * 查询报销申请列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/findRebList")
    public ModelAndView findRebList(HttpServletRequest request, HttpServletResponse response) {
        UserDto loginUser = LoginHelper.getLoginUser(request.getSession());
        ReimbursementDto dto = new ReimbursementDto();
        dto.setUserName(loginUser.getUserName());
        PageInfo<ReimbursementDto> page = reimbursementService.findRebList(dto);
        ModelAndView av = new ModelAndView();
        av.addObject("page", page);
        av.setViewName(PagePathCst.BASEPATH_ACTIVITI_APPLY + "reimbursement/rebApplyList");
        return av;
    }

    /**
     * 报销申请页面
     *
     * @param dto
     * @return
     */
    @RequestMapping("/editReb")
    public ModelAndView editReb(ReimbursementDto dto) {
        dto.setProcessDefinitionKey(processDefinitionKey);
        ModelAndView av = new ModelAndView();
        av.setViewName(PagePathCst.BASEPATH_ACTIVITI_APPLY + "reimbursement/rebApply");
        av.addObject("rebDto", dto);
        return av;
    }
}