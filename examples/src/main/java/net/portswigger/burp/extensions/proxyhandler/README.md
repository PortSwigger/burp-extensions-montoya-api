Proxy Handler Example Extension
============================

###### Demonstrates performing various actions on requests passing through the Proxy

---

The extension works as follows:
- It registers a Proxy handler
- For outgoing request messages:
  - It drops all `POST` requests
  - Requests with `foo` in the URL are not intercepted
  - If the `Content-Type` is `JSON`, the request is highlighted `RED` and Burp rules for Interception are followed
  - All other requests are intercepted
  - User modified requests are continued as normal
- For incoming response messages:
  - All responses that contain the string `username` are highlighted `BLUE`
  - All other responses follow Burp rules for Interception