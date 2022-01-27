<div id="top"></div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

Simple Demo application written in Kotlin for Android to display information from the <a href="https://data.gov.ie/dataset/luas-forecasting-api/resource/078346e0-fe7f-4e71-9c51-21c78520dc3d">LUAS Forecast API</a>

<p align="right">(<a href="#top">back to top</a>)</p>



### Design Decisions

When creating the application I opted to use `Koin` for the Dependency Injection because of it's simplicity and kotlinized approach. `Koin` reduces the amount of boiler-plate code and allows for easy unit testing.

`Retrofit` was used to get the forecast data and `Tickaroo XML` was used for data extraction.
The reason for using `Tickaroo` over `Retrofit's SimpleXml` is that Tickaroo allows for lists to be built directly from the XML without any separate converters, and it's supported.
I also implemented `OkHTTP3's Logging Interceptor` to allow request/response logging.

For testing and mocking I mostly used `JUnit4` along with the `coroutines test library` to allow me to test my coroutines correctly. I opted for `Mockito-Kotlin` over conventional `Mockito` because of it's Kotlinized approach.

Libraries Used:

```
// Kotlin & Core
implementation "androidx.core:core-ktx:$CORE_KTX_VERSION"
implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$KOTLIN_VERSION"
implementation "androidx.appcompat:appcompat:$APPCOMPAT_VERSION"'

// Android Lifecycle
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_VERSION"
implementation "androidx.lifecycle:lifecycle-livedata-ktx:$LIFECYCLE_VERSION"
kapt "androidx.lifecycle:lifecycle-compiler:$LIFECYCLE_VERSION"

// Material Design
implementation "com.google.android.material:material:$MATERIAL_VERSION"

// Koin
implementation "io.insert-koin:koin-android:$KOIN_VERSION"
testImplementation "io.insert-koin:koin-test:$KOIN_TEST_VERSION"

// Retrofit
implementation "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
implementation "com.squareup.retrofit2:converter-simplexml:$RETROFIT_VERSION"

// OkHTTPLogging Interceptor
implementation "com.squareup.okhttp3:logging-interceptor:$OKHTTP_INT_VERSION"

// Tickaroo
implementation "com.tickaroo.tikxml:annotation:$TICKAROO_VERSION"
implementation "com.tickaroo.tikxml:core:$TICKAROO_VERSION"
kapt "com.tickaroo.tikxml:processor:$TICKAROO_VERSION"
implementation "com.tickaroo.tikxml:converter-htmlescape:$TICKAROO_VERSION"
implementation "com.tickaroo.tikxml:retrofit-converter:$TICKAROO_VERSION"

// Mockito
testImplementation "org.mockito.kotlin:mockito-kotlin:$MOCKITO_VERSION"

// JUnit
testImplementation "junit:junit:$JUNIT_VERSION"
androidTestImplementation "androidx.test.ext:junit:$JUNIT_EXT_VERSION"

// Espresso
androidTestImplementation "androidx.test.espresso:espresso-core:$ESPRESSO_CORE_VERSION"

// Coroutines
testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:$COROUTINES_VERSION"

// Arch Core
testImplementation "androidx.arch.core:core-testing:$ARCH_CORE_VERSION"

// JUnit ktx
testImplementation "androidx.test.ext:junit-ktx:$TEST_KTX_VERSION"
```



<p align="right">(<a href="#top">back to top</a>)</p>

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>
