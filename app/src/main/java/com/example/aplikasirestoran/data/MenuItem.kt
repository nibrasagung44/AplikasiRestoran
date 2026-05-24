package com.example.aplikasirestoran.data

data class MenuItem(
    val id: Int,
    val name: String,
    val price: Int,
    val description: String,
    val category: String,
    val emoji: String
)

val menuList = listOf(
    MenuItem(
        id = 1,
        name = "Nasi Goreng Spesial",
        price = 35000,
        description = "Nasi goreng dengan telur mata sapi, ayam suwir, udang, dan sayuran segar. Disajikan dengan acar mentimun, kerupuk udang, dan sambal terasi pilihan. Cita rasa autentik bumbu rempah khas nusantara.",
        category = "Makanan",
        emoji = "🍳"
    ),
    MenuItem(
        id = 2,
        name = "Soto Ayam Lamongan",
        price = 28000,
        description = "Soto ayam kuah bening khas Lamongan dengan potongan ayam kampung, bihun, tauge, dan telur rebus. Dilengkapi koya gurih dan jeruk nipis segar untuk kesempurnaan rasa.",
        category = "Makanan",
        emoji = "🥣"
    ),
    MenuItem(
        id = 3,
        name = "Ayam Bakar Bumbu Rujak",
        price = 42000,
        description = "Ayam kampung pilihan yang dibakar dengan bumbu rujak khas: cabai merah, gula merah, dan rempah pilihan. Disajikan dengan nasi putih, lalapan segar, dan sambal terasi.",
        category = "Makanan",
        emoji = "🍗"
    ),
    MenuItem(
        id = 4,
        name = "Gado-Gado Jakarta",
        price = 25000,
        description = "Sayuran rebus segar (kangkung, kacang panjang, tauge, kentang) dilengkapi tahu dan tempe goreng, telur rebus, dan disiram saus kacang autentik yang kaya rasa.",
        category = "Makanan",
        emoji = "🥗"
    ),
    MenuItem(
        id = 5,
        name = "Pisang Goreng Keju",
        price = 18000,
        description = "Pisang kepok pilihan digoreng dengan tepung renyah, disajikan hangat dengan taburan keju parut melt dan susu kental manis. Camilan sempurna untuk menemani waktu santai.",
        category = "Makanan",
        emoji = "🍌"
    ),
    MenuItem(
        id = 6,
        name = "Es Teh Tarik",
        price = 12000,
        description = "Teh hitam premium yang diseduh dengan teknik tarik tradisional, menghasilkan busa lembut dan rasa yang kaya. Disajikan dingin dengan es batu untuk kesegaran maksimal.",
        category = "Minuman",
        emoji = "🧋"
    ),
    MenuItem(
        id = 7,
        name = "Jus Alpukat Krim",
        price = 20000,
        description = "Alpukat segar pilihan yang diblender lembut dengan susu segar, sedikit gula, dan topping susu kental manis. Minuman bergizi tinggi yang mengenyangkan dan menyegarkan.",
        category = "Minuman",
        emoji = "🥑"
    ),
    MenuItem(
        id = 8,
        name = "Es Kelapa Muda",
        price = 15000,
        description = "Kelapa muda segar langsung dari pohun pilihan, disajikan dengan air kelapa asli yang manis alami dan daging kelapa muda yang lembut. Menyegarkan dan alami.",
        category = "Minuman",
        emoji = "🥥"
    ),
)