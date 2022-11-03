/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package net.portswigger.burp.extensions.sample;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.logging.Logging;

import java.io.PrintStream;

//Burp will auto-detect and load any class that extends BurpExtension.
public class HelloWorld implements BurpExtension
{
    @Override
    public void initialize(MontoyaApi api)
    {
        // set our extension name
        api.extension().setName("Hello world extension");

        Logging logging = api.logging();

        // obtain our output and error streams
        PrintStream out = logging.output();
        PrintStream err = logging.error();

        // write a message to our output stream
        out.println("Hello output.");
        logging.logToOutput("Straight to output.");

        // write a message to our error stream
        err.println("Hello error.");
        logging.logToError("Straight to error.");

        // write a message to the Burp alerts tab
        logging.raiseInfoEvent("Hello info event.");
        logging.raiseDebugEvent("Hello debug event.");
        logging.raiseErrorEvent("Hello error event.");
        logging.raiseCriticalEvent("Hello critical event.");

        // throw an exception that will appear in our error stream
        throw new RuntimeException("Hello exceptions.");
    }
}
