package com.example.chatapppractice.users

import android.provider.ContactsContract.CommonDataKinds.Email

//we are not creating a data class here because firebase required a empty constructor
class User {
    var email:String? = null
    var name:String? = null
    var uid:String? = null

    constructor(){}

    constructor(name: String?,email: String?, uid:String?)
    {
        this.name= name
        this.email = email
        this.uid  = uid
    }

}