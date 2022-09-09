package com.grzybolevsky.eshop.api.users

import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    var email: String,
    @OneToOne
    var details: UserDetails = UserDetails(),
    @Id
    @GeneratedValue
    var id: Long? = null
)

fun User.toView() = UserView(email, details.toView())

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
    var id: Long? = null
)

fun UserDetails.toView() = UserDetailsView(firstName, secondName, address, city, country, postCode, phone)

data class UserView(
    val email: String,
    val userDetails: UserDetailsView
)

data class UserDetailsView(
    val firstName: String,
    val secondName: String,
    val address: String,
    val city: String,
    val country: String,
    val postCode: String,
    val phone: String
)