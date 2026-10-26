# AllergyTrack (HT-08)

Prototipe UTS Pemrograman Bergerak untuk mencatat paparan makanan dan keluhan yang muncul. Aplikasi ini dirancang sebagai diary dengan data simulasi, bukan alat diagnosis alergi.

## Status saat ini

Proyek Android Studio berbasis Kotlin dan Jetpack Compose sudah dibuat, dan aplikasi awal dari template telah dijalankan di ponsel. Form, validasi, riwayat, dan ringkasan AllergyTrack **belum diimplementasikan**.

## Rencana fitur UTS

- Satu layar yang dapat digulir untuk mencatat tanggal dan jam makan, makanan, kategori keluhan, jam keluhan muncul, tindakan, dan catatan tambahan.
- Validasi input wajib dan waktu, tombol tambah catatan serta reset form.
- Daftar catatan sementara dengan empty state dan kartu riwayat.
- Ringkasan jumlah paparan, jumlah per kategori keluhan, dan makanan yang paling sering dicatat.
- Pesan umum untuk mencari pertolongan medis dalam keadaan darurat, tanpa klaim diagnosis.

Data hanya akan disimpan sementara selama aplikasi berjalan. Database dan CRUD lengkap direncanakan untuk tahap UAS.

## Menjalankan proyek

1. Buka folder proyek ini di Android Studio.
2. Tunggu Gradle Sync selesai.
3. Pilih emulator atau ponsel Android yang terhubung, lalu jalankan konfigurasi `app`.

Proyek saat ini memakai Minimum SDK API 24, Jetpack Compose, dan Material 3.

## Wireframe

- [Kondisi awal](docs/wireframe.png)
- [Setelah satu catatan ditambahkan](docs/wireframe-setelah-catatan.png)
- [File Figma yang dapat diedit](https://www.figma.com/design/JPvNfnmlo3gLt3VcjDOije)

## Dokumentasi lanjutan

Hasil pengujian, screenshot aplikasi, kontribusi anggota, APK, dan rincian implementasi akan ditambahkan selama pengerjaan UTS. Penggunaan AI yang memengaruhi proyek dicatat di [AI_USAGE.md](AI_USAGE.md).
