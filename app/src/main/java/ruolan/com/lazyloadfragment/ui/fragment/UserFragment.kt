package ruolan.com.lazyloadfragment.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user.*
import ruolan.com.lazyloadfragment.R
import ruolan.com.lazyloadfragment.adapter.SearchUserAdapter
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
                        mRecyclerView.layoutManager = LinearLayoutManager(activity)
                        mRecyclerView.adapter = SearchUserAdapter(t.data)
                    }
                })


    }


    override fun onFragmentVisibleChange(isVisiable: Boolean) {

    }

}