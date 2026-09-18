# Media Sequencer Backend


## Project Layers

### Controller Layer

The controller layer is responsible for exposing the application's REST endpoints and handling requests coming from the client.

It:
- Receives HTTP requests
- Handles request parameters
- Delegates operations to the service layer
- Returns responses to the client

Controllers:
- `MediaController`
- `WindowController`
- `SyncController`


## API Endpoints

### Media

- `GET /api/media` — Fetch all available media.

### Windows

- `GET /api/windows` — Fetch all configured display windows.
- `GET /api/windows/{windowId}/playlist` — Fetch the playlist configured for a specific window.
- `POST /api/windows/{windowId}/playlist` — Add media to a specific window's playlist.

### Sync

- `GET /api/sync` — Fetch the current synchronization state.
- `POST /api/sync` — Trigger synchronized playback of selected media across all windows for a configured duration.

### WebSocket

- `SockJS /ws` — Establish a real-time WebSocket connection.
- `/topic/sync` — Broadcast synchronization events to all connected windows.
- `/topic/window/{windowId}/playlist` — Broadcast playlist updates to a specific window.

### Key Features

- Media management for images, videos, and blank screens.
- Independent playlists for multiple display windows.
- Dynamic playlist updates without page refresh.
- Configurable media duration.
- 5-hour continuous playback cycle.
- Real-time synchronization of selected media across all windows.
- Configurable synchronization duration.
- Automatic return to the normal playlist after synchronization ends.
- Persistent media, window, playlist, and synchronization data using MySQL.

---

### Service Layer

The service layer contains the core business operations of the application.

It handles:
- Media management
- Display window management
- Playlist management
- Media synchronization
- Synchronization timestamps and duration
- Sending real-time updates through WebSocket

Services:
- `MediaSequencerService`

---

### Repository Layer

The repository layer provides database access through Spring Data JPA.

It is responsible for:
- Persisting entities
- Fetching records
- Updating records
- Removing records
- Executing database queries

Repositories:
- `MediaRepository`
- `DisplayWindowRepo`
- `PlaylistRepository`
- `SyncRepository`

---

### Entity Layer

The entity layer defines the application's persistent data models and their relationships with database tables.

Entities:
- `Media`
- `DisplayWindow`
- `PlaylistItems`
- `Sync`

It uses JPA annotations to map Java objects to MySQL tables and establish relationships between entities.

---

### Configuration Layer

The configuration layer contains the application's infrastructure and framework configuration.

It handles:
- WebSocket and STOMP configuration
- SockJS endpoint configuration
- CORS configuration
- Allowed frontend origins
- WebSocket topics and endpoints

Configuration classes:
- `WebSocketConfig`
- `CorsConfig`

---

### DTO Layer

The DTO layer defines the objects used to exchange data between the frontend and backend.

DTOs are used to:
- Represent API request data
- Define API response data
- Transfer required information between layers
- Avoid exposing internal entity objects directly

Examples:
- `SyncRequest`
- `SyncResponse`

---

### Database Layer

The database layer provides persistent storage using MySQL.

It stores:
- Media details
- Display window information
- Playlist configurations
- Playlist item positions and durations
- Current synchronization state

Technologies:
- MySQL
- Spring Data JPA
- Hibernate
