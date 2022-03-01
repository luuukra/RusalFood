package com.example.rusalfood.data.network

import com.example.rusalfood.data.models.MockOrder

class MockOrders() {

    var order1 = MockOrder(0, "Mamma Mia", 12000, "Done", "22.22.2022, 15:23")
    var order2 = MockOrder(1, "Whole Foods", 9000, "Pending", "21.00.2021, 13:00")
    var order3 = MockOrder(2, "Lindt", 16000, "Done", "01.01.2019, 15:23")
    var order4 = MockOrder(3, "Starbucks", 28300, "In kitchen", "15.03.2011, 5:41")
    var order5 = MockOrder(4, "Travelers Coffee", 5400, "Done", "31.01.2020, 6:20")

    var mockOrders = listOf(order1, order2, order3, order4, order5)




}