package com.example.day12_githubstar.model

import android.os.Parcel
import android.os.Parcelable

data class ProjectModel(
    val projectName: String?,
    val description: String?,
    val avatarURL: String?,
    val starCount: Int,
    val forkCount: Int,
    val username: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(projectName)
        parcel.writeString(description)
        parcel.writeString(avatarURL)
        parcel.writeInt(starCount)
        parcel.writeInt(forkCount)
        parcel.writeString(username)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProjectModel> {
        override fun createFromParcel(parcel: Parcel): ProjectModel {
            return ProjectModel(parcel)
        }

        override fun newArray(size: Int): Array<ProjectModel?> {
            return arrayOfNulls(size)
        }
    }
}