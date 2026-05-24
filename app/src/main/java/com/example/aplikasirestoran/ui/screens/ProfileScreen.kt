package com.example.aplikasirestoran.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplikasirestoran.data.RestaurantPreferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    restaurantPrefs: RestaurantPreferences,
    onNavigateToEdit: () -> Unit,
    onNavigateBack: () -> Unit
) {
    // Gunakan State agar refresh otomatis saat kembali dari EditProfile
    // Dengan collectAsState dari Flow atau cukup re-read saat composable di-recompose
    var profile by remember { mutableStateOf(restaurantPrefs.getProfile()) }

    // Refresh data saat layar ini aktif kembali (menggunakan LaunchedEffect + key)
    // Cara sederhana: baca ulang setiap kali composable direkomposisi
    DisposableEffect(Unit) {
        profile = restaurantPrefs.getProfile()
        onDispose { }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Profil Restoran", fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
        ) {
            // Header dengan avatar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(bottom = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(88.dp)
                            .background(
                                MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("🏪", fontSize = 44.sp)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = profile.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height((-20).dp))

            // Card info profil
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    ProfileInfoItem(
                        icon = Icons.Default.Store,
                        label = "Nama Restoran",
                        value = profile.name
                    )
                    Divider(modifier = Modifier.padding(vertical = 12.dp))
                    ProfileInfoItem(
                        icon = Icons.Default.LocationOn,
                        label = "Alamat",
                        value = profile.address
                    )
                    Divider(modifier = Modifier.padding(vertical = 12.dp))
                    ProfileInfoItem(
                        icon = Icons.Default.Info,
                        label = "Deskripsi",
                        value = profile.description
                    )
                    Divider(modifier = Modifier.padding(vertical = 12.dp))
                    ProfileInfoItem(
                        icon = Icons.Default.Schedule,
                        label = "Jam Operasional",
                        value = profile.hours
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tombol Edit
            Button(
                onClick = {
                    onNavigateToEdit()
                    // Refresh profile setelah kembali
                    profile = restaurantPrefs.getProfile()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Default.Edit, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Edit Profil", fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun ProfileInfoItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(verticalAlignment = Alignment.Top) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Medium
            )
        }
    }
}