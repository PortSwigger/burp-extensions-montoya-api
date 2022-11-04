/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package net.portswigger.burp.extensions.proxyhandler;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

//Burp will auto-detect and load any class that extends BurpExtension.
public class ProxyHandlerExample implements BurpExtension
{
    @Override
    public void initialize(MontoyaApi api)
    {
        //Register proxy handlers with Burp.
        api.proxy().registerRequestHandler(new MyProxyHttpRequestHandler());
        api.proxy().registerResponseHandler(new MyProxyHttpResponseHandler());
    }
}
