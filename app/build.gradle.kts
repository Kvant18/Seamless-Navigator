import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "org.domino.seamless"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.domino.seamless"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        resourceConfigurations.addAll(listOf("ru", "en"))
    }

    buildTypes {
        release {
            val p = Properties()
            p.load(project.rootProject.file("local.properties").reader())
            val yourKey: String = p.getProperty("MAPKIT_API_KEY")
            buildConfigField("String", "MAPKIT_API_KEY", "\"$yourKey\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isDebuggable = false
            isJniDebuggable = false
            isRenderscriptDebuggable = false
        }

        debug {
            val p = Properties()
            p.load(project.rootProject.file("local.properties").reader())
            val yourKey: String = p.getProperty("MAPKIT_API_KEY")
            buildConfigField("String", "MAPKIT_API_KEY", "\"$yourKey\"")
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.maps.mobile)
    implementation(libs.androidx.preference)
    implementation(libs.play.services.location)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.code.scanner)
}

