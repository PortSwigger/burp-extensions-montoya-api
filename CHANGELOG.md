# Changelog

## v2025.11
- Added ability to name hotkeys.

## v2025.10
- Added *RankingUtils* which can be used to rank a collection of *HttpRequestResponse* objects.
- Added optional description to *SettingsPanelSetting*.
- Added ability to retrieve ID from *ProxyHttpRequestResponse* and *ProxyWebSocketMessage*.
- Added support for *DEFLATE* compression within *CompressionUtils*. 

## v2025.8
- Added ability to open Burp's Settings window.
- Added ability to create *HttpHeader* using byte[].
- Added ability to set caret position in Editors. 

## v2025.7
- Added ability to retrieve Organizer entries.

## v2025.6
- Added ability to register scan checks of different types, such as active per host.
- Added *SettingsPanelBuilder* to create settings panels.
- Added *ShellUtils* to simplify external process execution.

## v2025.5
- Added ability to register a custom settings panel.

## v2025.4
- Added ability to import bambdas using the new *Bambda* interface.

## v2025.3
- Added ability to register Editor hotkeys.
- Added additional URL encoding methods.
- Added overloaded logToOutput method to *Logging*.
- Added utility hasRequestSelection and hasResponseSelection to *RequestResponseSelection*.

## v2025.2
- Added ability to get parameters without specifying the type from *HttpRequest*.
- Added ability to retrieve project identifier using *Project*.
- Added ability to send chat requests to LLMs using the new *Ai* interface.

## v2024.12
- Added ability to set custom server name indicator using *RequestOptions*. 
- Added ability to set custom response timeout using *RequestOptions*. 

## v2024.11
- Added ability to retrieve issues and requests responses from *SiteMapNode*.

## v2024.7
- Added JSON parsing / manipulation support with *JsonUtils* and *JsonNode*.
- Added ability to control redirection behavior when issuing HTTP requests using *RedirectionMode* and *RequestOptions*.
- Added utility methods to *HttpRequest* and *HttpResponse* which add, update or remove multiple headers.
- Added *EditorOptions.SHOW_NON_PRINTABLE_CHARACTERS* and *EditorOptions.WRAP_LINES* which can be applied when creating RawEditors.
- Added method to *Intruder* which enables sending of HTTP requests with an associated tab name.
- Added *Project* which allows retrieval of the current project name.
- Added method to *Proxy* to determine the current interception state.
