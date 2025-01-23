# Character API

This API provides functionality to manage information related to characters, including searching and filtering characters based on various criteria.

## Endpoints

### **GET /character/findAll**

Retrieves a list of characters based on the provided filters.

**URL:** `/character/findAll`
- **Method:** `GET`
- **Query Parameters:**
  - `limit` *(optional)*: Maximum number of results to return. Defaults to `10`.
  - `name` *(optional)*: Filter by character name. If not provided, no name filter is applied.
  - `series` *(required)*: Array of series IDs to filter characters.
  - `comic` *(optional)*: Array of comic IDs to filter characters.
