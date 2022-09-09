package com.grzybolevsky.eshop.api.users

import com.grzybolevsky.eshop.api.baskets.Basket
import com.grzybolevsky.eshop.api.baskets.toView
import com.grzybolevsky.eshop.api.orders.Order
import com.grzybolevsky.eshop.api.orders.toView
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    var email: String,
    @OneToOne
    var client: Client = Client(),
    @OneToOne
    var userDetails: UserDetails = UserDetails(),
    @Id
    @GeneratedValue
    var userId: Long? = null
)

fun User.toView() = UserView(email, userDetails.toView(), client.toView())

@Entity
class UserDetails(
    var firstName: String = "",
    var secondName: String = "",
    var address: String = "",
    var city: String = "",
    var country: String = "",
    var postCode: String = "",
    var phone: String = "",
    @Id
    @GeneratedValue
    var userDetailsId: Long? = null
)

fun UserDetails.toView() = UserDetailsView(firstName, secondName, address, city, country, postCode, phone)

@Entity
@Table(name = "clients")
class Client(
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, targetEntity = Order::class)
    var orders: List<Order> = ArrayList(),
    @OneToOne
    var basket: Basket = Basket(),
    @Id
    @GeneratedValue
    var clientId: Long? = null
)

fun Client.toView() = ClientView(orders.map(Order::toView).toList(), basket.toView())