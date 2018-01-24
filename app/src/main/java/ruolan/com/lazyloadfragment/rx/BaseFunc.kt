package ruolan.com.lazyloadfragment.rx

import ruolan.com.lazyloadfragment.data.model.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by wuyinlei on 2018/1/24.
 *
 * @function
 */
class BaseFunc<T>:Func1<BaseResp<T>,Observable<T>> {
    override fun call(t: BaseResp<T>): Observable<T> {
        if (t.code != 0) {
            return Observable.error(BaseException(t.code, t.message))
        }
        return Observable.just(t.data)
    }
}