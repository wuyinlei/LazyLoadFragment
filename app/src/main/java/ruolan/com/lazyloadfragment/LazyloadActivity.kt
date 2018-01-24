package ruolan.com.lazyloadfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_lazy_load.*
import ruolan.com.lazyloadfragment.adapter.FragmentVpAdapter

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

        mFragments.add(ContentFragment())
        mFragments.add(ContentFragment())
        mFragments.add(ContentFragment())
        mFragments.add(ContentFragment())

        mTabLayout.setupWithViewPager(mViewPager)

        mViewPager.adapter = FragmentVpAdapter(supportFragmentManager,mFragments,mTitles)

    }




}