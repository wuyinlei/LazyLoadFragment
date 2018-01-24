package ruolan.com.lazyloadfragment.data.api

import retrofit2.http.GET
import ruolan.com.lazyloadfragment.data.model.BaseResp
import ruolan.com.lazyloadfragment.data.model.Content
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/24.
 *
 * @function
 */

interface ContentApi{

    @GET("user")
    fun content(): Observable<BaseResp<List<Content>>>


}
