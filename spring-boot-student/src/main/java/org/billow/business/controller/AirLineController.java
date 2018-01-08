package org.billow.business.controller;

import org.billow.business.model.AirLine;
import org.billow.business.model.AirLinePK;
import org.billow.business.service.AirLineService;
import org.billow.tools.PageableTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        System.out.println(airLine);
        return airLine;
    }

    @RequestMapping("/findByName")
    public List<AirLine> findByName() {
        String name = "上海-北京";
        return airLineService.findByName(name);
    }

    @RequestMapping("/findByStartcityAndEndcity")
    public List<AirLine> findByStartcityAndEndcity() {
        String startCity = "";
        String endCity = "";
        return airLineService.findByStartCityAndEndCity(startCity, endCity);
    }

    @RequestMapping("/findAll")
    public Page<AirLine> findAll() {
        return airLineService.findAll(PageableTools.basicPage(0, "name"));
    }

    @RequestMapping("/saveAirLine")
    public ResponseEntity saveAirLine() {
        AirLine airLine = new AirLine("guangzhou", "shanghai", "广州-上海");
        airLineService.saveAirLine(airLine);
        return new ResponseEntity<>(true, HttpStatus.OK);
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
    public List<AirLine> complexQueryAirLine1(AirLine airLine) {
        String startCity = "guangzhou";
        AirLinePK airLinePK = new AirLinePK();
        airLinePK.setStartCity(startCity);
        airLine.setId(airLinePK);
        List<AirLine> airLines = airLineService.complexQueryAirLine1(airLine);
        return airLines;
    }

    /**
     * 复杂查询,继承JpaSpecificationExecutor
     *
     * @return
     */
    @RequestMapping("/complexQueryAirLine2")
    public List<AirLine> complexQueryAirLine2(AirLine airLine) {
        String startCity = null;//"guangzhou";
        AirLinePK airLinePK = airLine.getId();
        airLinePK.setStartCity(startCity);
        airLine.setId(airLinePK);
        List<AirLine> airLines = airLineService.complexQueryAirLine2(airLine);
        return airLines;
    }

    @RequestMapping("/updateAirLine")
    public void updateAirLine() {
        String name = "北京-上海";
        airLineService.updateAirLine(name);
    }
}
