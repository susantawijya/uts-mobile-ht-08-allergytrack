package com.example.allergytrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.allergytrack.model.AllergyFormState
import com.example.allergytrack.model.AllergyRecord
import com.example.allergytrack.ui.components.FormButtons
import com.example.allergytrack.ui.components.FormSection
import com.example.allergytrack.ui.components.HeaderSection
import com.example.allergytrack.ui.components.HistorySection
import com.example.allergytrack.ui.components.SummarySection
import com.example.allergytrack.ui.theme.AllergyTrackTheme
import com.example.allergytrack.util.AllergySummaryCalculator
import com.example.allergytrack.util.AllergyValidator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AllergyTrackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AllergyTrackApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/**
 * Layar utama aplikasi AllergyTrack (Prototipe UTS satu layar).
 */
@Composable
fun AllergyTrackApp(modifier: Modifier = Modifier) {
    // 1. State penampung list catatan sementara di memori
    val allergyRecords = remember { mutableStateListOf<AllergyRecord>() }

    // 2. State penampung input formulir
    var formState by remember { mutableStateOf(AllergyFormState()) }

    // 3. Derived State untuk kalkulasi ringkasan statistik otomatis setiap data berubah
    val summary by remember {
        derivedStateOf { AllergySummaryCalculator.calculate(allergyRecords) }
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Seksi 1: Header & Disclaimer Darurat
        HeaderSection()

        Spacer(modifier = Modifier.height(24.dp))

        // Seksi 2: Form Input Catatan Baru
        FormSection(
            formState = formState,
            onFormStateChange = { formState = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Seksi 3: Tombol Aksi Tambah & Reset
        FormButtons(
            onAddClick = {
                // Validasi input form
                val (validatedState, isValid) = AllergyValidator.validate(formState)
                formState = validatedState

                if (isValid) {
                    // Buat object AllergyRecord baru
                    val newRecord = AllergyRecord(
                        tanggalPaparan = formState.tanggalPaparan.trim(),
                        jamMakan = formState.jamMakan.trim(),
                        namaMakanan = formState.namaMakanan.trim(),
                        kategoriKeluhan = formState.kategoriKeluhan.trim(),
                        jamKeluhan = formState.jamKeluhan.trim(),
                        tindakan = formState.tindakan.trim(),
                        catatanTambahan = formState.catatanTambahan.trim()
                    )

                    // Tambahkan ke daftar sementara di bagian paling atas
                    allergyRecords.add(0, newRecord)

                    // Reset formulir kembali kosong
                    formState = AllergyFormState()
                }
            },
            onResetClick = {
                // Pengosongan formulir saat tombol reset diklik
                formState = AllergyFormState()
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Seksi 4: Ringkasan Statistik
        SummarySection(summary = summary)

        Spacer(modifier = Modifier.height(24.dp))

        // Seksi 5: Riwayat Catatan
        HistorySection(records = allergyRecords)

        Spacer(modifier = Modifier.height(24.dp))

        // Footer Note
        Text(
            text = "Gunakan data simulasi. Konten dapat digulir.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}
