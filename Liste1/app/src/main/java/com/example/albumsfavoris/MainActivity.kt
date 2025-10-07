package com.example.albumsfavoris

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.albumsfavoris.ui.theme.AlbumsFavorisTheme
import androidx.compose.runtime.remember

class MainActivity : ComponentActivity() {
    private val albums = mutableStateListOf(
        Album(0, "Illmatic", "Nas"),
        Album(1, "The Chronic", "Dr. Dre"),
        Album(2, "To Pimp a Butterfly", "Kendrick Lamar"),
        Album(3, "Ready to Die", "The Notorious B.I.G."),
        Album(4, "The Marshall Mathers LP", "Eminem"),
        Album(5, "Enter the Wu-Tang (36 Chambers)", "Wu-Tang Clan"),
        Album(6, "My Beautiful Dark Twisted Fantasy", "Kanye West"),
        Album(7, "good kid, m.A.A.d city", "Kendrick Lamar"),
        Album(8, "All Eyez on Me", "2Pac"),
        Album(9, "The Blueprint", "Jay-Z")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlbumsFavorisTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "albumList",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("albumList") {
                            AlbumListe(albums, navController)
                        }
                        composable("albumDetail/{albumId}") { it ->
                            val albumIdString = it.arguments?.getString("albumId")
                            val albumId = albumIdString?.toIntOrNull()
                            val album = albums.find { it.id == albumId }
                            if (album != null) {
                                AlbumDetail(album, albums, navController)
                            } else {
                                Text("Album non trouvé", modifier = Modifier.padding(16.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AlbumListe(albums: List<Album>, navController: androidx.navigation.NavHostController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(albums) { album ->
            AlbumItem(
                album = album,
                modifier = Modifier
                    .padding(8.dp)
                    .height(150.dp)
                    .clickable {
                        navController.navigate("albumDetail/${album.id}")
                    }
            )
        }
    }
}

@Composable
fun AlbumDetail(album: Album, albums: MutableList<Album>, navController: androidx.navigation.NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = album.name)
        Text(text = "Artist: ${album.artistName}")

        Button(
            onClick = {
                albums.remove(album)
                navController.navigateUp()
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Supprimer")
        }

        Button(onClick = { navController.navigateUp() }) {
            Text("Retour")
        }
    }
}

@Composable
fun AlbumItem(album: Album, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(text = album.name)
        Text(text = album.artistName)
    }
}
