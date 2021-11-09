package com.example.trainingtask2.ui

import com.example.trainingtask2.data.model.Result

/**
 * click listener on activity for adapter
 */
interface ClickListener {

    /**
     * transfer value from recyclerview to activity
     */
    fun clicked(position:Long?, item:Result?)

}