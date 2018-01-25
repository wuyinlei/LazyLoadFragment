package ruolan.com.lazyloadfragment.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import ruolan.com.lazyloadfragment.R
import ruolan.com.lazyloadfragment.data.model.Content
import ruolan.com.lazyloadfragment.utils.StringFormatUtil
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by wuyinlei on 2018/1/24.
 *
 * @function
 */

class SearchContentAdapter(private val mData: List<Content>) : RecyclerView.Adapter<SearchContentAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.search_content_adapter_item, parent, false))
    }

    var mkey: ArrayList<String> = arrayListOf()

    init {
        mkey.add("天涯")
        mkey.add("土豆")
        mkey.add("怕")
    }


    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val content = mData[position]
        Glide.with(holder.itemView.context)
                .load(content.picnewurl)
                .asBitmap()
                .into(holder.mContent)

        val sdf = SimpleDateFormat("yyyy-MM--dd")
        holder.mContentTime.text = sdf.format(Date(content.addtime.toLong() * 1000L))


        holder.mContentTitle.text = StringFormatUtil(
                holder.itemView.context,
                content.title,
                mkey,
                R.color.main_yellow)
                .fillColor()?.result

        holder.mContentName.text = StringFormatUtil(
                holder.itemView.context,
                content.stitle,
                mkey,
                R.color.main_yellow)
                .fillColor()?.result
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mContent = itemView.findViewById<ImageView>(R.id.mIvContent)!!
        val mContentTime = itemView.findViewById<TextView>(R.id.tv_content_time)!!
        val mContentTitle = itemView.findViewById<TextView>(R.id.tv_content_title)!!
        val mContentName = itemView.findViewById<TextView>(R.id.tv_content_name)!!
    }

}