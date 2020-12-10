## :fire: 1주차 세미나: View와 ViewGroup
:zap: 세미나 일자: 2020/11/21 </br>
:zap: 과제 완료일: 2020/12/10 </br>
</br>

![POSTMAN](https://user-images.githubusercontent.com/47806943/101796781-a4995e80-3b4c-11eb-908a-542f5d65fa86.png)
![무제 001](https://user-images.githubusercontent.com/47806943/101801845-20e27080-3b52-11eb-9e5d-b65df37203d6.jpeg)
</br>
 
#### :heavy_check_mark: 필수 과제: 로그인/회원가입 서버 통신 구현
* 로그인/회원가입 서버 통신 구현하기
* `Retrofit interface`와 구현체, `Request/Response` 객체에 대한 코드 필수
<br>

`필수 과제 구현 코드 - SoptRetrofitObject.kt`
```kotlin
object SoptRetrofitObject {

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_SOPT_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val SOPT_USER_API : SoptUserApi = retrofit.create(
        SoptUserApi::class.java)
}
```
<br>

`필수 과제 구현 코드 - SoptUserApi.kt`
```kotlin
interface SoptUserApi {

    @Headers("Content-Type: application/json")
    @POST("/users/signin")
    fun postSignIn(
        @Body body: RequestSignIn
    ): Call<ResponseUserData>

    @POST("/users/signup")
    fun postSignUp(
        @Body body: RequestSignUp
    ): Call<ResponseUserData>
}
```
<br>

`필수 과제 구현 코드 - RequestSignIn.kt`
```kotlin
data class RequestSignIn(
    val email: String,
    val password: String
)
```
<br>

`필수 과제 구현 코드 -  RequestSignUp.kt`
```kotlin
data class RequestSignUp(
    val email: String,
    val password: String,
    val userName: String
)
```
<br>

`필수 과제 구현 코드 -  RequestSignUp.kt`
```kotlin
data class ResponseUserData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val userName: String,
        val email: String,
        val password: String
    )
}
```
<br>

`필수 과제 구현 코드 -  SignInActivity.kt`
```kotlin
private fun signInUserToServer(email: String, password: String) {
    val call: Call<ResponseUserData> =
        SoptRetrofitObject.SOPT_USER_API.postSignIn(RequestSignIn(email, password))

    call.enqueue(object : Callback<ResponseUserData> {
        override fun onFailure(call: Call<ResponseUserData>, t: Throwable) {
            Log.d("TAG", t.localizedMessage!!.toString())
            Toast.makeText(applicationContext, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
        }

        override fun onResponse(
            call: Call<ResponseUserData>,
            response: Response<ResponseUserData>
        ) {
            response.takeIf { it.isSuccessful }
                ?.body()
                ?.let {
                    // SharedPreferences에 자동로그인을 위한 userName 및 userId 정보 저장
                    MyApplication.mySharedPreferences.setString(USER_NAME, it.data.userName)
                    MyApplication.mySharedPreferences.setString(USER_EMAIL, it.data.email)

                    // HomeActivity로 이동
                    val intent = Intent(applicationContext, HomeActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(applicationContext, "성공적으로 로그인되었습니다.", Toast.LENGTH_SHORT).show()
                }
        }
    })
}
```
<br>

`필수 과제 구현 코드 -  SignUpActivity.kt`
```kotlin
private fun signUpUserToServer(email: String, password: String, name: String) {
    val call: Call<ResponseUserData> =
        SoptRetrofitObject.SOPT_USER_API.postSignUp(RequestSignUp(email, password, name))

    call.enqueue(object : Callback<ResponseUserData> {
        override fun onFailure(call: Call<ResponseUserData>, t: Throwable) {
            Log.d("TAG", t.localizedMessage!!.toString())
            Toast.makeText(applicationContext, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
        }

        override fun onResponse(
            call: Call<ResponseUserData>,
            response: Response<ResponseUserData>
        ) {
            response.takeIf { it.isSuccessful }
                ?.body()
                ?.let {
                    Toast.makeText(applicationContext, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    passUserInfoToLoginActivity(it.data.userName, it.data.email, password)
                }
        }
    })
}
```
<br>

#### :heavy_check_mark: 성장 과제 1: 더미데이터 API 구현
* https://reqres.in/_ `LIST USERS` API 구현하기
* `RecycerView`로 통신 결과 출력하기
<br>

`성장 과제 1 구현 코드 - ReqresListUsersApi.kt`
```kotlin
interface ReqresListUsersApi {

    @Headers("Content-Type: application/json")
    @GET("/api/users?page=2")
    suspend fun getReqresListUsersData(
    ): Response<ReqresListUsersModel>


    companion object {
        operator fun invoke(): ReqresListUsersApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL_REQRES_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ReqresListUsersApi::class.java)
        }
    }
}
```
<br>

`성장 과제 1 구현 코드 - ReqresListUsersModel.kt`
```kotlin
data class ReqresListUsersModel(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val support: Support,
    val data: List<Data>
) {
    data class Support(
        val text: String,
        val url: String
    )

    @Parcelize
    data class Data(
        val avatar: String,
        val email: String,
        val first_name: String,
        val id: Int,
        val last_name: String
    ) : Parcelable
}
```
<br>

`성장 과제 1 구현 코드 - ReqresListUsersRequest.kt`
```kotlin
abstract class ReqresListUsersRequest {

    suspend fun <T : Any> requestApi(call: suspend () -> Response<T>): T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw ApiException(
                response.code().toString()
            )
        }
    }
}

class ApiException(message: String) : IOException(message)
```
<br>

`성장 과제 1 구현 코드 - ReqresListUsersRepository.kt`
```kotlin
class ReqresListUsersRepository(
    private val reqresListUsersApi: ReqresListUsersApi
) : ReqresListUsersRequest() {

    suspend fun getReqresUsers() = requestApi {
        reqresListUsersApi.getReqresListUsersData()
    }
}
```
<br>

`성장 과제 1 구현 코드 - ReqresListUsersViewModel.kt`
```kotlin
class ReqresListUsersViewModel(
    private val repository: ReqresListUsersRepository
) : ViewModel() {

    private lateinit var coroutineJob: Job
    private val reqresListUsersMutableLiveData = MutableLiveData<List<ReqresListUsersModel.Data>>()
    val reqresListUsersLiveData: LiveData<List<ReqresListUsersModel.Data>> get() = reqresListUsersMutableLiveData


    fun getReqresUsers() {
        coroutineJob = Coroutines.ioThenMain(
            { repository.getReqresUsers() },
            { reqresListUsersMutableLiveData.value = it?.data }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::coroutineJob.isInitialized) coroutineJob.cancel()
    }
}
```
<br>

`성장 과제 1 구현 코드 - ReqresListUsersViewModelFactory.kt`
```kotlin
@Suppress("UNCHECKED_CAST")
class ReqresListUsersViewModelFactory(
    private val repository: ReqresListUsersRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReqresListUsersViewModel(
            repository
        ) as T
    }
}
```
<br>

`성장 과제 1 구현 코드 - BottomNavReqresListUsersFragment.kt`
```kotlin
private fun setViewModel() {
    val api = ReqresListUsersApi()
    val repository = ReqresListUsersRepository(api)
    reqresListUsersViewModelFactory = ReqresListUsersViewModelFactory(repository)
    reqresListUsersViewModel = ViewModelProvider(this, reqresListUsersViewModelFactory)
        .get(ReqresListUsersViewModel::class.java)
    }

private fun setObserverToListViewModel() {
    reqresListUsersViewModel.reqresListUsersLiveData.observe(
        viewLifecycleOwner,
        Observer { reqresListUsers ->
            viewBinding.recyclerViewReqresListUsers.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                it.adapter = ReqresListUsersAdapter(reqresListUsers,this)
            }
        })
}
```
<br>

#### :heavy_check_mark: 성장 과제 2: 카카오 웹 검색 API 구현
* https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-doc API 구현하기
* `editText`에 입력된 검색어를 바탕으로 `RecycerView`에 통신 결과 출력하기
<br>

`성장 과제 2 구현 코드 - KakaoSearchApi.kt`
```kotlin
interface KakaoSearchApi {

    @Headers(
        "Content-Type: application/json",
        "Authorization: KakaoAK $KAKAO_SEARCH_API_KEY"
    )
    @GET("/v2/search/web")
    suspend fun getSearchResult(
        @Query("query") query: String
    ): Response<KakaoSearchModel>


    companion object {
        operator fun invoke(): KakaoSearchApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL_KAKAO_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(KakaoSearchApi::class.java)
        }
    }
}
```
<br>

`성장 과제 2 구현 코드 - KakaoSearchModel.kt`
```kotlin
data class KakaoSearchModel(
    val meta: Meta,
    val documents: List<Document>
) {
    data class Meta(
        val total_count: Int,
        val pageable_count: Int,
        val is_end: Boolean
    )
    data class Document(
        val dateTime: String,
        val contents: String,
        val title: String,
        val url: String
    )
}
```
<br>

`성장 과제 2 구현 코드 - KakaoSearchRepository.kt`
```kotlin
class KakaoSearchRepository(
    private val kakaoSearchApi: KakaoSearchApi
) : KakaoSearchRequest() {

    suspend fun getSearchResults(queryInput: String) = requestApi {
        kakaoSearchApi.getSearchResult(queryInput)
    }
}
```
<br>

`성장 과제 2 구현 코드 - KakaoSearchRequest.kt`
```kotlin
abstract class KakaoSearchRequest {

    suspend fun <T : Any> requestApi(call: suspend () -> Response<T>): T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw ApiException(
                response.code().toString()
            )
        }
    }
}

class ApiException(message: String) : IOException(message)
```
<br>

`성장 과제 2 구현 코드 - KakaoSearchViewModel.kt`
```kotlin
class KakaoSearchViewModel(
    private val repository: KakaoSearchRepository
) : ViewModel() {

    private lateinit var coroutineJob: Job
    private val kakaoSearchMutableLiveData = MutableLiveData<List<KakaoSearchModel.Document>>()
    val kakaoSearchLiveData: LiveData<List<KakaoSearchModel.Document>> get() = kakaoSearchMutableLiveData


    fun getSearchResults(queryInput: String) {
        coroutineJob = Coroutines.ioThenMain(
            { repository.getSearchResults(queryInput) },
            { kakaoSearchMutableLiveData.value = it?.documents }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::coroutineJob.isInitialized) coroutineJob.cancel()
    }
}
```
<br>

`성장 과제 2 구현 코드 - KakaoSearchViewModelFactory.kt`
```kotlin
@Suppress("UNCHECKED_CAST")
class KakaoSearchViewModelFactory(
    private val repository: KakaoSearchRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KakaoSearchViewModel(repository) as T
    }
}
```
<br>

`성장 과제 2 구현 코드 - KakaoSearchViewModelFactory.kt`
```kotlin
private fun setViewModel() {
    val api = KakaoSearchApi()
    val repository = KakaoSearchRepository(api)
    kakaoSearchViewModelFactory = KakaoSearchViewModelFactory(repository)
    kakaoSearchViewModel = ViewModelProvider(this, kakaoSearchViewModelFactory)
        .get(KakaoSearchViewModel::class.java)
}

private fun setObserverToViewModel() {
    kakaoSearchViewModel.kakaoSearchLiveData.observe(
        viewLifecycleOwner,
        Observer { searchResultList ->
            viewBinding.recyclerViewSearchResultContainer.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = KakaoSearchAdapter(searchResultList)
            }
        })
}
```
<br>
