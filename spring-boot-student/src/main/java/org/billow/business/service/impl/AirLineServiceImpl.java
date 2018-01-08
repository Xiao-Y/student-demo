package org.billow.business.service.impl;

import com.sun.org.apache.xerces.internal.xs.StringList;
import org.billow.business.dao.AirLineJpaDao;
import org.billow.business.model.AirLine;
import org.billow.business.model.AirLinePK;
import org.billow.business.model.extended.Param;
import org.billow.business.service.AirLineService;
import org.billow.tools.HQLTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class AirLineServiceImpl implements AirLineService {

    @Autowired
    private AirLineJpaDao airLineJpaDao;
    @Autowired
    private EntityManager entityManager;


    /**
     * @param name
     * @return
     */
    @Override
    public List<AirLine> findByName(String name) {
        return airLineJpaDao.findByName(name);
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
    public List<AirLine> complexQueryAirLine1(AirLine airLine) {
        String hql = "select t from AirLine t where 1=1";
        List<Param> params = new ArrayList<>();
        String whereHql = HQLTools.addPreparedConditions(airLine, params);
        hql += whereHql;
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i + 1, params.get(i).getValue());
        }
        return query.getResultList();
    }

    @Override
    public List<AirLine> complexQueryAirLine2(AirLine airLine) {
        AirLinePK airLinePK = airLine.getId();
        String startCity = airLinePK.getStartCity();
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

    @Override
    public List<AirLine> findByStartCityAndEndCity(String startCity, String endCity) {
        return airLineJpaDao.findByStartCityAndEndCity(startCity, endCity);
    }
}
