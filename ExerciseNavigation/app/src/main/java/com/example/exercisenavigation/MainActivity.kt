package com.example.exercisenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exercisenavigation.ui.theme.ExerciseNavigationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExerciseNavigationTheme{
                Navigation()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Accueil(
    navController: NavHostController,
    modifier: Modifier = Modifier
)  {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {Text(text = "Accueil")}
            )
        }
    )
    { padding ->
        Column (
            modifier.padding(padding)
        ){
            Button(onClick = { navController.navigate("article") }) {
                Text("Article")
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Article(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {Text(text = "Article")}
            )
        }
        )
    { padding ->
        Column (
            modifier.padding(padding)
        ){
            Button(onClick = { navController.navigate("contact") }) {
                Text("Contact")
            }
            Text(text = "Bonjour")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Contact(
    navController: NavHostController,
    modifier: Modifier = Modifier
)  {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {Text(text = "Contact")}
            )
        }
    )
    { padding ->
        Column (
            modifier.padding(padding)
        ){
            Button(onClick = { navController.navigate("accueil") }) {
                Text("Accueil")
            }
        }
    }
}


@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = "accueil",
        //modifier = modifier.padding(padding)
    ) {
        // on passe le navController à l'écran pour qu'il puisse naviguer à son tour
        composable(route = "accueil") { Accueil(navController, modifier) }
        composable(route = "article") { Article(navController, modifier) }
        composable(route = "contact") { Contact(navController, modifier) }
    }
}