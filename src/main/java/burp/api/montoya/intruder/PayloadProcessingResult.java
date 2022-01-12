/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.intruder;

/**
 * An instance of this interface should be returned by {@link PayloadProcessor#processPayload} if a custom
 * {@link PayloadProcessor} was registered with Intruder.
 */
public interface PayloadProcessingResult
{
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

    /**
     * This method is a helper method to create a new instance of {@link PayloadProcessingResult} with a
     * {@link PayloadProcessingAction#USE_PAYLOAD} action.
     *
     * @param processedPayload Processed payload value
     * @return A new {@link PayloadProcessingResult} instance.
     */
    static PayloadProcessingResult usePayload(byte[] processedPayload)
    {
        return new PayloadProcessingResult()
        {
            @Override
            public byte[] processedPayload()
            {
                return processedPayload;
            }

            @Override
            public PayloadProcessingAction action()
            {
                return PayloadProcessingAction.USE_PAYLOAD;
            }
        };
    }

    /**
     * This method is a helper method to create a new instance of {@link PayloadProcessingResult} with a
     * {@link PayloadProcessingAction#SKIP_PAYLOAD} action.
     *
     * @return A new {@link PayloadProcessingResult} instance.
     */
    static PayloadProcessingResult skipPayload()
    {
        return new PayloadProcessingResult()
        {
            @Override
            public byte[] processedPayload()
            {
                return null;
            }

            @Override
            public PayloadProcessingAction action()
            {
                return PayloadProcessingAction.SKIP_PAYLOAD;
            }
        };
    }
}
