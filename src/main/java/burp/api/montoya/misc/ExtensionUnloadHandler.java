/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.misc;

/**
 * Extensions can implement this interface and then call
 * {@link Misc#registerExtensionUnloadHandler(ExtensionUnloadHandler)} to
 * register an extension unload handler. The handler will be notified when an
 * extension is unloaded. <b>Note:</b> Any extensions that start background
 * threads or open system resources (such as files or database connections)
 * should register a handler and terminate threads / close resources when the
 * extension is unloaded.
 */
public interface ExtensionUnloadHandler
{
    /**
     * This method is called when the extension is unloaded.
     */
    void extensionUnloaded();
}
