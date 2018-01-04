package org.billow.service.impl;

import org.billow.dao.AirLineJpaDao;
import org.billow.model.AirLine;
import org.billow.model.AirLinePK;
import org.billow.service.AirLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AirLineServiceImpl implements AirLineService {

    @Autowired
    private AirLineJpaDao airLineJpaDao;

    /**
     * @param username
     * @return
     */
    @Override
    public List<AirLine> findByName(String username) {
        return airLineJpaDao.findByName(username);
    }

    @Override
    public Page<AirLine> findAll(Pageable pageable) {
        return airLineJpaDao.findAll(pageable);
    }

    @Override
    public AirLine findByPK(AirLinePK pk) {
        return airLineJpaDao.findOne(pk);
    }

    @Override
    @Transactional(readOnly = true)
    public void saveAirLine(AirLine airLine) {
        airLineJpaDao.save(airLine);
    }
}
