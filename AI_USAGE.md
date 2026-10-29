# Catatan Penggunaan AI - AllergyTrack (HT-08)

Dokumen ini mencatat transparansi penggunaan AI sebagai alat bantu pendukung (klarifikasi konsep, referensi sintaks dasar, dan saran tata letak).

**Catatan Integritas Akademik:**  
Seluruh penulisan kode program Kotlin, struktur komponen Jetpack Compose, pengelolaan state, logika validasi, dan pengujian fungsional dilakukan dan ditulis secara mandiri oleh mahasiswa kelompok. AI tidak digunakan untuk menghasilkan kode aplikasi secara keseluruhan.

---

| Tanggal | Alat AI | Prompt / Instruksi Utama | Bagian yang Digunakan | Modifikasi dan Pemahaman Mahasiswa |
| --- | --- | --- | --- | --- |
| 26 Oktober 2026 | ChatGPT | Menanyakan referensi standar wireframe dan tata letak aplikasi kesehatan. | Referensi struktur umum antarmuka satu layar. | Mahasiswa merancang dan menggambar sendiri wireframe di Figma sesuai spesifikasi modul HT-08. |
| 27 Oktober 2026 | ChatGPT | Menanyakan contoh sintaks penggunaan `data class` dan `mutableStateListOf` di Kotlin. | Contoh potongan sintaks (*code snippet*) dasar. | Mahasiswa menulis sendiri seluruh file `AllergyRecord.kt` dan `AllergyFormState.kt` di Android Studio. |
| 28 Oktober 2026 | ChatGPT | Menanyakan dokumentasi resmi Compose untuk `DatePickerDialog` dan `rememberTimePickerState`. | Contoh parameter callback `DatePickerDialog`. | Mahasiswa menyusun dan mengintegrasikan sendiri dialog DatePicker dan TimePicker ke dalam `FormSection.kt`. |
| 28 Oktober 2026 | ChatGPT | Diskusi logika percabangan untuk menampilkan kolom input saat opsi "Lainnya" dipilih. | Saran konsep percabangan `if-else`. | Mahasiswa menulis sendiri logika penambahan field `keluhanLainnya` dan penggabungan teksnya saat disimpan. |
| 29 Oktober 2026 | ChatGPT | Menanyakan cara pembersihan warning kompilasi `Locale` dan penyusunan tabel test case. | Format tabel markdown untuk laporan pengujian. | Mahasiswa melakukan pengujian manual 8 skenario pada emulator, memverifikasi tidak ada error, dan menyusun laporan `docs/test-cases.md`. |
