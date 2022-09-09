package com.grzybolevsky.eshop.api.users

class UserView(
    var email: String,
    var userDetails: UserDetailsView
)

class UserDetailsView(
    var firstName: String,
    var secondName: String,
    var address: String,
    var city: String,
    var country: String,
    var postCode: String,
    var phone: String
)