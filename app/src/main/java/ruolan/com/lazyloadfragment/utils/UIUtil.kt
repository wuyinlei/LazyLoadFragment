package ruolan.com.lazyloadfragment.utils

import android.content.Context

/**
 * Created by wuyinlei on 2018/1/24.
 */
class UIUtil {

    fun dip2px(context: Context, dpValue: Double): Int {
        val density = context.resources.displayMetrics.density
        return (dpValue * density.toDouble() + 0.5).toInt()
    }
}