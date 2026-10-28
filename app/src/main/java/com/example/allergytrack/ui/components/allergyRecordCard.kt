package com.example.allergytrack.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.allergytrack.model.AllergyRecord

/**
 * Komponen UI Kartu untuk menampilkan 1 detail item catatan paparan & keluhan alergi.
 */
@Composable
fun AllergyRecordCard(
    record: AllergyRecord,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // 1. Nama Makanan
            Text(
                text = record.namaMakanan,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(modifier = Modifier.height(4.dp))

            // 2. Tanggal & Jam Makan
            Text(
                text = "${record.tanggalPaparan} • Makan ${record.jamMakan}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Spacer(modifier = Modifier.height(4.dp))

            // 3. Keluhan Kategori & Jam Muncul
            Text(
                text = "Keluhan ${record.kategoriKeluhan.lowercase()} • Muncul ${record.jamKeluhan}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )

            // 4. Tindakan (bila ada)
            if (record.tindakan.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Tindakan: ${record.tindakan}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

            // 5. Catatan Tambahan (bila ada)
            if (record.catatanTambahan.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Catatan: ${record.catatanTambahan}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}