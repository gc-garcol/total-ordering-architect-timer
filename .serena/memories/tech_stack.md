# Technology Stack

## Core Technologies
- **Java 25** - Programming language with latest features
- **Spring Boot 4.0.0-SNAPSHOT** - Web framework and application container
- **Gradle** (Kotlin DSL) - Build system

## Key Dependencies
- **RocksDB 9.4.0** - Embedded key-value database for command log persistence
- **JCTools 4.0.5** - High-performance concurrent data structures
- **Agrona 2.3.0** - High-performance data structures and utilities
- **Lombok** - Code generation for reducing boilerplate
- **SpringDoc OpenAPI 3.0.0-M1** - API documentation (Swagger UI)
- **Thymeleaf** - Template engine for web views

## System Features
- Virtual threads enabled (`spring.threads.virtual.enabled: true`)
- Native access enabled for RocksDB (`--enable-native-access=ALL-UNNAMED`)
