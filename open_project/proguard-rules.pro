# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/mlogan/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keep class io.realm.annotations.RealmModule
-keep @io.realm.annotations.RealmModule class *
-keep class io.realm.internal.Keep
-keep @io.realm.internal.Keep class * { *; }
-dontwarn javax.**
-dontwarn io.realm.**
-dontwarn okio.**
-keepattributes *Annotation*,Signature
-dontwarn com.nhaarman.listviewanimations.**
-dontwarn com.akexorcist.**

-keep class dagger.** { *; }
-dontwarn dagger.internal.codegen.**

   #compile 'com.akexorcist:RoundCornerProgressBar:2.0.3'



# retrofit
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**
-dontwarn rx.**
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

# Aquery
-dontwarn com.androidquery.auth.**
-dontwarn oauth.**
-dontwarn com.android.auth.TwitterHandle.**
-keep class oauth.** { *; }
-keep class com.android.auth.TwitterHandle.** { *; }

-keep class com.android.packageinstaller.InstallFlowAnalytics.** { *; }
-dontwarn com.android.packageinstaller.InstallFlowAnalytics.**
-keep public class com.android.packageinstaller.InstallFlowAnalytics { *; }

#Butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }

# Dagger
-keep class dagger.** { *; }
-keepclassmembers,allowobfuscation class * {
    @javax.inject.* *;
    @dagger.* *;
    <init>();
}
-keep class **$$ModuleAdapter
-keep class **$$InjectAdapter
-keep class **$$StaticInjection
-keep class javax.inject.** { *; }
-dontwarn dagger.internal.codegen.**
-dontwarn dagger.producers.internal.**
-dontwarn dagger.shaded.auto.common.**
-keep class dagger.* { *; }
-keep class javax.inject.* { *; }
-keep class * extends dagger.internal.Binding
-keep class * extends dagger.internal.ModuleAdapter
-keep class * extends dagger.internal.StaticInjection

#-keepattributes *Annotation*
#-keepattributes *Provides*
#-keepattributes *Singleton*

#-dontwarn dagger.internal.codegen.**
#-keep class * extends dagger.internal.Binding
#-keep class * extends dagger.internal.ModuleAdapter
#-keep class * extends dagger.internal.StaticInjection
#-keep class **$$ModuleAdapter
#-keep class **$$InjectAdapter
#-keep class **$$StaticInjection
#-keep class javax.inject.** { *; }
#-keep class dagger.** { *; }
#-keepclassmembers,allowobfuscation class * {
#    @javax.inject.* *;
#    @dagger.* *;
#    <init>();
#}

-keepnames class ** { *; }

-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose