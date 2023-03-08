package com.example.demo.entry;

public class UserOrder {

    private Integer id;

    private Integer userId;

    private Long money;

    private String details;

    private Boolean isSuccess;

    private Boolean isRefund;

    private String createTime;

    private String modifyTime;

    public UserOrder(Integer userId, Long money, String details) {
        this.userId = userId;
        this.money = money;
        this.details = details;
        this.isSuccess = false;
        this.isRefund = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public Boolean isRefund() {
        return isRefund;
    }

    public void setRefund(Boolean refund) {
        isRefund = refund;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", money=" + money +
                ", details='" + details + '\'' +
                ", isSuccess=" + isSuccess +
                ", isRefund=" + isRefund +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                '}';
    }
}
