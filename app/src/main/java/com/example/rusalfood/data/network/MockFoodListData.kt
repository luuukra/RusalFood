package com.example.rusalfood.data.network

import com.example.rusalfood.data.models.foodList.Product
import com.example.rusalfood.data.models.foodList.ProductCategory
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
var products = listOf(
    Product(
        0,
        "Product One",
        "Food description",
        "https://images.pexels.com/photos/704571/pexels-photo-704571.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        1200
    ),
    Product(
        1,
        "Product Two",
        "",
        "https://media.baamboozle.com/uploads/images/221598/1608102785_81663",
        1500
    ),
    Product(
        2,
        "Product Three",
        "Another description",
        "https://images.pexels.com/photos/1260968/pexels-photo-1260968.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        2000
    ),
    Product(3, "Another Product", "", "", 890),
    Product(
        4,
        "One More Product",
        "Long interesting description of a product",
        "https://images.pexels.com/photos/2983101/pexels-photo-2983101.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        3200
    )
)

var products1 = listOf(
    Product(
        0,
        "Product One",
        "Food description",
        "https://images.pexels.com/photos/1583884/pexels-photo-1583884.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500",
        1200
    ),
    Product(
        1,
        "Product Two",
        "Some description",
        "https://images.pexels.com/photos/4128502/pexels-photo-4128502.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        1500
    ),
    Product(
        2,
        "Product Three",
        "Another description",
        "https://images.pexels.com/photos/1854652/pexels-photo-1854652.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        2000
    ),
    Product(4, "One More Product", "Long interesting description of a product", "", 3200)
)

var products2 = listOf(
    Product(
        0,
        "Product One",
        "Food description",
        "https://images.pexels.com/photos/704571/pexels-photo-704571.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        1200
    ),
    Product(1, "Product Two", "Some description", "", 1500),
    Product(
        2,
        "Product Three",
        "Another description",
        "https://images.pexels.com/photos/628776/pexels-photo-628776.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        2000
    ),
    Product(
        3,
        "Another Product",
        "Some longer description",
        "https://images.pexels.com/photos/1351238/pexels-photo-1351238.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        890
    ),
    Product(
        4,
        "One More Product",
        "Long interesting description of a product",
        "https://images.pexels.com/photos/3535383/pexels-photo-3535383.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        3200
    ),
    Product(
        5,
        "Best Product",
        "Long description of a product",
        "https://images.pexels.com/photos/1893556/pexels-photo-1893556.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        3200
    )
)

var products3 = listOf(
    Product(
        0,
        "Some cool product",
        "Food description",
        "https://images.pexels.com/photos/3655916/pexels-photo-3655916.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        1200
    ),
    Product(
        1,
        "Tasty product",
        "Some description",
        "https://images.pexels.com/photos/3659862/pexels-photo-3659862.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        1500
    ),
    Product(2, "Another cool product", "Another description", "", 2000),
    Product(
        3,
        "Some product",
        "Some longer description",
        "https://images.pexels.com/photos/3026805/pexels-photo-3026805.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        890
    ),
    Product(
        4,
        "One More Product",
        "Long interesting description of a product",
        "https://images.pexels.com/photos/3590401/pexels-photo-3590401.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        3200
    )
)

var products4 = listOf(
    Product(
        0,
        "First product",
        "Food description",
        "https://images.pexels.com/photos/286283/pexels-photo-286283.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        1200
    ),
    Product(
        1,
        "Second product",
        "Some description",
        "https://images.pexels.com/photos/3735153/pexels-photo-3735153.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        1500
    ),
    Product(
        2,
        "Third product",
        "Another description",
        "https://images.pexels.com/photos/4393021/pexels-photo-4393021.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        2000
    ),
    Product(
        4,
        "Fourth product",
        "Long interesting description of a product",
        "https://images.pexels.com/photos/5589033/pexels-photo-5589033.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        3200
    )
)


/**Mock ProductCategories**/
var productCategory = ProductCategory(0, "pizza", products)
var productCategory1 = ProductCategory(1, "burger", products1)
var productCategory2 = ProductCategory(2, "coffee", products2)
var productCategory3 = ProductCategory(3, "sweets", products)
var productCategory4 = ProductCategory(4, "alcohol", products1)
var productCategory5 = ProductCategory(5, "for kids", products2)
var productCategory6 = ProductCategory(6, "combo", products3)
var productCategory7 = ProductCategory(7, "donuts", products4)
var productCategory8 = ProductCategory(8, "tea", products2)
var productCategory9 = ProductCategory(9, "sushi", products3)


/**Mock One Restaurant categories lists*/
var productCategoriesList =
    listOf(
        productCategory,
        productCategory1,
        productCategory2,
        productCategory3,
        productCategory4,
        productCategory5
    )
var productCategoriesList1 =
    listOf(productCategory6, productCategory7, productCategory5, productCategory8, productCategory9)
var productCategoriesList2 =
    listOf(productCategory7, productCategory1, productCategory2, productCategory3, productCategory4)