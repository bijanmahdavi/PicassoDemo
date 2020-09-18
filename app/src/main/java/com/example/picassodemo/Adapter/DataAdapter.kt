package com.example.picassodemo.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picassodemo.Model.ImageData
import com.example.picassodemo.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_layout.view.*

class DataAdapter(var mContext: Context, var mList: ArrayList<ImageData>) : RecyclerView.Adapter<DataAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.grid_layout, parent, false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var images = mList[position]
        holder.bind(images)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(list: ArrayList<ImageData>) {
        mList = list
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(data: ImageData){
            Picasso.get()
                .load(data.src)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(itemView.grid_img_src)
            itemView.grid_text_view_title.text = data.src

            itemView.grid_text_view_title.setOnClickListener {
                Log.d("IT", "text item clicked")
            }


            itemView.grid_img_src.setOnClickListener {
                Log.d("IM", "image clicked")
            }
        }

    }


}