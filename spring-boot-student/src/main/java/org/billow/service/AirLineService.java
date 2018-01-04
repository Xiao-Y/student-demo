package org.billow.service;

import org.billow.model.AirLine;
import org.billow.model.AirLinePK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AirLineService {

    List<AirLine> findByName(String username);

    Page<AirLine> findAll(Pageable pageable);

    AirLine findByPK(AirLinePK pk);

    void saveAirLine(AirLine airLine);
}
