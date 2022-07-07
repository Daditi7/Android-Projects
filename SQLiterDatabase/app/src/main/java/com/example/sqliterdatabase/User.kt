package com.example.sqliterdatabase

class User{
   var id =0
   var email:String?=""
   var age:String?=""
   constructor(email: String?,age: String?){
      this.email=email
      this.age=age
   }
   constructor(){
   }
}
