package org.billow.business.service;

import org.billow.business.model.AirLine;
import org.billow.business.model.AirLinePK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AirLineService {

    List<AirLine> findByName(String name);

    Page<AirLine> findAll(Pageable pageable);

    AirLine findByPK(AirLinePK pk);

    void saveAirLine(AirLine airLine);

    List<AirLine> queryAirLine(String startCity);

    /**
     * 复杂查询,注入EntityManager
     *
     * @param airLine
     * @return
     */
    List<AirLine> complexQueryAirLine1(AirLine airLine);

    /**
     * 复杂查询,继承JpaSpecificationExecutor
     *
     * @param airLine
     * @return
     */
    List<AirLine> complexQueryAirLine2(AirLine airLine);

    void updateAirLine(String name);

    List<AirLine> findByStartCityAndEndCity(String startCity, String endCity);
}
