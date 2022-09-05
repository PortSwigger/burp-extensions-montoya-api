/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya;

import burp.api.montoya.collaborator.Collaborator;
import burp.api.montoya.comparer.Comparer;
import burp.api.montoya.decoder.Decoder;
import burp.api.montoya.http.Http;
import burp.api.montoya.intruder.Intruder;
import burp.api.montoya.logging.Logging;
import burp.api.montoya.misc.Misc;
import burp.api.montoya.persistence.Persistence;
import burp.api.montoya.proxy.Proxy;
import burp.api.montoya.repeater.Repeater;
import burp.api.montoya.scanner.Scanner;
import burp.api.montoya.scope.Scope;
import burp.api.montoya.sitemap.SiteMap;
import burp.api.montoya.ui.UserInterface;
import burp.api.montoya.utilities.Utilities;

/**
 * This interface is used by Burp Suite to pass a set of methods to extensions that can be used
 * to perform various actions within Burp. When an extension is loaded, Burp invokes its
 * {@link BurpExtension#initialize(MontoyaApi)} method and passes an instance
 * of the {@link MontoyaApi} interface. The extension may then invoke the
 * methods of this interface as required in order to extend Burp's
 * functionality.
 */
public interface MontoyaApi
{
    /**
     * This method is used to access the functionality of the Collaborator.
     *
     * @return An implementation of the Collaborator interface which exposes Collaborator based functionality.
     */
    Collaborator collaborator();

    /**
     * This method is used to access the functionality of the Comparer.
     *
     * @return An implementation of the Comparer interface which exposes Comparer based functionality.
     */
    Comparer comparer();

    /**
     * This method is used to access the functionality of the Decoder.
     *
     * @return An implementation of the Decoder interface which exposes Decoder based functionality.
     */
    Decoder decoder();

    /**
     * This method is used to access the functionality related to HTTP requests and responses.
     *
     * @return An implementation of the Http interface which exposes Http based functionality.
     */
    Http http();

    /**
     * This method is used to access the functionality of the Intruder.
     *
     * @return An implementation of the Comparer interface which exposes Comparer based functionality.
     */
    Intruder intruder();

    /**
     * This method is used to access the functionality related to logging and events.
     *
     * @return An implementation of the Logging interface which exposes Logging based functionality.
     */
    Logging logging();

    /**
     * This method is used to access the functionality related to persistence.
     *
     * @return An implementation of the Persistence interface which exposes Persistence based functionality.
     */
    Persistence persistence();

    /**
     * This method is used to access the functionality of the Proxy.
     *
     * @return An implementation of the Proxy interface which exposes Proxy based functionality.
     */
    Proxy proxy();

    /**
     * This method is used to access the functionality of the Repeater.
     *
     * @return An implementation of the Repeater interface which exposes Repeater based functionality.
     */
    Repeater repeater();

    /**
     * This method is used to access the functionality of the Scanner.
     *
     * @return An implementation of the Scanner interface which exposes Scanner based functionality.
     */
    Scanner scanner();

    /**
     * This method is used to access the functionality related to Burp's Suite-wide target scope.
     *
     * @return An implementation of the Scope interface which exposes Scope based functionality.
     */
    Scope scope();

    /**
     * This method is used to access the functionality of the Site Map.
     *
     * @return An implementation of the SiteMap interface which exposes Site Map based functionality.
     */
    SiteMap siteMap();

    /**
     * This method is used to access the functionality related to the user interface.
     *
     * @return An implementation of the UserInterface interface which exposes User Interface based functionality.
     */
    UserInterface userInterface();

    /**
     * This method is used to access additional utilities.
     *
     * @return An implementation of the Utilities interface which exposes additional utilities.
     */
    Utilities utilities();

    /**
     * This method is used to access Miscellaneous functionality related to Burp.
     *
     * @return An implementation of the Misc interface which exposes miscellaneous functionality.
     */
    Misc misc();
}
