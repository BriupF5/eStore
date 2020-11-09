package com.briup.bean;

public class Product {
    private Integer id;

    private String img;

    private Integer clickrate;

    private Integer remain;

    private String partpay;

    private Integer pricereduce;

    private Integer categorytwoid;

    private Integer hot;

    private Integer price;

    private String publish;

    private String parameter;

    private String name;

    private Long publishdate;

    private String quit;

    private String free;

    private String wraplist;

    private String introduction;

    private Integer sellnum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getClickrate() {
        return clickrate;
    }

    public void setClickrate(Integer clickrate) {
        this.clickrate = clickrate;
    }

    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }

    public String getPartpay() {
        return partpay;
    }

    public void setPartpay(String partpay) {
        this.partpay = partpay == null ? null : partpay.trim();
    }

    public Integer getPricereduce() {
        return pricereduce;
    }

    public void setPricereduce(Integer pricereduce) {
        this.pricereduce = pricereduce;
    }

    public Integer getCategorytwoid() {
        return categorytwoid;
    }

    public void setCategorytwoid(Integer categorytwoid) {
        this.categorytwoid = categorytwoid;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish == null ? null : publish.trim();
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Long publishdate) {
        this.publishdate = publishdate;
    }

    public String getQuit() {
        return quit;
    }

    public void setQuit(String quit) {
        this.quit = quit == null ? null : quit.trim();
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free == null ? null : free.trim();
    }

    public String getWraplist() {
        return wraplist;
    }

    public void setWraplist(String wraplist) {
        this.wraplist = wraplist == null ? null : wraplist.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Integer getSellnum() {
        return sellnum;
    }

    public void setSellnum(Integer sellnum) {
        this.sellnum = sellnum;
    }
}