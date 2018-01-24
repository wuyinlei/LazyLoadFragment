package ruolan.com.lazyloadfragment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ruolan.com.lazyloadfragment.R
import ruolan.com.lazyloadfragment.data.api.UserApi
import ruolan.com.lazyloadfragment.data.model.BaseResp
import ruolan.com.lazyloadfragment.data.model.User
import ruolan.com.lazyloadfragment.data.net.RetrofitFactory
import ruolan.com.lazyloadfragment.ext.execute
import ruolan.com.lazyloadfragment.rx.BaseSubscriber
import ruolan.com.lazyloadfragment.ui.fragment.base.SearchLazyloadBaseFragment

@Suppress("UNREACHABLE_CODE")
/**
 * Created by wuyinlei on 2018/1/24.
 *
 * @function
 */
class UserFragment : SearchLazyloadBaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_user, container, false)
        }
        initData()
        return rootView
    }

    private fun initData() {

        RetrofitFactory.instance.create(UserApi::class.java).user()
                .execute(object : BaseSubscriber<BaseResp<List<User>>>() {
                    override fun onNext(t: BaseResp<List<User>>) {

                    }
                })


    }


    override fun onFragmentVisibleChange(isVisiable: Boolean) {

    }

}