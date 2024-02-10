@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.exercise5.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.exercise5.ui.component.Toolbar
import com.example.exercise5.ui.theme.Exercise5Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val myViewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Exercise5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieCompose(myViewModel)
                }
            }
        }
    }
}

@Composable
fun MovieCompose(viewModel: MainViewModel) {

    val movies by viewModel.dataMovies.observeAsState(emptyList())
    val loading by viewModel.loadingData.observeAsState(false)

    LaunchedEffect(Unit) {
        viewModel.fetchPopularMovie()
    }


    Scaffold (
        topBar = {
            Toolbar(title = "Toolbar", modifier = Modifier.fillMaxWidth())
        }
    ) { paddingValues ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            val (title, movieList, loading) = createRefs()
            Text(
                text = "Simple List Example",
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                    }
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            LazyColumn(
                modifier = Modifier
                    .constrainAs(movieList) {
                        top.linkTo(title.bottom)
                    }
                    .fillMaxWidth()
            ) {

                items(movies) { movie ->
                    Text(text = movie.title, modifier = Modifier.fillMaxWidth())
                    Divider()
                }
            }


        }

    }

}
