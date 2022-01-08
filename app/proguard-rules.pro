

-keepattributes Signature
-keepattributes Annotation
# Gson specific classes

-dontshrink
-dontoptimize


-dontwarn com.google.firebase.**

-dontwarn androidx.appcompat.**
-dontwarn com.google.android.material.**
-dontwarn androidx.constraintlayout.**
-dontwarn androidx.coordinatorlayout.**
-dontwarn junit.**
-dontwarn androidx.test.ext.**
-dontwarn androidx.test.espresso.**
-dontwarn com.huawei.hms.**
-dontwarn com.qhutch.elevationimageview.**
-dontwarn org.jetbrains.kotlin.**
-dontwarn com.intuit.sdp.**
-dontwarn com.github.HotBitmapGG.**
-dontwarn com.airbnb.android.**
-dontwarn com.github.bumptech.glide.**
-dontwarn com.makeramen.**
-dontwarn com.squareup.retrofit2.**
-dontwarn com.squareup.okhttp3.**
-dontwarn androidx.localbroadcastmanager.**
-dontwarn com.intuit.ssp.**
-dontwarn jp.co.cyberagent.android.**
-dontwarn com.github.Jay-Goo.**
-dontwarn com.android.support.**
-dontwarn io.reactivex.rxjava2.**
-dontwarn com.tbruyelle.rxpermissions2.**
-dontwarn com.github.tntkhang.**
-dontwarn com.auron.**
-dontwarn com.facebook.android.**
-dontwarn com.google.android.gms.**
-dontwarn androidx.cardview.**
-dontwarn com.hedgehog.ratingbar.**
-dontwarn com.onesignal.**


# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

-ignorewarnings
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes SourceFile,LineNumberTable
-keep class com.huawei.hianalytics.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

#-keep class com.mstter.photoeditor.MyApp.**{ *; }
#-keep class com.mstter.photoeditor.provider.AccountProvider.**{ *; }
#
#-keepclassmembernames class com.mstter.photoeditor.provider.AccountProvider


-keepclassmembers class  com.jenuvid.scrnmirroring.model** { <fields>; }
-keepclassmembers class  com.jenuvid.scrnmirroring.Utils** { <fields>; }


-keepclassmembers class  com.example.softice.model** { <fields>; }
-keepclassmembers class  com.example.softice.utils** { <fields>; }
-keepclassmembers class  com.example.softice.ad** { <fields>; }
