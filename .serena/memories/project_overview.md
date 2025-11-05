# Project Overview

## Purpose
This project implements a **total ordering architecture** system that preserves determinism while updating outdated data. It's designed for consensus-based systems where the same sequence of commands must produce the same result regardless of when they are executed:

```
f(command[i -> j], timestampX) == f(command[i -> j], timestampY)
```

The system implements an order book with timeout/timer functionality, using a deterministic approach where commands are processed in a total order through a command queue, journaled, and handled with state management and sorted timers.

## Architecture
- **Producers** publish commands to a **command queue**
- **Consumer** loop consumes commands, journals them, and handles logic
- **State** is maintained with **sorted timers** for timeout handling
- **Tick commands** are spawned at intervals to trigger timeout processing

## Key Components
- Order book management (order creation, payment marking)
- Post management (buy/sell posts)
- Command log persistence using RocksDB
- REST API for order and post operations
- Swagger UI for API documentation
