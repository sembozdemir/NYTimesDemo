# NY Times Most Popular

This app shows New York Times Most Popular articles.

<img src="screenshot.png" width="260" />

- MVVM & Architecture Components (ViewModel, LiveData) are used in this project.
- It is written in Kotlin.
- Kotlin Coroutines is used for background operations.
- Dagger2 is used for dependency injection.
- Retrofit is used for networking.
- Timber is used for logging and reporting exceptions easily.
- Coil is used for image loading and caching.

## To run tests:

```
./gradlew test
```

You may view test results at `./app/build/reports/tests/testDevDebugUnitTest/index.html`.


## To run test coverage report:

```
./gradlew jacocoTestReport
```

You may view coverage report at `./app/build/reports/jacoco/jacocoTestReport/html/index.html`.
