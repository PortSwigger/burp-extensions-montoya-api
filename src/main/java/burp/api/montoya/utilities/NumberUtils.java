/*
 * Copyright (c) 2022. PortSwigger Ltd. All rights reserved.
 *
 * This code may be used to extend the functionality of Burp Suite Community Edition
 * and Burp Suite Professional, provided that this usage does not violate the
 * license terms for those products.
 */

package burp.api.montoya.utilities;

/**
 * This interface gives you access to number string conversion features.
 */
public interface NumberUtils
{
    /**
     * @param binaryString the binary string to convert
     * @return string containing the octal representation
     */
    default String convertBinaryToOctal(String binaryString)
    {
        return convertBinary(binaryString, 8);
    }

    /**
     * @param binaryString the binary string to convert
     * @return string containing the decimal representation
     */
    default String convertBinaryToDecimal(String binaryString)
    {
        return convertBinary(binaryString, 10);
    }

    /**
     * @param binaryString the binary string to convert
     * @return string containing the hex representation
     */
    default String convertBinaryToHex(String binaryString)
    {
        return convertBinary(binaryString, 16);
    }

    /**
     * @param octalString the octal string to convert
     * @return string containing the binary representation
     */
    default String convertOctalToBinary(String octalString)
    {
        return convertOctal(octalString, 2);
    }

    /**
     * @param octalString the octal string to convert
     * @return string containing the decimal representation
     */
    default String convertOctalToDecimal(String octalString)
    {
        return convertOctal(octalString, 10);
    }

    /**
     * @param octalString the octal string to convert
     * @return string containing the hex representation
     */
    default String convertOctalToHex(String octalString)
    {
        return convertOctal(octalString, 16);
    }

    /**
     * @param decimalString the decimal string to convert
     * @return string containing the binary representation
     */
    default String convertDecimalToBinary(String decimalString)
    {
        return convertDecimal(decimalString, 2);
    }

    /**
     * @param decimalString the decimal string to convert
     * @return string containing the octal representation
     */
    default String convertDecimalToOctal(String decimalString)
    {
        return convertDecimal(decimalString, 8);
    }

    /**
     * @param decimalString the decimal string to convert
     * @return string containing the hex representation
     */
    default String convertDecimalToHex(String decimalString)
    {
        return convertDecimal(decimalString, 16);
    }

    /**
     * @param hexString the hex string to convert
     * @return string containing the binary representation
     */
    default String convertHexToBinary(String hexString)
    {
        return convertHex(hexString, 2);
    }

    /**
     * @param hexString the hex string to convert
     * @return string containing the octal representation
     */
    default String convertHexToOctal(String hexString)
    {
        return convertHex(hexString, 8);
    }

    /**
     * @param hexString the hex string to convert
     * @return string containing the decimal representation
     */
    default String convertHexToDecimal(String hexString)
    {
        return convertHex(hexString, 10);
    }

    /**
     * @param binaryString the binary string to convert
     * @param radix        the radix to convert to
     * @return string containing the representation in the specified radix
     */
    String convertBinary(String binaryString, int radix);

    /**
     * @param octalString the octal string to convert
     * @param radix       the radix to convert to
     * @return string containing the representation in the specified radix
     */
    String convertOctal(String octalString, int radix);

    /**
     * @param decimalString the decimal string to convert
     * @param radix         the radix to convert to
     * @return string containing the representation in the specified radix
     */
    String convertDecimal(String decimalString, int radix);

    /**
     * @param hexString the hex string to convert
     * @param radix     the radix to convert to
     * @return string containing the representation in the specified radix
     */
    String convertHex(String hexString, int radix);
}
