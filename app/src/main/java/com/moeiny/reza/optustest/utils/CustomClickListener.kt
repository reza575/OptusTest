package com.moeiny.reza.optustest.utils

import com.moeiny.reza.optustest.repository.model.Photo
import com.moeiny.reza.optustest.repository.model.UserShow

    /*
     * this interface is essential to enabling clicklistener action for viewitems of ViewAdapter
        when we use databinding
    */

interface CustomClickListener {
    fun cardClicked(f: UserShow)
    fun cardClicked(f: Photo)

}