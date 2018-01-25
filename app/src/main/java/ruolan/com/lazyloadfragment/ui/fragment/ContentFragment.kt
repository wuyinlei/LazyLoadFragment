package ruolan.com.lazyloadfragment.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user.*
import org.jetbrains.anko.support.v4.toast
import ruolan.com.lazyloadfragment.R
import ruolan.com.lazyloadfragment.adapter.SearchContentAdapter
import ruolan.com.lazyloadfragment.data.api.ContentApi
import ruolan.com.lazyloadfragment.data.model.BaseResp
import ruolan.com.lazyloadfragment.data.model.Content
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
class ContentFragment : SearchLazyloadBaseFragment() {

    private var mRxSub: Subscription? = null
    private var mkey: String? = null
    private var mIsVisible = false

    private var mKeyNotValue = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_content, container, false)
        }
//        initData()
        subscribeEvent()
        return rootView
    }

    private fun initData(key: String) {
        toast("请求数据" + "   ContentFragment")
        RetrofitFactory.instance.create(ContentApi::class.java).content()
                .execute(object : BaseSubscriber<BaseResp<List<Content>>>() {
                    override fun onNext(t: BaseResp<List<Content>>) {
                        mRecyclerView.layoutManager = LinearLayoutManager(activity)
                        mRecyclerView.adapter = SearchContentAdapter(t.data)
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