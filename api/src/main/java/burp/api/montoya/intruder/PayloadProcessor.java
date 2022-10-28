/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.intruder;

/**
 * Extensions can implement this interface and then call {@link Intruder#registerPayloadProcessor} to register a
 * custom Intruder payload processor.
 */
public interface PayloadProcessor
{
    /**
     * This method returns the name Burp will use when displaying the payload processor
     * in a dropdown list in the UI.
     *
     * @return Name of the payload processor
     */
    String displayName();

    /**
     * This method is invoked by Burp each time the processor should be applied to an Intruder payload.
     *
     * @param currentPayload  The value of the payload to be processed.
     * @param originalPayload The value of the original payload prior to processing by any already-applied processing rules
     * @param insertionPoint  The insertion point data.
     * @return The value of the processed payload.
     */
    PayloadProcessingResult processPayload(Payload currentPayload, Payload originalPayload, IntruderInsertionPoint insertionPoint);
}
