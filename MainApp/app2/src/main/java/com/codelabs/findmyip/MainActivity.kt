package com.codelabs.findmyip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.codelabs.findmyip.client.RepositoryInstance
import com.codelabs.findmyip.model.IpModel
import com.codelabs.findmyip.ui.theme.FindMyIPTheme
import com.codelabs.findmyip.utilities.ResourceState
import com.codelabs.findmyip.viewmodel.MainViewModel
import com.codelabs.findmyip.viewmodel.MainViewModelFactory

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
                    viewModel = ViewModelProvider(this, MainViewModelFactory(RepositoryInstance()))
                        .get(MainViewModel::class.java)
//                    viewModel = ViewModelProvider(this)[MainViewModel::class.java]
                    val ipRes by viewModel.ip.collectAsState()
                    showData(ipRes)
                }
            }
        }
    }
}


@Composable
fun showData(ipModel: ResourceState<IpModel>){
    when(ipModel){
        is ResourceState.Loading ->{
            Loader()
        }
        is ResourceState.Success ->{
            val response = (ipModel as ResourceState.Success).data
            Column {
                showText(name = response.ip)
                showText(name = response.countryName)
                showText(name = response.city)
                showText(name = response.country)
            }

        }
        is ResourceState.Error -> {
            val error = (ipModel as ResourceState.Error)
            showError("IP information Loading Fail")
        }
    }
}
@Composable
fun Loader(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment =  Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp),
            color = Color.Black
        )
    }
}
@Composable
fun showText(name: String?, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
fun showError(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}