package com.example.android.databinding.basicsample.data.remote.response.movie.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GenresItem(

        @ColumnInfo(name = "name_genres_item")
        @SerializedName("name")
		var name: String? = null,

        @ColumnInfo(name = "id_genres_item")
        @SerializedName("id")
		@PrimaryKey(autoGenerate = false)
		var id: Long? = null
){
	@Ignore
	constructor(): this("",0)
}