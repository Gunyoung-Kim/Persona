package com.gunyoung.persona.common.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class User(
    @Id var userId: Long? = null,
    var taliId: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var phoneNumber: String = ""
) {

}