package com.hand.hjmall.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Description:    秒杀功能传递参数
 * @CreateDate:     2019/2/27 22:14
 * @CreateUser:      吴童
 * @Version:        1.0
 */
@ApiModel(value = "SmsFlashPromotionDto")
public class SmsFlashPromotionDto {

    @ApiModelProperty(value = "活动名称",required = true)
    private String title;

    @ApiModelProperty(value = "活动开始日期",required = true)
    private Date startDate;

    @ApiModelProperty(value = "活动结束日期",required = true)
    private Date endDate;

    @ApiModelProperty(value = "活动上下线状态",required = true)
    private Integer status;

    @ApiModelProperty(value = "活动秒杀时间段名称",required = false)
    private Date createTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SmsFlashPromotionDto{" +
                "title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
