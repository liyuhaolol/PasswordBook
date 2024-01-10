package com.lyh.cn.passwordbook.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PwdInfo implements Parcelable {
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
   @Property(nameInDb = "USERNAME")
   private String userName;
   @Generated(hash = 2031684303)
public PwdInfo(Long id, String webSiteName, String webSiteUrl, String email,
        String phone, String password, String remarks, String userName) {
    this.id = id;
    this.webSiteName = webSiteName;
    this.webSiteUrl = webSiteUrl;
    this.email = email;
    this.phone = phone;
    this.password = password;
    this.remarks = remarks;
    this.userName = userName;
}
@Generated(hash = 1050394560)
public PwdInfo() {
}
    protected PwdInfo(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        webSiteName = in.readString();
        webSiteUrl = in.readString();
        email = in.readString();
        phone = in.readString();
        password = in.readString();
        remarks = in.readString();
        userName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(webSiteName);
        dest.writeString(webSiteUrl);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(password);
        dest.writeString(remarks);
        dest.writeString(userName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PwdInfo> CREATOR = new Creator<PwdInfo>() {
        @Override
        public PwdInfo createFromParcel(Parcel in) {
            return new PwdInfo(in);
        }

        @Override
        public PwdInfo[] newArray(int size) {
            return new PwdInfo[size];
        }
    };

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
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
