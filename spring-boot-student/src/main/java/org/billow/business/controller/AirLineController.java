package org.billow.business.controller;

import org.billow.model.AirLine;
import org.billow.model.AirLinePK;
import org.billow.service.AirLineService;
import org.billow.tools.PageableTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuyongtao
 * @create 2018-01-04 17:18
 */
@RestController
@RequestMapping("/airLine")
public class AirLineController {

    @Autowired
    private AirLineService airLineService;

    @RequestMapping("/findByPK")
    public AirLine findByPK() {
        AirLine airLine = airLineService.findByPK(new AirLinePK("shanghai", "beijing"));
        AirLinePK airLinePK = airLine.getAirLinePK();
        System.out.println(airLinePK.getStartCity());
        System.out.println(airLinePK.getEndCity());
        return airLine;
    }

    @RequestMapping("/findAll")
    public Page<AirLine> findAll() {
        return airLineService.findAll(PageableTools.basicPage(0, "name"));
    }

    @RequestMapping("/saveAirLine")
    public void saveAirLine() {
        AirLine airLine = new AirLine("guangzhou", "nanjing", "广州-南京");
        airLineService.saveAirLine(airLine);
    }
}
