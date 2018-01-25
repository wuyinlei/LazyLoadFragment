package ruolan.com.lazyloadfragment.rx.rxbus


import java.util.concurrent.ConcurrentHashMap

import rx.Observable
import rx.subjects.PublishSubject
import rx.subjects.SerializedSubject
import rx.subjects.Subject

@Suppress("DEPRECATION")
/**
 * PublishSubject: 只会在订阅发生的时间点之后来自原始的Observable的数据发送给观观察者
 */
class RxBus {
    private val mBus: Subject<Any, Any>

    private val mStickEventMap: MutableMap<Class<*>, Any>

    init {
        mBus = SerializedSubject(PublishSubject.create())
        mStickEventMap = ConcurrentHashMap()
    }

    /**
     * 发送事件
     * @param event  事件
     */
    fun post(event: Any) {
        mBus.onNext(event)
    }

    /**
     * 根据传递的eventType 类型来返回特定的类型(EventType)给被观察者
     * @param eventType  类型
     * @param <T>  数据类型
     * @return  被观察者事件
    </T> */
    fun <T> toObservable(eventType: Class<T>): Observable<T> {
        return mBus.ofType(eventType)
    }

    /**
     * 返回是否有订阅者
     * @return  true  /  false
     */
    fun hasObservers(): Boolean {
        return mBus.hasObservers()
    }

    /*重置**/
    fun reset() {
        mDefaultInstance = null
    }

    fun postSticky(event: Any) {
        synchronized(mStickEventMap) {
            mStickEventMap.put(event.javaClass, event)
        }
        post(event)
    }

    /**
     * 根据传递的eventType  类型返回特定类型的被观察者
     * @param eventType  类型
     * @param <T>  泛型
     * @return  粘性事件类型
    </T> */
    fun <T> toObservableSticky(eventType: Class<T>): Observable<T> {
        synchronized(mStickEventMap) {
            val observable = mBus.ofType(eventType)
            val event = mStickEventMap[eventType]

            return if (event != null) {
                observable.mergeWith(Observable.create { subscriber -> subscriber.onNext(eventType.cast(event)) })
            } else {
                observable
            }
        }
    }

    /**
     * 根据eventType获取Sticky事件
     * @param eventType  类型
     * @param <T> 泛型
     * @return  粘性事件
    </T> */
    fun <T> getStickyEvent(eventType: Class<T>): T {
        synchronized(mStickEventMap) {
            return eventType.cast(mStickEventMap[eventType])
        }
    }

    /**
     * 移除指定eventType的Sticky事件
     * @param eventType  类型
     * @param <T> 泛型
     * @return 粘性事件
    </T> */
    fun <T> removeStickyEvent(eventType: Class<T>): T {
        synchronized(mStickEventMap) {
            return eventType.cast(mStickEventMap.remove(eventType))
        }
    }

    /**
     * 移除所有的Sticky事件
     */
    fun removeAllStickyEvents() {
        synchronized(mStickEventMap) {
            mStickEventMap.clear()
        }
    }

    companion object {

        @Volatile
        private var mDefaultInstance: RxBus? = null

        /**
         * 单例模式的使用，保证只有一个实例
         * @return  RxBus
         */
        val default: RxBus?
            get() {
                if (mDefaultInstance == null) {
                    synchronized(RxBus::class.java) {
                        mDefaultInstance = RxBus()
                    }
                }
                return mDefaultInstance
            }
    }


}
