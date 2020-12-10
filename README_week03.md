## :fire: 3주차 세미나: Fragment, ViewPager, BottomNavigation, TabLayout
:zap: 세미나 일자: 2020/10/31 </br>
:zap: 과제 완료일: 2020/12/10 </br>
</br>

![무제 001](https://user-images.githubusercontent.com/47806943/101779123-2f239300-3b38-11eb-9175-a7fe4998abe8.jpeg)
</br>
</br>

#### :heavy_check_mark: 필수 과제: `BottomNavigation`, `TabLayout`, `ViewPager` 구현하기
* `BottomNavigation` + `ViewPager`로 전체적인 화면 구성하기 (프로필 화면 - 리사이클러뷰 화면 - 비어있는 화면)
* 프로필 화면에 `TabLayout` 구현하기
<br>

`필수 과제 구현 코드 - HomeViewPagerAdapter.kt`
```kotlin
class HomeViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getItem(position: Int): Fragment = when(position){
        0 -> BottomNavProfileFragment()
        1 -> BottomNavReqresListUsersFragment()
        2 -> BottomNavKakaoSearchFragment()
        else -> throw IllegalStateException("Unexpected position: $position")
    }

    override fun getCount(): Int {
        return 3
    }

}
```
<br>

`필수 과제 구현 코드 - HomeActivity.kt`
```kotlin
class HomeActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setViewPager()
        setBottomNavigation()
    }

    private fun setViewPager() {
        viewPager.addOnPageChangeListener(this)
        viewPager.adapter = HomeViewPagerAdapter(supportFragmentManager)
    }

    private fun setBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
    }

    private val navigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {

            var index by Delegates.notNull<Int>()
            when (it.itemId) {
                R.id.menu_profile -> index = 0
                R.id.menu_list -> index = 1
                R.id.menu_search -> index = 2
            }
            viewPager.currentItem = index
            true
        }

    override fun onPageScrollStateChanged(state: Int) {}
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
    override fun onPageSelected(position: Int) {
        bottomNavigationView.menu.getItem(position).isChecked = true
    }
}
```
</br>

`필수 과제 구현 코드 - ProfileViewPagerAdapter.kt`
```kotlin
class ProfileViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getItem(position: Int): Fragment = when(position){
        0 -> ProfileInfoFragment()
        1 -> ProfileOthersFragment()
        else -> throw IllegalStateException("Unexpected position: $position")
    }

    override fun getCount(): Int {
        return 2
    }
}
```
<br>

`필수 과제 구현 코드 - BottomNavProfileFragment.kt`
```kotlin
class BottomNavProfileFragment : Fragment() {

    private lateinit var viewBinding: FragmentBottomNavProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentBottomNavProfileBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    // ui 작업 수행
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTabLayout()
    }

    private fun setTabLayout() {
        viewBinding.apply {
            viewPager.adapter = ProfileViewPagerAdapter(childFragmentManager)
            tabLayoutProfile.setupWithViewPager(viewBinding.viewPager)
            tabLayoutProfile.apply {
                getTabAt(0)?.text = "INFO"
                getTabAt(1)?.text = "OTHERS"
            }
        }
    }
}
```
<br>
