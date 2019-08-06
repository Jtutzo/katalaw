package com.jtutzo.katalaw.provider.user

import com.jtutzo.katalaw.domain.user.UserProjection
import com.jtutzo.katalaw.domain.user.UserProjectionRepository
import org.jooq.Configuration
import org.jooq.example.db.h2.Tables
import org.jooq.example.db.h2.Tables.*
import org.jooq.example.db.h2.tables.records.UserProjectionRecord
import org.jooq.impl.DAOImpl
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository
import java.util.*
import javax.inject.Inject

@Repository
class JooqUserProjectionRepository @Inject constructor(configuration: Configuration) :
        UserProjectionRepository, DAOImpl<UserProjectionRecord, UserProjection, UUID>(USER_PROJECTION, UserProjection::class.java, configuration) {

    override fun getId(userProjection: UserProjection): UUID = userProjection.id

    override fun create(user: UserProjection) = insert(user)

    fun deleteAll() {
        configuration().dsl().truncate(USER_PROJECTION).execute()
    }

}