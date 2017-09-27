package org.billow.controller.activiti.reimbursement;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 报销流程控制器<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-09-27 17:06:48
 */
@Controller
@RequestMapping("/reimbursementController")
public class ReimbursementController {

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView av = new ModelAndView();
        return av;
    }
}