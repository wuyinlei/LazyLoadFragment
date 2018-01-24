package ruolan.com.lazyloadfragment.data.api

import retrofit2.http.GET
import ruolan.com.lazyloadfragment.data.model.BaseResp
import ruolan.com.lazyloadfragment.data.model.Works
import rx.Observable

/**
 * Created by wuyinlei on 2018/1/24.
 *
 * @function
 */

interface WorkApi{


    @GET("work")
    fun work(): Observable<BaseResp<List<Works>>>


}
