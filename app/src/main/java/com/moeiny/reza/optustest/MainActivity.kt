package com.moeiny.reza.optustest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.optustest.repository.database.entitiy.UserEntity
import com.moeiny.reza.optustest.repository.model.UserShow
import com.moeiny.reza.optustest.view.adapter.UserAdapter
import com.moeiny.reza.optustest.viewmodel.UsersViewModel

class MainActivity : AppCompatActivity() {

    lateinit var userList:ArrayList<UserEntity>
    lateinit var showList:ArrayList<UserShow>
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        * Initialise views
         */
        setUpView()

        userList=viewModel.getAllUsers() as ArrayList<UserEntity>  // get All the Movie information from Database

        /*
        * Loading Data to main recycler view to show in MainAcivity
         */
        loadData()
    }

    fun loadData(){

        showList.clear()     // delete All data in showlist to prepare it to fill by update view objects to send to recyclerView

        /*
         * filling showlist with the movie information from dtabase to sennd to recyclerView
          */
        for ( i in 0..userList.size-1){
            var userShow= UserShow(
                userList[i].user_id.toString(),
                userList[i].name,
                userList[i].email,
                userList[i].phone
            )
            showList.add(userShow)
        }

        setDataOnRecycler(showList)           // Send data to recyclrView
    }

    fun setDataOnRecycler(filmList:List<UserShow>) {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = UserAdapter(this,filmList)
    }

    fun setUpView() {
        userList = ArrayList<UserEntity>()
        showList = ArrayList<UserShow>()
        viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)
        recyclerView = findViewById(R.id.rv_fragmentUser_users)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
