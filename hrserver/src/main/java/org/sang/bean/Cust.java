package org.sang.bean;

import java.util.Date;
import java.util.List;

public class Cust {
  
  private List<String> ids;
  
    /**
     * 主键
     */
    private String id;
    /**
     * 省份
     */
    private String provience;
    /**
     * 公司名称
     */
    private String company;
    /**
     * 姓名
     */
    private String nm;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 座机号
     */
    private String tele;
    /**
     * 职位
     */
    private String position;
    /**
     * 是否已导出
     */
    private String isExport;
    /**
     * 是否已删除
     */
    private String isDel;
    /**
     * 创建时间
     */
    private Date dtCreated;
    /**
     * 修改时间
     */
    private Date dtUpdated;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 修改人
     */
    private String updatedBy;
    /**
     * 版本
     */
    private int version;
    
    public List<String> getIds() {
      return ids;
    }
    public void setIds(List<String> ids) {
      this.ids = ids;
    }
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
    public String getProvience() {
      return provience;
    }
    public void setProvience(String provience) {
      this.provience = provience;
    }
    public String getCompany() {
      return company;
    }
    public void setCompany(String company) {
      this.company = company;
    }
    public String getNm() {
      return nm;
    }
    public void setNm(String nm) {
      this.nm = nm;
    }
    public String getMobile() {
      return mobile;
    }
    public void setMobile(String mobile) {
      this.mobile = mobile;
    }
    public String getTele() {
      return tele;
    }
    public void setTele(String tele) {
      this.tele = tele;
    }
    public String getPosition() {
      return position;
    }
    public void setPosition(String position) {
      this.position = position;
    }
    public String getIsExport() {
      return isExport;
    }
    public void setIsExport(String isExport) {
      this.isExport = isExport;
    }
    public String getIsDel() {
      return isDel;
    }
    public void setIsDel(String isDel) {
      this.isDel = isDel;
    }
    public Date getDtCreated() {
      return dtCreated;
    }
    public void setDtCreated(Date dtCreated) {
      this.dtCreated = dtCreated;
    }
    public Date getDtUpdated() {
      return dtUpdated;
    }
    public void setDtUpdated(Date dtUpdated) {
      this.dtUpdated = dtUpdated;
    }
    public String getCreatedBy() {
      return createdBy;
    }
    public void setCreatedBy(String createdBy) {
      this.createdBy = createdBy;
    }
    public String getUpdatedBy() {
      return updatedBy;
    }
    public void setUpdatedBy(String updatedBy) {
      this.updatedBy = updatedBy;
    }
    public int getVersion() {
      return version;
    }
    public void setVersion(int version) {
      this.version = version;
    }
    
    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Cust [id=");
      builder.append(id);
      builder.append(", provience=");
      builder.append(provience);
      builder.append(", company=");
      builder.append(company);
      builder.append(", nm=");
      builder.append(nm);
      builder.append(", mobile=");
      builder.append(mobile);
      builder.append(", tele=");
      builder.append(tele);
      builder.append(", position=");
      builder.append(position);
      builder.append(", isExport=");
      builder.append(isExport);
      builder.append(", isDel=");
      builder.append(isDel);
      builder.append(", dtCreated=");
      builder.append(dtCreated);
      builder.append(", dtUpdated=");
      builder.append(dtUpdated);
      builder.append(", createdBy=");
      builder.append(createdBy);
      builder.append(", updatedBy=");
      builder.append(updatedBy);
      builder.append(", version=");
      builder.append(version);
      builder.append("]");
      return builder.toString();
    }

}