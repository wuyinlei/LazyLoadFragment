package ruolan.com.lazyloadfragment.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user.*
import org.jetbrains.anko.support.v4.toast
import ruolan.com.lazyloadfragment.R
import ruolan.com.lazyloadfragment.adapter.SearchUserAdapter
import ruolan.com.lazyloadfragment.data.api.UserApi
import ruolan.com.lazyloadfragment.data.model.BaseResp
import ruolan.com.lazyloadfragment.data.model.User
import ruolan.com.lazyloadfragment.data.net.RetrofitFactory
import ruolan.com.lazyloadfragment.ext.execute
import ruolan.com.lazyloadfragment.rx.BaseSubscriber
import ruolan.com.lazyloadfragment.rx.event.KeyChangeEvent
import ruolan.com.lazyloadfragment.rx.rxbus.RxBus
import ruolan.com.lazyloadfragment.rx.rxbus.RxBusSubscriber
import ruolan.com.lazyloadfragment.ui.fragment.base.SearchLazyloadBaseFragment
import rx.Subscription

@Suppress("UNREACHABLE_CODE")
/**
 * Created by wuyinlei on 2018/1/24.
 *
 * @function
 */
class UserFragment : SearchLazyloadBaseFragment() {

    private var mRxSub: Subscription? = null

    private var mkey: String? = null
    private var mIsVisible = false
    private var mKeyNotValue = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_user, container, false)
        }
        subscribeEvent()
        return rootView
    }

    private fun initData(key:String) {
        toast("请求数据" + "   UserFragment")
        RetrofitFactory.instance.create(UserApi::class.java).user()
                .execute(object : BaseSubscriber<BaseResp<List<User>>>() {
                    override fun onNext(t: BaseResp<List<User>>) {
                        mRecyclerView.layoutManager = LinearLayoutManager(activity)
                        val userAdapter = SearchUserAdapter(t.data)
                        mRecyclerView.adapter = userAdapter
                    }
                })
    }

    private fun subscribeEvent() {
        mRxSub = RxBus.default?.toObservable(KeyChangeEvent::class.java)
                ?.subscribe(object : RxBusSubscriber<KeyChangeEvent>() {
                    override fun onEvent(t: KeyChangeEvent) {
                        if (t.hasKey) {
                            mkey = t.key
                            mKeyNotValue = true
                            if (mIsVisible) {
                                initData(mkey!!)
                                mKeyNotValue = false
                            }
                        } else {
                            toast("空数据了")
                        }
                    }

                })
    }

    override fun onDestroy() {
        super.onDestroy()
        mRxSub?.unsubscribe()
    }

    override fun onFragmentVisibleChange(isVisiable: Boolean) {
        mIsVisible = isVisible
        if (isVisible && mKeyNotValue) {
            initData(mkey!!)
            mKeyNotValue = false
        }
    }


}