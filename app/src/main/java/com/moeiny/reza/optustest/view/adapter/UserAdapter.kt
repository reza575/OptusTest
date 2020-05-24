package com.moeiny.reza.optustest.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.optustest.databinding.UserBinding
import com.moeiny.reza.optustest.repository.model.Photo
import com.moeiny.reza.optustest.repository.model.UserShow
import com.moeiny.reza.optustest.utils.CustomClickListener
import com.moeiny.reza.optustest.view.PhotoActivity


class UserAdapter(var context: Context, var userList:List<UserShow>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() ,
    CustomClickListener {

       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val userBinding = UserBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(userBinding)
    }

    override fun getItemCount(): Int {
        return userList.count()
    }

    override fun onBindViewHolder(holderUser: UserViewHolder, position: Int) {
        var user=userList.get(position)

        holderUser.bind(user)
        holderUser.userBinding.setItemClickListener(this)
    }

    inner class UserViewHolder(val userBinding: UserBinding): RecyclerView.ViewHolder(userBinding.root){
      fun bind(modelView: UserShow) {
          this.userBinding.usershow = modelView
      }
    }

    override fun cardClicked(user: UserShow) {
        val intent = Intent(context, PhotoActivity::class.java)
        intent.putExtra("albumId", user.id)
        context!!.startActivity(intent)

       }

    override fun cardClicked2(photo: Photo) {

    }

}