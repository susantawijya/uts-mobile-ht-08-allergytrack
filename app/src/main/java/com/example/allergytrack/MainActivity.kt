package com.example.allergytrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.allergytrack.model.AllergyCategories
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

@Composable
fun AllergyTrackApp(modifier: Modifier = Modifier) {
    val allergyRecords = remember { mutableStateListOf<AllergyRecord>() }
    var formState by remember { mutableStateOf(AllergyFormState()) }

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
        HeaderSection()

        Spacer(modifier = Modifier.height(24.dp))

        FormSection(
            formState = formState,
            onFormStateChange = { formState = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        FormButtons(
            onAddClick = {
                val (validatedState, isValid) = AllergyValidator.validate(formState)
                formState = validatedState

                if (isValid) {
                    val kategoriFinal = if (formState.kategoriKeluhan == AllergyCategories.LAINNYA && formState.keluhanLainnya.isNotBlank()) {
                        "Lainnya (${formState.keluhanLainnya.trim()})"
                    } else {
                        formState.kategoriKeluhan.trim()
                    }

                    val newRecord = AllergyRecord(
                        tanggalPaparan = formState.tanggalPaparan.trim(),
                        jamMakan = formState.jamMakan.trim(),
                        namaMakanan = formState.namaMakanan.trim(),
                        kategoriKeluhan = kategoriFinal,
                        jamKeluhan = formState.jamKeluhan.trim(),
                        tindakan = formState.tindakan.trim(),
                        catatanTambahan = formState.catatanTambahan.trim()
                    )

                    allergyRecords.add(0, newRecord)
                    formState = AllergyFormState()
                }
            },
            onResetClick = {
                formState = AllergyFormState()
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        SummarySection(summary = summary)

        Spacer(modifier = Modifier.height(24.dp))

        HistorySection(records = allergyRecords)

        Spacer(modifier = Modifier.height(16.dp))
    }
}
