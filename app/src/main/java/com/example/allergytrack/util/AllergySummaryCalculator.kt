package com.example.allergytrack.util

import com.example.allergytrack.model.AllergyCategories
import com.example.allergytrack.model.AllergyRecord
data class AllergySummary(
    val totalPaparan: Int = 0,
    val kulitCount: Int = 0,
    val pencernaanCount: Int = 0,
    val pernapasanCount: Int = 0,
    val lainnyaCount: Int = 0,
    val makananPalingSering: String = "Belum ada"
)
object AllergySummaryCalculator {
    fun calculate(records: List<AllergyRecord>): AllergySummary {
        if (records.isEmpty()) {
            return AllergySummary()
        }

        val total = records.size
        val kulit = records.count { it.kategoriKeluhan.equals(AllergyCategories.KULIT, ignoreCase = true) }
        val pencernaan = records.count { it.kategoriKeluhan.equals(AllergyCategories.PENCERNAAN, ignoreCase = true) }
        val pernapasan = records.count { it.kategoriKeluhan.equals(AllergyCategories.PERNAPASAN, ignoreCase = true) }
        val lainnya = records.count { it.kategoriKeluhan.equals(AllergyCategories.LAINNYA, ignoreCase = true) }

        // Mencari makanan yang paling sering muncul di dalam catatan
        val topFoodEntry = records.groupingBy { it.namaMakanan.trim() }
            .eachCount()
            .maxByOrNull { it.value }

        val makananSering = if (topFoodEntry != null) {
            "${topFoodEntry.key} • ${topFoodEntry.value} kali"
        } else {
            "Belum ada"
        }

        return AllergySummary(
            totalPaparan = total,
            kulitCount = kulit,
            pencernaanCount = pencernaan,
            pernapasanCount = pernapasan,
            lainnyaCount = lainnya,
            makananPalingSering = makananSering
        )
    }
}
