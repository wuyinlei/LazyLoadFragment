package ruolan.com.lazyloadfragment.ui.activity

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_lazy_load.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView
import ruolan.com.lazyloadfragment.R
import ruolan.com.lazyloadfragment.adapter.FragmentVpAdapter
import ruolan.com.lazyloadfragment.rx.event.KeyChangeEvent
import ruolan.com.lazyloadfragment.rx.rxbus.RxBus
import ruolan.com.lazyloadfragment.ui.fragment.ContentFragment
import ruolan.com.lazyloadfragment.ui.fragment.SearchSynthesisFragment
import ruolan.com.lazyloadfragment.ui.fragment.UserFragment
import ruolan.com.lazyloadfragment.ui.fragment.WorksFragment

@Suppress("DEPRECATION")
/**
 * Created by wuyinlei on 2018/1/24.
 *
 * @function  懒加载界面
 */
class LazyloadActivity : AppCompatActivity() {


    private var mFragments: ArrayList<Fragment> = arrayListOf()
    private var mTitles: ArrayList<String> = arrayListOf()

    init {
        mTitles.add("综合")
        mTitles.add("作品")
        mTitles.add("内容")
        mTitles.add("用户")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lazy_load)

        mFragments.add(SearchSynthesisFragment())
        mFragments.add(WorksFragment())
        mFragments.add(ContentFragment())
        mFragments.add(UserFragment())

        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = object : CommonNavigatorAdapter() {

            override fun getCount(): Int {
                return mTitles.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val badgePagerTitleView = BadgePagerTitleView(context)

                val simplePagerTitleView = ColorTransitionPagerTitleView(context)
                simplePagerTitleView.normalColor = resources.getColor(R.color.dynamics_publish_color)
                simplePagerTitleView.selectedColor = resources.getColor(R.color.main_yellow)
                simplePagerTitleView.text = mTitles[index]
                simplePagerTitleView.textSize = 14f
                simplePagerTitleView.setOnClickListener { mViewPager.currentItem = index }
                badgePagerTitleView.innerPagerTitleView = simplePagerTitleView

                return badgePagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.setColors(resources.getColor(R.color.main_yellow))
                indicator.yOffset = UIUtil.dip2px(context, 3.0).toFloat()
                indicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
                return indicator
            }
        }
        mMagicIndicatorSearch.navigator = commonNavigator
        val titleContainer = commonNavigator.titleContainer // must after setNavigator
        titleContainer.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        titleContainer.dividerDrawable = object : ColorDrawable() {
            override fun getIntrinsicWidth(): Int {
                return UIUtil.dip2px(this@LazyloadActivity, 15.0)
            }
        }

        ViewPagerHelper.bind(mMagicIndicatorSearch, mViewPager)


        mViewPager.adapter = FragmentVpAdapter(supportFragmentManager, mFragments, mTitles)
        //这个地方也是一个坑  如果需要搜索的达到要求 也就是当输入key关键字之后  我们当滑动到那个界面的时候  就要触发
        // 他的请求网络的接口  那么就需要fragment初始化保存在viewpager的内存管理中
        //当然这个我们是4个界面  所以要设置为3个  也就是当前所有的4个界面都要初始化出来
        //这个初始化之后   要配合Fragment-->setUserVisibleHint的这个方法来进行数据请求  才能更好的用户体验
        mViewPager.offscreenPageLimit = 3
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                mMagicIndicatorSearch.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                mMagicIndicatorSearch.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                mMagicIndicatorSearch.onPageScrollStateChanged(state)
            }
        })


        mEditSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (!s.toString().isEmpty()) {
                    val keyEvent = KeyChangeEvent(s.toString(), true)
                    RxBus.default?.post(keyEvent)
                } else {
                    val keyEvent = KeyChangeEvent("", false)
                    RxBus.default?.post(keyEvent)
                }
            }

        })


    }


}