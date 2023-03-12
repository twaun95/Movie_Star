# Grip_Android_TaewaunKwon

영화 검색

| 1 | 2 | 3 |
|:----------:|:----------:|:----------:|
| <video src="https://user-images.githubusercontent.com/43190509/209478380-64e9d43c-e9e6-426c-b8a0-f8ace050a030.mp4" width="200" height="400" /> | <video src="https://user-images.githubusercontent.com/43190509/209478389-2036b408-5177-4c77-8e9e-bde53efa3ae4.mp4" width="200" height="400"/> | <video src="https://user-images.githubusercontent.com/43190509/209478394-546693bb-2a8d-4828-9b35-4862c0e43d86.mp4" width="200" height="400"/> |

### 구현 목록
- 구성
    - 총 두개(검색, 즐겨찾기)의 하단바로 구성
    - 검색 입력창과 "내 즐겨찾기"를 보여주는 상단바
    - 가운데는 검색 목록 또는 즐겨찾기 목록
    - 앱 첫 진입 시 검색탭으로 시작, 상단바에 검색창, 목록에 "검색 결과가 없습니다."
- 검색 시작
    - 검색 창 클릭시 돋보기 모양으로 설정, 버튼 클릭 시 키보드 내려가며 검색 시작.
    - 한 줄에 영화 두개씩 노출, 상단바 고정
- 검색 결과
    - 영화 아이템(포스터이미지, 즐겨찾기(하트), 영화제목, 연도, 타입) 표시
    - 검색 결과 목록 최하단 내렸을 때 다음 페이지 API 호출
    - 검색 결과 없으면 "검색 결과가 없습니다."
    - 검색 결과 중 즐겨찾기(하트) 클릭하면 팝업(현재 ON/OFF 상태에 따라 달라짐)
- 즐겨찾기
    - 즐겨찾기 목록 확인 ( 검색 결과와 동일 )
    - 하트 클릭시 제거 팝업, 즉시 제거

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


