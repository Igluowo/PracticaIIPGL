package com.example.aplicacionpreguntas

class Pregunta {
    var pregunta : String
    var respuesta : Boolean
    var imagenId : Int

    constructor(pregunta: String, respuesta : Boolean, imagenId : Int) {
        this.pregunta = pregunta
        this.respuesta = respuesta
        this.imagenId = imagenId
    }
}