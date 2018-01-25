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

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val content = mData[position]
        Glide.with(holder.itemView.context)
                .load(content.picnewurl)
                .asBitmap()
                .into(holder.mContent)

        val sdf = SimpleDateFormat("yyyy-MM--dd")
        holder.mContentTime.text = sdf.format(Date(content.addtime.toLong() * 1000L))
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