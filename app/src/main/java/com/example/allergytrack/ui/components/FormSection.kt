package com.example.allergytrack.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.allergytrack.model.AllergyCategories
import com.example.allergytrack.model.AllergyFormState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSection(
    formState: AllergyFormState,
    onFormStateChange: (AllergyFormState) -> Unit,
    modifier: Modifier = Modifier
) {
    var expandedCategoryDropdown by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var showJamMakanPicker by remember { mutableStateOf(false) }
    var showJamKeluhanPicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    val jamMakanState = rememberTimePickerState(is24Hour = true)
    val jamKeluhanState = rememberTimePickerState(is24Hour = true)

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val formatter = SimpleDateFormat("dd MMM yyyy", Locale.forLanguageTag("id-ID"))
                        onFormStateChange(formState.copy(tanggalPaparan = formatter.format(Date(millis)), tanggalError = null))
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Batal")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showJamMakanPicker) {
        AlertDialog(
            onDismissRequest = { showJamMakanPicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val timeStr = String.format(Locale.getDefault(), "%02d:%02d", jamMakanState.hour, jamMakanState.minute)
                    onFormStateChange(formState.copy(jamMakan = timeStr, jamMakanError = null))
                    showJamMakanPicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showJamMakanPicker = false }) {
                    Text("Batal")
                }
            },
            text = { TimePicker(state = jamMakanState) }
        )
    }

    if (showJamKeluhanPicker) {
        AlertDialog(
            onDismissRequest = { showJamKeluhanPicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val timeStr = String.format(Locale.getDefault(), "%02d:%02d", jamKeluhanState.hour, jamKeluhanState.minute)
                    onFormStateChange(formState.copy(jamKeluhan = timeStr, jamKeluhanError = null))
                    showJamKeluhanPicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showJamKeluhanPicker = false }) {
                    Text("Batal")
                }
            },
            text = { TimePicker(state = jamKeluhanState) }
        )
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Catatan baru",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = formState.tanggalPaparan,
            onValueChange = {},
            readOnly = true,
            label = { Text("Tanggal paparan") },
            placeholder = { Text("Pilih tanggal paparan") },
            isError = formState.tanggalError != null,
            supportingText = {
                formState.tanggalError?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDatePicker = true }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = formState.jamMakan,
            onValueChange = {},
            readOnly = true,
            label = { Text("Jam makan (HH:mm)") },
            placeholder = { Text("Pilih jam makan") },
            isError = formState.jamMakanError != null,
            supportingText = {
                formState.jamMakanError?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showJamMakanPicker = true }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = formState.namaMakanan,
            onValueChange = { onFormStateChange(formState.copy(namaMakanan = it, namaMakananError = null)) },
            label = { Text("Nama/jenis makanan") },
            placeholder = { Text("Contoh: Roti isi telur") },
            isError = formState.namaMakananError != null,
            supportingText = {
                formState.namaMakananError?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = expandedCategoryDropdown,
            onExpandedChange = { expandedCategoryDropdown = !expandedCategoryDropdown },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = formState.kategoriKeluhan.ifBlank { "Pilih kategori" },
                onValueChange = {},
                readOnly = true,
                label = { Text("Kategori keluhan") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategoryDropdown) },
                isError = formState.kategoriError != null,
                supportingText = {
                    formState.kategoriError?.let {
                        Text(text = it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expandedCategoryDropdown,
                onDismissRequest = { expandedCategoryDropdown = false }
            ) {
                AllergyCategories.list.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category) },
                        onClick = {
                            onFormStateChange(formState.copy(kategoriKeluhan = category, kategoriError = null))
                            expandedCategoryDropdown = false
                        }
                    )
                }
            }
        }

        if (formState.kategoriKeluhan == AllergyCategories.LAINNYA) {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = formState.keluhanLainnya,
                onValueChange = { onFormStateChange(formState.copy(keluhanLainnya = it)) },
                label = { Text("Catatan untuk kategori lainnya") },
                placeholder = { Text("Sebutkan keluhan lainnya") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = formState.jamKeluhan,
            onValueChange = {},
            readOnly = true,
            label = { Text("Jam keluhan muncul (HH:mm)") },
            placeholder = { Text("Pilih jam keluhan muncul") },
            isError = formState.jamKeluhanError != null,
            supportingText = {
                formState.jamKeluhanError?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showJamKeluhanPicker = true }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = formState.tindakan,
            onValueChange = { onFormStateChange(formState.copy(tindakan = it)) },
            label = { Text("Tindakan yang dilakukan (opsional)") },
            placeholder = { Text("Contoh: Menghubungi orang tua") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = formState.catatanTambahan,
            onValueChange = { onFormStateChange(formState.copy(catatanTambahan = it)) },
            label = { Text("Catatan tambahan (opsional)") },
            placeholder = { Text("Contoh: Gatal di lengan") },
            singleLine = false,
            maxLines = 3,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
