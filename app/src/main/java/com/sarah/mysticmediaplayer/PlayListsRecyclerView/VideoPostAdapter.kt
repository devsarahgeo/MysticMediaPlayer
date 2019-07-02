package com.sarah.mysticmediaplayer.PlayListsRecyclerView

import android.content.Context
import android.content.Intent
import android.support.annotation.NonNull
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.sarah.mysticmediaplayer.ListData
import com.sarah.mysticmediaplayer.PlayLists
import com.sarah.mysticmediaplayer.R
import com.sarah.mysticmediaplayer.YoutubePlayerView
import com.squareup.picasso.Picasso


class VideoPostAdapter(val context:Context,val listData: ListData) : RecyclerView.Adapter<VideoPostAdapter.YoutubePostHolder>() {
    override fun getItemCount(): Int {
        return listData.items.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubePostHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_videos, parent, false)
        return YoutubePostHolder(view)
    }

    override fun onBindViewHolder(holder: YoutubePostHolder, position: Int) {
        val currentPosn = holder.adapterPosition
        val videoTitle = listData.items[position]
        holder.textViewTitle.text = videoTitle.snippet.title
        holder.textViewDes.text=videoTitle.snippet.description
        holder.textViewDate.text = videoTitle.snippet.publishedAt
        Picasso.with(holder.itemView.context).load(videoTitle.snippet.thumbnails.high.url).into(holder.ImageThumb)
        holder.ImageThumb.setOnClickListener{
            val intent  =Intent(context,YoutubePlayerView::class.java)
            intent.putExtra("videoid",videoTitle.snippet.resourceId.videoId)
            context.startActivity(intent)
        }
        //set the views here
//        val textViewTitle = holder.textViewTitle
//        val textViewDes = holder.textViewDes
//        val textViewDate = holder.textViewDate
//        val ImageThumb = holder.ImageThumb
//
//        val `object` = dataSet[position]
//
//        textViewTitle.setText(`object`.title)
//        textViewDes.setText(`object`.description)
//        textViewDate.setText(`object`.publishedAt)
////        holder.bind(dataSet[position], listener)
//
//        //TODO: image will be downloaded from url
//        Picasso.with(mContext).load(`object`.thumbnail).into(ImageThumb)


    }

    class YoutubePostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var textViewTitle: TextView
        internal var textViewDes: TextView
        internal var textViewDate: TextView
        internal var ImageThumb: ImageView

        init {
            this.textViewTitle = itemView.findViewById(R.id.textViewTitle)
            this.textViewDes = itemView.findViewById(R.id.textViewDes)
            this.textViewDate = itemView.findViewById(R.id.textViewDate)
            this.ImageThumb = itemView.findViewById(R.id.ImageThumb) as ImageView

        }
    }
}