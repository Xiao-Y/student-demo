package org.billow.api.reimbursement;

import com.github.pagehelper.PageInfo;
import org.billow.api.base.BaseService;
import org.billow.model.expand.ReimbursementDto;

/**
 * 报销流程接口<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-09-27 17:27:38
 */
public interface ReimbursementService extends BaseService<ReimbursementDto> {


    /**
     * 根据条件查询出报销申请
     *
     * @param dto 查询条件
     * @return 分布数据
     * @date: 2017/9/28 14:47
     * @author:liuyongtao
     */
    PageInfo<ReimbursementDto> findRebList(ReimbursementDto dto);
}