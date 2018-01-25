package ruolan.com.lazyloadfragment.rx.rxbus

import rx.Subscriber

/**
 * Created by wuyinlei on 2017/1/5.
 */

abstract class RxBusSubscriber<T> : Subscriber<T>() {
    override fun onCompleted() {

    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }

    override fun onNext(t: T) {
        try {
            onEvent(t)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    abstract fun onEvent(t: T)
}
