## :fire: 2주차 세미나: RecyclerView
:zap: 세미나 일자: 2020/10/17 </br>
:zap: 과제 완료일: 2020/12/10 </br>
</br>

![무제 001](https://user-images.githubusercontent.com/47806943/101776714-b969f800-3b34-11eb-93e3-a12fa5db8b95.jpeg) </br>
</br>

#### :heavy_check_mark: 필수 과제: `RecyclerView` 구현하기
* 로그인 버튼을 누르면 `RecyclerView`를 보여주는 화면으로 이동하기
* `RecyclerView`의 각 아이템을 클릭하면 해당 아이템의 정보를 가지고 있는 상세화면으로 이동하기
<br>

`필수 과제 구현 코드 - ReqresListUsersAdapter.kt`
```kotlin
class ReqresListUsersAdapter(
    private val reqresListUsers: List<ReqresListUsersModel.Data>,
    private val clickListener: ItemListeners
) : RecyclerView.Adapter<ReqresListUsersAdapter.ReqresListUsersViewHolder>() {


    override fun getItemCount() = reqresListUsers.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReqresListUsersViewHolder {
        return ReqresListUsersViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_reqres_list_user,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReqresListUsersViewHolder, position: Int) {
        holder.dataBinding.listUsers = reqresListUsers[position]
        holder.dataBinding.root.setOnClickListener {
            clickListener.onClickReqresListUsersItem(it, reqresListUsers[position])
        }
    }

    inner class ReqresListUsersViewHolder(
        val dataBinding: ItemReqresListUserBinding
    ) : RecyclerView.ViewHolder(dataBinding.root)
}
```
</br>

`필수 과제 구현 코드 - BottomNavReqresListUsersFragment.kt`
```kotlin
private fun setObserverToListViewModel() {
    reqresListUsersViewModel.reqresListUsersLiveData.observe(
        viewLifecycleOwner,
        Observer { reqresListUsers ->
            viewBinding.recyclerViewReqresListUsers.also {
            
                // 리사이클러뷰 설정
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                it.adapter =
                    ReqresListUsersAdapter(
                        reqresListUsers,
                        this
                    )
            }
        })
}

override fun onClickReqresListUsersItem(view: View, userData: ReqresListUsersModel.Data) {

    // 리사이클러뷰 아이템의 상세 화면으로 이동하는 인텐트 설정
    val intent = Intent(context, ReqresListUsersDetailActivity::class.java)
    intent.putExtra(USER_DATA, userData)
    startActivity(intent)
}
```
<br>

#### :heavy_check_mark: 성장 과제 1: `GridLayout` 만들기
* 필수 과제로 만든 `RecyclerView` 아이템을 격자 형태로 바꾸기
<br>

`성장 과제 1 구현 코드 - ReqresGridUsersAdapter.kt`
```kotlin
class ReqresGridUsersAdapter(
    private val reqresListUsers: List<ReqresListUsersModel.Data>,
    private val clickListener: ItemListeners
) : RecyclerView.Adapter<ReqresGridUsersAdapter.ReqresListUsersViewHolder>() {


    override fun getItemCount() = reqresListUsers.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReqresListUsersViewHolder {
        return ReqresListUsersViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_reqres_grid_user,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReqresListUsersViewHolder, position: Int) {
        holder.dataBinding.listUsers = reqresListUsers[position]
        holder.dataBinding.root.setOnClickListener {
            clickListener.onClickReqresListUsersItem(it, reqresListUsers[position])
        }
    }

    inner class ReqresListUsersViewHolder(
        val dataBinding: ItemReqresGridUserBinding
    ) : RecyclerView.ViewHolder(dataBinding.root)
}
```
<br>

`성장 과제 1 구현 코드 - BottomNavReqresListUsersFragment.kt`
```kotlin
private fun setListeners() {
    viewBinding.FABGridLayout.setOnClickListener(onClickListener)
}

private val onClickListener = View.OnClickListener {
    when (it.id) {
        viewBinding.FABGridLayout.id -> {
        
            // FABGridLayout 버튼을 누를 때마다 호출
            setLayoutManager()
        }
    }
}

private fun setLayoutManager() {

    // viewModel에 부착되어있던 observer 제거
    removeObserverFromViewModel()
    viewBinding.apply {
    
        // layoutStatus의 값에 따라 리사이클러뷰의 레이아웃 및 어댑터 재설정
        if (layoutStatus == "list") {
            FABGridLayout.setImageResource(R.drawable.icon_grid)
            setObserverToListViewModel()
            layoutStatus = "grid"

        } else {
            FABGridLayout.setImageResource(R.drawable.icon_list)
            setObserverToGridViewModel()
            layoutStatus = "list"
        }
    }
}

private fun setObserverToListViewModel() {
    reqresListUsersViewModel.reqresListUsersLiveData.observe(
        viewLifecycleOwner,
        Observer { reqresListUsers ->
            viewBinding.recyclerViewReqresListUsers.also {
            
                // LinearLayoutManager + ReqresListUsersAdapter
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                it.adapter =
                    ReqresListUsersAdapter(
                        reqresListUsers,
                        this
                    )
            }
        })
}

private fun setObserverToGridViewModel() {
    reqresListUsersViewModel.reqresListUsersLiveData.observe(
        viewLifecycleOwner,
        Observer { reqresListUsers ->
            viewBinding.recyclerViewReqresListUsers.also {
            
                // GridLayoutManager + ReqresGridUsersAdapter
                it.layoutManager = GridLayoutManager(context, 2)
                it.setHasFixedSize(true)
                it.adapter =
                    ReqresGridUsersAdapter(
                        reqresListUsers,
                        this
                    )
            }
        })
}

private fun removeObserverFromViewModel() {
    reqresListUsersViewModel.reqresListUsersLiveData.removeObservers(viewLifecycleOwner)
}
```
<br>
