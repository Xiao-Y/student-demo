package org.billow.business.dao;

import org.billow.business.model.AirLine;
import org.billow.business.model.AirLinePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirLineJpaDao extends JpaRepository<AirLine, AirLinePK>, JpaSpecificationExecutor<AirLine> {

    List<AirLine> findByName(String name);

    @Query("select t from AirLine t where t.id.startCity = ?")
    List<AirLine> queryAirLine(String startCity);

    @Modifying
    @Query("update AirLine t set t.name = ? where t.id.startCity = 'beijing' and t.id.endCity = 'shanghai'")
    void updateAirLine(String name);

    @Query("select t from AirLine t where t.id.startCity = ? and t.id.endCity = ?")
    List<AirLine> findByStartCityAndEndCity(String startCity, String endCity);
}
