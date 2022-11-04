Burp Extensions API - Montoya Version
============================

This repository holds the Burp Suite Pro Extensions API for building user extensions ([BApps](https://portswigger.net/bappstore/)) leveraging the core functionality of Burp.

Supported Burp Suite Version
--------
Minimum required version of Burp Suite is **2022.11**

Download
--------

Available on [Maven Central](https://search.maven.org/search?q=Montoya-api) and in [Burp Suite](https://portswigger.net/burp/communitydownload).

<h5>Maven</h5>

    <dependency>
        <groupId>net.portswigger.burp.extensions</groupId>
        <artifactId>montoya-api</artifactId>
        <version>LATEST</version>
    </dependency>

<h5>Gradle</h5>

    implementation 'net.portswigger.burp.extensions:montoya-api:+'

Getting Started
--------

Create a class that implements [BurpExtension](https://github.com/PortSwigger/burp-extensions-montoya-api/blob/main/api/src/main/java/burp/api/montoya/BurpExtension.java). 
The initialize method will give you an implementation of the [MontoyaApi](https://github.com/PortSwigger/burp-extensions-montoya-api/blob/main/api/src/main/java/burp/api/montoya/MontoyaApi.java) which can be used to interact and modify Burp suite.

Documentation
--------
* [Javadoc](https://portswigger.github.io/burp-extensions-montoya-api/javadoc/burp/api/montoya/MontoyaApi.html)

Examples
--------

* [Hello World](https://github.com/PortSwigger/burp-extensions-montoya-api/blob/main/examples/src/main/java/net/portswigger/burp/extensions/helloworld)
* [HTTP Handlers](https://github.com/PortSwigger/burp-extensions-montoya-api/blob/main/examples/src/main/java/net/portswigger/burp/extensions/httphandler)
* [Proxy Handlers](https://github.com/PortSwigger/burp-extensions-montoya-api/blob/main/examples/src/main/java/net/portswigger/burp/extensions/proxyhandler)
* [Event Listeners](https://github.com/PortSwigger/burp-extensions-montoya-api/blob/main/examples/src/main/java/net/portswigger/burp/extensions/eventlisteners)
* [Traffic Redirector](https://github.com/PortSwigger/burp-extensions-montoya-api/blob/main/examples/src/main/java/net/portswigger/burp/extensions/trafficredirector)
* [Multi-API: Using both versions of the Api](https://github.com/PortSwigger/burp-extensions-montoya-api/blob/main/examples/src/main/java/net/portswigger/burp/extensions/multiapi)
