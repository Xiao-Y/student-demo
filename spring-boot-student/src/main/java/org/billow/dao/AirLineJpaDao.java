package org.billow.dao;

import org.billow.model.AirLine;
import org.billow.model.AirLinePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirLineJpaDao extends JpaRepository<AirLine, AirLinePK> {

    List<AirLine> findByName(String name);
}
