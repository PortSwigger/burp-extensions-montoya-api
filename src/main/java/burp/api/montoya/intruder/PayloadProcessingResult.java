/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.intruder;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * An instance of this interface should be returned by {@link PayloadProcessor#processPayload} if a custom
 * {@link PayloadProcessor} was registered with Intruder.
 */
public interface PayloadProcessingResult
{
    /**
     * This method is a helper method to create a new instance of {@link PayloadProcessingResult} with a
     * {@link PayloadProcessingAction#USE_PAYLOAD} action.
     *
     * @param processedPayload Processed payload value
     * @return A new {@link PayloadProcessingResult} instance.
     */
    static PayloadProcessingResult usePayload(byte[] processedPayload)
    {
        return payloadProcessingResult(processedPayload, PayloadProcessingAction.USE_PAYLOAD);
    }

    /**
     * This method is a helper method to create a new instance of {@link PayloadProcessingResult} with a
     * {@link PayloadProcessingAction#SKIP_PAYLOAD} action.
     *
     * @return A new {@link PayloadProcessingResult} instance.
     */
    static PayloadProcessingResult skipPayload()
    {
        return payloadProcessingResult(null, PayloadProcessingAction.SKIP_PAYLOAD);
    }

    private static PayloadProcessingResult payloadProcessingResult(byte[] processedPayload, PayloadProcessingAction action)
    {
        return FACTORY.payloadProcessingResult(processedPayload, action);
    }

    /**
     * @return The current value of the processed payload.
     */
    byte[] processedPayload();

    /**
     * This method is called by Burp to see what action it should perform with the payload. If the value
     * is {@link PayloadProcessingAction#USE_PAYLOAD}, Burp will use the payload in the attack or skip it
     * if the value is {@link PayloadProcessingAction#SKIP_PAYLOAD}.
     *
     * @return Action to perform with the payload.
     */
    PayloadProcessingAction action();
}
