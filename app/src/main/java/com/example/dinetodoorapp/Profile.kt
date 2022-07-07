package com.example.dinetodoorapp

class Profile {
    lateinit var name: String
    lateinit var phone:String
    lateinit var email:String
    lateinit var password:String
    lateinit var address:String

    constructor(){}
    constructor(name: String, phone: String, email:String){
        this.name = name
        this.phone = phone
        this.email = email
        this.password = password
        this.address = address
    }

}
