package ruolan.com.lazyloadfragment.utils


import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan

import java.util.regex.Matcher
import java.util.regex.Pattern

@Suppress("DEPRECATION")
/**
 * Created by wuyinlei on 2018/1/25.
 *
 * @function 上色工具类
 */

class StringFormatUtil
/**
 * @param context      上下文
 * @param wholeStr     全部文字
 * @param highlightStr 改变颜色的文字
 * @param color        颜色
 */
(private val mContext: Context,
 private val wholeStr: String,
 private val highlightStr: List<String>,
 private var color: Int) {


    private var spBuilder: SpannableStringBuilder? = null

    /**
     * 获取到已经更改好的结果(这个时候已经实现了高亮,在获取这个result的时候不要toString()要不然会把色调去除的)
     *
     * @return result
     */
    val result: SpannableStringBuilder?
        get() = if (spBuilder != null) {
            spBuilder
        } else null

    /**
     * 填充颜色
     *
     * @return StringFormatUtil
     */
    fun fillColor(): StringFormatUtil? {

        if (!TextUtils.isEmpty(wholeStr) && highlightStr.isNotEmpty()) {

            spBuilder = SpannableStringBuilder(wholeStr.toLowerCase())
            //上色
            color = mContext.resources.getColor(color)

            highlightStr
                    .map {
                        //匹配规则
                        Pattern.compile(it)
                        //匹配字段
                    }
                    .map {
                        it.matcher(spBuilder!!)

                        //开始循环查找里面是否包含关键字
                    }
                    .forEach {
                        while (it.find()) {
                            val start = it.start()
                            val end = it.end()
                            spBuilder!!.setSpan(ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                        }
                    }
            return this
        }
        return null
    }
}