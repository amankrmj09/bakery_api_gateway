# Microservices API Report

This document outlines the purpose, expected inputs, and returns of each service and its corresponding API endpoints.

## bakery_auth_service
**Purpose:** Handles operations and endpoints related to auth functionality.

### AuthController.java
#### `POST` `/api/auth/register`
- **Method Name:** `register`
- **Parameters Expected:**
  - `request` (RegisterRequest, RequestBody)
- **Returns:** `ResponseEntity<AuthResponse>`

#### `POST` `/api/auth/login`
- **Method Name:** `login`
- **Parameters Expected:**
  - `request` (LoginRequest, RequestBody)
- **Returns:** `ResponseEntity<AuthResponse>`

#### `POST` `/api/auth/refresh`
- **Method Name:** `refreshToken`
- **Parameters Expected:**
  - `request` (HttpServletRequest)
- **Returns:** `ResponseEntity<AuthResponse>`

#### `POST` `/api/auth/refresh-token`
- **Method Name:** `refreshTokenFromBody`
- **Parameters Expected:**
  - `request` (Map<String, String>, RequestBody)
- **Returns:** `ResponseEntity<AuthResponse>`

#### `POST` `/api/auth/validate`
- **Method Name:** `validateToken`
- **Parameters Expected:**
  - `request` (HttpServletRequest)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/auth/validate-token`
- **Method Name:** `validateTokenFromBody`
- **Parameters Expected:**
  - `request` (Map<String, String>, RequestBody)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/auth/logout`
- **Method Name:** `logout`
- **Parameters Expected:**
  - `request` (HttpServletRequest)
- **Returns:** `ResponseEntity<Map<String, String>>`

#### `POST` `/api/auth/change-password`
- **Method Name:** `changePassword`
- **Parameters Expected:**
  - `request` (Map<String, String>, RequestBody)
  - `httpRequest` (HttpServletRequest)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/auth/verify-email/{userId}`
- **Method Name:** `verifyEmail`
- **Parameters Expected:**
  - `userId` (UUID, PathVariable)
- **Returns:** `ResponseEntity<Map<String, String>>`

#### `GET` `/api/auth/health`
- **Method Name:** `health`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, String>>`

#### `GET` `/api/auth/me`
- **Method Name:** `getCurrentUser`
- **Parameters Expected:**
  - `request` (HttpServletRequest)
- **Returns:** `ResponseEntity<Map<String, Object>>`

### UserController.java
#### `GET` `/api/users/profile`
- **Method Name:** `getUserProfile`
- **Parameters Expected:**
  - `request` (HttpServletRequest)
- **Returns:** `ResponseEntity<UserResponse>`

#### `PUT` `/api/users/profile`
- **Method Name:** `updateUserProfile`
- **Parameters Expected:**
  - `request` (RegisterRequest, RequestBody)
  - `httpRequest` (HttpServletRequest)
- **Returns:** `ResponseEntity<UserResponse>`

#### `GET` `/api/users/{userId}`
- **Method Name:** `getUserById`
- **Parameters Expected:**
  - `userId` (UUID, PathVariable)
  - `request` (HttpServletRequest)
- **Returns:** `ResponseEntity<UserResponse>`

#### `GET` `/api/users/admin/all`
- **Method Name:** `getAllUsers`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<List<UserResponse>>`

#### `GET` `/api/users/admin/search`
- **Method Name:** `searchUsers`
- **Parameters Expected:**
  - `query` (String, RequestParam)
- **Returns:** `ResponseEntity<List<UserResponse>>`

#### `GET` `/api/users/admin/role/{role}`
- **Method Name:** `getUsersByRole`
- **Parameters Expected:**
  - `role` (String, PathVariable)
- **Returns:** `ResponseEntity<List<UserResponse>>`

#### `PUT` `/api/users/admin/{userId}/role`
- **Method Name:** `updateUserRole`
- **Parameters Expected:**
  - `userId` (UUID, PathVariable)
  - `request` (Map<String, String>, RequestBody)
- **Returns:** `ResponseEntity<Map<String, String>>`

#### `PUT` `/api/users/admin/{userId}/status`
- **Method Name:** `updateUserStatus`
- **Parameters Expected:**
  - `userId` (UUID, PathVariable)
  - `request` (Map<String, String>, RequestBody)
- **Returns:** `ResponseEntity<Map<String, String>>`

#### `POST` `/api/users/admin/{userId}/unlock`
- **Method Name:** `unlockUserAccount`
- **Parameters Expected:**
  - `userId` (UUID, PathVariable)
- **Returns:** `ResponseEntity<Map<String, String>>`

#### `DELETE` `/api/users/admin/{userId}`
- **Method Name:** `deleteUser`
- **Parameters Expected:**
  - `userId` (UUID, PathVariable)
- **Returns:** `ResponseEntity<Map<String, String>>`

#### `GET` `/api/users/admin/statistics`
- **Method Name:** `getUserStatistics`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Long>>`

---

## bakery_cart_service
**Purpose:** Handles operations and endpoints related to cart functionality.

### CartController.java
#### `GET` `/api/carts/{cartId}`
- **Method Name:** `getCartById`
- **Parameters Expected:**
  - `cartId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<CartResponse>`

#### `GET` `/api/carts/user/{userId}`
- **Method Name:** `getOrCreateCartForUser`
- **Parameters Expected:**
  - `userId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<CartResponse>`

#### `GET` `/api/carts/session/{sessionId}`
- **Method Name:** `getOrCreateCartForSession`
- **Parameters Expected:**
  - `sessionId` (String, PathVariable)
  - `"X-Session-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<CartResponse>`

#### `POST` `/api/carts/{cartId}/items`
- **Method Name:** `addItemToCart`
- **Parameters Expected:**
  - `cartId` (UUID, PathVariable)
  - `request` (AddItemRequest, RequestBody)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<CartResponse>`

#### `PUT` `/api/carts/{cartId}/items/{itemId}`
- **Method Name:** `updateCartItem`
- **Parameters Expected:**
  - `cartId` (UUID, PathVariable)
  - `itemId` (UUID, PathVariable)
  - `request` (UpdateItemRequest, RequestBody)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<CartResponse>`

#### `DELETE` `/api/carts/{cartId}/items/{itemId}`
- **Method Name:** `removeItemFromCart`
- **Parameters Expected:**
  - `cartId` (UUID, PathVariable)
  - `itemId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<CartResponse>`

#### `DELETE` `/api/carts/{cartId}/items`
- **Method Name:** `clearCart`
- **Parameters Expected:**
  - `cartId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<CartResponse>`

#### `PATCH` `/api/carts/{cartId}`
- **Method Name:** `updateCart`
- **Parameters Expected:**
  - `cartId` (UUID, PathVariable)
  - `request` (CartUpdateRequest, RequestBody)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<CartResponse>`

#### `POST` `/api/carts/merge`
- **Method Name:** `mergeCarts`
- **Parameters Expected:**
  - `request` (MergeCartsRequest, RequestBody)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<CartResponse>`

#### `POST` `/api/carts/{cartId}/save`
- **Method Name:** `saveCartForLater`
- **Parameters Expected:**
  - `cartId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<CartResponse>`

#### `POST` `/api/carts/{cartId}/checkout`
- **Method Name:** `checkoutCart`
- **Parameters Expected:**
  - `cartId` (UUID, PathVariable)
  - `request` (CheckoutRequest, RequestBody)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/carts/user/{userId}/all`
- **Method Name:** `getUserCarts`
- **Parameters Expected:**
  - `userId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<CartResponse>>`

#### `GET` `/api/carts/status/{status}`
- **Method Name:** `getCartsByStatus`
- **Parameters Expected:**
  - `status` (Cart.CartStatus, PathVariable)
  - `"X-User-Role"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<CartResponse>>`

#### `GET` `/api/carts/statistics`
- **Method Name:** `getCartStatistics`
- **Parameters Expected:**
  - `false` (@RequestParam(required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/carts/health`
- **Method Name:** `health`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, String>>`

### CartItemController.java
#### `GET` `/api/cart-items/{itemId}`
- **Method Name:** `getCartItemById`
- **Parameters Expected:**
  - `itemId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<CartItemResponse>`

#### `GET` `/api/cart-items/cart/{cartId}`
- **Method Name:** `getCartItems`
- **Parameters Expected:**
  - `cartId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<CartItemResponse>>`

#### `GET` `/api/cart-items/cart/{cartId}/saved`
- **Method Name:** `getSavedItems`
- **Parameters Expected:**
  - `cartId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<CartItemResponse>>`

#### `POST` `/api/cart-items/{itemId}/save-for-later`
- **Method Name:** `saveItemForLater`
- **Parameters Expected:**
  - `itemId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<CartItemResponse>`

#### `POST` `/api/cart-items/{itemId}/move-to-cart`
- **Method Name:** `moveItemToCart`
- **Parameters Expected:**
  - `itemId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<CartItemResponse>`

#### `GET` `/api/cart-items/health`
- **Method Name:** `health`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, String>>`

### HealthController.java
#### `GET` `/api/health`
- **Method Name:** `health`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/info`
- **Method Name:** `info`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/metrics`
- **Method Name:** `metrics`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

---

## bakery_notification_service
**Purpose:** Handles operations and endpoints related to notification functionality.

### AdminController.java
#### `GET` `/api/admin/overview`
- **Method Name:** `getSystemOverview`
- **Parameters Expected:**
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/admin/statistics`
- **Method Name:** `getComprehensiveStatistics`
- **Parameters Expected:**
  - `(YYYY-MM-DD` (@Parameter(description = "Start date)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/admin/test/email`
- **Method Name:** `testEmailService`
- **Parameters Expected:**
  - `address"` (@Parameter(description = "Test email)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/admin/test/sms`
- **Method Name:** `testSmsService`
- **Parameters Expected:**
  - `number"` (@Parameter(description = "Test phone)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/admin/test/push`
- **Method Name:** `testPushService`
- **Parameters Expected:**
  - `ARN"` (@Parameter(description = "Test endpoint)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/admin/maintenance`
- **Method Name:** `runSystemMaintenance`
- **Parameters Expected:**
  - `run"` (@Parameter(description = "Maintenance tasks to)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/admin/test/notification`
- **Method Name:** `sendTestNotification`
- **Parameters Expected:**
  - `(EMAIL` (@Parameter(description = "Notification type)
  - `SMS` (SMS)
  - `PUSH` (PUSH)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/admin/health`
- **Method Name:** `getServiceHealth`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/admin/info`
- **Method Name:** `getSystemInfo`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/admin/gc`
- **Method Name:** `forceGarbageCollection`
- **Parameters Expected:**
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

### CampaignController.java
#### `PUT` `/api/campaigns/{campaignId}`
- **Method Name:** `updateCampaign`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Campaign)
  - `true` (required =)
- **Returns:** `ResponseEntity<CampaignResponse>`

#### `GET` `/api/campaigns/{campaignId}`
- **Method Name:** `getCampaignById`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Campaign)
  - `true` (required =)
- **Returns:** `ResponseEntity<CampaignResponse>`

#### `GET` `/api/campaigns/status/{status}`
- **Method Name:** `getCampaignsByStatus`
- **Parameters Expected:**
  - `status"` (@Parameter(description = "Campaign)
  - `true` (required =)
- **Returns:** `ResponseEntity<List<CampaignResponse>>`

#### `GET` `/api/campaigns/type/{campaignType}`
- **Method Name:** `getCampaignsByType`
- **Parameters Expected:**
  - `type"` (@Parameter(description = "Campaign)
  - `true` (required =)
- **Returns:** `ResponseEntity<List<CampaignResponse>>`

#### `GET` `/api/campaigns/paginated`
- **Method Name:** `getAllActiveCampaignsPaginated`
- **Parameters Expected:**
  - `(0-based` (@Parameter(description = "Page number)
- **Returns:** `ResponseEntity<Page<CampaignResponse>>`

#### `GET` `/api/campaigns/search`
- **Method Name:** `searchCampaigns`
- **Parameters Expected:**
  - `term"` (@Parameter(description = "Search)
  - `true` (required =)
- **Returns:** `ResponseEntity<List<CampaignResponse>>`

#### `PUT` `/api/campaigns/{campaignId}/start`
- **Method Name:** `startCampaign`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Campaign)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `PUT` `/api/campaigns/{campaignId}/pause`
- **Method Name:** `pauseCampaign`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Campaign)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `PUT` `/api/campaigns/{campaignId}/resume`
- **Method Name:** `resumeCampaign`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Campaign)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `PUT` `/api/campaigns/{campaignId}/complete`
- **Method Name:** `completeCampaign`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Campaign)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `PUT` `/api/campaigns/{campaignId}/cancel`
- **Method Name:** `cancelCampaign`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Campaign)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `DELETE` `/api/campaigns/{campaignId}`
- **Method Name:** `deleteCampaign`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Campaign)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/campaigns/statistics`
- **Method Name:** `getCampaignStatistics`
- **Parameters Expected:**
  - `(YYYY-MM-DD` (@Parameter(description = "Start date)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/campaigns/cleanup`
- **Method Name:** `cleanupOldCampaigns`
- **Parameters Expected:**
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/campaigns/health`
- **Method Name:** `healthCheck`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

### DeviceTokenController.java
#### `POST` `/api/device-tokens/register`
- **Method Name:** `registerDeviceToken`
- **Parameters Expected:**
  - `request` (DeviceRegistrationRequest, RequestBody)
  - `"X-User-Agent"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<DeviceTokenResponse>`

#### `PUT` `/api/device-tokens/{tokenId}`
- **Method Name:** `updateDeviceToken`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Device token)
  - `true` (required =)
- **Returns:** `ResponseEntity<DeviceTokenResponse>`

#### `GET` `/api/device-tokens/{tokenId}`
- **Method Name:** `getDeviceTokenById`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Device token)
  - `true` (required =)
- **Returns:** `ResponseEntity<DeviceTokenResponse>`

#### `GET` `/api/device-tokens/user/{userId}`
- **Method Name:** `getDeviceTokensByUser`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "User)
  - `true` (required =)
- **Returns:** `ResponseEntity<List<DeviceTokenResponse>>`

#### `GET` `/api/device-tokens/user/{userId}/active`
- **Method Name:** `getActiveDeviceTokensByUser`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "User)
  - `true` (required =)
- **Returns:** `ResponseEntity<List<DeviceTokenResponse>>`

#### `GET` `/api/device-tokens/platform/{platform}`
- **Method Name:** `getDeviceTokensByPlatform`
- **Parameters Expected:**
  - `(iOS` (@Parameter(description = "Platform)
  - `ANDROID` (ANDROID)
  - `WEB` (WEB)
- **Returns:** `ResponseEntity<List<DeviceTokenResponse>>`

#### `GET` `/api/device-tokens/user/{userId}/paginated`
- **Method Name:** `getDeviceTokensByUserPaginated`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "User)
  - `true` (required =)
- **Returns:** `ResponseEntity<Page<DeviceTokenResponse>>`

#### `PUT` `/api/device-tokens/{tokenId}/activate`
- **Method Name:** `activateDeviceToken`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Device token)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `PUT` `/api/device-tokens/{tokenId}/deactivate`
- **Method Name:** `deactivateDeviceToken`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Device token)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `PUT` `/api/device-tokens/{tokenId}/enable-notifications`
- **Method Name:** `enableNotifications`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Device token)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `PUT` `/api/device-tokens/{tokenId}/disable-notifications`
- **Method Name:** `disableNotifications`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Device token)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/device-tokens/{tokenId}/subscribe`
- **Method Name:** `subscribeToTopic`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Device token)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/device-tokens/{tokenId}/unsubscribe`
- **Method Name:** `unsubscribeFromTopic`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Device token)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `DELETE` `/api/device-tokens/{tokenId}`
- **Method Name:** `deleteDeviceToken`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Device token)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/device-tokens/statistics`
- **Method Name:** `getDeviceTokenStatistics`
- **Parameters Expected:**
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/device-tokens/validate`
- **Method Name:** `validateDeviceTokens`
- **Parameters Expected:**
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/device-tokens/cleanup`
- **Method Name:** `cleanupDeviceTokens`
- **Parameters Expected:**
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/device-tokens/health`
- **Method Name:** `healthCheck`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

### NotificationController.java
#### `POST` `/api/notifications/bulk`
- **Method Name:** `sendBulkNotifications`
- **Parameters Expected:**
  - `requests` (List<SendNotificationRequest>, RequestBody)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/notifications/{notificationId}`
- **Method Name:** `getNotificationById`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Notification)
  - `true` (required =)
- **Returns:** `ResponseEntity<NotificationResponse>`

#### `GET` `/api/notifications/user/{userId}`
- **Method Name:** `getNotificationsByUser`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "User)
  - `true` (required =)
- **Returns:** `ResponseEntity<List<NotificationResponse>>`

#### `GET` `/api/notifications/user/{userId}/paginated`
- **Method Name:** `getNotificationsByUserPaginated`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "User)
  - `true` (required =)
- **Returns:** `ResponseEntity<Page<NotificationResponse>>`

#### `GET` `/api/notifications/status/{status}`
- **Method Name:** `getNotificationsByStatus`
- **Parameters Expected:**
  - `status"` (@Parameter(description = "Notification)
  - `true` (required =)
- **Returns:** `ResponseEntity<List<NotificationResponse>>`

#### `GET` `/api/notifications/type/{type}`
- **Method Name:** `getNotificationsByType`
- **Parameters Expected:**
  - `type"` (@Parameter(description = "Notification)
  - `true` (required =)
- **Returns:** `ResponseEntity<List<NotificationResponse>>`

#### `GET` `/api/notifications/campaign/{campaignId}`
- **Method Name:** `getNotificationsByCampaign`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Campaign)
  - `true` (required =)
- **Returns:** `ResponseEntity<List<NotificationResponse>>`

#### `PUT` `/api/notifications/{notificationId}/cancel`
- **Method Name:** `cancelNotification`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Notification)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `PUT` `/api/notifications/{notificationId}/opened`
- **Method Name:** `markNotificationAsOpened`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Notification)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `PUT` `/api/notifications/{notificationId}/clicked`
- **Method Name:** `markNotificationAsClicked`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Notification)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/notifications/statistics`
- **Method Name:** `getNotificationStatistics`
- **Parameters Expected:**
  - `(YYYY-MM-DD` (@Parameter(description = "Start date)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/notifications/process-pending`
- **Method Name:** `processPendingNotifications`
- **Parameters Expected:**
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/notifications/retry-failed`
- **Method Name:** `retryFailedNotifications`
- **Parameters Expected:**
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/notifications/cleanup`
- **Method Name:** `cleanupOldNotifications`
- **Parameters Expected:**
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/notifications/health`
- **Method Name:** `healthCheck`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

### TemplateController.java
#### `PUT` `/api/templates/{templateId}`
- **Method Name:** `updateTemplate`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Template)
  - `true` (required =)
- **Returns:** `ResponseEntity<TemplateResponse>`

#### `GET` `/api/templates/{templateId}`
- **Method Name:** `getTemplateById`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Template)
  - `true` (required =)
- **Returns:** `ResponseEntity<TemplateResponse>`

#### `GET` `/api/templates/name/{templateName}`
- **Method Name:** `getTemplateByName`
- **Parameters Expected:**
  - `name"` (@Parameter(description = "Template)
  - `true` (required =)
- **Returns:** `ResponseEntity<TemplateResponse>`

#### `GET` `/api/templates/type/{templateType}`
- **Method Name:** `getTemplatesByType`
- **Parameters Expected:**
  - `type"` (@Parameter(description = "Template)
  - `true` (required =)
- **Returns:** `ResponseEntity<List<TemplateResponse>>`

#### `GET` `/api/templates/paginated`
- **Method Name:** `getAllActiveTemplatesPaginated`
- **Parameters Expected:**
  - `(0-based` (@Parameter(description = "Page number)
- **Returns:** `ResponseEntity<Page<TemplateResponse>>`

#### `GET` `/api/templates/search`
- **Method Name:** `searchTemplates`
- **Parameters Expected:**
  - `term"` (@Parameter(description = "Search)
  - `true` (required =)
- **Returns:** `ResponseEntity<List<TemplateResponse>>`

#### `GET` `/api/templates/default/{templateType}`
- **Method Name:** `getDefaultTemplate`
- **Parameters Expected:**
  - `type"` (@Parameter(description = "Template)
  - `true` (required =)
- **Returns:** `ResponseEntity<TemplateResponse>`

#### `PUT` `/api/templates/{templateId}/set-default`
- **Method Name:** `setAsDefaultTemplate`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Template)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `PUT` `/api/templates/{templateId}/activate`
- **Method Name:** `activateTemplate`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Template)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `PUT` `/api/templates/{templateId}/deactivate`
- **Method Name:** `deactivateTemplate`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Template)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `DELETE` `/api/templates/{templateId}`
- **Method Name:** `deleteTemplate`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Template)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/templates/{templateId}/validate`
- **Method Name:** `validateTemplate`
- **Parameters Expected:**
  - `ID"` (@Parameter(description = "Template)
  - `true` (required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/templates/statistics`
- **Method Name:** `getTemplateStatistics`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/templates/health`
- **Method Name:** `healthCheck`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

---

## bakery_order_service
**Purpose:** Handles operations and endpoints related to order functionality.

### HealthController.java
#### `GET` `/api/health`
- **Method Name:** `health`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/info`
- **Method Name:** `info`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/metrics`
- **Method Name:** `metrics`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

### OrderController.java
#### `GET` `/api/orders/{orderId}`
- **Method Name:** `getOrderById`
- **Parameters Expected:**
  - `orderId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<OrderResponse>`

#### `GET` `/api/orders/number/{orderNumber}`
- **Method Name:** `getOrderByOrderNumber`
- **Parameters Expected:**
  - `orderNumber` (String, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<OrderResponse>`

#### `GET` `/api/orders/user/{userId}`
- **Method Name:** `getOrdersByUserId`
- **Parameters Expected:**
  - `userId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<OrderResponse>>`

#### `GET` `/api/orders/user/{userId}/paginated`
- **Method Name:** `getOrdersByUserIdWithPagination`
- **Parameters Expected:**
  - `userId` (UUID, PathVariable)
  - `"0"` (@RequestParam(defaultValue =)
- **Returns:** `ResponseEntity<Page<OrderResponse>>`

#### `GET` `/api/orders/status/{status}`
- **Method Name:** `getOrdersByStatus`
- **Parameters Expected:**
  - `status` (Order.OrderStatus, PathVariable)
- **Returns:** `ResponseEntity<List<OrderResponse>>`

#### `GET` `/api/orders/search`
- **Method Name:** `searchOrders`
- **Parameters Expected:**
  - `query` (String, RequestParam)
- **Returns:** `ResponseEntity<List<OrderResponse>>`

#### `GET` `/api/orders/recent`
- **Method Name:** `getRecentOrders`
- **Parameters Expected:**
  - `"7"` (@RequestParam(defaultValue =)
- **Returns:** `ResponseEntity<List<OrderResponse>>`

#### `GET` `/api/orders/filter`
- **Method Name:** `getOrdersWithFilters`
- **Parameters Expected:**
  - `false` (@RequestParam(required =)
- **Returns:** `ResponseEntity<List<OrderResponse>>`

#### `PATCH` `/api/orders/{orderId}/status`
- **Method Name:** `updateOrderStatus`
- **Parameters Expected:**
  - `orderId` (UUID, PathVariable)
  - `request` (OrderStatusUpdateRequest, RequestBody)
  - `"X-User-Role"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<OrderResponse>`

#### `POST` `/api/orders/{orderId}/cancel`
- **Method Name:** `cancelOrder`
- **Parameters Expected:**
  - `orderId` (UUID, PathVariable)
  - `request` (Map<String, String>, RequestBody)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<OrderResponse>`

#### `GET` `/api/orders/statistics`
- **Method Name:** `getOrderStatistics`
- **Parameters Expected:**
  - `false` (@RequestParam(required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/orders/health`
- **Method Name:** `health`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, String>>`

#### `POST` `/api/orders/{orderId}/payment-update`
- **Method Name:** `updateOrderPaymentStatus`
- **Parameters Expected:**
  - `orderId` (UUID, PathVariable)
  - `paymentUpdate` (Map<String, Object>, RequestBody)
- **Returns:** `ResponseEntity<Map<String, String>>`

---

## bakery_payment_service
**Purpose:** Handles operations and endpoints related to payment functionality.

### HealthController.java
#### `GET` `/api/health`
- **Method Name:** `health`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/info`
- **Method Name:** `info`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/metrics`
- **Method Name:** `metrics`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

### PaymentController.java
#### `GET` `/api/payments/{paymentId}`
- **Method Name:** `getPaymentById`
- **Parameters Expected:**
  - `paymentId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<PaymentResponse>`

#### `GET` `/api/payments/reference/{paymentReference}`
- **Method Name:** `getPaymentByReference`
- **Parameters Expected:**
  - `paymentReference` (String, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<PaymentResponse>`

#### `GET` `/api/payments/order/{orderId}`
- **Method Name:** `getPaymentByOrderId`
- **Parameters Expected:**
  - `orderId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<PaymentResponse>`

#### `GET` `/api/payments/user/{userId}`
- **Method Name:** `getPaymentsByUserId`
- **Parameters Expected:**
  - `userId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<PaymentResponse>>`

#### `GET` `/api/payments/status/{status}`
- **Method Name:** `getPaymentsByStatus`
- **Parameters Expected:**
  - `status` (Payment.PaymentStatus, PathVariable)
  - `"X-User-Role"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<PaymentResponse>>`

#### `PATCH` `/api/payments/{paymentId}/status`
- **Method Name:** `updatePaymentStatus`
- **Parameters Expected:**
  - `paymentId` (UUID, PathVariable)
  - `request` (PaymentStatusUpdateRequest, RequestBody)
  - `"X-User-Role"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<PaymentResponse>`

#### `POST` `/api/payments/{paymentId}/cancel`
- **Method Name:** `cancelPayment`
- **Parameters Expected:**
  - `paymentId` (UUID, PathVariable)
  - `request` (Map<String, String>, RequestBody)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<PaymentResponse>`

#### `POST` `/api/payments/{paymentId}/retry`
- **Method Name:** `retryPayment`
- **Parameters Expected:**
  - `paymentId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<PaymentResponse>`

#### `GET` `/api/payments/statistics`
- **Method Name:** `getPaymentStatistics`
- **Parameters Expected:**
  - `false` (@RequestParam(required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/payments/health`
- **Method Name:** `health`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, String>>`

### RefundController.java
#### `GET` `/api/refunds/{refundId}`
- **Method Name:** `getRefundById`
- **Parameters Expected:**
  - `refundId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<RefundResponse>`

#### `GET` `/api/refunds/reference/{refundReference}`
- **Method Name:** `getRefundByReference`
- **Parameters Expected:**
  - `refundReference` (String, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<RefundResponse>`

#### `GET` `/api/refunds/payment/{paymentId}`
- **Method Name:** `getRefundsByPaymentId`
- **Parameters Expected:**
  - `paymentId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<RefundResponse>>`

#### `GET` `/api/refunds/user/{userId}`
- **Method Name:** `getRefundsByUser`
- **Parameters Expected:**
  - `userId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<RefundResponse>>`

#### `GET` `/api/refunds/status/{status}`
- **Method Name:** `getRefundsByStatus`
- **Parameters Expected:**
  - `status` (Refund.RefundStatus, PathVariable)
  - `"X-User-Role"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<RefundResponse>>`

#### `POST` `/api/refunds/{refundId}/approve`
- **Method Name:** `approveRefund`
- **Parameters Expected:**
  - `refundId` (UUID, PathVariable)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<RefundResponse>`

#### `POST` `/api/refunds/{refundId}/reject`
- **Method Name:** `rejectRefund`
- **Parameters Expected:**
  - `refundId` (UUID, PathVariable)
  - `request` (Map<String, String>, RequestBody)
  - `"X-User-Id"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<RefundResponse>`

#### `GET` `/api/refunds/pending`
- **Method Name:** `getPendingRefunds`
- **Parameters Expected:**
  - `"X-User-Role"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<RefundResponse>>`

#### `GET` `/api/refunds/completed`
- **Method Name:** `getCompletedRefunds`
- **Parameters Expected:**
  - `"X-User-Role"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<RefundResponse>>`

#### `GET` `/api/refunds/failed`
- **Method Name:** `getFailedRefunds`
- **Parameters Expected:**
  - `"X-User-Role"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<RefundResponse>>`

#### `GET` `/api/refunds/search`
- **Method Name:** `searchRefunds`
- **Parameters Expected:**
  - `query` (String, RequestParam)
  - `"X-User-Role"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<RefundResponse>>`

#### `GET` `/api/refunds/filter`
- **Method Name:** `getRefundsWithFilters`
- **Parameters Expected:**
  - `false` (@RequestParam(required =)
- **Returns:** `ResponseEntity<List<RefundResponse>>`

#### `GET` `/api/refunds/statistics`
- **Method Name:** `getRefundStatistics`
- **Parameters Expected:**
  - `false` (@RequestParam(required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/refunds/health`
- **Method Name:** `health`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, String>>`

### TransactionController.java
#### `GET` `/api/transactions/{transactionId}`
- **Method Name:** `getTransactionById`
- **Parameters Expected:**
  - `transactionId` (UUID, PathVariable)
  - `"X-User-Role"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<PaymentTransactionResponse>`

#### `GET` `/api/transactions/payment/{paymentId}`
- **Method Name:** `getTransactionsByPaymentId`
- **Parameters Expected:**
  - `paymentId` (UUID, PathVariable)
  - `"X-User-Role"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<PaymentTransactionResponse>>`

#### `GET` `/api/transactions/status/{status}`
- **Method Name:** `getTransactionsByStatus`
- **Parameters Expected:**
  - `status` (PaymentTransaction.TransactionStatus, PathVariable)
  - `"X-User-Role"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<PaymentTransactionResponse>>`

#### `GET` `/api/transactions/type/{type}`
- **Method Name:** `getTransactionsByType`
- **Parameters Expected:**
  - `type` (PaymentTransaction.TransactionType, PathVariable)
  - `"X-User-Role"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<PaymentTransactionResponse>>`

#### `GET` `/api/transactions/pending`
- **Method Name:** `getPendingTransactions`
- **Parameters Expected:**
  - `"X-User-Role"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<PaymentTransactionResponse>>`

#### `GET` `/api/transactions/failed`
- **Method Name:** `getFailedTransactions`
- **Parameters Expected:**
  - `"X-User-Role"` (@RequestHeader(value =)
  - `false` (required =)
- **Returns:** `ResponseEntity<List<PaymentTransactionResponse>>`

#### `GET` `/api/transactions/statistics`
- **Method Name:** `getTransactionStatistics`
- **Parameters Expected:**
  - `false` (@RequestParam(required =)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/transactions/health`
- **Method Name:** `health`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, String>>`

---

## bakery_product_service
**Purpose:** Handles operations and endpoints related to product functionality.

### CategoryController.java
#### `GET` `/api/categories/active`
- **Method Name:** `getActiveCategories`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<List<CategoryResponse>>`

#### `GET` `/api/categories/with-products`
- **Method Name:** `getCategoriesWithProducts`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<List<CategoryResponse>>`

#### `GET` `/api/categories/with-active-products`
- **Method Name:** `getCategoriesWithActiveProducts`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<List<CategoryResponse>>`

#### `GET` `/api/categories/{categoryId}`
- **Method Name:** `getCategoryById`
- **Parameters Expected:**
  - `categoryId` (UUID, PathVariable)
- **Returns:** `ResponseEntity<CategoryResponse>`

#### `PUT` `/api/categories/{categoryId}`
- **Method Name:** `updateCategory`
- **Parameters Expected:**
  - `categoryId` (UUID, PathVariable)
  - `request` (CategoryRequest, RequestBody)
- **Returns:** `ResponseEntity<CategoryResponse>`

#### `DELETE` `/api/categories/{categoryId}`
- **Method Name:** `deleteCategory`
- **Parameters Expected:**
  - `categoryId` (UUID, PathVariable)
- **Returns:** `ResponseEntity<Map<String, String>>`

#### `GET` `/api/categories/search`
- **Method Name:** `searchCategories`
- **Parameters Expected:**
  - `query` (String, RequestParam)
- **Returns:** `ResponseEntity<List<CategoryResponse>>`

#### `POST` `/api/categories/{categoryId}/toggle-status`
- **Method Name:** `toggleCategoryStatus`
- **Parameters Expected:**
  - `categoryId` (UUID, PathVariable)
- **Returns:** `ResponseEntity<CategoryResponse>`

#### `POST` `/api/categories/reorder`
- **Method Name:** `reorderCategories`
- **Parameters Expected:**
  - `categoryOrders` (Map<UUID, Integer>, RequestBody)
- **Returns:** `ResponseEntity<Map<String, String>>`

#### `GET` `/api/categories/statistics`
- **Method Name:** `getCategoryStatistics`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/categories/health`
- **Method Name:** `health`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, String>>`

### HealthController.java
#### `GET` `/api/health`
- **Method Name:** `health`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/info`
- **Method Name:** `info`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/metrics`
- **Method Name:** `metrics`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

### InventoryController.java
#### `GET` `/api/inventory/product/{productId}`
- **Method Name:** `getInventoryByProductId`
- **Parameters Expected:**
  - `productId` (UUID, PathVariable)
- **Returns:** `ResponseEntity<InventoryResponse>`

#### `GET` `/api/inventory/sku/{sku}`
- **Method Name:** `getInventoryByProductSku`
- **Parameters Expected:**
  - `sku` (String, PathVariable)
- **Returns:** `ResponseEntity<InventoryResponse>`

#### `GET` `/api/inventory/low-stock`
- **Method Name:** `getLowStockItems`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<List<InventoryResponse>>`

#### `GET` `/api/inventory/out-of-stock`
- **Method Name:** `getOutOfStockItems`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<List<InventoryResponse>>`

#### `GET` `/api/inventory/needs-reorder`
- **Method Name:** `getItemsNeedingReorder`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<List<InventoryResponse>>`

#### `GET` `/api/inventory/expired`
- **Method Name:** `getExpiredItems`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<List<InventoryResponse>>`

#### `GET` `/api/inventory/expiring-soon`
- **Method Name:** `getItemsExpiringSoon`
- **Parameters Expected:**
  - `"24"` (@RequestParam(defaultValue =)
- **Returns:** `ResponseEntity<List<InventoryResponse>>`

#### `PUT` `/api/inventory/product/{productId}`
- **Method Name:** `updateInventory`
- **Parameters Expected:**
  - `productId` (UUID, PathVariable)
  - `request` (InventoryUpdateRequest, RequestBody)
- **Returns:** `ResponseEntity<InventoryResponse>`

#### `POST` `/api/inventory/product/{productId}/add-stock`
- **Method Name:** `addStock`
- **Parameters Expected:**
  - `productId` (UUID, PathVariable)
  - `request` (Map<String, Object>, RequestBody)
- **Returns:** `ResponseEntity<InventoryResponse>`

#### `POST` `/api/inventory/product/{productId}/reserve`
- **Method Name:** `reserveStock`
- **Parameters Expected:**
  - `productId` (UUID, PathVariable)
  - `request` (Map<String, Integer>, RequestBody)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/inventory/product/{productId}/release-reserved`
- **Method Name:** `releaseReservedStock`
- **Parameters Expected:**
  - `productId` (UUID, PathVariable)
  - `request` (Map<String, Integer>, RequestBody)
- **Returns:** `ResponseEntity<Map<String, String>>`

#### `POST` `/api/inventory/product/{productId}/consume`
- **Method Name:** `consumeStock`
- **Parameters Expected:**
  - `productId` (UUID, PathVariable)
  - `request` (Map<String, Integer>, RequestBody)
- **Returns:** `ResponseEntity<Map<String, String>>`

#### `GET` `/api/inventory/product/{productId}/availability`
- **Method Name:** `checkStockAvailability`
- **Parameters Expected:**
  - `productId` (UUID, PathVariable)
  - `quantity` (Integer, RequestParam)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/inventory/product/{productId}/available-stock`
- **Method Name:** `getAvailableStock`
- **Parameters Expected:**
  - `productId` (UUID, PathVariable)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `POST` `/api/inventory/bulk-update-minimum-stock`
- **Method Name:** `bulkUpdateMinimumStock`
- **Parameters Expected:**
  - `productMinimumStocks` (Map<UUID, Integer>, RequestBody)
- **Returns:** `ResponseEntity<Map<String, String>>`

#### `GET` `/api/inventory/statistics`
- **Method Name:** `getInventoryStatistics`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/inventory/health`
- **Method Name:** `health`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, String>>`

### ProductController.java
#### `GET` `/api/products/active`
- **Method Name:** `getActiveProducts`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<List<ProductResponse>>`

#### `GET` `/api/products/available`
- **Method Name:** `getAvailableProducts`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<List<ProductResponse>>`

#### `GET` `/api/products/featured`
- **Method Name:** `getFeaturedProducts`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<List<ProductResponse>>`

#### `GET` `/api/products/on-sale`
- **Method Name:** `getProductsOnSale`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<List<ProductResponse>>`

#### `GET` `/api/products/recent`
- **Method Name:** `getRecentlyAddedProducts`
- **Parameters Expected:**
  - `"7"` (@RequestParam(defaultValue =)
- **Returns:** `ResponseEntity<List<ProductResponse>>`

#### `GET` `/api/products/{productId}`
- **Method Name:** `getProductById`
- **Parameters Expected:**
  - `productId` (UUID, PathVariable)
- **Returns:** `ResponseEntity<ProductResponse>`

#### `GET` `/api/products/batch`
- **Method Name:** `getProductsByIds`
- **Parameters Expected:**
  - `productIds` (List<UUID>, RequestParam)
- **Returns:** `ResponseEntity<List<ProductResponse>>`

#### `POST` `/api/products/batch/validate`
- **Method Name:** `validateProducts`
- **Parameters Expected:**
  - `productIds` (List<UUID>, RequestBody)
- **Returns:** `ResponseEntity<List<ProductResponse>>`

#### `GET` `/api/products/sku/{sku}`
- **Method Name:** `getProductBySku`
- **Parameters Expected:**
  - `sku` (String, PathVariable)
- **Returns:** `ResponseEntity<ProductResponse>`

#### `GET` `/api/products/category/{categoryId}`
- **Method Name:** `getProductsByCategory`
- **Parameters Expected:**
  - `categoryId` (UUID, PathVariable)
- **Returns:** `ResponseEntity<List<ProductResponse>>`

#### `GET` `/api/products/category/{categoryId}/paginated`
- **Method Name:** `getProductsByCategoryWithPagination`
- **Parameters Expected:**
  - `categoryId` (UUID, PathVariable)
  - `"0"` (@RequestParam(defaultValue =)
- **Returns:** `ResponseEntity<Page<ProductResponse>>`

#### `GET` `/api/products/search`
- **Method Name:** `searchProducts`
- **Parameters Expected:**
  - `query` (String, RequestParam)
- **Returns:** `ResponseEntity<List<ProductResponse>>`

#### `GET` `/api/products/search/paginated`
- **Method Name:** `searchProductsWithPagination`
- **Parameters Expected:**
  - `query` (String, RequestParam)
  - `"0"` (@RequestParam(defaultValue =)
- **Returns:** `ResponseEntity<Page<ProductResponse>>`

#### `GET` `/api/products/price-range`
- **Method Name:** `getProductsByPriceRange`
- **Parameters Expected:**
  - `minPrice` (BigDecimal, RequestParam)
  - `maxPrice` (BigDecimal, RequestParam)
- **Returns:** `ResponseEntity<List<ProductResponse>>`

#### `GET` `/api/products/tag/{tag}`
- **Method Name:** `getProductsByTag`
- **Parameters Expected:**
  - `tag` (String, PathVariable)
- **Returns:** `ResponseEntity<List<ProductResponse>>`

#### `GET` `/api/products/without-allergen/{allergen}`
- **Method Name:** `getProductsWithoutAllergen`
- **Parameters Expected:**
  - `allergen` (String, PathVariable)
- **Returns:** `ResponseEntity<List<ProductResponse>>`

#### `GET` `/api/products/filter`
- **Method Name:** `searchProductsWithFilters`
- **Parameters Expected:**
  - `false` (@RequestParam(required =)
- **Returns:** `ResponseEntity<List<ProductResponse>>`

#### `PUT` `/api/products/{productId}`
- **Method Name:** `updateProduct`
- **Parameters Expected:**
  - `productId` (UUID, PathVariable)
  - `request` (ProductRequest, RequestBody)
- **Returns:** `ResponseEntity<ProductResponse>`

#### `PATCH` `/api/products/{productId}/status`
- **Method Name:** `updateProductStatus`
- **Parameters Expected:**
  - `productId` (UUID, PathVariable)
  - `request` (Map<String, String>, RequestBody)
- **Returns:** `ResponseEntity<ProductResponse>`

#### `POST` `/api/products/{productId}/toggle-featured`
- **Method Name:** `toggleFeaturedStatus`
- **Parameters Expected:**
  - `productId` (UUID, PathVariable)
- **Returns:** `ResponseEntity<ProductResponse>`

#### `DELETE` `/api/products/{productId}`
- **Method Name:** `deleteProduct`
- **Parameters Expected:**
  - `productId` (UUID, PathVariable)
- **Returns:** `ResponseEntity<Map<String, String>>`

#### `GET` `/api/products/{productId}/availability`
- **Method Name:** `checkProductAvailability`
- **Parameters Expected:**
  - `productId` (UUID, PathVariable)
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/products/statistics`
- **Method Name:** `getProductStatistics`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, Object>>`

#### `GET` `/api/products/health`
- **Method Name:** `health`
- **Parameters Expected:**
  - None
- **Returns:** `ResponseEntity<Map<String, String>>`

---
