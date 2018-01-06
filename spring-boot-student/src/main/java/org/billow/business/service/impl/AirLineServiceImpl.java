package org.billow.business.service.impl;

import org.billow.business.dao.AirLineJpaDao;
import org.billow.business.model.AirLine;
import org.billow.business.model.AirLinePK;
import org.billow.business.service.AirLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class AirLineServiceImpl implements AirLineService {

    @Autowired
    private AirLineJpaDao airLineJpaDao;
    @Autowired
    private EntityManager entityManager;

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
    @Transactional
    public void saveAirLine(AirLine airLine) {
        airLineJpaDao.save(airLine);
    }

    @Override
    public List<AirLine> queryAirLine(String startCity) {
        return airLineJpaDao.queryAirLine(startCity);
    }

    @Override
    public List<AirLine> complexQueryAirLine1(String startCity) {
        String hql = "select t from AirLine t";
        Query query = entityManager.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public List<AirLine> complexQueryAirLine2(String name) {
        List<AirLine> airLines = airLineJpaDao.findAll(new Specification<AirLine>() {
            @Override
            public Predicate toPredicate(Root<AirLine> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> startCityPath = root.get("name");
                query.where(cb.equal(startCityPath, name));
                return null;
            }
        });
        return airLines;
    }
}
