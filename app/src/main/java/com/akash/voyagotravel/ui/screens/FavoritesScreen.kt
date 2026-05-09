package com.akash.voyagotravel.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.akash.voyagotravel.ui.navigation.VoyagoBottomNavigation
import com.akash.voyagotravel.ui.navigation.Screen
import coil.compose.AsyncImage

// Professional Data Model
data class FavoriteDestination(
    val id: String,
    val name: String,
    val location: String,
    val image: String,
    val rating: Double,
    val price: String,
    val tag: String
)

// Mock Backend Repository with expanded list
object FavoritesRepository {
    private val _favorites = mutableStateListOf(
        // International
        FavoriteDestination("bali", "Bali Adventure", "Indonesia", "https://images.unsplash.com/photo-1537996194471-e657df975ab4?auto=format&fit=crop&q=80&w=400", 4.8, "₹39,999", "Trending"),
        FavoriteDestination("santorini", "Santorini Escape", "Greece", "https://images.unsplash.com/photo-1570077188670-e3a8d69ac5ff?auto=format&fit=crop&q=80&w=400", 4.9, "₹85,000", "Luxury"),
        FavoriteDestination("kyoto", "Kyoto Tradition", "Japan", "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?auto=format&fit=crop&q=80&w=400", 4.8, "₹75,000", "Culture"),
        FavoriteDestination("swiss_alps", "Swiss Alps", "Switzerland", "https://images.unsplash.com/photo-1531310197839-ccf54634509e?auto=format&fit=crop&q=80&w=400", 4.7, "₹1,20,000", "Nature"),
        FavoriteDestination("paris", "Paris Romance", "France", "https://images.unsplash.com/photo-1502602898657-3e91760cbb34?auto=format&fit=crop&q=80&w=400", 4.6, "₹95,000", "Romantic"),
        FavoriteDestination("cairo", "Cairo Pyramids", "Egypt", "https://images.unsplash.com/photo-1572252017450-4824659a8c6a?auto=format&fit=crop&q=80&w=400", 4.5, "₹55,000", "History"),
        FavoriteDestination("new_york", "NYC Skyline", "USA", "https://images.unsplash.com/photo-1496442226666-8d4d0e62e6e9?auto=format&fit=crop&q=80&w=400", 4.7, "₹1,10,000", "City"),
        FavoriteDestination("dubai", "Dubai Luxury", "UAE", "https://images.unsplash.com/photo-1512453979798-5ea266f8880c?auto=format&fit=crop&q=80&w=400", 4.8, "₹65,000", "Modern"),
        
        // India
        FavoriteDestination("jaipur", "Pink City", "Jaipur, India", "https://images.unsplash.com/photo-1477584264176-a51e891785ef?auto=format&fit=crop&q=80&w=400", 4.7, "₹15,000", "Heritage"),
        FavoriteDestination("goa", "Beach Paradise", "Goa, India", "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?auto=format&fit=crop&q=80&w=400", 4.6, "₹12,000", "Relax"),
        FavoriteDestination("kerala", "Backwaters", "Kerala, India", "https://images.unsplash.com/photo-1593693397690-362cb9666fc2?auto=format&fit=crop&q=80&w=400", 4.8, "₹25,000", "Nature"),
        FavoriteDestination("leh_ladakh", "High Passes", "Ladakh, India", "https://images.unsplash.com/photo-1581791538302-03537b9c97bf?auto=format&fit=crop&q=80&w=400", 4.9, "₹45,000", "Adventure"),
        FavoriteDestination("varanasi", "Holy Ganges", "Varanasi, India", "https://images.unsplash.com/photo-1561361513-2d000a50f0dc?auto=format&fit=crop&q=80&w=400", 4.7, "₹10,000", "Spiritual"),
        FavoriteDestination("agra", "Taj Mahal", "Agra, India", "https://images.unsplash.com/photo-1564507592333-c60657eea523?auto=format&fit=crop&q=80&w=400", 4.9, "₹8,000", "Iconic"),
        FavoriteDestination("hampi", "Ruins of Hampi", "Karnataka, India", "https://images.unsplash.com/photo-1580191947416-62d35a55e71d?auto=format&fit=crop&q=80&w=400", 4.7, "₹18,000", "Antique")
    )
    
    val favorites: List<FavoriteDestination> get() = _favorites

    fun removeFavorite(id: String) {
        _favorites.removeAll { it.id == id }
    }
}

@Composable
fun FavoritesScreen(navController: NavHostController) {
    val favoriteList = FavoritesRepository.favorites

    Scaffold(
        bottomBar = { VoyagoBottomNavigation(navController) },
        containerColor = Color(0xFFF8F9FA)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "My Wishlist",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF1B4332)
                )
            )
            
            Text(
                text = "${favoriteList.size} destinations saved",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            if (favoriteList.isEmpty()) {
                EmptyFavorites()
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(favoriteList, key = { it.id }) { destination ->
                        FavoriteDestinationCard(
                            destination = destination,
                            onClick = { navController.navigate(Screen.Detail.createRoute(destination.id)) },
                            onRemove = { FavoritesRepository.removeFavorite(destination.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteDestinationCard(
    destination: FavoriteDestination,
    onClick: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            ) {
                AsyncImage(
                    model = destination.image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                
                // Tag Badge
                Surface(
                    modifier = Modifier.padding(12.dp).align(Alignment.TopStart),
                    color = Color.Black.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = destination.tag,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                // Remove Button
                IconButton(
                    onClick = onRemove,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(32.dp)
                        .background(Color.White.copy(alpha = 0.9f), CircleShape)
                ) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "Remove",
                        tint = Color.Red,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = destination.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFF0077B6),
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = destination.location,
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = destination.price,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF2D6A4F),
                        fontSize = 14.sp
                    )
                    
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFF39C12),
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = destination.rating.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyFavorites() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Default.Favorite,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = Color.LightGray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Your Wishlist is Empty",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Start exploring and save your\nfavorite destinations here.",
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            color = Color.Gray
        )
    }
}
