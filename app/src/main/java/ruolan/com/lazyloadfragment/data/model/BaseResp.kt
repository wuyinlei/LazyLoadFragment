package ruolan.com.lazyloadfragment.data.model

/**
 * Created by wuyinlei on 2018/1/24.
 */
data class BaseResp<out T>(val code: Int, val message: String, val data: T)