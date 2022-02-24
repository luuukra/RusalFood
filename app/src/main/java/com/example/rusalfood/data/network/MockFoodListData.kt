package com.example.rusalfood.data.network

import com.example.rusalfood.data.models.Product
import com.example.rusalfood.data.models.ProductCategory
import javax.inject.Inject

class MockFoodListData @Inject constructor() {

    /**Mock Map of restaurant ID's and restaurant food lists*/
    var foodListByPlaceId = mutableMapOf(
        Pair(0, productCategoriesList),
        Pair(1, productCategoriesList1),
        Pair(2, productCategoriesList2)
    )

}


/**Mock Products**/
var products = listOf<Product>(
    Product(0, "a", "b", "https://images.pexels.com/photos/704571/pexels-photo-704571.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 1000),
    Product(1, "a", "b", "https://images.pexels.com/photos/4128502/pexels-photo-4128502.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 1000),
    Product(2, "a", "b", "https://images.pexels.com/photos/704571/pexels-photo-704571.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 1000),
    Product(3, "a", "b", "https://images.pexels.com/photos/4128502/pexels-photo-4128502.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 1000),
    Product(4, "a", "b", "https://images.pexels.com/photos/704571/pexels-photo-704571.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 1000)
)

var products1 = listOf<Product>(
    Product(0, "r", "R", "https://images.pexels.com/photos/704571/pexels-photo-704571.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 2000),
    Product(1, "r", "R", "https://images.pexels.com/photos/4128502/pexels-photo-4128502.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 2000),
    Product(2, "r", "R", "https://images.pexels.com/photos/704571/pexels-photo-704571.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 2000),
    Product(3, "r", "R", "https://images.pexels.com/photos/4128502/pexels-photo-4128502.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 2000),
)

var products2 = listOf<Product>(
    Product(0, "T", "t", "https://images.pexels.com/photos/704571/pexels-photo-704571.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 3000),
    Product(1, "T", "t", "https://images.pexels.com/photos/4128502/pexels-photo-4128502.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 3000),
    Product(2, "T", "t", "https://images.pexels.com/photos/704571/pexels-photo-704571.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 3000),
    Product(3, "T", "t", "https://images.pexels.com/photos/4128502/pexels-photo-4128502.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 3000),
    Product(4, "T", "t", "https://images.pexels.com/photos/704571/pexels-photo-704571.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 3000),
    Product(5, "T", "t", "https://images.pexels.com/photos/4128502/pexels-photo-4128502.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 3000),
    Product(6, "T", "t", "https://images.pexels.com/photos/704571/pexels-photo-704571.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940", 3000)
)


/**Mock ProductCategories**/
var productCategory = ProductCategory(0, "pizza", products)
var productCategory1 = ProductCategory(1, "burger", products1)
var productCategory2 = ProductCategory(2, "coffee", products2)
var productCategory3 = ProductCategory(3, "sweets", products)
var productCategory4 = ProductCategory(4, "alcohol", products1)
var productCategory5 = ProductCategory(5, "for kids", products2)


/**Mock One Restaurant categories lists*/
var productCategoriesList =
    listOf<ProductCategory>(productCategory, productCategory1, productCategory2, productCategory3, productCategory4, productCategory5)
var productCategoriesList1 =
    listOf<ProductCategory>(productCategory, productCategory1, productCategory2, productCategory4, productCategory5)
var productCategoriesList2 =
    listOf<ProductCategory>(productCategory, productCategory1, productCategory2, productCategory3, productCategory4)