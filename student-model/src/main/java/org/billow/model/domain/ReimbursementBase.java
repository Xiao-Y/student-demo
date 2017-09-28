package org.billow.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.billow.model.base.BaseModel;
import org.billow.model.custom.WorkFlowDto;

/**
 * 报销流程数据库模型<br>
 * <p>
 * 对应的表名：t_reimbursement
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-09-27 17:27:37
 */
public class ReimbursementBase extends WorkFlowDto implements Serializable {

    public ReimbursementBase() {
        super();
    }

    /**
     * 主键构造器
     *
     * @param id
     */
    public ReimbursementBase(Integer id) {
        super();
        this.id = id;
    }

    // 报销金额
    private BigDecimal amount;
    // 申请人Id
    private Integer userId;
    // 申请人名称
    private String userName;
    // 流程实例Id
    private String processInstanceId;
    // 申请时间
    private Date applyTime;
    //
    private Integer id;
    // 创建时间
    private Date creatDate;
    // 更新时间
    private Date updateDate;
    // 状态
    private String status;
    //报销备注
    private String reason;
    //报销类型;1-差旅费,2-招待费,3-车辆费用,4-培训费,5-电话费,6-网络费,7-低值易耗品及备品备件
    private String applyType;

    /**
     * 报销金额
     *
     * @return
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * 报销金额
     *
     * @param amount
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 申请人Id
     *
     * @return
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 申请人Id
     *
     * @param userId
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 申请人名称
     *
     * @return
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 申请人名称
     *
     * @param userName
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 流程实例Id
     *
     * @return
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public String getProcessInstanceId() {
        return this.processInstanceId;
    }

    /**
     * 流程实例Id
     *
     * @param processInstanceId
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    /**
     * 申请时间
     *
     * @return
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public Date getApplyTime() {
        return this.applyTime;
    }

    /**
     * 申请时间
     *
     * @param applyTime
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * @return
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * @param id
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 创建时间
     *
     * @return
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public Date getCreatDate() {
        return this.creatDate;
    }

    /**
     * 创建时间
     *
     * @param creatDate
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    /**
     * 更新时间
     *
     * @return
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public Date getUpdateDate() {
        return this.updateDate;
    }

    /**
     * 更新时间
     *
     * @param updateDate
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 状态
     *
     * @return
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 状态
     *
     * @param status
     * @author billow<br>
     * @date: 2017-09-27 17:27:37
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 报销备注
     *
     * @return
     */
    public String getReason() {
        return reason;
    }

    /**
     * 报销备注
     *
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 报销类型;1-差旅费,2-招待费,3-车辆费用,4-培训费,5-电话费,6-网络费,7-低值易耗品及备品备件
     *
     * @return
     */
    public String getApplyType() {
        return applyType;
    }

    /**
     * 报销类型;1-差旅费,2-招待费,3-车辆费用,4-培训费,5-电话费,6-网络费,7-低值易耗品及备品备件
     *
     * @param applyType
     */
    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    /**
     * 主键toString 非主键不允许添加
     */
    @Override
    public String toString() {
        return "PK[id = " + id + "]";
    }
}  