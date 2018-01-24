package ruolan.com.lazyloadfragment.ext

import ruolan.com.lazyloadfragment.data.model.BaseResp
import ruolan.com.lazyloadfragment.rx.BaseSubscriber
import ruolan.com.lazyloadfragment.rx.BaseFunc
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by wuyinlei on 2018/1/24.
 *
 * @function  扩展类
 */


fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber)
}


/*
    扩展数据转换
 */
fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc())
}