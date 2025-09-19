# Lets_Go_Generate_User_Pics — Android test tool to seed user photos

Small Android/Kotlin utility that **crops, compresses, and uploads images** to the server to generate **random test accounts** (used during development). Images are resized to a consistent portrait aspect, a thumbnail is generated, and both are sent to the server over **gRPC**.

> **Stack:** Kotlin · Android Fragments · gRPC (AndroidChannelBuilder) · Coroutines · ThumbnailUtils · Glide (optional)

---

## What it does (skim me)
- **Bulk seed photos** for test users (no real PII required).
- **Auto-crop & compress:** portrait 1200×1930, JPEG ~30% quality; thumbnail height 200px (width scaled).
- **Dual-payload upload:** full image + thumbnail.
- **Connection helpers:** custom **multi-address name resolver** for simple client-side load balancing.
- **Safety rails:** client-side size check (skips images ≥ 1 MB after compression).

---

## How it works

1. **Pick source images**  
   Images live in `res/drawable*`. A click on the **Continue** button triggers the upload of a predefined batch
   (see `uploadfiles()` for the list of resource IDs).

2. **Crop & compress**  
   - Portrait crop: **1200×1930** (`ThumbnailUtils.extractThumbnail`)  
   - Thumbnail: **height 200px** (width computed to keep aspect ratio)  
   - JPEG quality ≈ **30%** for both.

3. **Send to server**  
   - gRPC client (`AndroidChannelBuilder`) connects to the server (default example uses `10.0.2.2:50052` for the emulator).  
   - A **MultiAddressNameResolverFactory** can register multiple `InetSocketAddress` targets and route via the alias `"multiaddress"`.

4. **Server response**  
   The server returns a processed byte array (e.g., the stored or transformed image); the UI decodes it and shows a preview.

---

## Code tour (where to look)

- **`EnterInfo.kt`** — main fragment and end-to-end flow:
  - **gRPC channel:** `createdChannel` via `AndroidChannelBuilder.forAddress(...)` (or `forTarget("multiaddress")` with a custom resolver).
  - **Multi host support:** `MultiAddressNameResolverFactory` registers multiple addresses with `NameResolverRegistry`, enabling `"multiaddress"` target.
  - **Batch upload:** `uploadfiles()` iterates through drawable resources and calls `convertResourceIDToByteArray(...)`.
  - **Image pipeline:** `sendByteArrayToServer(...)`  
    Crops → compresses → **size guard (<1 MB)** → calls `SendPicturesForTesting.runClient(...)`.
  - **Helpers:** `MakeshiftCoroutineConditionVariable` for simple async coordination; example `RetrieveServerLoad` calls included.

> The gRPC client class `Clients.SendPicturesForTesting` is used to send `(fullImage, thumbnail)` to the server.

---

## Key parameters (tweak as needed)

- **Full image target:** `1200 × 1930` (portrait)  
- **Thumbnail target:** `200px` height; width = `200 * 1200 / 1930` (preserves aspect)  
- **JPEG quality:** ~`30`  
- **Client size limit:** `< 1 MB` (post-compression)  
- **Default endpoint (example):** `10.0.2.2:50052` (Android emulator → host loopback)  
- **Optional multi-address:** register several `InetSocketAddress` and connect via `forTarget("multiaddress")`.

---

## Notes & good practices
- **Test data only:** images are stock/sample photos used for testing purposes. No real user data.  
- **Wait-for-ready / retries:** consider enabling gRPC’s wait-for-ready or backoff if you extend this tool.  
- **Production apps shouldn’t ship seeds:** this project is for **internal testing** and portfolio purposes.

---

## Related
- **Server (C++)** — stateless hub, gRPC/Protobuf, MongoDB  
  👉 [`Lets_Go_Server`](https://github.com/lets-go-app-pub/Lets_Go_Server)

- **Android Client (Kotlin)** — auth, profiles, activities, chat *(SDK versions may be dated)*  
  👉 [`Lets_Go_Android_Client`](https://github.com/lets-go-app-pub/Lets_Go_Android_Client)

## Status & compatibility
Portfolio reference. Android/Gradle versions may be legacy; endpoints in code are placeholders and should be replaced for local testing.

## License
MIT
