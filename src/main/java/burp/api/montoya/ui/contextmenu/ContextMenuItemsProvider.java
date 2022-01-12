/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.ui.contextmenu;

import javax.swing.JMenuItem;
import java.util.List;

/**
 * This interface allows extensions to implement and register a provider for custom context menu items.
 */
public interface ContextMenuItemsProvider
{
    /**
     * This method is invoked by Burp Suite when the user requests a context menu anywhere in the user interface.
     * Extensions should return {@code null} from this method, to indicate that no menu items are required.
     *
     * @param event This object can be queried to find out about HTTP request/responses or issues that are associated with the context menu invocation.
     * @return A list of custom menu items (which may include sub-menus, checkbox menu items, etc.) that should be displayed.
     */
    List<JMenuItem> provideMenuItems(ContextMenuEvent event);
}
