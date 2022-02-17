package com.example.rusalfood.data.network

import com.example.rusalfood.domain.models.Food
import com.example.rusalfood.domain.models.Place
import javax.inject.Inject


class MockData @Inject constructor() {

    var placesList = listOf<Place>(

        Place(
            0,
            "Starbucks",
            "БЦ Esentai Tower, проспект Аль-Фараби, 77/7",
            "https://img.kapital.kz/Oe5cNsvzAzQ/rs:auto:1200:627:1:0/g:sm/czM6Ly9rYXBpdGFsLXN0YXRpYy9pbWcvbGVnYWN5L2ltYWdlcy9kLzgvNS8zLzkvNDQ2YmQ3YjFkYTQzOGM4ZWVhYmU3ZjgxNjc4LmpwZw",
            listOf(
                "https://restolife.kz/upload/information_system_6/6/2/2/item_6223/small_information_items_property_5675.jpg",
                "https://restolife.kz/upload/information_system_6/2/1/5/item_21585/information_items_property_24311.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS5Y5mqLUSRyWzRQogTvFBVZBuA_w-ZSS4tIPBWTacSd4CkdCnMnaakWZSFN3UT8UucTYI&usqp=CAU",
                "https://cosmo.kz/img/4/2108.jpg",
                "https://restolife.kz/upload/information_system_6/6/2/2/item_6223/small_information_items_property_5681.jpg",
            ),
            listOf("Напитки", "Десерты", "Новинки", "Кофе в пакетах", "Аксессуары"),
            listOf(
                Food(
                    "Напитки",
                    "Iced Latte",
                    1200,
                    "Классический латте",
                    "https://assets.mystarbucks.com.au/imagecache/bestfit/620x634/_files/Beverages/sbx20190823-43221-icedalmondmilkhoneyflatwhite-ongreen-corelib-srgb.jpg",
                ),
                Food(
                    "Напитки",
                    "Americano",
                    800,
                    "American Americano",
                    "https://www.starbucks.com.au/imagecache/bestfit/750x750/_files/Beverages/sbx20190607-32079-icedcaffeamericano-ongreen-corelib-srgb.jpg",
                ),
                Food(
                    "Напитки",
                    "Peach Tea",
                    1400,
                    "П-персик!",
                    "data:image/jpeg;base64,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",
                ),
                Food(
                    "Десерты",
                    "Чизкейк",
                    2800,
                    "Сырный тортик",
                    "https://img-global.cpcdn.com/recipes/31371b019c40837994d8e520c6bd4c1e0111d256a324686389e30b6a6d22428b/1200x630cq70/photo.jpg"
                ),
                Food(
                    "Десерты",
                    "Тройное мороженое",
                    3600,
                    "Как одно мороженое, только три",
                    "https://i.pinimg.com/736x/77/4d/ba/774dba89410d1fa40b756f7daf7463bb--hd-iphone--wallpapers-wallpapers-tumblr.jpg"
                )
            ),
        ),

        Place(
            1,
            "Del Papa",
            "ул. Бухар жырау, 66, уг. ул. Ауэзова",
            "https://restolife.kz/upload/information_system_6/5/0/8/item_5080/information_items_property_17200.jpg",
            listOf(
                "https://media-cdn.tripadvisor.com/media/photo-s/03/93/24/bd/la-papa.jpg",
                "https://redtable.kz/wp-content/uploads/2019/08/%D0%B4%D0%B5%D0%BB%D1%8C-%D0%BF%D0%B0%D0%BF%D0%B0-%D0%B0%D1%81%D1%82%D0%B0%D0%BD%D0%B0-2.jpg",
                "https://redtable.kz/wp-content/uploads/2019/07/%D0%B4%D0%B5%D0%BB%D1%8C-%D0%BF%D0%B0%D0%BF%D0%B0-%D1%80%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0-3.jpg",
                "https://www.lampadary.ru/wa-data/public/photos/74/01/174/174.670x0.jpg",
                "https://www.lampadary.ru/wa-data/public/photos/76/01/176/176.670x0.jpg",
            ),
            listOf("Пицца", "Паста", "Салаты", "Антипасти", "Напитки", "Что-то еще"),
            listOf(
                Food(
                    "Пицца",
                    "Пицца Маргарита",
                    1600,
                    "Классическая пицца, именнованная в честь итальянской королевы",
                    "https://km-doma.ru/assets/gallery_thumbnails/31/319484a4bb725e4eacab62c7f0c7f1ed.jpg",
                ),
                Food(
                    "Пицца",
                    "Пицца Мексикано",
                    2400,
                    "Мексиканская пицца, aka пицца Мексикано",
                    "https://global-sushi.kz/wp-content/uploads/2021/03/%D0%BF%D0%B8%D1%86%D1%86%D0%B0-%D0%BA%D0%B0%D0%B7%D0%B0%D1%85%D1%81%D1%82%D0%B0%D0%BD-300x300.jpeg",
                ),
                Food(
                    "Пицца",
                    "Пицца Кальцоне",
                    3600,
                    "Кольцевая пицца Кальцоне",
                    "https://fornelli.ru/upload/iblock/987/calzone900.jpg",
                )
            ),
        ),
    )
}