package ruolan.com.lazyloadfragment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ruolan.com.lazyloadfragment.R
import ruolan.com.lazyloadfragment.ui.fragment.base.SearchLazyloadBaseFragment

@Suppress("UNREACHABLE_CODE")
/**
 * Created by wuyinlei on 2018/1/24.
 *
 * @function
 */
class ContentFragment : SearchLazyloadBaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_content, container, false)
        }
        return rootView
    }


    override fun onFragmentVisibleChange(isVisiable: Boolean) {

    }

}