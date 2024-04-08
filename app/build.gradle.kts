plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id ("org.greenrobot.greendao")
}

android {
    namespace = "com.lyh.cn.passwordbook"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lyh.cn.passwordbook"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        debug {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            ndk {
                abiFilters.add("arm64-v8a")
                abiFilters.add("x86_64")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    greendao{
        schemaVersion = 1
        daoPackage = "com.lyh.cn.passwordbook.greendao"
        targetGenDir = file("src/main/java")
    }

    buildFeatures {
        viewBinding = true
    }
}


dependencies {

    implementation (libs.appcompat)
    implementation (libs.material)
    implementation (libs.constraintlayout)
    implementation (libs.greendao)
    //权限框架
    implementation (libs.permission)
    //上拉刷新,加头尾
    implementation (libs.brvah)
    //通用的工具类
    implementation (libs.commonutils)
    //fastjson2
    implementation (libs.fastjson2)
    //指纹工具
    implementation (libs.fingerprintutils)
}

tasks.configureEach {
    if (name.matches(Regex("\\w*compile\\w*Kotlin"))){
        dependsOn("greendao")
    }
    if (name.matches(Regex("\\w*kaptGenerateStubs\\w*Kotlin"))) {
        dependsOn("greendao")
    }
    if (name.matches(Regex("\\w*kapt\\w*Kotlin"))) {
        dependsOn("greendao")
    }
}

