package burp.api.montoya.scanner.scancheck;

import burp.api.montoya.scanner.Scanner;

/**
 * The type of scan check ({@link ActiveScanCheck} or {@link PassiveScanCheck}) that will be run.
 *
 * The scan check type defines the point a scan check is invoked by the Scanner.
 *
 * The following scan check types are available:
 *   {@link #PER_HOST},
 *   {@link #PER_REQUEST},
 *   {@link #PER_INSERTION_POINT}
 */
public enum ScanCheckType
{
    /**
     * The Scanner invokes the scan check once for each <b>host</b>.
     */
    PER_HOST,

    /**
     * The Scanner invokes the scan check once for each <b>request</b>.
     */
    PER_REQUEST,

    /**
     * The Scanner invokes the scan check once for each <b>insertion point</b>.
     *
     * <b>Note:</b> Only applicable to {@link ActiveScanCheck}. Registering a {@link PassiveScanCheck} using
     * {@link Scanner#registerPassiveScanCheck(PassiveScanCheck, ScanCheckType)} with this type will throw
     * an {@link IllegalArgumentException}.
     */
    PER_INSERTION_POINT
}
