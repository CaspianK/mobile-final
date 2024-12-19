package com.example.afinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.afinal.data.AppDatabase
import com.example.afinal.models.User
import com.example.afinal.ui.theme.FinalTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = AppDatabase.getInstance(applicationContext)
        lifecycleScope.launch {
            val userDao = database.userDao()
            userDao.insertUser(User(id = 21, username = "John Doe", email = "john@example.com", passwordHash = "\$2a\$12\$hiE4jmXck9/kqVIOJZMHlOuYZqLOWXcOTdiLZ1lCp8HamlTt.PhyK"))
        }
//        val repository = CatFactRepository(
//            RetrofitInstance.api,
//            database.breedDao(),
//            database.catFactDao()
//        )
//        val viewModel = CatFactViewModel(repository)
        setContent {
            FinalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Text(
        text = "Hello, world!",
        modifier = modifier
    )
}