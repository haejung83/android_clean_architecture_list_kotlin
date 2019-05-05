# [List] Android Clean Architecture Pattern (Presentation: MVP) 
> RxJava와 함께 간단한 Clean Architecture 패턴을 사용한 List 화면 Android 예제 입니다. 



* 사용기술

  * Clean Architecture Pattern
    * [안드로이드에 Clean Architecture 적용하기](https://academy.realm.io/kr/posts/clean-architecture-in-android/)
    * [The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
  * MVP
  * Kotlin

  

* Data Server

  * 간단한 데이터 서버로부터 RESTful API를 사용하여 표시할 정보를 획득 합니다.

  * ```shell
    $ git clone https://github.com/haejung83/server_drone_kotlin.git
    $ cd server_drone_kotlin
    $ ./gradlew bootRun
    `
