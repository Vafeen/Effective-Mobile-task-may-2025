package com.example.emtask.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.emtask.ui.theme.EMTaskTheme
import com.example.navigation.NavRoot
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EMTaskTheme { NavRoot() }
        }
    }
}