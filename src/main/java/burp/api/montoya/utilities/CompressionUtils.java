/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.utilities;

/**
 * This interface gives you access to data compression features.
 */
public interface CompressionUtils
{
    /**
     * Compress data using the specified compression type.
     *
     * @param data data to be compressed
     * @param type {@link CompressionType} to use
     * @return compressed data
     */
    byte[] compress(byte[] data, CompressionType type);

    /**
     * Decompress data compressed using the specified compression type.
     *
     * @param compressedData data to be decompressed
     * @param type           {@link CompressionType} of the compressed data
     * @return decompressed data
     */
    byte[] decompress(byte[] compressedData, CompressionType type);
}
