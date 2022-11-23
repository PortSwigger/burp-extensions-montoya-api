Multi-API Example Extension
============================

###### Demonstrates using both the Montoya API and the legacy Extender API in one extension

---

This extension works as follows:
- `registerExtenderCallbacks()` is called
  - It registers an extension name
  - It also includes `http://test.url` as an in-scope URL
- `initialize()`is called
  - It registers a new Suite tab
  - Registers an extension name, which overwrites the `registerExtenderCallbacks()` name
  - Checks whether `http://test.url` is in scope, and writes an alert to the Dashboard
- The new Suite tab contains a button
  - When the button is used, the filename according to both the old API and Montoya API are printed to the output