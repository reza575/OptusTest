package com.moeiny.reza.optustest.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.optustest.R
import com.moeiny.reza.optustest.databinding.ActivityPhotoBinding
import com.moeiny.reza.optustest.repository.database.entitiy.PhotoEntity
import com.moeiny.reza.optustest.repository.model.Photo
import com.moeiny.reza.optustest.repository.model.PhotoShow
import com.moeiny.reza.optustest.view.adapter.PhotoAdapter
import com.moeiny.reza.optustest.viewmodel.UsersViewModel


class PhotoActivity : AppCompatActivity() {
    lateinit var photoList: List<PhotoEntity>
    lateinit var albumId: String
    lateinit var recyclerView: RecyclerView
    lateinit var id:String
    lateinit var context : Context
    lateinit var viewModel: UsersViewModel
    lateinit var mBinding: ActivityPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo)

        viewModel= ViewModelProviders.of(this).get(UsersViewModel::class.java)
        val bundle = intent.extras

        albumId=bundle?.getString("albumId").toString()
        var photoShow=PhotoShow(albumId,"","","","")


        context=this

        mBinding.photoshow=photoShow


        setUpView()

        photoList=viewModel.getPhotoByAlbumId(albumId.toInt())
        setDataOnRecycler()
    }



    fun setDataOnRecycler() {
        val photoListTemp = ArrayList<Photo>()
        for(item:PhotoEntity in photoList ){
            var photo=Photo(item.album_id,item.photo_id,item.thumbnailUrl,item.title,item.url)
               photoListTemp.add(photo)
        }
        viewModel.setPhotoList(photoListTemp)


        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = PhotoAdapter(context!!, viewModel.getPhotoList())
    }

    fun setUpView() {
        recyclerView = findViewById(R.id.rv_fragmentphoto_photo)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}
