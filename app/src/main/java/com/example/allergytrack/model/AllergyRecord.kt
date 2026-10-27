package com.example.allergytrack.model
data class AllergyRecord(
    val id: Long = System.currentTimeMillis(),
    val tanggalPaparan: String,
    val jamMakan: String,
    val namaMakanan: String,
    val kategoriKeluhan: String,
    val jamKeluhan: String,
    val tindakan: String = "",
    val catatanTambahan: String = ""
)


object AllergyCategories {
    const val KULIT = "Kulit"
    const val PENCERNAAN = "Pencernaan"
    const val PERNAPASAN = "Pernapasan"
    const val LAINNYA = "Lainnya"

    val list = listOf(KULIT, PENCERNAAN, PERNAPASAN, LAINNYA)
}
