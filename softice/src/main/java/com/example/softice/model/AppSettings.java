package com.example.softice.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AppSettings {
    @SerializedName("APP_SETTINGS")
    public APPSETTINGS aPP_SETTINGS;
    @SerializedName("PLACEMENT")
    public PLACEMENT pLACEMENT;
    @SerializedName("MORE_APP")
    public List<MOREAPP> mORE_APP;
    @SerializedName("MORE APP HOME")
    public List<MOREAPPHOME> mOREAPPHOME;
    @SerializedName("MORE APP EXIT")
    public List<MOREAPPEXIT> mOREAPPEXIT;
    @SerializedName("App_Inhouse")
    public List<AppInhouse> app_Inhouse;
    @SerializedName("STATUS")
    public boolean sTATUS;
    @SerializedName("MSG")
    public String mSG;

    public APPSETTINGS getaPP_SETTINGS() {
        return aPP_SETTINGS;
    }

    public PLACEMENT getpLACEMENT() {
        return pLACEMENT;
    }

    public List<MOREAPP> getmORE_APP() {
        return mORE_APP;
    }

    public List<MOREAPPHOME> getmOREAPPHOME() {
        return mOREAPPHOME;
    }

    public List<MOREAPPEXIT> getmOREAPPEXIT() {
        return mOREAPPEXIT;
    }

    public List<AppInhouse> getApp_Inhouse() {
        return app_Inhouse;
    }

    public boolean issTATUS() {
        return sTATUS;
    }

    public String getmSG() {
        return mSG;
    }

    public class AppInhouse implements Serializable {
        public String app_title;
        public String app_desc;
        public String app_icon;
        public String app_header_image;
        public String app_uri;
        public String app_rating;
        public String app_cta_text;
        public String app_price;
        public String app_download;

        public String getApp_title() {
            return app_title;
        }

        public String getApp_desc() {
            return app_desc;
        }

        public String getApp_icon() {
            return app_icon;
        }

        public String getApp_header_image() {
            return app_header_image;
        }

        public String getApp_uri() {
            return app_uri;
        }

        public String getApp_rating() {
            return app_rating;
        }

        public String getApp_cta_text() {
            return app_cta_text;
        }

        public String getApp_price() {
            return app_price;
        }

        public String getApp_download() {
            return app_download;
        }
    }

    public class APPSETTINGS {
        public String id;
        public String appid;
        public String accid;
        public String catid;
        public String appname;
        public String packname;
        public String logo;
        public boolean versionupdatedialog;
        public String versioncode;
        public boolean redirectotherapp;
        public String redirectotherapppackage;
        public boolean moreapp;
        public boolean needinternet;
        public String policylink;
        public String privacypolicy;
        public String appaccountlink;
        public String notificationid;
        public String notificationkey;
        public boolean showadinapp;
        public String alladdformat;
        public boolean howshowaddequence;
        public String appsequence;
        public String alernateAdShow;
        public boolean howshowinterstailadsequence;
        public String adPlatformSequenceInterstitial;
        public String alernateAdShowInterstitial;
        public boolean howshowrewardvideoadsequence;
        public String adPlatformSequenceRewardvideo;
        public String alernateAdShowRewardvideo;
        public boolean howshownativeadsequence;
        public String adPlatformSequenceNativead;
        public String alernateAdShowNativead;
        public boolean showtestad;
        @SerializedName("IntCounter")
        public String intCounter;
        @SerializedName("IntBackCounter")
        public String intBackCounter;
        @SerializedName("AMDClickStatus")
        public boolean aMDClickStatus;
        @SerializedName("AltIntAppOpenStuse")
        public boolean altIntAppOpenStuse;
        public boolean appopenad;
        public boolean adshowingpopup;
        public boolean showappinhouse;
        @SerializedName("Under Maintenance")
        public String underMaintenance;
        @SerializedName("Ad Button Animation")
        public String adbuttonanimation;
        public String getId() {
            return id;
        }

        public String getAppid() {
            return appid;
        }

        public String getAccid() {
            return accid;
        }

        public String getCatid() {
            return catid;
        }

        public String getAppname() {
            return appname;
        }

        public String getPackname() {
            return packname;
        }

        public String getLogo() {
            return logo;
        }

        public boolean isVersionupdatedialog() {
            return versionupdatedialog;
        }

        public String getVersioncode() {
            return versioncode;
        }

        public boolean isRedirectotherapp() {
            return redirectotherapp;
        }

        public String getRedirectotherapppackage() {
            return redirectotherapppackage;
        }

        public boolean isMoreapp() {
            return moreapp;
        }

        public boolean isNeedinternet() {
            return needinternet;
        }

        public String getPolicylink() {
            return policylink;
        }

        public String getPrivacypolicy() {
            return privacypolicy;
        }

        public String getAppaccountlink() {
            return appaccountlink;
        }

        public String getNotificationid() {
            return notificationid;
        }

        public String getNotificationkey() {
            return notificationkey;
        }

        public boolean isShowadinapp() {
            return showadinapp;
        }

        public String getAlladdformat() {
            return alladdformat;
        }

        public boolean isHowshowaddequence() {
            return howshowaddequence;
        }

        public String getAppsequence() {
            return appsequence;
        }

        public String getAlernateAdShow() {
            return alernateAdShow;
        }

        public boolean isHowshowinterstailadsequence() {
            return howshowinterstailadsequence;
        }

        public String getAdPlatformSequenceInterstitial() {
            return adPlatformSequenceInterstitial;
        }

        public String getAlernateAdShowInterstitial() {
            return alernateAdShowInterstitial;
        }

        public boolean isHowshowrewardvideoadsequence() {
            return howshowrewardvideoadsequence;
        }

        public String getAdPlatformSequenceRewardvideo() {
            return adPlatformSequenceRewardvideo;
        }

        public String getAlernateAdShowRewardvideo() {
            return alernateAdShowRewardvideo;
        }

        public boolean isHowshownativeadsequence() {
            return howshownativeadsequence;
        }

        public String getAdPlatformSequenceNativead() {
            return adPlatformSequenceNativead;
        }

        public String getAlernateAdShowNativead() {
            return alernateAdShowNativead;
        }

        public boolean isShowtestad() {
            return showtestad;
        }

        public String getIntCounter() {
            return intCounter;
        }

        public String getIntBackCounter() {
            return intBackCounter;
        }

        public boolean isaMDClickStatus() {
            return aMDClickStatus;
        }

        public boolean isAltIntAppOpenStuse() {
            return altIntAppOpenStuse;
        }

        public boolean isAppopenad() {
            return appopenad;
        }

        public boolean isAdshowingpopup() {
            return adshowingpopup;
        }

        public boolean isShowappinhouse() {
            return showappinhouse;
        }
        public String getUnderMaintenance() {
            return underMaintenance;
        }

        public String getAdbuttonanimation() {
            return adbuttonanimation;
        }
    }

    public class Unity {
        public boolean ad_showAdStatus;
        @SerializedName("AppID")
        public String appID;
        @SerializedName("Interstitial")
        public String interstitial;
        @SerializedName("RewardedVideo")
        public String rewardedVideo;

        public boolean isAd_showAdStatus() {
            return ad_showAdStatus;
        }

        public String getAppID() {
            return appID;
        }

        public String getInterstitial() {
            return interstitial;
        }

        public String getRewardedVideo() {
            return rewardedVideo;
        }
    }

    public class Banner {
        public String id;

        public String getId() {
            return id;
        }
    }

    public class Interstitial {
        public String id;


        public String getId() {
            return id;
        }
    }

    public class Native {
        public String id;

        public String getId() {
            return id;
        }
    }

    public class RewardedVideo {
        public String id;

        public String getId() {
            return id;
        }
    }

    public class MoPub {
        public boolean ad_showAdStatus;
        @SerializedName("Banner")
        public List<Banner> banner;
        @SerializedName("Interstitial")
        public List<Interstitial> interstitial;
        @SerializedName("Native")
        public List<Native> natives;
        @SerializedName("RewardedVideo")
        public List<RewardedVideo> rewardedVideo;

        public boolean isAd_showAdStatus() {
            return ad_showAdStatus;
        }

        public List<Banner> getBanner() {
            return banner;
        }

        public List<Interstitial> getInterstitial() {
            return interstitial;
        }

        public List<Native> getNatives() {
            return natives;
        }

        public List<RewardedVideo> getRewardedVideo() {
            return rewardedVideo;
        }
    }

    public class AppOpen {
        public String id;

        public String getId() {
            return id;
        }
    }

    public class Admob {
        @SerializedName("AppID")
        public String appID;
        @SerializedName("Banner")
        public List<Banner> banner;
        @SerializedName("Interstitial")
        public List<Interstitial> interstitial;
        @SerializedName("Native")
        public List<Native> natives;
        @SerializedName("RewardedVideo")
        public List<RewardedVideo> rewardedVideo;
        @SerializedName("AppOpen")
        public List<AppOpen> appOpen;
        public boolean ad_showAdStatus;

        public String getAppID() {
            return appID;
        }

        public List<Banner> getBanner() {
            return banner;
        }

        public List<Interstitial> getInterstitial() {
            return interstitial;
        }

        public List<Native> getNatives() {
            return natives;
        }

        public List<RewardedVideo> getRewardedVideo() {
            return rewardedVideo;
        }

        public List<AppOpen> getAppOpen() {
            return appOpen;
        }

        public boolean isAd_showAdStatus() {
            return ad_showAdStatus;
        }
    }

    public class PLACEMENT {
        @SerializedName("Unity")
        public Unity unity;
        @SerializedName("MoPub")
        public MoPub moPub;
        @SerializedName("Admob")
        public Admob admob;

        public Unity getUnity() {
            return unity;
        }

        public MoPub getMoPub() {
            return moPub;
        }

        public Admob getAdmob() {
            return admob;
        }
    }

    public class MOREAPP {
        public String appid;
        public String appname;
        public String packname;
        public String logo;

        public String getAppid() {
            return appid;
        }

        public String getAppname() {
            return appname;
        }

        public String getPackname() {
            return packname;
        }

        public String getLogo() {
            return logo;
        }
    }

    public class MOREAPPHOME {
        public String appid;
        public String appname;
        public String packname;
        public String logo;

        public String getAppid() {
            return appid;
        }

        public String getAppname() {
            return appname;
        }

        public String getPackname() {
            return packname;
        }

        public String getLogo() {
            return logo;
        }
    }

    public class MOREAPPEXIT {
        public String appid;
        public String appname;
        public String packname;
        public String logo;

        public String getAppid() {
            return appid;
        }

        public String getAppname() {
            return appname;
        }

        public String getPackname() {
            return packname;
        }

        public String getLogo() {
            return logo;
        }
    }
}


