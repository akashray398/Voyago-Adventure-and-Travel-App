package com.akash.voyagotravel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.akash.voyagotravel.ui.navigation.VoyagoNavGraph
import com.akash.voyagotravel.ui.theme.VoyagoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VoyagoTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // For now, we don't apply innerPadding to NavHost as some screens might be fullscreen
                    // but we can pass it if needed.
                    VoyagoNavGraph(navController = navController)
                }
            }
        }
    }
}
