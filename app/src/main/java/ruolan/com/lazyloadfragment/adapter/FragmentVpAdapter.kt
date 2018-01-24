package ruolan.com.lazyloadfragment.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup

/**
 * Created by wuyinlei on 2018/1/24.
 *
 * @function
 */
class FragmentVpAdapter constructor(fm: FragmentManager, fragment: List<Fragment>,titles:ArrayList<String>) : FragmentStatePagerAdapter(fm) {


    private var mFragment: List<Fragment> = arrayListOf()
    private var mTitles :ArrayList<String> = arrayListOf()

    init {
        mFragment = fragment
        this.mTitles = titles
    }

    override fun getItem(position: Int): Fragment {
        return mFragment.get(position)
    }

    override fun getCount(): Int {
        return mFragment.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitles.get(position)
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        return super.instantiateItem(container, position)
    }
}