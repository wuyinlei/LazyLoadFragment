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
import ruolan.com.lazyloadfragment.data.model.Works

/**
 * Created by wuyinlei on 2018/1/24.
 *
 * @function
 */

class SearchWorksAdapter(val mData: List<Works>) : RecyclerView.Adapter<SearchWorksAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.search_work_adapter_item, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val work = mData[position]
        Glide.with(holder.itemView.context)
                .load(work.firstpic)
                .asBitmap()
                .into(holder.mIvWorks)

        holder.mTvWorkTitle.text = work.food_name
        holder.mTvWorkContent.text = work.ingredients
        holder.mTvWorkName.text = work.accessories
        holder.mTvWorkCollect.text = work.likenum + "人收藏"
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mIvWorks = itemView.findViewById<ImageView>(R.id.iv_works)!!
        val mTvWorkTitle = itemView.findViewById<TextView>(R.id.tv_works_title)!!
        val mTvWorkContent = itemView.findViewById<TextView>(R.id.tv_works_content)!!
        val mTvWorkCollect = itemView.findViewById<TextView>(R.id.tv_works_collect)!!
        val mTvWorkName = itemView.findViewById<TextView>(R.id.tv_works_name)!!

    }

}