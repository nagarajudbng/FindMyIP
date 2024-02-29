package com.codelabs.findmyip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.codelabs.findmyip.data.remote.RepositoryImpl
import com.codelabs.findmyip.model.IPModel
import com.codelabs.findmyip.presentation.MainViewModel
import com.codelabs.findmyip.presentation.MainViewModelFactory
import com.codelabs.findmyip.ui.theme.FindMyIPTheme
import com.codelabs.findmyip.utiles.ResponseState

class MainActivity : ComponentActivity() {
    lateinit var viewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindMyIPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    viewModel = ViewModelProvider(this,MainViewModelFactory(RepositoryImpl()))
                        .get(MainViewModel::class.java)
                    val ip by viewModel.ip.collectAsState()
                    updateUI(ip)
                }
            }
        }
    }
}

@Composable
fun updateUI(response: ResponseState<IPModel>, modifier: Modifier = Modifier){

    when(response){
        is ResponseState.Loading->{
            loading()
        }
        is ResponseState.Success ->{
            var ipModel = response.data
            ipModel.ip?.let {
                Text(
                    text = it,
                    modifier
                )
            }
        }
        is ResponseState.Fail -> {
            var message: String = response.error
            Text(
                text = message,
                modifier
            )
        }
    }
}
@Composable
fun loading(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(40.dp),
            color = Color.Black
        )
    }
}











@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FindMyIPTheme {
        Greeting("Android")
    }
}