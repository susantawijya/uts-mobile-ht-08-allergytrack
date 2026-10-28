package com.example.allergytrack.ui.components
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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

/**
 * Komponen UI Formulir "Catatan baru" yang berisi seluruh field input
 * beserta penanganan error validasi teks.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSection(
    formState: AllergyFormState,
    onFormStateChange: (AllergyFormState) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expandedCategoryDropdown by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Catatan baru",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 1. Tanggal Paparan
        OutlinedTextField(
            value = formState.tanggalPaparan,
            onValueChange = { onFormStateChange(formState.copy(tanggalPaparan = it, tanggalError = null)) },
            label = { Text("Tanggal paparan") },
            placeholder = { Text("Contoh: 26 Okt 2026") },
            isError = formState.tanggalError != null,
            supportingText = {
                formState.tanggalError?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 2. Jam Makan (HH:mm)
        OutlinedTextField(
            value = formState.jamMakan,
            onValueChange = { onFormStateChange(formState.copy(jamMakan = it, jamMakanError = null)) },
            label = { Text("Jam makan (HH:mm)") },
            placeholder = { Text("Contoh: 12:00") },
            isError = formState.jamMakanError != null,
            supportingText = {
                formState.jamMakanError?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 3. Nama/jenis Makanan
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
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 4. Kategori Keluhan (Exposed Dropdown Menu)
        ExposedDropdownMenuBox(
            expanded = expandedCategoryDropdown,
            onExpandedChange = { expandedCategoryDropdown = !expandedCategoryDropdown },
            modifier = Modifier.fillMaxWidth(),
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
                    .fillMaxWidth(),
            )

            ExposedDropdownMenu(
                expanded = expandedCategoryDropdown,
                onDismissRequest = { expandedCategoryDropdown = false },
            ) {
                AllergyCategories.list.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category) },
                        onClick = {
                            onFormStateChange(formState.copy(kategoriKeluhan = category, kategoriError = null))
                            expandedCategoryDropdown = false
                        },
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 5. Jam Keluhan Muncul (HH:mm)
        OutlinedTextField(
            value = formState.jamKeluhan,
            onValueChange = { onFormStateChange(formState.copy(jamKeluhan = it, jamKeluhanError = null)) },
            label = { Text("Jam keluhan muncul (HH:mm)") },
            placeholder = { Text("Contoh: 12:40") },
            isError = formState.jamKeluhanError != null,
            supportingText = {
                formState.jamKeluhanError?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 6. Tindakan yang Dilakukan (Opsional)
        OutlinedTextField(
            value = formState.tindakan,
            onValueChange = { onFormStateChange(formState.copy(tindakan = it)) },
            label = { Text("Tindakan yang dilakukan (opsional)") },
            placeholder = { Text("Contoh: Menghubungi orang tua") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 7. Catatan Tambahan (Opsional)
        OutlinedTextField(
            value = formState.catatanTambahan,
            onValueChange = { onFormStateChange(formState.copy(catatanTambahan = it)) },
            label = { Text("Catatan tambahan (opsional)") },
            placeholder = { Text("Contoh: Gatal di lengan") },
            singleLine = false,
            maxLines = 3,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}