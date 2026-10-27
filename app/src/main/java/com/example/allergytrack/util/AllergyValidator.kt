package com.example.allergytrack.util
import com.example.allergytrack.model.AllergyFormState
object AllergyValidator {
    fun validate(state: AllergyFormState): Pair<AllergyFormState, Boolean> {
        val tanggalErr = if (state.tanggalPaparan.isBlank()) "Tanggal paparan wajib diisi" else null
        val jamMakanErr = if (state.jamMakan.isBlank()) "Jam makan wajib diisi" else null
        val namaMakananErr = when {
            state.namaMakanan.isBlank() -> "Nama makanan wajib diisi"
            state.namaMakanan.trim().length < 2 -> "Nama makanan minimal 2 karakter"
            else -> null
        }
        val kategoriErr = if (state.kategoriKeluhan.isBlank()) "Kategori keluhan wajib dipilih" else null
        val jamKeluhanErr = if (state.jamKeluhan.isBlank()) "Jam keluhan wajib diisi" else null

        val isValid = tanggalErr == null &&
                jamMakanErr == null &&
                namaMakananErr == null &&
                kategoriErr == null &&
                jamKeluhanErr == null

        val updatedState = state.copy(
            tanggalError = tanggalErr,
            jamMakanError = jamMakanErr,
            namaMakananError = namaMakananErr,
            kategoriError = kategoriErr,
            jamKeluhanError = jamKeluhanErr
        )

        return Pair(updatedState, isValid)
    }
}
