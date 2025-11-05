# Suggested Commands

## Running the Application
```bash
./gradlew bootRun -Dspring-boot.run.jvmArguments="--enable-native-access=ALL-UNNAMED"
```

## Building the Project
```bash
./gradlew build
```

## Cleaning the Build
```bash
./gradlew clean
```

## Accessing Documentation
- **Swagger UI**: http://localhost:8080/swagger
- API documentation available at application root when running

## System Utilities (Darwin/macOS)
- `git` - Version control
- `ls` - List directory contents
- `cd` - Change directory
- `grep` - Search text patterns
- `find` - Find files and directories
- Standard Unix commands available via zsh shell

## Database/Storage
- RocksDB data stored in: `./data/rocksdb`
- Command logs persisted to RocksDB

## Configuration
- Main configuration: `src/main/resources/application.yaml`
- Key settings:
  - Virtual threads enabled
  - RocksDB path: `./data/rocksdb`
  - Queue handler batch sizes and tick intervals configurable
