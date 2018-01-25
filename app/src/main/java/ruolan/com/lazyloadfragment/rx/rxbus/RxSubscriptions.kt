package ruolan.com.lazyloadfragment.rx.rxbus

import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * Created by wuyinlei on 2017/1/5.
 * @function 订阅管理类  防止内存泄漏
 */

class RxSubscriptions {

    private val mSubscriptions = CompositeSubscription()

    val isUnsubscribed: Boolean
        get() = mSubscriptions.isUnsubscribed

    fun add(s: Subscription?) {
        if (s != null) {
            mSubscriptions.add(s)
        }
    }

    fun remove(s: Subscription?) {
        if (s != null) {
            mSubscriptions.remove(s)
        }
    }

    fun clear() {
        mSubscriptions.clear()
    }

    fun unsubscribe() {
        mSubscriptions.unsubscribe()
    }

    fun hasSubscriptions(): Boolean {
        return mSubscriptions.hasSubscriptions()
    }
}
