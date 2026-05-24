package com.example.aplikasirestoran.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aplikasirestoran.data.RestaurantPreferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    restaurantPrefs: RestaurantPreferences,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    // Pre-fill form dengan data dari SharedPreferences
    var name by remember { mutableStateOf(restaurantPrefs.getName()) }
    var address by remember { mutableStateOf(restaurantPrefs.getAddress()) }
    var description by remember { mutableStateOf(restaurantPrefs.getDescription()) }
    var hours by remember { mutableStateOf(restaurantPrefs.getHours()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Edit Profil", fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Batal")
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
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Perbarui informasi restoran Anda",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )

            // Field: Nama Restoran
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nama Restoran") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(14.dp)
            )

            // Field: Alamat
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Alamat") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2,
                maxLines = 3,
                shape = RoundedCornerShape(14.dp)
            )

            // Field: Deskripsi
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Deskripsi Singkat") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 5,
                shape = RoundedCornerShape(14.dp)
            )

            // Field: Jam Buka
            OutlinedTextField(
                value = hours,
                onValueChange = { hours = it },
                label = { Text("Jam Operasional") },
                placeholder = { Text("Contoh: Senin-Minggu: 08.00 - 22.00 WIB") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tombol Simpan
            Button(
                onClick = {
                    // Simpan ke SharedPreferences
                    restaurantPrefs.saveProfile(
                        name = name.trim(),
                        address = address.trim(),
                        description = description.trim(),
                        hours = hours.trim()
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                enabled = name.isNotBlank() && address.isNotBlank()
            ) {
                Text("💾  Simpan Perubahan", fontWeight = FontWeight.SemiBold)
            }

            // Tombol Batal
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text("Batal", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}