# AllergyTrack (HT-08) - HealthTrack Mobile Suite

Prototipe aplikasi kesehatan preventif berbasis Android untuk mencatat paparan makanan dan keluhan alergi. Aplikasi ini berfungsi sebagai *food allergy diary* berbasis edukasi dan self-monitoring menggunakan data simulasi, bukan sebagai alat diagnosis medis.

---

## 📌 Deskripsi Proyek
- **Modul:** HT-08 AllergyTrack
- **Mata Kuliah:** Pemrograman Bergerak (SSI3140)
- **Program Studi:** Sistem Informasi — Universitas Ma Chung
- **Dosen Pengampu:** Muhammad Nurwegiono, S.Kom., M.Kom.
- **Platform:** Android Studio, Kotlin, Jetpack Compose, Material 3 (Single Screen / Satu Layar)

---

## 🚀 Fitur Utama UTS
1. **Header & Disclaimer Medis:**  
   Menampilkan judul aplikasi, deskripsi, kode modul `HT-08`, dan banner peringatan darurat/disclaimer etika kesehatan.
2. **Formulir "Catatan Baru" dengan Interactive Picker:**  
   - Tanggal paparan menggunakan **DatePicker (Kalender)** dialog.
   - Jam makan & Jam keluhan menggunakan **TimePicker (Jam Analog/Digital)** dialog.
   - Kategori keluhan (Dropdown: Kulit, Pencernaan, Pernapasan, Lainnya).
   - Kolom teks tambahan *"Catatan untuk kategori lainnya"* yang otomatis muncul ketika opsi "Lainnya" dipilih.
   - Field opsional untuk Tindakan dan Catatan Tambahan.
   - Validasi input dengan teks pesan kesalahan berwarna merah.
3. **Tombol Aksi Tambah & Reset:**  
   Tombol *Tambah Catatan* untuk menyimpan data valid dan tombol *Reset* untuk mengosongkan seluruh isian form.
4. **Ringkasan Statistik Otomatis:**  
   Secara real-time mengalkulasi Total Paparan, Keluhan Per Kategori, dan Makanan Paling Sering Dicatat.
5. **Riwayat Catatan & Empty State:**  
   Menampilkan *Empty State* ("Belum ada catatan.") saat data kosong, dan menampilkan daftar kartu riwayat catatan secara berurutan saat data ditambahkan.

---

## 🛠️ Arsitektur & Penulisan Kode
Seluruh arsitektur komponen Jetpack Compose, model data, logika validasi, dan pengujian aplikasi ditulis secara mandiri oleh mahasiswa dengan struktur modular:
```
com.example.allergytrack/
├── MainActivity.kt                  # Layar Utama Compose (AllergyTrackApp)
├── model/
│   ├── AllergyRecord.kt             # Data class model catatan alergi
│   └── AllergyFormState.kt          # State holder input form & error
├── util/
│   ├── AllergyValidator.kt          # Logika validasi input form
│   └── AllergySummaryCalculator.kt  # Logika kalkulasi statistik ringkasan
└── ui/
    └── components/
        ├── HeaderSection.kt         # Komponen Header & Banner Darurat
        ├── FormSection.kt           # Komponen Form Input, DatePicker, TimePicker, & Dropdown
        ├── FormButtons.kt           # Komponen Tombol Tambah & Reset
        ├── SummarySection.kt        # Komponen Kartu Ringkasan
        ├── AllergyRecordCard.kt     # Komponen Kartu Item Catatan
        └── HistorySection.kt        # Komponen Seksi Riwayat & Empty State
```

---

## 💻 Cara Menjalankan Aplikasi
1. Buka folder proyek ini di **Android Studio**.
2. Tunggu hingga **Gradle Sync** selesai sepenuhnya.
3. Sambungkan emulator Android atau perangkat fisik (Minimum SDK API 24).
4. Klik tombol **Run App ▶️** (`Shift + F10`).

---

## 📄 Berkas Pengumpulan & Dokumentasi
- 📝 **Laporan Kontribusi:** [`CONTRIBUTIONS.md`](CONTRIBUTIONS.md)
- 🤖 **Catatan Penggunaan AI:** [`AI_USAGE.md`](AI_USAGE.md)
- 🧪 **Laporan Test Cases:** [`docs/test-cases.md`](docs/test-cases.md)

---

## 🔮 Rencana Kelanjutan pada UAS
- Integrasi **Room Database** dan **SQLite** untuk penyimpanan data persisten.
- Implementasi **CRUD Lengkap** (Create, Read, Update, Delete) pada catatan alergi.
- Navigasi beberapa layar menggunakan **Navigation Compose**.
- Fitur pencarian (*search*) dan *filter* riwayat berdasarkan tanggal atau kategori keluhan.
