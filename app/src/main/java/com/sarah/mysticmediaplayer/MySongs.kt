package com.sarah.mysticmediaplayer

import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.sarah.mysticmediaplayer.R.id.sbProgress
import kotlinx.android.synthetic.main.fragment_my_songs.*
import kotlinx.android.synthetic.main.song_ticket.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class MySongs : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var listSongs = ArrayList<SongInfo>()
    var mp: MediaPlayer?=null
    val REQUEST_PERMISSIONS = 1
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
        return inflater.inflate(R.layout.fragment_my_songs, container, false)
    }
    fun checkUserPermissions(){
        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(context!!,android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),REQUEST_PERMISSIONS)
                return
            }
        }
        loadPhoneSongs()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        checkUserPermissions()
        val mytracking = MySongTrack()
        mytracking.start()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            REQUEST_PERMISSIONS-> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadPhoneSongs()
                } else {
                    Toast.makeText(context, "Cannot access songs from phone", Toast.LENGTH_LONG).show()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

    }
    inner class MySongTrack(): Thread() {
        override fun run() {
            while (true){
                try {
                    Thread.sleep(1000)
                }catch (ex:Exception){}
                if(activity!=null){
                    activity!!.runOnUiThread{
                        if(mp!==null) {
                            sbProgress.progress = mp!!.currentPosition
                        }

                    }
                }
            }
        }

    }
    inner class MySongAdapter: BaseAdapter {
        var mySongList = ArrayList<SongInfo>()
        constructor(mySongList:ArrayList<SongInfo>):super(){
            this.mySongList = mySongList
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val myView = layoutInflater.inflate(R.layout.song_ticket,null)
            val Song = this.mySongList[position]
            myView.tvSongName.text = Song.Title
            myView.tvAuthorname.text = Song.Authorname
            myView.btnPlay.setOnClickListener(View.OnClickListener {

                if(myView.btnPlay.text.equals("STOP")){
                    mp!!.stop()
                    myView.btnPlay.text="HIT IT"
                }else{
                    mp = MediaPlayer()
                    try{
                        mp!!.setDataSource(Song.SongURL)
                        mp!!.prepare()
                        mp!!.start()
                        myView.btnPlay.text="STOP"
                        sbProgress.max=mp!!.duration
                    }catch (ex:Exception){}
                }
            })

            return myView
        }

        override fun getItem(position: Int): Any {
            return this.mySongList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return this.mySongList.size
        }

    }
    fun loadPhoneSongs(){
        val allSongsURI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!=0"
        val cursor = activity!!.contentResolver.query(allSongsURI,null,selection,null,null)
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    val songURL = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val songName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                    val songArtist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    listSongs.add(SongInfo(songName,songArtist,songURL))

                }while (cursor.moveToNext())
            }
            cursor.close()
            val adapter = MySongAdapter(listSongs)
            lvListSongs.adapter = adapter
        }

    }
}
