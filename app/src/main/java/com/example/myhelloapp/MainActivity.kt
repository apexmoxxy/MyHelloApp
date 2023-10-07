package com.example.myhelloapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myhelloapp.ui.theme.MyHelloAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyHelloAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "homepage"){
                        composable(route = "homepage") {
                            WelcomeScreen(navController)
                        }
                        composable(route = "pagetwo") {
                            PageTwo()
                        }
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(navController: NavController) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var baseURL = "http://10.0.2.2:1337/api/"
    var retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LoginService::class.java)
    var call = retrofit.getData(LoginData("royhan","ASdasdas148\$"))
    call.enqueue(object : Callback<LoginResponse>{
        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
            if (response.code() == 200){
                var jwt = response.body()?.jwt

            }
        }

        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            print(t.message)
        }

    })
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Jokowi") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        bottomBar = {
            BottomAppBar {
                Text(text = "QRis", textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())
            }
        }
    ) {
        innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = username,
                onValueChange = { newText -> username = newText },
                label = { Text("Username") },
                modifier = Modifier.padding(bottom = 5.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { newText -> password = newText },
                label = { Text("Password") },
                modifier = Modifier.padding(bottom = 24.dp)
            )
            ElevatedButton(onClick = { navController.navigate("pagetwo") }) {
                Text(text = "Login")
            }
        }
    }
}

@Composable
fun PageTwo(){
    Text("Login successfully")
}
//fun Greeting(){
//    var username by remember {
//        mutableStateOf(TextFieldValue(""))
//    }
//    var password by remember {
//        mutableStateOf(TextFieldValue(""))
//    }
//    Column ( modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.Center) {
//        Row {
//            Text(text = "ROMI", fontSize = 24.sp, textAlign = TextAlign.Center)
//            Text(text = "Rombeng Masa kini", fontSize = 20.sp, textAlign = TextAlign.Justify)
//        }
//        ElevatedButton(onClick = { /*TODO*/ }) {
//            Text(text = "Click")
//        }
//        TextField(value = , onValueChange = )
//    }
//}
