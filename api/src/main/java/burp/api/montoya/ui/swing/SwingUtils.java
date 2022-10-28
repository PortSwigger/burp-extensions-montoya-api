/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.swing;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Window;

/**
 * This interface gives you access to swing utilities.
 */
public interface SwingUtils
{
    /**
     * @return the main Burp suite frame.
     */
    Frame suiteFrame();

    /**
     * Retrieve the top-level {@code Window} containing the supplied component.
     *
     * @param component the component.
     * @return the top-level {@code Window} containing the component.
     */
    Window windowForComponent(Component component);
}
