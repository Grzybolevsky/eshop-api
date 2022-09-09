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