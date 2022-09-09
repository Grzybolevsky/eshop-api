package com.grzybolevsky.eshop.api.users

import com.grzybolevsky.eshop.api.baskets.BasketView
import com.grzybolevsky.eshop.api.orders.OrderView


class UserView(
    var email: String,
    var userDetails: UserDetailsView,
    var client: ClientView,
)

class UserDetailsView(
    var firstName: String,
    var secondName: String,
    var address: String,
    var city: String,
    var country: String,
    var postCode: String,
    var phone: String,
)

class ClientView(orders: List<OrderView>, basket: BasketView)