package ruolan.com.lazyloadfragment.rx

import android.util.Log
import rx.Subscriber

/**
 * Created by wuyinlei on 2018/1/24.
 *
 * @function
 */
open class BaseSubscriber<T> : Subscriber<T>() {

    override fun onNext(t: T) {
    }

    override fun onCompleted() {
    }

    override fun onError(e: Throwable?) {
        Log.d("BaseSubscriber",e?.message.toString())
    }
}