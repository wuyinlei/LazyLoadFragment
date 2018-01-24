package ruolan.com.lazyloadfragment.data.api

import retrofit2.http.GET
import ruolan.com.lazyloadfragment.data.model.BaseResp
import ruolan.com.lazyloadfragment.data.model.User
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/24.
 *
 * @function
 */

interface UserApi {

    @GET("user")
    fun user(): Observable<BaseResp<List<User>>>

}
