# Code Style and Conventions

## Package Structure
Standard Java package naming: `gc.garcol.totalorderingarchitecttimer`

## Package Organization
- `controller/` - REST controllers and request payloads
- `service/` - Business logic and request handling
- `repository/` - Data access layer (RocksDB implementation)
- `model/` - Domain models, events, and transport DTOs
  - `domain/` - Core domain entities (Order, Post, User, etc.)
  - `event/` - Domain events (OrderCreated, PostCreated)
  - `transport/` - Command and query DTOs for inter-service communication
- `config/` - Configuration properties classes
- `exception/` - Custom exceptions
- `mapper/` - Data transformation logic
- `util/` - Utility classes
- `view/` - Web view controllers

## Naming Conventions
- Standard Java naming conventions (PascalCase for classes, camelCase for methods/variables)
- Repository implementations use `*RepositoryImpl` suffix
- Request/Response DTOs use descriptive names (e.g., `CreateOrderRequest`, `QueryOrdersResult`)

## Code Style
- Uses **Lombok** annotations extensively:
  - `@RequiredArgsConstructor` for dependency injection
  - `@Slf4j` for logging
  - `@Data`, `@Getter`, `@Setter` for data classes
- JavaDoc comments for classes with `@author` and `@since` tags
- Spring annotations for dependency injection and component scanning

## Design Patterns
- Repository pattern for data access abstraction
- Command/Query separation (CQRS-inspired) in transport layer
- Event-driven architecture with domain events
- Service layer pattern for business logic
