package com.example.rusalfood.data.network

import com.example.rusalfood.data.models.MockOrder
import javax.inject.Inject

interface IMockOrders {

    fun getOrdersList() : List<MockOrder>

    fun getOrderById(orderId: Int) : MockOrder

}

class MockOrders @Inject constructor() : IMockOrders {

    override fun getOrderById(orderId: Int): MockOrder {
        return mockOrders[orderId]
    }

    override fun getOrdersList(): List<MockOrder> {
        return mockOrders
    }

    var order1 = MockOrder(0, "Wall Street 1","Wallmart", 12000, "Done", "22.22.2022, 15:23")
    var order2 = MockOrder(1, "Wall Street 2","Whole Foods", 9000, "Pending", "21.00.2021, 13:00")
    var order3 = MockOrder(2, "Wall Street 3","Lindt", 16000, "Done", "01.01.2019, 15:23")
    var order4 = MockOrder(3, "Wall Street 4","Starbucks", 28300, "In kitchen", "15.03.2011, 5:41")
    var order5 = MockOrder(4, "Wall Street 5","Travelers Coffee", 5400, "Done", "31.01.2020, 6:20")

    private val mockOrders = listOf(order1, order2, order3, order4, order5)
}