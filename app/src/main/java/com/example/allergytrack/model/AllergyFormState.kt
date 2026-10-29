package com.example.allergytrack.model

data class AllergyFormState(
    val tanggalPaparan: String = "",
    val jamMakan: String = "",
    val namaMakanan: String = "",
    val kategoriKeluhan: String = "",
    val keluhanLainnya: String = "",
    val jamKeluhan: String = "",
    val tindakan: String = "",
    val catatanTambahan: String = "",

    val tanggalError: String? = null,
    val jamMakanError: String? = null,
    val namaMakananError: String? = null,
    val kategoriError: String? = null,
    val jamKeluhanError: String? = null
)
