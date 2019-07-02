package com.sarah.mysticmediaplayer.PlayListsRecyclerView

import android.os.Parcel
import android.os.Parcelable


class YoutubeDataModel() : Parcelable {
    var title = ""
    var description = ""
    var publishedAt = ""
    var thumbnail = ""
    var video_id = ""

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(description)
        dest.writeString(publishedAt)
        dest.writeString(thumbnail)
        dest.writeString(video_id)
    }


    protected constructor(`in`: Parcel) : this() {
        readFromParcel(`in`)
    }

    fun readFromParcel(`in`: Parcel) {
        this.title = `in`.readString()
        this.description = `in`.readString()
        this.publishedAt = `in`.readString()
        this.thumbnail = `in`.readString()
        this.video_id = `in`.readString()

    }

    val CREATOR: Parcelable.Creator<YoutubeDataModel> = object : Parcelable.Creator<YoutubeDataModel> {
        override fun createFromParcel(`in`: Parcel): YoutubeDataModel {
            return YoutubeDataModel(`in`)
        }

        override fun newArray(size: Int): Array<YoutubeDataModel?> {
            return arrayOfNulls(size)
        }
    }
}