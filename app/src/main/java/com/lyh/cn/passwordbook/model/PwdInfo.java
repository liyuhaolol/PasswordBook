package com.lyh.cn.passwordbook.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PwdInfo {
   @Id
   private Long id;
   @Property(nameInDb = "SITENAME")
   private String webSiteName;
   @Property(nameInDb = "SITEURL")
   private String webSiteUrl;
   @Property(nameInDb = "EMAIL")
   private String email;
   @Property(nameInDb = "HASPHONE")
   private Boolean hasPhone;
   @Property(nameInDb = "PASSWORD")
   private String password;
   @Property(nameInDb = "REMARKS")
   private String remarks;
   @Generated(hash = 678739742)
   public PwdInfo(Long id, String webSiteName, String webSiteUrl, String email,
           Boolean hasPhone, String password, String remarks) {
       this.id = id;
       this.webSiteName = webSiteName;
       this.webSiteUrl = webSiteUrl;
       this.email = email;
       this.hasPhone = hasPhone;
       this.password = password;
       this.remarks = remarks;
   }
   @Generated(hash = 1050394560)
   public PwdInfo() {
   }
   public Long getId() {
       return this.id;
   }
   public void setId(Long id) {
       this.id = id;
   }
   public String getWebSiteName() {
       return this.webSiteName;
   }
   public void setWebSiteName(String webSiteName) {
       this.webSiteName = webSiteName;
   }
   public String getWebSiteUrl() {
       return this.webSiteUrl;
   }
   public void setWebSiteUrl(String webSiteUrl) {
       this.webSiteUrl = webSiteUrl;
   }
   public String getEmail() {
       return this.email;
   }
   public void setEmail(String email) {
       this.email = email;
   }
   public Boolean getHasPhone() {
       return this.hasPhone;
   }
   public void setHasPhone(Boolean hasPhone) {
       this.hasPhone = hasPhone;
   }
   public String getPassword() {
       return this.password;
   }
   public void setPassword(String password) {
       this.password = password;
   }
   public String getRemarks() {
       return this.remarks;
   }
   public void setRemarks(String remarks) {
       this.remarks = remarks;
   }
}
