plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("com.google.dagger.hilt.android")
	kotlin("kapt")
	id("com.google.devtools.ksp")
}

android {
	namespace = "org.british_information_technologies.compass"
	compileSdk = 34

	defaultConfig {
		applicationId = "org.british_information_technologies.compass"
		minSdk = 29
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		viewBinding = true
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.3"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {
	val roomVersion = "2.6.0"
	val navigationVersion = "2.7.4"
	val hiltVersion = "2.48"

	//	kotlin
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

	// android
	implementation("androidx.core:core-ktx:1.12.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("androidx.activity:activity-compose:1.8.0")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

	// compose
	implementation("androidx.compose.ui:ui")
	implementation("androidx.compose.ui:ui-graphics")
	implementation("androidx.compose.ui:ui-tooling-preview")
	implementation(platform("androidx.compose:compose-bom:2023.03.00"))
	debugImplementation("androidx.compose.ui:ui-tooling")
	debugImplementation("androidx.compose.ui:ui-test-manifest")

	// navigation
	implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")
	implementation("androidx.navigation:navigation-compose:$navigationVersion")

	// material UI
	implementation("com.google.android.material:material:1.10.0")
	implementation("androidx.compose.material3:material3:1.1.2")

	// Hilt
	implementation("com.google.dagger:hilt-android:$hiltVersion")
	kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

	// room
	implementation("androidx.room:room-runtime:$roomVersion")
	annotationProcessor("androidx.room:room-compiler:$roomVersion")
	ksp("androidx.room:room-compiler:$roomVersion")
	implementation("androidx.room:room-ktx:$roomVersion")
	implementation("androidx.room:room-paging:$roomVersion")

	// live data
	implementation("androidx.compose.runtime:runtime-livedata")
	implementation("androidx.lifecycle:lifecycle-livedata-ktx")
}