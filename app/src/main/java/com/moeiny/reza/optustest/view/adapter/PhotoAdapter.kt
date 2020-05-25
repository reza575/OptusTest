package com.moeiny.reza.optustest.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.optustest.databinding.PhotoBinding
import com.moeiny.reza.optustest.repository.model.Photo
import com.moeiny.reza.optustest.repository.model.UserShow
import com.moeiny.reza.optustest.utils.CustomClickListener
import com.moeiny.reza.optustest.view.ShowActivity
import com.squareup.picasso.Picasso


class PhotoAdapter(var context: Context, var photoList:List<Photo>): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() ,
    CustomClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val photoBinding = PhotoBinding.inflate(layoutInflater, parent, false)
        return PhotoViewHolder(photoBinding)
    }

    override fun getItemCount(): Int {
        return photoList.count()
    }

    override fun onBindViewHolder(holderPhoto: PhotoViewHolder, position: Int) {
        var photo=photoList.get(position)

        holderPhoto.bind(photo)

        holderPhoto.photoBinding.setItemClickListener(this)
    }

    inner class PhotoViewHolder(val photoBinding: PhotoBinding): RecyclerView.ViewHolder(photoBinding.root){
      fun bind(modelView: Photo) {
          this.photoBinding.photoshow = modelView
      }
    }

    override fun cardClicked(user: UserShow) {

       }

    override fun cardClicked(photo: Photo) {
        val intent = Intent(context, ShowActivity::class.java)
        intent.putExtra("photoId", photo.id.toString())
        context!!.startActivity(intent)
    }


    companion object{
         @JvmStatic
         @BindingAdapter("imageUrl")
         fun loadImage(view: ImageView,url:String) {
            Picasso.get().load(url).into(view)
         }
    }


}