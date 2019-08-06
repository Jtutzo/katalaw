package com.jtutzo.katalaw.bootstrap

import org.axonframework.common.jdbc.ConnectionProvider
import org.axonframework.common.jdbc.PersistenceExceptionResolver
import org.axonframework.common.transaction.TransactionManager
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.axonframework.eventhandling.tokenstore.jdbc.JdbcTokenStore
import org.axonframework.eventhandling.tokenstore.jdbc.TokenSchema
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.eventsourcing.eventstore.jdbc.EventSchema
import org.axonframework.eventsourcing.eventstore.jdbc.JdbcEventStorageEngine
import org.axonframework.modelling.saga.repository.jdbc.GenericSagaSqlSchema
import org.axonframework.modelling.saga.repository.jdbc.JdbcSagaStore
import org.axonframework.serialization.Serializer
import org.axonframework.spring.config.AxonConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AxonConfig {

    @Bean
    fun eventStorageEngine(defaultSerializer: Serializer,
                           persistenceExceptionResolver: PersistenceExceptionResolver,
                           serializer: Serializer,
                           configuration: AxonConfiguration,
                           connectionProvider: ConnectionProvider,
                           transactionManager: TransactionManager,
                           eventSchema: EventSchema): EventStorageEngine =
            JdbcEventStorageEngine.builder()
                    .snapshotSerializer(defaultSerializer)
                    .upcasterChain(configuration.upcasterChain())
                    .persistenceExceptionResolver(persistenceExceptionResolver)
                    .eventSerializer(serializer)
                    .connectionProvider(connectionProvider)
                    .transactionManager(transactionManager)
                    .schema(eventSchema)
                    .build()

    @Bean
    fun tokenStore(connectionProvider: ConnectionProvider, serializer: Serializer, tokenSchema: TokenSchema): TokenStore =
            JdbcTokenStore.builder()
                    .connectionProvider(connectionProvider)
                    .serializer(serializer)
                    .schema(tokenSchema)
                    .build()

    @Bean
    fun sagaStore(connectionProvider: ConnectionProvider, serializer: Serializer): JdbcSagaStore =
            JdbcSagaStore.builder()
                    .connectionProvider(connectionProvider)
                    .sqlSchema(GenericSagaSqlSchema())
                    .serializer(serializer)
                    .build()

    @Bean
    fun eventSchema(): EventSchema = EventSchema.builder()
            .eventTable("domain_event_entry")
            .snapshotTable("snapshot_event_entry")
            .globalIndexColumn("global_index")
            .eventIdentifierColumn("event_identifier")
            .metaDataColumn("meta_data")
            .payloadRevisionColumn("payload_revision")
            .payloadTypeColumn("payload_type")
            .timestampColumn("time_stamp")
            .aggregateIdentifierColumn("aggregate_identifier")
            .sequenceNumberColumn("sequence_number")
            .build()

    @Bean
    fun tokenSchema(): TokenSchema = TokenSchema.builder()
            .setTokenTable("token_entry")
            .setProcessorNameColumn("processor_name")
            .setTokenTypeColumn("token_type")
            .build()

}
