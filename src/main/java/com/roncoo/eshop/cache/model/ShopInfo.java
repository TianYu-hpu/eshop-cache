package com.roncoo.eshop.cache.model;

public class ShopInfo {
    private Long id;

    private String name;

    private Integer level;

    private Double goodcommentrate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getGoodcommentrate() {
        return goodcommentrate;
    }

    public void setGoodcommentrate(Double goodcommentrate) {
        this.goodcommentrate = goodcommentrate;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ShopInfo other = (ShopInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getGoodcommentrate() == null ? other.getGoodcommentrate() == null : this.getGoodcommentrate().equals(other.getGoodcommentrate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getGoodcommentrate() == null) ? 0 : getGoodcommentrate().hashCode());
        return result;
    }
}