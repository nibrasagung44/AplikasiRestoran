package com.example.aplikasirestoran.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

val StarGold    = Color(0xFFFFC107)
val StarEmpty   = Color(0xFFD4C4BB)

@Composable
fun StarRatingBar(
    currentRating: Int,
    onRatingChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    starCount: Int = 5
) {
    // Tracking bintang yang sedang "dipencet" untuk animasi bounce
    var animatingIndex by remember { mutableIntStateOf(-1) }

    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            for (index in 1..starCount) {
                val isFilled = index <= currentRating

                // Animasi scale saat bintang diklik
                val animatedScale by animateFloatAsState(
                    targetValue  = if (animatingIndex == index) 1.35f else 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness    = Spring.StiffnessHigh
                    ),
                    finishedListener = { animatingIndex = -1 },
                    label = "star_scale_$index"
                )

                Icon(
                    imageVector = if (isFilled) Icons.Filled.Star else Icons.Outlined.StarOutline,
                    contentDescription = "Bintang $index",
                    tint = if (isFilled) StarGold else StarEmpty,
                    modifier = Modifier
                        .size(36.dp)
                        .scale(animatedScale)
                        .clickable {
                            // Klik bintang yang sama → reset ke 0 (hapus rating)
                            val newRating = if (currentRating == index) 0 else index
                            animatingIndex = index
                            onRatingChanged(newRating)
                        }
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Label teks rating
            Text(
                text = when (currentRating) {
                    0    -> "Belum dinilai"
                    1    -> "⭐ Mengecewakan"
                    2    -> "⭐⭐ Kurang"
                    3    -> "⭐⭐⭐ Cukup Enak"
                    4    -> "⭐⭐⭐⭐ Enak Sekali"
                    else -> "⭐⭐⭐⭐⭐ Luar Biasa!"
                },
                style     = MaterialTheme.typography.bodySmall,
                color     = if (currentRating > 0)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f),
                fontWeight = FontWeight.Medium
            )
        }
    }
}