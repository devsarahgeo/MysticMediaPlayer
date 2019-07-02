package com.sarah.mysticmediaplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_youtube_player_view.*


class YoutubePlayerView : YouTubeBaseActivity() {
    lateinit var videoId:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_player_view)
        val buttonPlay = findViewById<Button>(R.id.buttonPlay)
        val youtubePlayerView = findViewById<YouTubePlayerView>(R.id.youtubePlayerView)
        videoId = intent.extras.getString("videoid","")
        buttonPlay.setOnClickListener{
            val videoId=videoId
            playVideo(videoId, youtubePlayerView)
        }


    }

    fun playVideo(videoId: String, youTubePlayerView: YouTubePlayerView) {
        //initialize youtube player view
        youTubePlayerView.initialize("YOUR API KEY HERE",
                object : YouTubePlayer.OnInitializedListener {
                    override fun onInitializationSuccess(provider: YouTubePlayer.Provider,
                                                         youTubePlayer: YouTubePlayer, b: Boolean) {
                        youTubePlayer.cueVideo(videoId)
                    }

                    override fun onInitializationFailure(provider: YouTubePlayer.Provider,
                                                         youTubeInitializationResult: YouTubeInitializationResult) {

                    }
                })
    }
}
