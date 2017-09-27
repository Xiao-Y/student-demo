package org.billow.service.reimbursement;

import javax.annotation.Resource;

import org.billow.api.reimbursement.ReimbursementService;
import org.billow.dao.ReimbursementDao;
import org.billow.model.expand.ReimbursementDto;
import org.billow.dao.base.BaseDao;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 报销流程实现类<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-09-27 17:27:38
 */
@Service
public class ReimbursementServiceImpl extends BaseServiceImpl<ReimbursementDto> implements ReimbursementService {

    @Resource
    private ReimbursementDao reimbursementDao;

    @Resource
    @Override
    public void setBaseDao(BaseDao<ReimbursementDto> baseDao) {
        super.baseDao = this.reimbursementDao;
    }
}    