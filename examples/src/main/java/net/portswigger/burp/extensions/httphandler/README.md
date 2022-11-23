HTTP Handler Example Extension
============================

###### Demonstrates performing various actions on requests passing through any tool in Burp

---

The extension works as follows:
- It registers an HTTP handler
- For outgoing request messages:
  - If the request is a `POST` request:
    - The body of the request is logged to output
    - A comment is added to the request
  - A URL parameter is added to the request
- For incoming response messages:
  - If the corresponding request contained a `Content-Length` header, a `BLUE` highlight is added