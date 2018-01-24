package ruolan.com.lazyloadfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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
        initView(rootView)
        return rootView
    }

    fun initView(rootView: View?) {

    }

    override fun onFragmentVisibleChange(isVisiable: Boolean) {

    }

}