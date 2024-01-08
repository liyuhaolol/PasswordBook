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
   @Property(nameInDb = "PHONE")
   private String phone;
   @Property(nameInDb = "PASSWORD")
   private String password;
   @Property(nameInDb = "REMARKS")
   private String remarks;
   @Generated(hash = 794108925)
public PwdInfo(Long id, String webSiteName, String webSiteUrl, String email,
        String phone, String password, String remarks) {
    this.id = id;
    this.webSiteName = webSiteName;
    this.webSiteUrl = webSiteUrl;
    this.email = email;
    this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
