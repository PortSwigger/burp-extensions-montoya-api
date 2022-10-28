/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.core;

/**
 * This interface represents a task on the Dashboard.
 */
public interface Task
{
    /**
     * This method is used to delete the task.
     */
    void delete();

    String statusMessage();
}
