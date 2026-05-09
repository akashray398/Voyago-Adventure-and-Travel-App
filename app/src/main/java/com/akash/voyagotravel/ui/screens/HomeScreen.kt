package com.akash.voyagotravel.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.akash.voyagotravel.ui.navigation.Screen
import com.akash.voyagotravel.ui.navigation.VoyagoBottomNavigation

@Composable
fun HomeScreen(navController: NavHostController) {
    var selectedCategory by remember { mutableStateOf("Beach") }
    
    Scaffold(
        bottomBar = { VoyagoBottomNavigation(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Header()
            Spacer(modifier = Modifier.height(24.dp))
            SearchBar()
            Spacer(modifier = Modifier.height(24.dp))
            Categories(selectedCategory) { selectedCategory = it }
            Spacer(modifier = Modifier.height(32.dp))
            
            // Dynamic Section based on Category
            CategoryExploreSection(navController, selectedCategory)
            
            Spacer(modifier = Modifier.height(32.dp))
            FeaturedDestinations(navController)
            Spacer(modifier = Modifier.height(32.dp))
            PopularPlaces(navController)
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Hello, Akash",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray)
            )
            Text(
                text = "Where to next?",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
        }
        AsyncImage(
            model = "https://images.unsplash.com/photo-1527631746610-bca00a040d60?auto=format&fit=crop&q=80&w=200",
            contentDescription = "Profile",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(2.dp, Color(0xFF0077B6), CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search destinations...", color = Color.Gray) },
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF1F3F5),
                unfocusedContainerColor = Color(0xFFF1F3F5),
                disabledContainerColor = Color(0xFFF1F3F5),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF0077B6)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.FilterList, contentDescription = null, tint = Color.White)
        }
    }
}

@Composable
fun Categories(selectedCategory: String, onCategorySelected: (String) -> Unit) {
    val categories = listOf("Beach", "Mountain", "Trek", "Resort", "Adventure")
    
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(categories) { category ->
            val isSelected = category == selectedCategory
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .background(if (isSelected) Color(0xFF0077B6) else Color(0xFFF1F3F5))
                    .clickable { onCategorySelected(category) }
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Text(
                    text = category,
                    color = if (isSelected) Color.White else Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

data class CategoryItem(
    val id: String,
    val name: String,
    val location: String,
    val price: String,
    val image: String,
    val rating: Double,
    val category: String
)

val allCategoryItems = listOf(
    // Beach
    CategoryItem("bali", "Bali Bliss", "Indonesia", "₹39,999", "https://images.unsplash.com/photo-1537996194471-e657df975ab4", 4.8, "Beach"),
    CategoryItem("goa", "Goa Sands", "India", "₹12,000", "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2", 4.6, "Beach"),
    CategoryItem("santorini", "Oia Cliffs", "Greece", "₹85,000", "https://images.unsplash.com/photo-1570077188670-e3a8d69ac5ff", 4.9, "Beach"),
    CategoryItem("maldives", "Azure Atolls", "Maldives", "₹95,000", "https://images.unsplash.com/photo-1514282401047-d79a71a590e8", 4.9, "Beach"),
    
    // Mountain
    CategoryItem("swiss_alps", "Swiss Peaks", "Switzerland", "₹1,20,000", "https://images.unsplash.com/photo-1531310197839-ccf54634509e", 4.7, "Mountain"),
    CategoryItem("leh_ladakh", "Ladakh High", "India", "₹45,000", "https://images.unsplash.com/photo-1581791538302-03537b9c97bf", 4.9, "Mountain"),
    CategoryItem("manali", "Manali Valley", "India", "₹18,000", "https://images.unsplash.com/photo-1591263591466-601729066672", 4.5, "Mountain"),
    CategoryItem("nepal", "Everest View", "Nepal", "₹1,10,000", "https://images.unsplash.com/photo-1544735716-392fe2489ffa", 4.9, "Mountain"),

    // Trek
    CategoryItem("hampi", "Hampi Ruins", "India", "₹18,000", "https://images.unsplash.com/photo-1580191947416-62d35a55e71d", 4.7, "Trek"),
    CategoryItem("machu_picchu", "Inca Trail", "Peru", "₹1,50,000", "https://images.unsplash.com/photo-1587595431973-160d0d94add1", 4.9, "Trek"),
    CategoryItem("valley_flowers", "Flower Trek", "India", "₹22,000", "https://images.unsplash.com/photo-1590050752117-23a9d7fc218c", 4.8, "Trek"),
    CategoryItem("kilimanjaro", "Kili Summit", "Tanzania", "₹1,80,000", "https://images.unsplash.com/photo-1589136777351-fdc9c9ca0836", 4.8, "Trek"),

    // Resort
    CategoryItem("dubai", "Burj Al Arab", "UAE", "₹2,50,000", "https://images.unsplash.com/photo-1512453979798-5ea266f8880c", 4.9, "Resort"),
    CategoryItem("udaipur", "Lake Palace", "India", "₹45,000", "https://images.unsplash.com/photo-1603262110263-fb0112e7cc33", 4.8, "Resort"),
    CategoryItem("maldives_resort", "Water Villa", "Maldives", "₹1,40,000", "https://images.unsplash.com/photo-1439066615861-d1af74d74000", 4.9, "Resort"),
    CategoryItem("mauritius", "Coral Resort", "Mauritius", "₹85,000", "https://images.unsplash.com/photo-1552083375-1447ce886485", 4.7, "Resort"),

    // Adventure
    CategoryItem("queenstown", "Bungy Jump", "New Zealand", "₹1,95,000", "https://images.unsplash.com/photo-1506744038136-46273834b3fb", 4.9, "Adventure"),
    CategoryItem("iceland", "Glacier Walk", "Iceland", "₹1,65,000", "https://images.unsplash.com/photo-1504109586057-7a2ae83d1338", 4.8, "Adventure"),
    CategoryItem("rishikesh", "River Rafting", "India", "₹12,000", "https://images.unsplash.com/photo-1511216173041-700d64210e34", 4.7, "Adventure"),
    CategoryItem("moab", "Skydiving", "USA", "₹1,45,000", "https://images.unsplash.com/photo-1528127269322-539801943592", 4.8, "Adventure")
)

@Composable
fun CategoryExploreSection(navController: NavHostController, category: String) {
    val filteredItems = allCategoryItems.filter { it.category == category }
    
    Column {
        Text(
            text = "Explore $category",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(filteredItems) { item ->
                CategoryDestinationCard(navController, item)
            }
        }
    }
}

@Composable
fun CategoryDestinationCard(navController: NavHostController, item: CategoryItem) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(280.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable { navController.navigate(Screen.Detail.createRoute(item.id)) }
    ) {
        AsyncImage(
            model = item.image + "?auto=format&fit=crop&q=80&w=400",
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(item.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(item.location, color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
            Text(item.price, color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 14.sp)
        }
        
        Surface(
            modifier = Modifier.padding(12.dp).align(Alignment.TopEnd),
            color = Color.White.copy(alpha = 0.9f),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFF39C12), modifier = Modifier.size(12.dp))
                Spacer(modifier = Modifier.width(2.dp))
                Text(item.rating.toString(), fontWeight = FontWeight.Bold, fontSize = 10.sp)
            }
        }
    }
}

@Composable
fun FeaturedDestinations(navController: NavHostController) {
    val featured = listOf(
        FeaturedItem("bali", "Bali, Indonesia", "₹39,999", "https://images.unsplash.com/photo-1537996194471-e657df975ab4?auto=format&fit=crop&q=80&w=600"),
        FeaturedItem("leh_ladakh", "Ladakh, India", "₹45,000", "https://images.unsplash.com/photo-1506461883276-594a12b11cf3?auto=format&fit=crop&q=80&w=600"),
        FeaturedItem("santorini", "Santorini, Greece", "₹85,000", "https://images.unsplash.com/photo-1570077188670-e3a8d69ac5ff?auto=format&fit=crop&q=80&w=600"),
        FeaturedItem("agra", "Agra, India", "₹8,000", "https://images.unsplash.com/photo-1564507592333-c60657eea523?auto=format&fit=crop&q=80&w=600"),
        FeaturedItem("swiss_alps", "Swiss Alps", "₹1,20,000", "https://images.unsplash.com/photo-1531310197839-ccf54634509e?auto=format&fit=crop&q=80&w=600"),
        FeaturedItem("dubai", "Dubai, UAE", "₹65,000", "https://images.unsplash.com/photo-1512453979798-5ea266f8880c?auto=format&fit=crop&q=80&w=600")
    )

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Featured Destinations",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            TextButton(onClick = { navController.navigate(Screen.Favorites.route) }) {
                Text("See All", color = Color(0xFF0077B6), fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(end = 24.dp)
        ) {
            items(featured) { item ->
                DestinationCard(navController, item.id, item.name, item.price, item.image)
            }
            item {
                SeeMoreCard { navController.navigate(Screen.Favorites.route) }
            }
        }
    }
}

@Composable
fun SeeMoreCard(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .height(320.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(Color(0xFFF1F3F5))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = Color(0xFF0077B6),
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "See More",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Text(
                text = "Explore All",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
            )
        }
    }
}

data class FeaturedItem(val id: String, val name: String, val price: String, val image: String)

@Composable
fun DestinationCard(navController: NavHostController, id: String, name: String, price: String, image: String) {
    Box(
        modifier = Modifier
            .width(240.dp)
            .height(320.dp)
            .clip(RoundedCornerShape(28.dp))
            .clickable { navController.navigate(Screen.Detail.createRoute(id)) }
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                        startY = 400f
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {
            Text(name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("From $price", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
        }
        
        // Rating Badge
        Surface(
            modifier = Modifier.padding(16.dp).align(Alignment.TopEnd),
            color = Color.White.copy(alpha = 0.9f),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFF39C12), modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("4.8", fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun PopularPlaces(navController: NavHostController) {
    val popular = listOf(
        PopularItem("kyoto", "Kyoto", "Japan", "₹75,000", "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?auto=format&fit=crop&q=80&w=200"),
        PopularItem("paris", "Paris", "France", "₹95,000", "https://images.unsplash.com/photo-1502602898657-3e91760cbb34?auto=format&fit=crop&q=80&w=200"),
        PopularItem("cairo", "Cairo", "Egypt", "₹55,000", "https://images.unsplash.com/photo-1572252017450-4824659a8c6a?auto=format&fit=crop&q=80&w=200")
    )

    Column {
        Text(
            text = "Popular Places",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(16.dp))
        popular.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFF8F9FA))
                    .clickable { navController.navigate(Screen.Detail.createRoute(item.id)) }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = item.image,
                    contentDescription = null,
                    modifier = Modifier.size(70.dp).clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(item.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(item.location, color = Color(0xFF0077B6), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(horizontalAlignment = Alignment.End) {
                    Text(item.price, fontWeight = FontWeight.ExtraBold, color = Color.Black, fontSize = 16.sp)
                    Text("/person", color = Color.Gray, fontSize = 11.sp)
                }
            }
        }
    }
}

data class PopularItem(val id: String, val name: String, val location: String, val price: String, val image: String)
