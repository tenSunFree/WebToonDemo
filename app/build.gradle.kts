//import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
//    id("org.jlleitschuh.gradle.ktlint")
}

// apply(from = "${rootProject.projectDir}/gradle/commonConfiguration.gradle")

android {

    compileSdkVersion(30)

            // buildToolsVersion ProjectConfigurations.buildTools
//
            // defaultConfig {
            //
            // }

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        applicationId = "com.example.webtoondemo"
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
    }

    // signingConfigs {
    //     getByName(BuildType.DEBUG) {
    //         storeFile = project.rootProject.file("debug.keystore")
    //         storePassword = "android"
    //         keyAlias = "androiddebugkey"
    //         keyPassword = "android"
    //     }
    // }

    buildTypes {
        getByName(BuildType.DEBUG) {
            signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".debug"
        }

        getByName(BuildType.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles(getDefaultProguardFile("proguard-android.txt"))
            proguardFiles(file("proguard-rules.pro"))
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dep.AndroidX.Compose.version
    }

    lintOptions {
        checkOnly("Interoperability")
        disable("ContentDescription")
        isAbortOnError = false
        xmlReport = true
    }

    useLibrary("android.test.mock")
}

dependencies {
    // implementation(project(":core"))
    // implementation(project(":core-android"))
    // implementation(project(":model"))
    // implementation(project(":data"))
    // implementation(project(":domain"))
    // implementation(project(":site"))
    // implementation(project(":compose"))
    // implementation(project(":ui-common"))
    // implementation(project(":ui-weekly"))
    // implementation(project(":ui-episode"))
    // implementation(project(":ui-detail"))
    // implementation(project(":ui-setting"))

    implementation(Dep.Kotlin.stdlibJvm)

    implementation(Dep.AndroidX.activity.ktx)
    implementation(Dep.AndroidX.fragment.ktx)
    implementation(Dep.AndroidX.lifecycle.viewModelKtx)

    // Android UI
    implementation(Dep.AndroidX.UI.material)

    implementation(Dep.AndroidX.coreKtx)
    implementation(Dep.AndroidX.UI.preference)

    // Compose
    implementation(Dep.AndroidX.Compose.activity)
    implementation(Dep.AndroidX.Compose.ui)
    implementation(Dep.AndroidX.Compose.material)
    implementation(Dep.AndroidX.Compose.tooling)
    implementation(Dep.AndroidX.Compose.livedata)
    implementation(Dep.AndroidX.Compose.constraintlayout)
    implementation(Dep.AndroidX.Compose.viewModel)

    // OkHttp
    implementation(Dep.OkHttp.loggingInterceptor)

    // Hilt
    implementation(Dep.Dagger.Hilt.android)
    kapt(Dep.Dagger.Hilt.compiler)

    // kotlin
    implementation(Dep.Kotlin.coroutines.core)
    implementation(Dep.Kotlin.coroutines.android)

    // Jsoup
    implementation(Dep.jsoup)

    implementation(Dep.Accompanist.glide)
    implementation(Dep.Accompanist.insets)
    implementation(Dep.Accompanist.pager)

    implementation(Dep.Accompanist.insets)
    implementation(Dep.Accompanist.glide)

    implementation(Dep.timber)
//    implementation(Dep.leakCanary)

    testImplementation(Dep.Test.junit)
    testImplementation(Dep.Test.assertJ)
    testImplementation(Dep.Test.mockito)
}

kapt {
    useBuildCache = true
}

//ktlint {
//    debug.set(true)
//    verbose.set(true)
//    android.set(true)
//    outputToConsole.set(true)
//    outputColorName.set("RED")
//    ignoreFailures.set(true)
//    reporters {
//        reporter(ReporterType.CHECKSTYLE)
//    }
//    filter {
//        exclude("**/generated/**")
//        include("**/kotlin/**")
//    }
//}
