package com.akash.voyagotravel.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.akash.voyagotravel.ui.navigation.VoyagoBottomNavigation
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { VoyagoBottomNavigation(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.Start)
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Avatar
            Box(contentAlignment = Alignment.BottomEnd) {
                AsyncImage(
                    model = "https://images.unsplash.com/photo-1542359649-31e03cd4d909?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8YWR2ZW50dXJlfGVufDB8fDB8fHww",
                    contentDescription = null,
                    modifier = Modifier.size(120.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF0077B6))
                        .border(2.dp, Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Edit, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text("Akash Yadav", style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold))
            Text("adventure_traveler@voyago.com", color = Color.Gray)
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Stats
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                ProfileStat("24", "Trips")
                ProfileStat("12", "Countries")
                ProfileStat("4.9", "Rating")
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Menu
            Column(modifier = Modifier.fillMaxWidth()) {
                ProfileMenuItem(Icons.Default.History, "Travel History")
                ProfileMenuItem(Icons.Default.Payment, "Payment Methods")
                ProfileMenuItem(Icons.Default.Notifications, "Notifications")
                ProfileMenuItem(Icons.Default.Settings, "Settings")
                ProfileMenuItem(Icons.Default.Logout, "Logout", tint = Color.Red)
            }
        }
    }
}

@Composable
fun ProfileStat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text(text = label, color = Color.Gray, fontSize = 14.sp)
    }
}

@Composable
fun ProfileMenuItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, tint: Color = Color.Black) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF1F3F5))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = tint)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = label, fontWeight = FontWeight.Medium, color = tint)
        Spacer(modifier = Modifier.weight(1f))
        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.Gray)
    }
}
