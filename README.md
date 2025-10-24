# Responsi 1 - Pemrograman Mobile

## Identitas

| Informasi | Detail |
|-----------|--------|
| Nama | Jeskris Oktovianus Silahooy |
| NIM | H1D023003 |
| Shift Baru | A |
| Shift Asal | C |

---

## Alur Data

### API Setup
- Base URL: `https://api.football-data.org/v4/`
- Endpoint: `GET /teams/{id}`
- Team ID: 58 (Aston Villa FC)
- Header: `X-Auth-Token: 05f3bba899d14e489201897aac8a4dc4`

### Alur Pengambilan Data

```
API Request
    ↓
Retrofit + Gson (parse JSON)
    ↓
Data Model (SearchResponse, Player, Coach)
    ↓
TeamRepository (fetch dan post ke LiveData)
    ↓
TeamViewModel (manage state)
    ↓
Activities observe LiveData
    ↓
Update UI dengan data dari API
```

### MainActivity
- Menampilkan nama klub dari API
- Menampilkan deskripsi klub

### ClubHistoryActivity
- Menampilkan judul "Aston Villa - History"
- Menampilkan sejarah klub dari strings.xml

### HeadCoachActivity
- Memanggil API untuk get data pelatih
- Menampilkan nama, tanggal lahir, dan kebangsaan pelatih
- Menampilkan foto pelatih menggunakan Glide

### TeamSquadActivity
- Memanggil API untuk get daftar pemain
- Mengurutkan pemain berdasarkan posisi:
  - Goalkeeper (GK) - Kuning
  - Defender (DEF) - Biru
  - Midfielder (MID) - Hijau
  - Forward (FW) - Merah
- Menampilkan nama dan asal negara pemain
- Detail pemain tampil di Bottom Sheet saat diklik

---

## Model Data

```
SearchResponse
├── id: Int
├── name: String
├── shortName: String
├── coach: Coach
│   ├── id: Int
│   ├── name: String
│   ├── dateOfBirth: String
│   └── nationality: String
└── squad: List<Player>
    ├── id: Int
    ├── name: String
    ├── position: String
    ├── dateOfBirth: String
    └── nationality: String
```

---

## Struktur Project

```
app/src/main/
├── java/com/unsoed/responsi1mobileh1d023003/
│   ├── MainActivity.kt
│   ├── ClubHistoryActivity.kt
│   ├── HeadCoachActivity.kt
│   ├── TeamSquadActivity.kt
│   ├── data/
│   │   ├── model/SearchResponse.kt
│   │   ├── network/Api.kt
│   │   ├── network/RetrofitInstance.kt
│   │   └── repository/TeamRepository.kt
│   ├── ui/
│   │   ├── adapter/PlayerAdapter.kt
│   │   └── dialog/PlayerDetailBottomSheet.kt
│   └── viewmodel/TeamViewModel.kt
├── res/
│   ├── layout/
│   │   ├── activity_main.xml
│   │   ├── activity_club_history.xml
│   │   ├── activity_head_coach.xml
│   │   └── activity_team_squad.xml
│   ├── drawable/
│   │   ├── coach.jpg
│   │   ├── logo.png
│   │   ├── stadium.jpg
│   │   └── ic_launcher_background.xml
│   └── values/strings.xml
└── AndroidManifest.xml
```

---

## Library yang Digunakan

- Retrofit 2.9.0 - HTTP client
- Gson - JSON parsing
- Glide 4.15.1 - Image loading
- RecyclerView 1.3.1 - List display
- CardView 1.0.0 - Card component
- Material 1.9.0 - UI components
- Lifecycle & ViewModel - State management

---

## Cara Menjalankan

```bash
cd c:\Users\jeskr\AndroidStudioProjects\Responsi1MobileH1D023003

./gradlew assembleDebug

adb install app/build/outputs/apk/debug/app-debug.apk
```

---

## Video Demo

[Link video demo akan ditambahkan di sini]

---
