package com.mmd.pms.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有实体类的超类
 */
public class BaseEntity implements Serializable{

    private static final long serialVersionUID = 5385418073421356566L;

    /**
     * 删除标记 - 正常
     */
    public static final String DEL_FLAG_NORMAL = "0";

    /**
     * 删除标记 - 删除
     */
    public static final String DEL_FLAG_DELETE = "1";


    //alt + insert
    protected Integer id;  //主键，唯一标识符
    //protected User createBy; //创建者
    //protected User updateBy; //更新者
    protected Date createDate; //创建时间
    protected Date updateDate; //更新时间
    protected String delFlag = DEL_FLAG_NORMAL; //删除标志 （0 正常， 1 删除）
    @Length(max = 100, message = "备注信息不能超过100个字符")
    protected String remarks; //备注

    //protected Map<String, String> dataScopeFilter; //数据过滤

    /**
     * 执行新增操作之前需要执行的步骤
     * 在Service类里，调用mapper接口的insert方法以前主动调用

    public void preInsert () {
        User user = UserUtils.getCurrentUser();
        if (user != null) {
            this.createBy = user;
            this.updateBy = user;
        }

        Date now = Calendar.getInstance().getTime();
        this.setCreateDate(now);
        this.setUpdateDate(now);
    }*/

    /**
     * 执行修改操作之前需要执行的步骤
     * 在Service类里，调用mapper接口的update方法以前主动调用

    public void preUpadate () {
        User user = UserUtils.getCurrentUser();
        if (user != null) {
            this.updateBy = user;
        }

        Date now = Calendar.getInstance().getTime();
        this.setUpdateDate(now);
    } */

    public BaseEntity() {
    }

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /*
    @JsonIgnore
    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    @JsonIgnore
    public User getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(User updateBy) {
        this.updateBy = updateBy;
    }*/

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    //在生成json字符串时，格式化属性
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    @JsonIgnore //在生成json字符串时，忽略当前属性
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
