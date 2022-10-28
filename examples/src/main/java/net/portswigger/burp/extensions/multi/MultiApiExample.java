/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package net.portswigger.burp.extensions.multi;

import burp.IBurpExtender;
import burp.IBurpExtenderCallbacks;
import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;

//Burp will auto-detect and load any class that extends BurpExtension / IBurpExtender
//When a class extends both BurpExtension AND IBurpExtender Burp will inject both of the relevant API implementations
//It will inject IBurpExtender first, then BurpExtension.
//This will BurpExtension precedence over IBurpExtender
public class MultiApiExample implements BurpExtension, IBurpExtender
{
    private MontoyaApi montoyaApi;
    private IBurpExtenderCallbacks oldApi;

    //Invoked Last
    @Override
    public void initialize(MontoyaApi api)
    {
        this.montoyaApi = api;

        //Register a suite tab that has a button that uses both api's
        api.userInterface().registerSuiteTab("My Suite Tab", new MySuiteTab());

        api.extension().setName("Montoya Name"); //Replaces name set by old Api.

        if (api.scope().isInScope("http://test.url")) //Is true because old API added it to scope.
        {
            oldApi.issueAlert("test.url is in scope"); //OLD api has been set, so we can use it.
        }
        else
        {
            api.extension().unload(); //Should never happen
        }
    }

    //Invoked First
    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        this.oldApi = callbacks;

        callbacks.setExtensionName("Old Name"); //Will be replaced by Montoya version.

        try
        {
            URL url = new URL("http://test.url");
            callbacks.includeInScope(url); //Include test.url in the scope
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    private class MySuiteTab extends JComponent
    {
        public MySuiteTab()
        {
            JPanel customTabContent = new JPanel();
            customTabContent.setName("The One Ring Custom Tab Panel");
            customTabContent.setBackground(Color.GRAY);

            JButton button = new JButton("Print filename to log file");

            button.addActionListener(e -> {
                montoyaApi.logging().logToOutput("Montoya API used to log:" + montoyaApi.extension().filename());
                oldApi.printOutput("Old API used to log:" + oldApi.getExtensionFilename());
            });

            customTabContent.add(button);
            add(customTabContent);
        }
    }
}
