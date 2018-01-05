package org.billow.business.controller;

import org.billow.business.model.AirLine;
import org.billow.business.model.AirLinePK;
import org.billow.business.service.AirLineService;
import org.billow.tools.PageableTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        AirLine airLine = new AirLine("guangzhou", "beijing", "广州-北京");
        airLineService.saveAirLine(airLine);
    }

    @RequestMapping("/queryAirLine")
    public List<AirLine> queryAirLine() {
        String startCity = "guangzhou";
        List<AirLine> airLines = airLineService.queryAirLine(startCity);
        return airLines;
    }

    /**
     * 复杂查询
     *
     * @return
     */
    @RequestMapping("/complexQueryAirLine1")
    public List<AirLine> complexQueryAirLine1() {
        String startCity = "guangzhou";
        List<AirLine> airLines = airLineService.complexQueryAirLine1(startCity);
        return airLines;
    }

    /**
     * 复杂查询,继承JpaSpecificationExecutor
     *
     * @return
     */
    @RequestMapping("/complexQueryAirLine2")
    public List<AirLine> complexQueryAirLine2() {
        String startCity = "guangzhou";
        List<AirLine> airLines = airLineService.complexQueryAirLine2(startCity);
        return airLines;
    }
}
