package com.binea.cms.model;

/**
 * Created by binea
 * Date: 30/11/2017
 * TIME: 10:51 PM
 */
public class CmsTag {
    private Integer tagId;

    private String name;

    private String description;

    private String icon;

    private Byte type;

    private String alias;

    private Long ctime;

    private Long orders;

    private static final long serialVersionUID = 1L;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "CmsTag{" +
                "tagId=" + tagId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", type=" + type +
                ", alias='" + alias + '\'' +
                ", ctime=" + ctime +
                ", orders=" + orders +
                '}';
    }
}
