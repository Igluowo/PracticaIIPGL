package com.example.aplicacionpreguntas

import android.os.Bundle
import android.os.ResultReceiver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aplicacionpreguntas.ui.theme.AplicacionPreguntasTheme
import java.util.Random
import java.util.concurrent.ThreadLocalRandom

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplicacionPreguntasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Principal()
                }
            }
        }
    }
}

@Composable
fun Principal() {
    val pregunta1 = Pregunta("¿Queso?", false, R.drawable.queso)
    val pregunta2 = Pregunta("Venezuela FC ha ganado un mundial", false, R.drawable.escudovenezuela)
    val pregunta3 = Pregunta("El sol brilla y la luna no", false, R.drawable.rana)
    val pregunta4 = Pregunta("La vaca lola, la vaca lola, tiene cabeza y tiene cola", true, R.drawable.vaca)
    val pregunta5 = Pregunta("¿España?", true, R.drawable.hispano)
    val pregunta6 = Pregunta("Según un usuario experto en debates en la plataforma " +
            "'X' El color de pelo no natural de las mujeres es una señal para alejarte, " +
            "¿es cierta esta afirmación?", true, R.drawable.x)
    val listaPreguntas = ArrayList<Pregunta>()
    listaPreguntas.add(pregunta1)
    listaPreguntas.add(pregunta2)
    listaPreguntas.add(pregunta3)
    listaPreguntas.add(pregunta4)
    listaPreguntas.add(pregunta5)
    listaPreguntas.add(pregunta6)
    var opcion by remember {mutableStateOf("")}
    var respuestaUsuario by remember { mutableStateOf(false) }
    var noRespondido by remember { mutableStateOf(false) }
    var contador by remember { mutableStateOf(0) }
    var numeroPregunta by remember { mutableStateOf(0) }
    var pregunta by remember { mutableStateOf(listaPreguntas.get(numeroPregunta).pregunta) }
    var imagenId by remember { mutableStateOf(listaPreguntas.get(numeroPregunta).imagenId) }
    var respuesta by remember { mutableStateOf(listaPreguntas.get(numeroPregunta).respuesta) }
    val random = Random()
    Column(verticalArrangement = Arrangement.SpaceBetween
        ,horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = pregunta, Modifier.padding(20.dp))
        Image(painter = painterResource(id = imagenId), contentDescription = "")
        Box {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(opcion)
                if (noRespondido && contador == 1) {
                    comprobarRespuesta(respuesta, respuestaUsuario)
                }
                Row() {
                    Button(onClick = {
                        if (contador == 0) {
                            noRespondido = true; respuestaUsuario = true;
                            contador++
                        }
                    }, colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                        enabled = contador != 1
                    ) {
                        Text(text = "True")
                    }
                    Button(onClick = {
                        if (contador == 0) {
                            noRespondido = true; respuestaUsuario = false;
                            contador++
                        }
                    }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        enabled = contador != 1
                    ) {
                        Text(text = "False")
                    }
                }
                Row(Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        numeroPregunta--; contador = 0; pregunta =
                        listaPreguntas.get(numeroPregunta).pregunta;
                        imagenId = listaPreguntas.get(numeroPregunta).imagenId;
                        respuesta = listaPreguntas.get(numeroPregunta).respuesta
                    }, shape = RectangleShape, modifier = Modifier.padding(12.dp, 0.dp),
                       ) {
                        Text(text = "Previous")
                    }
                    Button(onClick = {
                        numeroPregunta++; contador = 0; pregunta =
                        listaPreguntas.get(random.nextInt(0..listaPreguntas.size)).pregunta;
                        imagenId = listaPreguntas.get(numeroPregunta).imagenId;
                        respuesta = listaPreguntas.get(numeroPregunta).respuesta
                    }, shape = RectangleShape, modifier = Modifier.padding(12.dp, 0.dp)) {
                        //Icon(painter = R., contentDescription = )
                        Text(text = "Next")
                    }
                }
            }
        }
    }
}

@Composable
fun comprobarRespuesta(respuesta: Boolean, respuestaUsuario :Boolean) {
    if (respuesta == respuestaUsuario) {
        Text(text = "La respuesta es correcta", color = Color.Green)
    }else{
        Text(text = "La respuesta es incorrecta", color = Color.Red)
    }
}

fun Random.nextInt(range: IntRange): Int {
    return range.first + nextInt(range.last - range.first)
}

