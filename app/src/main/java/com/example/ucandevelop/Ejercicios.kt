package com.example.ucandevelop

class Ejercicios {

    lateinit var id:String
    lateinit var description: String
    lateinit var ayuda: String
    lateinit var nivel: String

    constructor(){}

    constructor(id:String, description:String, ayuda:String, nivel:String){
        this.id = id
        this.description = description
        this.ayuda = ayuda
        this.nivel = nivel
    }


}