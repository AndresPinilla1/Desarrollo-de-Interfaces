package com.example.di_teclado_musical


import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.di_teclado_musical.ui.theme.DI_Teclado_MusicalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DI_Teclado_MusicalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun miTeclado() {
    // Obtenemos el contexto actual de la aplicación
    val context = LocalContext.current

    // Inicializamos los MediaPlayer para cada una de las notas musicales
    val doo = MediaPlayer.create(context, R.raw.doo)
    val fa = MediaPlayer.create(context, R.raw.fa)
    val la = MediaPlayer.create(context, R.raw.la)
    val mi = MediaPlayer.create(context, R.raw.mi)
    val re = MediaPlayer.create(context, R.raw.re)
    val si = MediaPlayer.create(context, R.raw.si)
    val sol = MediaPlayer.create(context, R.raw.sol)

    // Hacmeos una lista de los nombres de las notas que se mostrarán en las teclas
    val audioNames = listOf("Do", "Re", "Mi", "Fa", "Sol", "La", "Si")

    // Hcemos también una lista de los colores que se van a usar para cada tecla
    val colors = listOf(
        Color(0xFFBB86FC), // Morado suave
        Color(0xFF03DAC5), // Verde aqua
        Color(0xFFFFEB3B), // Amarillo brillante
        Color(0xFFF06292), // Rosa suave
        Color(0xFF4CAF50), // Verde
        Color(0xFF2196F3), // Azul
        Color(0xFFFF5722)  // Naranja
    )

    // Definimos una columna que ocupará toda la pantalla
    Column(
        modifier = Modifier
            // Ocupar todo el espacio disponible
            .fillMaxSize()
            // Fondo oscuro
            .background(Color(0xFF121212))
            // Evita que las teclas se superpongan con las barras del sistema
            .systemBarsPadding()
            // Agrega un padding alrededor de las teclas
            .padding(16.dp)
    ) {
        // Repetimos cada una de las cajas para cada una de las teclas, 7 en total
        repeat(7) { index ->
            // Asignamos el color a la tecla correspondiente
            val color = colors[index]
            // Creamos una caja para cada tecla
            Box(
                modifier = Modifier
                    // Distribuye el espacio verticalmente entre las teclas
                    .weight(1f)
                    // Asegura que la tecla ocupe todo el ancho
                    .fillMaxWidth()
                    // Espaciado entre teclas
                    .padding(8.dp)
                    // Esquinas redondeadas
                    .clip(RoundedCornerShape(16.dp))
                    // Establecemos el color de fondo de la tecla
                    .background(color)
                    // Añadimos un sombra a las teclas
                    .shadow(10.dp, RoundedCornerShape(16.dp))
                    // Definimos la acción, en este caso reproducir un sonido, al hacer clic en la tecla
                    .clickable {
                        when (index) {
                            0 -> doo.start()
                            1 -> re.start()
                            2 -> mi.start()
                            3 -> fa.start()
                            4 -> sol.start()
                            5 -> la.start()
                            else -> si.start()
                        }
                    },
                // Centra el texto dentro de la tecla
                contentAlignment = Alignment.Center
            ) {
                // Indicamos el texto que se muestra en las teclas
                Text(
                    // Texto que se mostrará en la tecla
                    text = audioNames[index],
                    // Color del texto (blanco para contraste)
                    color = Color.White,
                    // Tamaño de fuente
                    fontSize = 28.sp,
                    // Letras en negrita
                    fontWeight = FontWeight.Bold,
                    // Cambiamos la fuente
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // Llamamos a la función de teclado
    miTeclado()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DI_Teclado_MusicalTheme {
        Greeting("Android")
    }
}