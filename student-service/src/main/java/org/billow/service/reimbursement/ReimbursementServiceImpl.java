package org.billow.service.reimbursement;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;
import org.billow.api.reimbursement.ReimbursementService;
import org.billow.api.workflow.WorkFlowService;
import org.billow.dao.ReimbursementDao;
import org.billow.model.expand.ReimbursementDto;
import org.billow.dao.base.BaseDao;
import org.billow.service.base.BaseServiceImpl;
import org.billow.utils.PageHelper;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private WorkFlowService workFlowService;

    @Resource
    @Override
    public void setBaseDao(BaseDao<ReimbursementDto> baseDao) {
        super.baseDao = this.reimbursementDao;
    }

    @Override
    public PageInfo<ReimbursementDto> findRebList(ReimbursementDto dto) {
        PageHelper.startPage();
        List<ReimbursementDto> list = reimbursementDao.selectAll(dto);
        try {
            if (ToolsUtils.isNotEmpty(list)) {
                workFlowService.findTaskNodeList(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo<ReimbursementDto> page = new PageInfo<>(list);
        return page;
    }
}