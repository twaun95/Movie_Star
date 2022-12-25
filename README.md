# Grip_Android_TaewaunKwon

그립컴퍼니 사전과제

# Structure

<pre>
&boxvr;&boxh;&boxh;application
&boxv;    &boxvr;&boxh;&boxh; Logger
&boxv;    &boxur;&boxh;&boxh; Application(Hilt)
&boxv;
&boxvr;&boxh;&boxh;buildSrc(KTS 빌드 버전)
&boxvr;&boxh;&boxh;data
&boxv;    &boxvr;&boxh;&boxh; di (NetworkModule, RepositoryModule)
&boxv;    &boxur;&boxh;&boxh; model (APIKey, Response)
&boxv;    &boxur;&boxh;&boxh; repository (MovieRepositoryImpl 구현체)
&boxv;    &boxur;&boxh;&boxh; service (MovieService Interface)
&boxvr;&boxh;&boxh;domain
&boxv;    &boxvr;&boxh;&boxh; di (UseCaseModule)
&boxv;    &boxvr;&boxh;&boxh; model (MovieEntity, Result)
&boxv;    &boxur;&boxh;&boxh; repository(MovieRepository Interface)
&boxv;    &boxur;&boxh;&boxh; usecase (검색, 다음 페이지)
&boxur;&boxh;&boxh;presentation
    &boxvr;&boxh;&boxh; adapter (RecyclerView, BindingAdapter, ViewPager)
    &boxvr;&boxh;&boxh; base (baseActivity, baseFragment, baseViewModel, baseDialog)
    &boxvr;&boxh;&boxh; dialog
    &boxvr;&boxh;&boxh; model (ViewMode - 검색, 즐겨찾기)
    &boxvr;&boxh;&boxh; view (ToolBar)
    &boxur;&boxh;&boxh; ui
          &boxvr;&boxh;&boxh; main
          &boxv;      &boxvr;&boxh;&boxh; Activity
          &boxv;      &boxur;&boxh;&boxh; ViewModel
          &boxvr;&boxh;&boxh; search(검색)
          &boxv;      &boxvr;&boxh;&boxh; Fragment
          &boxv;      &boxur;&boxh;&boxh; ViewModel
          &boxur;&boxh;&boxh; bookmark(즐겨찾기)
                &boxvr;&boxh;&boxh; Fragment
                &boxur;&boxh;&boxh; ViewModel
</pre>


# Skills
- Kotlin
- CleanArchitecture + MVVM
- KTS 빌드구성
- JetPack(DataBinding, Hilt, LifeCycle)
- Retrofit
- Flow, LiveData
- Coroutine
- Image Glide
- Leak Canary

# Environment
- Android Studio Chipmunk 2021.2.1
- minSdkVersion 20
- targetSdkVersion 30
- Test Device - Galaxy S10e


