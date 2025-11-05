# Project Structure

## Root Directory
```
total-ordering-architect-timer/
├── build.gradle.kts          # Gradle build configuration
├── settings.gradle.kts        # Gradle settings
├── compose.yaml              # Docker Compose configuration
├── readme.md                 # Project documentation
├── gradlew                   # Gradle wrapper (Unix)
├── gradlew.bat              # Gradle wrapper (Windows)
└── src/
    └── main/
        ├── java/            # Java source code
        └── resources/       # Configuration and templates
```

## Source Code Organization
```
src/main/java/gc/garcol/totalorderingarchitecttimer/
├── Bootstrap.java                      # Application initialization
├── TotalOrderingArchitectTimerApplication.java  # Spring Boot entry point
├── config/                             # Configuration classes
│   ├── ConsumerVariables.java
│   └── RocksDBVariables.java
├── controller/                         # REST controllers
│   ├── OrderBookRestController.java
│   └── payload/                        # Request/Response DTOs
│       ├── CreateOrderRequest.java
│       ├── CreatePostRequest.java
│       ├── QueryOrdersRequest.java
│       ├── QueryPostsRequest.java
│       └── RequestMapper.java
├── exception/                          # Custom exceptions
│   └── LogicException.java
├── mapper/                             # Data mappers
│   └── CommandLogParser.java
├── model/                              # Domain models
│   ├── domain/                         # Core entities
│   │   ├── Order.java
│   │   ├── OrderStatus.java
│   │   ├── Post.java
│   │   ├── PostType.java
│   │   └── User.java
│   ├── event/                         # Domain events
│   │   ├── Event.java
│   │   ├── OrderCreated.java
│   │   └── PostCreated.java
│   ├── transport/                     # Command/Query DTOs
│   │   ├── Command*.java              # Command types
│   │   ├── Query*.java                # Query types
│   │   ├── dto/                       # Data transfer objects
│   │   └── ...
│   ├── Message.java
│   └── Tuple.java
├── repository/                         # Data access layer
│   ├── CommandLogRepository.java
│   ├── CommandLogRockDBRepositoryImpl.java
│   └── OrderBookRepository.java
├── service/                            # Business logic
│   ├── OrderBookService.java
│   ├── RequestChannel.java
│   ├── RequestHandler.java
│   ├── RequestPublisher.java
│   └── SnapshotService.java
├── util/                               # Utilities
│   └── ByteUtils.java
└── view/                               # Web views
    └── HomeController.java
```

## Resources
```
src/main/resources/
├── application.yaml                    # Application configuration
└── templates/                          # Thymeleaf templates
    └── index.html
```

## Data Directory
```
data/
└── rocksdb/                           # RocksDB database files
    └── commandLog/                    # Command log storage
```
