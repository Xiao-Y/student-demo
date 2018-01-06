package org.billow.business.service.impl;

import com.sun.org.apache.xerces.internal.xs.StringList;
import org.billow.business.dao.AirLineJpaDao;
import org.billow.business.model.AirLine;
import org.billow.business.model.AirLinePK;
import org.billow.business.service.AirLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.Path;
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
    public List<AirLine> complexQueryAirLine2(String startCity) {
//        List<AirLine> airLines = airLineJpaDao.findAll(new Specification<AirLine>() {
//            @Override
//            public Predicate toPredicate(Root<AirLine> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                Path<StringList> startCityPath = root.get("id").get("startCity");
//                query.where(cb.equal(startCityPath, startCity));
//                return null;
//            }
//        });
        List<AirLine> airLines = airLineJpaDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Path<StringList> startCityPath = root.get("id").get("startCity");
//            return criteriaBuilder.and(criteriaBuilder.equal(startCityPath, name));
            criteriaQuery.where(criteriaBuilder.equal(startCityPath, startCity));
            return null;
        });
        return airLines;
    }

    @Override
    @Transactional
    public void updateAirLine(String name) {
        airLineJpaDao.updateAirLine(name);
    }
}
