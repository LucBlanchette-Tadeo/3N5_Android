package com.Luc.tapelelapin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Luc.tapelelapin.ui.theme.TapeLeLapinTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TapeLeLapinTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {Text(text = "Tape le lapin")},
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    }
                ) {
                    innerPadding ->
                    EcranPrincipal(
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}

@Composable
fun EcranPrincipal(modifier: Modifier = Modifier) {
    Column (
         modifier = modifier
    ){
       Row(
              modifier = Modifier
       ){
            Text(
                text = "0 pafs"
            )
            Text(
               text = "0 flops"
            )
       }
       Text(
          text = "Tape le lapin"
       )
        GrilleTuiles(
            modifier
        )
    }
}

@Composable
private fun GrilleTuiles(modifier: Modifier) {
    var positionLapin = Random.nextInt(9)
    Column(
        modifier = modifier
    ) {
        for (i in 0..2) {
            Row(
                modifier = Modifier
            ) {
                for (j in 0..2) {
                    var indexTuile = i*3 +j
                    Tuile(
                        modifier = Modifier.padding(6.dp),
                        estLapin = positionLapin == indexTuile
                    )
                }
            }
        }
    }
}

@Composable
private fun Tuile(
    modifier: Modifier,
    estLapin : Boolean
) {
    Button(
        modifier = modifier,
        onClick = { }
    ) {
        Text(
            text = if(estLapin) "Lapin" else "Taupe",
            fontSize = 25.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TapeLeLapinTheme {
        EcranPrincipal()
    }
}