package com.geekforgeek.netfix_retrofit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen() {

    val retrofitInstance = RetrofitHelper.getInstance()
    val retrofitRepo = RetrofitRepo(retrofitInstance)
    var moviesList = arrayListOf<MovieModel>()
    val onlyOnNetflix by retrofitRepo.onlyOnNetflix.collectAsState()
    val blockBaster by retrofitRepo.blockBaster.collectAsState()
    val trending by retrofitRepo.trending.collectAsState()
    val watchAgain by retrofitRepo.watchAgain.collectAsState()
    moviesList.add(
        MovieModel(
            title = "",
            description = "",
            image = "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_QL75_UX380_CR0,1,380,562_.jpg",
            rating = "",
            year = ""
        )
    )
    moviesList.add(
        MovieModel(
            title = "",
            description = "",
            image = "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL75_UY562_CR8,0,380,562_.jpg",
            rating = "",
            year = ""
        )
    )
    moviesList.add(
        MovieModel(
            title = "",
            description = "",
            image = "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_QL75_UX380_CR0,0,380,562_.jpg",
            rating = "",
            year = ""
        )
    )

    LaunchedEffect(Unit) {
        retrofitRepo.getData()
    }

    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(R.drawable.netflix_logo_icon),
                        "netflix logo",
                        modifier = Modifier.size(32.dp)
                    )
                },
                actions = {
                    Icon(Icons.Default.Search, "search Icon", tint = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Black)
            )
        },
        content = { innerPadding ->

            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 56.dp).verticalScroll(rememberScrollState()),
            ) {

                Spacer(
                    Modifier
                        .height(88.dp)
                        .padding(innerPadding)
                )

                Text(
                    text = "Only on Netflix",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )

                LazyRow {
                    items(onlyOnNetflix) { list ->
                        Card(
                            onClick = {},
                            modifier = Modifier.padding(end = 8.dp, top = 12.dp),
                            elevation = CardDefaults.cardElevation(disabledElevation = 8.dp),

                            ) {
                            Box {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(list.image)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(165.dp, 210.dp)
                                )

                                Image(
                                    painter = painterResource(R.drawable.netflix_logo_icon),
                                    "netflix logo",
                                    modifier = Modifier
                                        .size(32.dp)
                                        .padding(8.dp)
                                )
                            }
                        }
                    }

                }

                NetflexLayout("Blockbuster Action", blockBaster)
                NetflexLayout("Trending Now", trending)
                NetflexLayout("Watch it Again", watchAgain)
            }

        }
    )

}

@Composable
fun NetflexLayout(title: String, movieList: ArrayList<MovieModel>) {

    Spacer(Modifier.height(12.dp))
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Color.White
    )

    LazyRow {
        items(movieList) { list ->
            Card(
                onClick = {},
                modifier = Modifier.padding(end = 8.dp, top = 12.dp),
                elevation = CardDefaults.cardElevation(disabledElevation = 8.dp),

                ) {
                Box {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(list.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(120.dp, 180.dp)
                    )

                    Image(
                        painter = painterResource(R.drawable.netflix_logo_icon),
                        "netflix logo",
                        modifier = Modifier
                            .size(32.dp)
                            .padding(8.dp)
                    )
                }

            }
        }

    }
}
