package com.gunyoung.persona.common.model

import org.hibernate.annotations.Where
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import java.time.LocalDate
import java.time.OffsetDateTime
import javax.persistence.*

const val NON_EXIST_ID = -1L

@MappedSuperclass
abstract class IdentifiableEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
)

@MappedSuperclass
abstract class AuditableEntity(
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    var createdAt: OffsetDateTime? = null,
    @LastModifiedBy
    @Column(name = "last_modified_at", nullable = false)
    var lastModifiedAt: OffsetDateTime? = null
) : IdentifiableEntity() {

    @PrePersist
    fun onPrePersist() {
        createdAt = OffsetDateTime.now()
        lastModifiedAt = OffsetDateTime.now()
    }

    @PreUpdate
    fun onPreUpdate() {
        lastModifiedAt = OffsetDateTime.now()
    }
}

@Entity
@Table(
    name = "user",
    indexes = [
        Index(name = "USER_TALI_ID_INDEX", columnList = "tali_id"),
        Index(name = "USER_STATUS_INDEX", columnList = "status")
    ]
)
@Where(clause = "status != 'DELETED")
class UserEntity(
    @Column(name = "tali_id", length = 20, unique = true, nullable = false)
    var taliId: String = "",
    @Column(name = "first_name", length = 10, nullable = false)
    var firstName: String = "",
    @Column(name = "last_name", length = 10, nullable = false)
    var lastName: String = "",
    @Column(name = "nick_name", length = 10, nullable = false)
    var nickName: String = "",
    @Column(name = "email", length = 50, unique = true, nullable = false)
    var email: String = "",
    @Column(name = "phone_number", length = 30, nullable = false)
    var phoneNumber: String = "",
    @Column(name = "birth_date", nullable = false)
    var birthDate: LocalDate = LocalDate.now(),
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: UserStatus = UserStatus.ACTIVE
) : AuditableEntity()

@Entity
@Table(name = "user_alarm_option")
class AlarmOptionEntity(
    @Column(name = "sms_alarm_received")
    var isSmsAlarmReceived: Boolean = false,
    @Column(name = "mail_alarm_received")
    var isMailAlarmReceived: Boolean = false,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity? = null,
) : AuditableEntity()


