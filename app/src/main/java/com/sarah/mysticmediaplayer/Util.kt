package com.sarah.mysticmediaplayer


object Util {

    val DEBUG = false

    val SUGGESTIONS_URL = "http://suggestqueries.google.com/complete/search?client=youtube&ds=yt&q="
    val YOUTUBE_BASE_URL = "http://youtube.com/watch?v="
    val SHARE_VIDEO_URL = "http://youtube.com/watch?v="
    val SHARE_PLAYLIST_URL = "https://www.youtube.com/playlist?list="
    val YOUTUBE_TYPE = "YT_MEDIA_TYPE"
    val YOUTUBE_TYPE_VIDEO = "YT_VIDEO"
    val YOUTUBE_TYPE_PLAYLIST = "YT_PLAYLIST"
    val YOUTUBE_TYPE_PLAYLIST_VIDEO_POS = "YT_PLAYLIST_VIDEO_POS"
    val YOUTUBE_API_KEY = "AIzaSyAR3lyb-ucc8JYrSHw0rfCaXCYHveGy6U8"
    val PLAYLIST_ID = "PL6CTrxW12Bre4kny-OhqOEQwNjso0VKPc"//here you should use your playlist id for testing purpose you can use this api also
    val CHANNLE_GET_URL = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=$PLAYLIST_ID&maxResults=20&key=$YOUTUBE_API_KEY"


    val NUMBER_OF_VIDEOS_RETURNED: Long = 30 //due to YouTube API rules - MAX 50

}