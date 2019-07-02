package com.sarah.mysticmediaplayer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sarah.mysticmediaplayer.PlayListsRecyclerView.YoutubeDataModel
import com.sarah.mysticmediaplayer.PlayListsRecyclerView.VideoPostAdapter
import android.support.v7.widget.RecyclerView

import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_play_lists.*
import org.json.JSONException
import org.json.JSONObject
import okhttp3.*
import java.io.IOException
import java.nio.channels.Channel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PlayLists : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var mList_videos: RecyclerView? = null
    private var adapter: VideoPostAdapter? = null
    private var mListData = ArrayList<YoutubeDataModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_play_lists, container, false)
        val playlistrecyler = rootView.findViewById(R.id.playlist_recyler) as RecyclerView
        playlistrecyler.layoutManager = LinearLayoutManager(activity)
//        playlist_recyler.adapter = VideoPostAdapter(mListData)
        fetchJSON()

        return rootView

    }

    fun fetchJSON(){
        val url = Util.CHANNLE_GET_URL
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object :Callback{
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println("json is:$body")
                val gson = GsonBuilder().create()
                val listdata = gson.fromJson(body,ListData::class.java)
                activity!!.runOnUiThread{
                    playlist_recyler.adapter = VideoPostAdapter(context!!,listdata)

                }

            }

            override fun onFailure(call: Call?, e: IOException?) {
                print("failed to execute")

            }
        })
    }

//    fun parseVideoListFromResponse(jsonObject: JSONObject): ArrayList<YoutubeDataModel> {
//        val mList = ArrayList<YoutubeDataModel>()
//
//        if (jsonObject.has("items")) {
//            try {
//                val jsonArray = jsonObject.getJSONArray("items")
//                for (i in 0 until jsonArray.length()) {
//                    val json = jsonArray.getJSONObject(i)
//                    if (json.has("kind")) {
//                        if (json.getString("kind") == "youtube#playlistItem") {
//                            val youtubeObject = YoutubeDataModel()
//                            val jsonSnippet = json.getJSONObject("snippet")
//                            var vedio_id = ""
//                            if (jsonSnippet.has("resourceId")) {
//                                val jsonResource = jsonSnippet.getJSONObject("resourceId")
//                                vedio_id = jsonResource.getString("videoId")
//
//                            }
//                            val title = jsonSnippet.getString("title")
//                            val description = jsonSnippet.getString("description")
//                            val publishedAt = jsonSnippet.getString("publishedAt")
//                            val thumbnail = jsonSnippet.getJSONObject("thumbnails").getJSONObject("high").getString("url")
//
//                            youtubeObject.title = title
//                            youtubeObject.description = description
//                            youtubeObject.publishedAt = publishedAt
//                            youtubeObject.thumbnail = thumbnail
//                            youtubeObject.video_id = vedio_id
//                            mList.add(youtubeObject)
//
//                        }
//                    }
//
//
//                }
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//
//        }
//
//        return mList
//
//    }


}
class ListData(val items:List<Item>)

class Item(val kind:String,val etag:String,val id:String,val snippet:Snippet)

class Snippet(val publishedAt:String,val channelId:String,val title:String,val description:String,val thumbnails:ThumbNails,val channelTitle:String,val playlistId:String,val position:Int,val resourceId:ResourceId)
class ResourceId(val kind:String,val videoId:String)
class ThumbNails(val default:Default,val medium:Medium,val high:High,val standard:Standard,val maxres:Maxres)
class Default(val url:String,val width:Int,val height:Int)
class Medium(val url:String,val width:Int,val height:Int)
class High(val url:String,val width:Int,val height:Int)
class Standard(val url:String,val width:Int,val height:Int)
class Maxres(val url:String,val width:Int,val height:Int)
