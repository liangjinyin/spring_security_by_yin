package com.jin.yin.security.common.bmould.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jin.yin.security.common.utils.DateUtils;
import com.jin.yin.security.models.system.user.entity.User;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: liangjinyin
 * @Date: 2018-08-30
 * @Description:
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseEntity<T> implements Serializable{
    @JsonIgnore
    protected Integer id;
    @JsonIgnore
    protected Integer flag;
    @JsonIgnore
    protected String remarks;
    @JsonIgnore
    protected User createBy;

    protected Date createDate;

    protected User updateBy;

    protected Date updateDate;

    protected String createDateString;

    protected String updateDateString;

    /**
     * 获取创建时间字符串
     * @return yyyy-MM-dd HH:mm:ss
     */
    public String getCreateDateString()
    {
        if (createDate == null)
        {
            createDate = new Date();
        }
        return DateUtils.formatDateTime(createDate);
    }

    /**
     * 获取修改时间字符串
     * @return yyyy-MM-dd HH:mm:ss
     */
    public String getUpdateDateString()
    {
        if (updateDate == null)
        {
            updateDate = new Date();
        }
        return DateUtils.formatDateTime(updateDate);
    }

    @Length(min = 0, max = 255)
    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    @JsonIgnore
    public User getCreateBy()
    {
        return createBy;
    }

    public void setCreateBy(User createBy)
    {
        this.createBy = createBy;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    @JsonIgnore
    public User getUpdateBy()
    {
        return updateBy;
    }

    public void setUpdateBy(User updateBy)
    {
        this.updateBy = updateBy;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
