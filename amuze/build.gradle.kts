import com.android.build.gradle.internal.tasks.factory.dependsOn
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    // `maven-publish`
    alias(libs.plugins.com.vanniktech.maven.publish)
    // signing
    alias(libs.plugins.jetbrains.compose.compiler)
    alias(libs.plugins.gradle.ktlint)
}

val versionName: String = System.getenv("VERSION_NAME") ?: "0.0.1"
val artifactId = "amuze"
val groupId = "com.infbyte"

android {
    namespace = "com.infbyte.amuze"
    version = versionName
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.com.google.ump)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

/* publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.infbyte"
            artifactId = id
            version = versionName
            afterEvaluate {
                from(components["release"])
            }
        }
    }

    repositories {
        maven {
            name = "GithubPackages"
            url = URI.create("https://maven.pkg.github.com/shubertm/Amuze")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }

    }
}*/

mavenPublishing {

    coordinates(groupId, artifactId, versionName)

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)

    signAllPublications()

    pom {
        name.set("Amuze")
        description.set("Common functionality for media apps")
        inceptionYear.set("2024")
        url.set("https://github.com/shubertm/amuze")

        packaging = "aar"

        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/license/mit")
                distribution.set("https://github.com/shubertm/Amuze/blob/main/LICENSE")
            }
        }
        developers {
            developer {
                id.set("shubertm")
                name.set("Shubert Munthali")
                url.set("https://github.com/shubertm/")
            }
        }

        scm {
            url.set("https://github.com/shubertm/amuze")
            connection.set("scm:git:git://github.com/shubertm/amuze.git")
            developerConnection.set("scm:git:ssh://github.com/shubertm/amuze.git")
        }
    }
}

tasks.preBuild.dependsOn("ktlintCheck")
tasks.ktlintCheck.dependsOn("ktlintFormat")
