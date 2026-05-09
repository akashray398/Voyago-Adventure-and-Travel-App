package com.akash.voyagotravel.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.akash.voyagotravel.ui.navigation.Screen
import java.util.*

data class DestinationDetail(
    val id: String,
    val name: String,
    val location: String,
    val country: String,
    val rating: Double,
    val weather: String,
    val duration: String,
    val budget: String,
    val description: String,
    val mainImage: String,
    val gallery: List<String>
)

fun getDestinationDetails(id: String?): DestinationDetail {
    return when (id) {
        "jaipur" -> DestinationDetail(
            id = "jaipur",
            name = "Jaipur - The Pink City",
            location = "Rajasthan",
            country = "India",
            rating = 4.7,
            weather = "32°C",
            duration = "4 Days",
            budget = "₹15,000",
            description = "Famous for its magnificent palaces and vibrant culture, Jaipur is the 'Pink City' of India. It's renowned for the iconic Hawa Mahal, majestic Amer Fort, and authentic Rajasthani Thali. The city belongs to the rich heritage of the Rajputana era, offering a royal experience with its bustling bazaars and traditional Kathak performances.",
            mainImage = "https://images.unsplash.com/photo-1524230572899-a752b3835840?auto=format&fit=crop&q=80&w=1200",
            gallery = generateLargeGallery("jaipur")
        )
        "kerala" -> DestinationDetail(
            id = "kerala",
            name = "Kerala - God's Own Country",
            location = "Alleppey",
            country = "India",
            rating = 4.8,
            weather = "27°C",
            duration = "6 Days",
            budget = "₹25,000",
            description = "Kerala is a tropical paradise famous for its tranquil backwaters, palm-lined beaches, and spice plantations. It is the birthplace of Ayurveda and the classical Kathakali dance. Visitors flock here for houseboat stays in Alleppey, the lush tea gardens of Munnar, and the traditional Sadhya feast served on banana leaves.",
            mainImage = "https://images.unsplash.com/photo-1593693397690-362cb9666fc2?auto=format&fit=crop&q=80&w=1200",
            gallery = generateLargeGallery("kerala")
        )
        "agra" -> DestinationDetail(
            id = "agra",
            name = "Agra - City of Love",
            location = "Uttar Pradesh",
            country = "India",
            rating = 4.9,
            weather = "30°C",
            duration = "2 Days",
            budget = "₹8,000",
            description = "Home to the Taj Mahal, one of the Seven Wonders of the World, Agra is a testament to eternal love. It belongs to the Mughal Empire's legacy, featuring the grand Agra Fort and Fatehpur Sikri. The city is famous for its intricate marble inlay work and the iconic 'Petha' sweet, offering a deep dive into medieval history.",
            mainImage = "https://images.unsplash.com/photo-1564507592333-c60657eea523?auto=format&fit=crop&q=80&w=1200",
            gallery = generateLargeGallery("agra")
        )
        "goa" -> DestinationDetail(
            id = "goa",
            name = "Goa - Sun & Sand",
            location = "North Goa",
            country = "India",
            rating = 4.6,
            weather = "29°C",
            duration = "5 Days",
            budget = "₹12,000",
            description = "Goa is India's premier beach destination, famous for its golden sands, vibrant nightlife, and Portuguese-inspired architecture. It belongs to a unique blend of Indian and European cultures, featuring 17th-century churches and tropical spice plantations. Known for its seafood and the lively Carnival festival.",
            mainImage = "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?auto=format&fit=crop&q=80&w=1200",
            gallery = generateLargeGallery("goa")
        )
        "leh_ladakh" -> DestinationDetail(
            id = "leh_ladakh",
            name = "Leh Ladakh - High Passes",
            location = "Ladakh",
            country = "India",
            rating = 4.9,
            weather = "15°C",
            duration = "8 Days",
            budget = "₹45,000",
            description = "A haven for adventure seekers, Ladakh is famous for its high-altitude mountain passes, crystal-clear lakes like Pangong Tso, and ancient Buddhist monasteries. It belongs to the high-desert landscape of the Himalayas, offering breathtaking trekking trails and a unique Tibetan-influenced culture.",
            mainImage = "https://images.unsplash.com/photo-1506461883276-594a12b11cf3?auto=format&fit=crop&q=80&w=1200", // NEW ICONIC MONASTERY IMAGE
            gallery = generateLargeGallery("ladakh")
        )
        "varanasi" -> DestinationDetail(
            id = "varanasi",
            name = "Varanasi - The Spiritual Heart",
            location = "Uttar Pradesh",
            country = "India",
            rating = 4.7,
            weather = "28°C",
            duration = "3 Days",
            budget = "₹10,000",
            description = "Varanasi is one of the world's oldest living cities, famous for its spiritual energy and the sacred River Ganges. It belongs to the core of Hindu tradition, renowned for the mesmerizing Ganga Aarti at Dashashwamedh Ghat. The city is famous for its narrow alleys, ancient temples, and fine Banarasi silk saris.",
            mainImage = "https://images.unsplash.com/photo-1561361513-2d000a50f0dc?auto=format&fit=crop&q=80&w=1200",
            gallery = generateLargeGallery("varanasi")
        )
        "hampi" -> DestinationDetail(
            id = "hampi",
            name = "Hampi - The Lost Empire",
            location = "Karnataka",
            country = "India",
            rating = 4.7,
            weather = "30°C",
            duration = "3 Days",
            budget = "₹18,000",
            description = "A UNESCO World Heritage site, Hampi is famous for its boulder-strewn landscape and the ruins of the Vijayanagara Empire. It belongs to a rich medieval history, featuring the Virupaksha Temple and the Stone Chariot. It's a surreal destination for history buffs and rock climbers alike.",
            mainImage = "https://images.unsplash.com/photo-1580191947416-62d35a55e71d?auto=format&fit=crop&q=80&w=1200",
            gallery = generateLargeGallery("hampi")
        )
        "bali" -> DestinationDetail(
            id = "bali",
            name = "Bali - Island of the Gods",
            location = "Ubud",
            country = "Indonesia",
            rating = 4.8,
            weather = "28°C",
            duration = "5 Days",
            budget = "₹39,999",
            description = "Bali is world-famous for its forested volcanic mountains, iconic rice paddies, and coral reefs. It belongs to a deeply spiritual Hindu culture, reflected in its thousands of temples and traditional Kecak fire dances. Ubud is the cultural heart, famous for its art markets and serene yoga retreats.",
            mainImage = "https://images.unsplash.com/photo-1537996194471-e657df975ab4?auto=format&fit=crop&q=80&w=1200",
            gallery = generateLargeGallery("bali")
        )
        "santorini" -> DestinationDetail(
            id = "santorini",
            name = "Santorini - Aegean Gem",
            location = "Oia",
            country = "Greece",
            rating = 4.9,
            weather = "24°C",
            duration = "4 Days",
            budget = "₹85,000",
            description = "Famous for its white-washed buildings with blue domes, Santorini offers the world's most stunning sunsets in Oia. It belongs to the Cyclades islands, formed by a massive volcanic eruption. Known for its unique volcanic beaches (Red and Black sand) and exquisite Mediterranean cuisine.",
            mainImage = "https://images.unsplash.com/photo-1570077188670-e3a8d69ac5ff?auto=format&fit=crop&q=80&w=1200",
            gallery = generateLargeGallery("santorini")
        )
        "kyoto" -> DestinationDetail(
            id = "kyoto",
            name = "Kyoto - Ancient Capital",
            location = "Kyoto",
            country = "Japan",
            rating = 4.8,
            weather = "22°C",
            duration = "5 Days",
            budget = "₹75,000",
            description = "Kyoto is the heart of traditional Japan, famous for its thousands of classical Buddhist temples, Zen gardens, and imperial palaces. It belongs to the era of Shoguns and Samurais, renowned for its Geisha districts (Gion) and the stunning Arashiyama Bamboo Grove. A city where tradition meets natural beauty.",
            mainImage = "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?auto=format&fit=crop&q=80&w=1200",
            gallery = generateLargeGallery("kyoto")
        )
        "paris" -> DestinationDetail(
            id = "paris",
            name = "Paris - City of Light",
            location = "Paris",
            country = "France",
            rating = 4.6,
            weather = "18°C",
            duration = "3 Days",
            budget = "₹95,000",
            description = "Paris is a global center for art, fashion, and gastronomy. Famous for the Eiffel Tower, Louvre Museum, and Notre-Dame Cathedral, it belongs to a rich history of revolution and romance. Known for its café culture, haute couture, and the charming streets of Montmartre.",
            mainImage = "https://images.unsplash.com/photo-1502602898657-3e91760cbb34?auto=format&fit=crop&q=80&w=1200",
            gallery = generateLargeGallery("paris")
        )
        "dubai" -> DestinationDetail(
            id = "dubai",
            name = "Dubai - Future City",
            location = "Dubai",
            country = "UAE",
            rating = 4.8,
            weather = "35°C",
            duration = "4 Days",
            budget = "₹65,000",
            description = "Dubai is famous for its ultramodern architecture, luxury shopping, and desert adventures. It belongs to a landscape of record-breaking feats, featuring the world's tallest building (Burj Khalifa) and the Palm Jumeirah islands. A futuristic oasis offering world-class entertainment and high-end dining.",
            mainImage = "https://images.unsplash.com/photo-1512453979798-5ea266f8880c?auto=format&fit=crop&q=80&w=1200",
            gallery = generateLargeGallery("dubai")
        )
        "new_york" -> DestinationDetail(
            id = "new_york",
            name = "New York - The Big Apple",
            location = "Manhattan",
            country = "USA",
            rating = 4.7,
            weather = "20°C",
            duration = "5 Days",
            budget = "₹1,10,000",
            description = "The city that never sleeps, New York is famous for its iconic skyline, Central Park, and Broadway. It belongs to the melting pot of global cultures, featuring the Statue of Liberty and Times Square. Renowned for its diverse food scene, world-class museums, and the energy of its five boroughs.",
            mainImage = "https://images.unsplash.com/photo-1496442226666-8d4d0e62e6e9?auto=format&fit=crop&q=80&w=1200",
            gallery = generateLargeGallery("new_york")
        )
        else -> DestinationDetail(
            id = "unknown",
            name = id?.replace("_", " ")?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() } ?: "Unknown",
            location = "Varies",
            country = "World",
            rating = 4.5,
            weather = "25°C",
            duration = "5 Days",
            budget = "₹50,000",
            description = "Discover the unique charm and beauty of this incredible destination. Explore the hidden gems, taste the local cuisine, and immerse yourself in the vibrant culture.",
            mainImage = "https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&q=80&w=1200",
            gallery = generateLargeGallery("generic")
        )
    }
}

fun generateLargeGallery(keyword: String): List<String> {
    val specificImages = when (keyword) {
        "jaipur" -> listOf(
            "https://images.unsplash.com/photo-1524230572899-a752b3835840", // Palace
            "https://images.unsplash.com/photo-1590050752117-23a9d7fc218c", // Food
            "https://images.unsplash.com/photo-1591533830207-6c382638848f", // Dance
            "https://images.unsplash.com/photo-1599661046289-e31897846e41", // Jewelry
            "https://images.unsplash.com/photo-1563290231-158f9679698c", // Amer Fort
            "https://images.unsplash.com/photo-1563450917021-9966770e0a5b", // Streets
            "https://images.unsplash.com/photo-1547758426-4176-a51e891785ef", // Night Palace
            "https://images.unsplash.com/photo-1603262110263-fb0112e7cc33", // Textiles
            "https://images.unsplash.com/photo-1592323985011-85e68b350f96", // Bazaar
            "https://images.unsplash.com/photo-1589308078059-be1415eab4c3"  // Thali 2
        )
        "kerala" -> listOf(
            "https://images.unsplash.com/photo-1593693397690-362cb9666fc2", // Backwaters
            "https://images.unsplash.com/photo-1544212911-37d4468f74a0", // Sadhya
            "https://images.unsplash.com/photo-1582201943021-e8e5b3061b7b", // Kathakali
            "https://images.unsplash.com/photo-1502444330042-d1a1ddf9bb5b", // Elephant
            "https://images.unsplash.com/photo-1540611025311-01df3cef54b5", // Tea
            "https://images.unsplash.com/photo-1516690561799-46d8f74f9abf", // Beach
            "https://images.unsplash.com/photo-1602216056096-3b40cc0c9944", // Plantation
            "https://images.unsplash.com/photo-1581441363689-1f3c3c414635", // Boat
            "https://images.unsplash.com/photo-1595815771614-ade9d652a65d", // Munnar
            "https://images.unsplash.com/photo-1511216173041-700d64210e34"  // Varkala
        )
        "agra" -> listOf(
            "https://images.unsplash.com/photo-1564507592333-c60657eea523", // Taj
            "https://images.unsplash.com/photo-1548013146-72479768bbaa", // Taj Detail
            "https://images.unsplash.com/photo-1585506942812-e72b29cef752", // Agra Fort
            "https://images.unsplash.com/photo-1590480436952-45e05697d84b", // Petha
            "https://images.unsplash.com/photo-1524492718563-0975608d0f1b", // Streets
            "https://images.unsplash.com/photo-1504535311059-d6527b14092b", // Yamuna
            "https://images.unsplash.com/photo-1571536802811-9dec070b407b", // Gardens
            "https://images.unsplash.com/photo-1568241434190-7c2725e2270b", // Mughal
            "https://images.unsplash.com/photo-1568241434190-7c2725e2270b", // Red Fort 2
            "https://images.unsplash.com/photo-1597541604100-f9b2f2814821"  // Tomb
        )
        "goa" -> listOf(
            "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2", // Beach
            "https://images.unsplash.com/photo-1542125387-c7128488903c", // Church
            "https://images.unsplash.com/photo-1566444855365-5c1613f1da90", // Seafood
            "https://images.unsplash.com/photo-1561567101-7ec97096e268", // Shacks
            "https://images.unsplash.com/photo-1590001158193-7f3daba5139a", // Sunset
            "https://images.unsplash.com/photo-1512480681822-2744246104ac", // Party
            "https://images.unsplash.com/photo-1515238152791-8216bfdf89a7", // Palms
            "https://images.unsplash.com/photo-1614082242765-7c98ca0f3df3", // Market
            "https://images.unsplash.com/photo-1561069934-eee225952461", // Ocean
            "https://images.unsplash.com/photo-1519046904884-53103b34b206"  // Resort
        )
        "ladakh" -> listOf(
            "https://images.unsplash.com/photo-1506461883276-594a12b11cf3", // Monastery 1
            "https://images.unsplash.com/photo-1527333656061-ca7adf608ae1", // Pangong Lake
            "https://images.unsplash.com/photo-1581791538302-03537b9c97bf", // Prayer Flags
            "https://images.unsplash.com/photo-1544085311-11a028465b03", // River Valley
            "https://images.unsplash.com/photo-1589136777351-fdc9c9ca0836", // Mountains Peak
            "https://images.unsplash.com/photo-1614092053702-861d8ec78912", // High Road
            "https://images.unsplash.com/photo-1570168007204-dfb528c6958f", // Nubra Valley
            "https://images.unsplash.com/photo-1626621341517-bbf3d9990a23", // Buddhism
            "https://images.unsplash.com/photo-1580980064731-893043818e6c", // Local Food
            "https://images.unsplash.com/photo-1506461883276-594a12b11cf3"  // Gompa
        )
        "varanasi" -> listOf(
            "https://images.unsplash.com/photo-1561361513-2d000a50f0dc", // Ghats
            "https://images.unsplash.com/photo-1561358309-158f9679698c", // Aarti
            "https://images.unsplash.com/photo-1585640381650-749e75704128", // Boat
            "https://images.unsplash.com/photo-1591533830207-6c382638848f", // Dance
            "https://images.unsplash.com/photo-1570133618391-450a80d199cc", // Temple
            "https://images.unsplash.com/photo-1590050752117-23a9d7fc218c", // Street Food
            "https://images.unsplash.com/photo-1571404177309-90696706e90a", // Sadhu
            "https://images.unsplash.com/photo-1562979314-eee745e700ae", // Silk
            "https://images.unsplash.com/photo-1512100356956-c12872638f6d", // Streets
            "https://images.unsplash.com/photo-1542359649-31e03cd4d909"  // Evening
        )
        "hampi" -> listOf(
            "https://images.unsplash.com/photo-1580191947416-62d35a55e71d", // Ruins
            "https://images.unsplash.com/photo-1563290231-158f9679698c", // Boulders
            "https://images.unsplash.com/photo-1602216056096-3b40cc0c9944", // Temple
            "https://images.unsplash.com/photo-1544085311-11a028465b03", // River
            "https://images.unsplash.com/photo-1591533830207-6c382638848f", // Carving
            "https://images.unsplash.com/photo-1594993877167-a0b9b6707d39", // Architecture
            "https://images.unsplash.com/photo-1581441363689-1f3c3c414635", // Coracle
            "https://images.unsplash.com/photo-1506744038136-46273834b3fb", // Landscape
            "https://images.unsplash.com/photo-1533105079780-92b9be482077", // Steps
            "https://images.unsplash.com/photo-1503177119275-0aa32b3a9368"  // Ruins 2
        )
        "bali" -> listOf(
            "https://images.unsplash.com/photo-1537996194471-e657df975ab4", // Temple
            "https://images.unsplash.com/photo-1537944434965-cf4679d1a598", // Beach
            "https://images.unsplash.com/photo-1469012846872-2313620d437e", // Food
            "https://images.unsplash.com/photo-1518548419970-58e3b4079ab2", // Rice
            "https://images.unsplash.com/photo-1558005530-d7c4cd66398b", // Dance
            "https://images.unsplash.com/photo-1552733407-5d5c46c3bb3b", // Forest
            "https://images.unsplash.com/photo-1534464539032-7518c465817e", // Waterfall
            "https://images.unsplash.com/photo-1520250497591-112f2f40a3f4", // Resort
            "https://images.unsplash.com/photo-1537944434965-cf4679d1a598", // Nusa
            "https://images.unsplash.com/photo-1537953391147-f459c0b0520a"  // Sunset
        )
        "santorini" -> listOf(
            "https://images.unsplash.com/photo-1570077188670-e3a8d69ac5ff", // Oia
            "https://images.unsplash.com/photo-1563789031959-4c02bcb413ef", // View
            "https://images.unsplash.com/photo-1571401835393-8c5f35328320", // Domes
            "https://images.unsplash.com/photo-1533105079780-92b9be482077", // Steps
            "https://images.unsplash.com/photo-1549488344-1f9b8d2bd1f3", // Food
            "https://images.unsplash.com/photo-1561069934-eee225952461", // Sea
            "https://images.unsplash.com/photo-1558431382-bb7b68c4b5d7", // Street
            "https://images.unsplash.com/photo-1549488344-1f9b8d2bd1f3", // Cuisine
            "https://images.unsplash.com/photo-1502784444187-359ac186c5bb", // Architecture
            "https://images.unsplash.com/photo-1571536802811-9dec070b407b"  // Village
        )
        "kyoto" -> listOf(
            "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e", // Temple
            "https://images.unsplash.com/photo-1545569341-9eb8b30979d9", // Forest
            "https://images.unsplash.com/photo-1476124369491-e7addf5db371", // Food
            "https://images.unsplash.com/photo-1493780490677-0ce274e8e3b5", // Gion
            "https://images.unsplash.com/photo-1490806678282-44195d773443", // Zen
            "https://images.unsplash.com/photo-1524413840807-0c3cb6fa808d", // Pagoda
            "https://images.unsplash.com/photo-1491884661903-0668f4401721", // Garden
            "https://images.unsplash.com/photo-1504198453319-5ce911bafcde", // Culture
            "https://images.unsplash.com/photo-1542359649-31e03cd4d909", // Autumn
            "https://images.unsplash.com/photo-1526481280693-3bfa756150f1"  // Street
        )
        "paris" -> listOf(
            "https://images.unsplash.com/photo-1502602898657-3e91760cbb34", // Eiffel
            "https://images.unsplash.com/photo-1499856871958-5b9627545d1a", // Louvre
            "https://images.unsplash.com/photo-1511739001486-6bfe10ce785f", // Cafe
            "https://images.unsplash.com/photo-1431274172761-fca41d930114", // Arc
            "https://images.unsplash.com/photo-1522093007474-d86e9bf7ba6f", // Seine
            "https://images.unsplash.com/photo-1503152394-c57de99ff126", // Bakery
            "https://images.unsplash.com/photo-1494236581339-7ac458ba7e70", // Art
            "https://images.unsplash.com/photo-1513581166391-887a96df91fa", // Night
            "https://images.unsplash.com/photo-1503917988258-f19782f4bc9a", // Sacre Coeur
            "https://images.unsplash.com/photo-1500313830540-7b6650a7cfee"  // Streets
        )
        "dubai" -> listOf(
            "https://images.unsplash.com/photo-1512453979798-5ea266f8880c", // Burj
            "https://images.unsplash.com/photo-1518684079-3c830dcef090", // Desert
            "https://images.unsplash.com/photo-1546412414-e1885261bb9b", // Palm
            "https://images.unsplash.com/photo-1512632510497-52f8ceaf99a4", // Food
            "https://images.unsplash.com/photo-1528702748617-c64d49f918af", // Mall
            "https://images.unsplash.com/photo-1526495124232-a02e198d4fca", // Souk
            "https://images.unsplash.com/photo-1523450001312-daa4e2e1bad6", // Beach
            "https://images.unsplash.com/photo-1522204523234-8729aa6e3d5f", // Fountain
            "https://images.unsplash.com/photo-1512453979798-5ea266f8880c", // Marina
            "https://images.unsplash.com/photo-1549488344-1f9b8d2bd1f3"  // Cuisine
        )
        "new_york" -> listOf(
            "https://images.unsplash.com/photo-1496442226666-8d4d0e62e6e9", // Skyline
            "https://images.unsplash.com/photo-1533929736458-ca588d08c8be", // Times Square
            "https://images.unsplash.com/photo-1522083165195-3424ed129620", // Central Park
            "https://images.unsplash.com/photo-1546198632-9ef6368bef12", // Food
            "https://images.unsplash.com/photo-1506744038136-46273834b3fb", // Brooklyn
            "https://images.unsplash.com/photo-1485871982721-9c44a7a698a3", // Subway
            "https://images.unsplash.com/photo-1511216173041-700d64210e34", // Library
            "https://images.unsplash.com/photo-1501196354995-cbb51c65aaea", // People
            "https://images.unsplash.com/photo-1533105079780-92b9be482077", // Architecture
            "https://images.unsplash.com/photo-1504109586057-7a2ae83d1338"  // Urban
        )
        else -> listOf(
            "https://images.unsplash.com/photo-1506744038136-46273834b3fb",
            "https://images.unsplash.com/photo-1539367628448-4bc5c9d171c8",
            "https://images.unsplash.com/photo-1503177119275-0aa32b3a9368",
            "https://images.unsplash.com/photo-1504109586057-7a2ae83d1338",
            "https://images.unsplash.com/photo-1512100356956-c12872638f6d",
            "https://images.unsplash.com/photo-1501785888041-af3ef285b470",
            "https://images.unsplash.com/photo-1476514525535-07fb3b4ae5f1",
            "https://images.unsplash.com/photo-1527631746610-bca00a040d60"
        )
    }

    // Diverse set of extra images to fill up to 20 unique photos
    val extraImages = listOf(
        "https://images.unsplash.com/photo-1536431311719-398b6704d4cc", // Map
        "https://images.unsplash.com/photo-1500835595327-8339ca553c39", // Flying
        "https://images.unsplash.com/photo-1488646953014-85cb44e25828", // Planning
        "https://images.unsplash.com/photo-1436491865332-7a61a109c0f2", // Window view
        "https://images.unsplash.com/photo-1504150559411-2d5830e60aa5", // Coffee
        "https://images.unsplash.com/photo-1523906834658-6e24ef2386f9", // Venice style
        "https://images.unsplash.com/photo-1505118380757-91f5f45d8de4", // Island
        "https://images.unsplash.com/photo-1526772662000-3f88f10405ff", // Trekking
        "https://images.unsplash.com/photo-1501555088652-021faa106b9b", // Mountain peak
        "https://images.unsplash.com/photo-1470770841072-f978cf4d019e", // House
        "https://images.unsplash.com/photo-1441974231531-c6227db76b6e", // Woods
        "https://images.unsplash.com/photo-1501446522557-3ecdd9bf9732", // Sunset beach
        "https://images.unsplash.com/photo-1518173946687-a4c8a983378a", // Rain
        "https://images.unsplash.com/photo-1530521954074-e64f6810b32d", // Resort pool
        "https://images.unsplash.com/photo-1506744038136-46273834b3fb", // Landscape 2
        "https://images.unsplash.com/photo-1469854523086-cc02fe5d8800"  // Road trip 2
    )

    // Filter out potential duplicates and take exactly 20 images
    return (specificImages + extraImages).distinct().take(20).map { "$it?auto=format&fit=crop&q=80&w=600" }
}

@Composable
fun DestinationDetailScreen(navController: NavHostController, destinationId: String?) {
    val scrollState = rememberScrollState()
    val destination = remember(destinationId) { getDestinationDetails(destinationId) }
    var isFavorite by remember { mutableStateOf(false) }
    
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .background(Color(0xFFE9ECEF))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(destination.mainImage)
                    .crossfade(true)
                    .build(),
                contentDescription = "Destination Hero Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.3f)),
                            startY = 300f
                        )
                    )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Black.copy(alpha = 0.4f), Color.Transparent)
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(380.dp))
            
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                color = Color.White,
                tonalElevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = destination.name,
                                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFF0077B6), modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("${destination.location}, ${destination.country}", color = Color.Gray, fontSize = 14.sp)
                            }
                        }
                        
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFFFEF3E7),
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFF39C12), modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(destination.rating.toString(), fontWeight = FontWeight.Bold, color = Color(0xFFF39C12))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        DetailInfoItem(Icons.Default.Cloud, destination.weather, "Weather")
                        DetailInfoItem(Icons.Default.Timer, destination.duration, "Duration")
                        DetailInfoItem(Icons.Default.AccountBalanceWallet, destination.budget, "Budget")
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = "About Destination",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = destination.description,
                        color = Color.Gray,
                        lineHeight = 24.sp,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = "Gallery (${destination.gallery.size} Photos)",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ) {
                        items(destination.gallery) { imageUrl ->
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(imageUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "Gallery Image",
                                modifier = Modifier
                                    .size(150.dp, 200.dp)
                                    .clip(RoundedCornerShape(20.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(120.dp))
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.9f))
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
            }
            IconButton(
                onClick = { isFavorite = !isFavorite },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.9f))
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else Color.Black
                )
            }
        }

        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            color = Color.Transparent
        ) {
            Box(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.White.copy(alpha = 0.9f), Color.White)
                        )
                    )
                    .padding(24.dp)
                    .navigationBarsPadding()
            ) {
                Button(
                    onClick = { navController.navigate(Screen.Booking.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0077B6)),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ) {
                    Text(
                        text = "Book Your Trip Now",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun DetailInfoItem(icon: androidx.compose.ui.graphics.vector.ImageVector, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF1F3F5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp), tint = Color(0xFF0077B6))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Text(text = label, color = Color.Gray, fontSize = 12.sp)
    }
}
