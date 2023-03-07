Burp Extensions API - Montoya
============================

This repository holds the Burp Suite Extensions API for building user extensions ([BApps](https://portswigger.net/bappstore/)) leveraging the core functionality of Burp.


Download
--------

Available on [Maven Central](https://search.maven.org/search?q=Montoya-api) and in [Burp Suite](https://portswigger.net/burp/communitydownload).

<h5>Maven</h5>

    <dependency>
        <groupId>net.portswigger.burp.extensions</groupId>
        <artifactId>montoya-api</artifactId>
        <version>2023.4</version>
    </dependency>

<h5>Gradle</h5>

    implementation 'net.portswigger.burp.extensions:montoya-api:2023.4'

Getting Started
--------

Create a class that implements [BurpExtension](https://github.com/PortSwigger/burp-extensions-montoya-api/blob/main/api/src/main/java/burp/api/montoya/BurpExtension.java). 
The initialize method will give you an implementation of the [MontoyaApi](https://github.com/PortSwigger/burp-extensions-montoya-api/blob/main/api/src/main/java/burp/api/montoya/MontoyaApi.java) which can be used to interact and modify Burp suite.

Documentation
--------
* [Javadoc](https://portswigger.github.io/burp-extensions-montoya-api/javadoc/burp/api/montoya/MontoyaApi.html)

Examples
--------
https://github.com/PortSwigger/burp-extensions-montoya-api-examples