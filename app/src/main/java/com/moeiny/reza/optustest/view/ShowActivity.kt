package com.moeiny.reza.optustest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.moeiny.reza.optustest.R
import com.moeiny.reza.optustest.databinding.ActivityShowBinding
import com.moeiny.reza.optustest.repository.model.PhotoShow
import com.moeiny.reza.optustest.viewmodel.UsersViewModel


class ShowActivity : AppCompatActivity() {

    lateinit var viewModel: UsersViewModel
    lateinit var mBinding: ActivityShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_show)
        viewModel= ViewModelProviders.of(this).get(UsersViewModel::class.java)
        val bundle = intent.extras
        var photoId=bundle?.getString("photoId").toString().toInt()
        var photo=viewModel.getPhotoById(photoId)
        var photoShow=PhotoShow(photo.album_id.toString(),photo.photo_id.toString(),photo.thumbnailUrl,
            photo.title,photo.url)
        mBinding.photoshow=photoShow
    }
}
