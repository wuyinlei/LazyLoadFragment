package ruolan.com.lazyloadfragment.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import ruolan.com.lazyloadfragment.R
import ruolan.com.lazyloadfragment.data.model.User
import ruolan.com.lazyloadfragment.utils.StringFormatUtil

@Suppress("DEPRECATION")
/**
 * Created by wuyinlei on 2018/1/24.
 *
 * @function
 */

class SearchUserAdapter(val mData: List<User>) : RecyclerView.Adapter<SearchUserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.search_user_adapter_item, parent, false))
    }

    var mkey: ArrayList<String> = arrayListOf()

    init {
        mkey.add("兰")
        mkey.add("二")
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = mData[position]
        Glide.with(holder.itemView.context)
                .load(user.headpic)
                .asBitmap()
                .into(holder.mUserProfile)


        holder.mUserName.text = StringFormatUtil(
                holder.itemView.context,
                user.nick_name,
                mkey,
                R.color.main_yellow)
                .fillColor()?.result

        holder.mUserWhere.text = StringFormatUtil(
                holder.itemView.context,
                user.unit + " " + user.duty,
                mkey,
                R.color.main_yellow)
                .fillColor()?.result

        if (user.fs == "1") {
            holder.mBtAttention.setBackgroundResource(R.mipmap.following)
        } else {
            holder.mBtAttention.setBackgroundResource(R.mipmap.addfollow)
        }

    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mUserProfile = itemView.findViewById<CircleImageView>(R.id.user_profile)!!
        val mUserName = itemView.findViewById<TextView>(R.id.user_name)!!
        val mUserWhere = itemView.findViewById<TextView>(R.id.user_where)!!
        val mBtAttention = itemView.findViewById<Button>(R.id.bt_attention)!!
    }

}
