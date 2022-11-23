Event Listeners Example Extension
============================

###### Registers handlers for various runtime events, and prints a message when each event occurs.

---

This extension demonstrates how to register listeners for various runtime
events:
- HTTP requests and responses for all Burp tools.
- HTTP messages intercepted by the Proxy.
- Addition of new scan issues.
- The extension being unloaded by the user.

The sample extension simply prints a message to the output stream when an event
occurs.