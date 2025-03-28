# Changelog

## v2025.3
- Added ability to register Editor hotkeys.
- Added additional URL encoding methods.
- Added overloaded logToOutput method to Logging.
- Added utility hasRequestSelection and hasResponseSelection to RequestResponseSelection.

## v2025.2
- Added ability to get parameters without specifying the type from *HttpRequest*.
- Added ability to retrieve project identifier using *Project*.
- Added ability to send chat requests to LLMs using the new *Ai* interface.

## v2024.12
- Added ability to set custom server name indicator using *RequestOptions*. 
- Added ability to set custom response timeout using *RequestOptions*. 

## v2024.11
- Added requestResponse and issues methods to *SiteMapNode*.

## v2024.7

- Added JSON parsing / manipulation support with *JsonUtils* and *JsonNode*.
- Added ability to control redirection behavior when issuing HTTP requests using *RedirectionMode* and *RequestOptions*.
- Added utility methods to *HttpRequest* and *HttpResponse* which add, update or remove multiple headers.
- Added *EditorOptions.SHOW_NON_PRINTABLE_CHARACTERS* and *EditorOptions.WRAP_LINES* which can be applied when creating RawEditors.
- Added method to *Intruder* which enables sending of HTTP requests with an associated tab name.
- Added *Project* which allows retrieval of the current project name.
- Added method to *Proxy* to determine the current interception state.
