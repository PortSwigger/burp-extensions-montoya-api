package burp.api.montoya.http.execution;

import java.time.Duration;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * The resource pool a {@link RequestExecutionEngine} run draws on for concurrency, throttle and
 * retries. Pass it to {@link RequestEngineOptions#withResourcePool(ResourcePool)}. Obtain one
 * of three ways:
 *
 * <ul>
 *   <li>{@link #resourcePool()} - a new private pool you can tune with the {@code withX} methods.</li>
 *   <li>{@link #defaultResourcePool()} - Burp's shared default pool, used as-is.</li>
 *   <li>{@link #existingResourcePool(String)} - a pool the user has already configured, by name.</li>
 * </ul>
 *
 * <p>The latter two reference pools whose settings are owned by Burp, so they are immutable:
 * calling a {@code withX} method on them throws {@link IllegalStateException}.
 */
public interface ResourcePool
{
    /**
     * Set the maximum number of requests sent concurrently from this pool.
     *
     * @param concurrentRequestLimit The maximum number of in-flight requests (in the range 1 to 999).
     *
     * @return resource pool
     *
     * @throws IllegalArgumentException if {@code concurrentRequestLimit} is outside the range 1 to 999.
     */
    ResourcePool withConcurrentRequestLimit(int concurrentRequestLimit);

    /**
     * Set a fixed delay between consecutive requests across the run, capping the overall
     * send rate regardless of concurrency.
     *
     * @param delay The delay between requests. {@link Duration#ZERO} disables throttling.
     *
     * @return resource pool
     *
     * @throws IllegalArgumentException if {@code delay} is null or negative.
     */
    ResourcePool withThrottle(Duration delay);

    /**
     * Set the number of times a connection-level failure is retried before the request is
     * recorded as failed. Only transport-level failures are retried; an HTTP 4xx or 5xx
     * response is a {@link RequestStatus#RESPONDED} answer and is never retried.
     *
     * @param maxRetries The maximum number of retries per request (zero or more).
     *
     * @return resource pool
     *
     * @throws IllegalArgumentException if {@code maxRetries} is negative.
     */
    ResourcePool withMaxRetries(int maxRetries);

    /**
     * Obtain a new private, tunable resource pool at the default settings (concurrent request
     * limit of {@code 10}, no throttle, no retries).
     *
     * @return resource pool
     */
    static ResourcePool resourcePool()
    {
        return FACTORY.resourcePool();
    }

    /**
     * Use Burp's shared default resource pool as-is. The returned pool is immutable - its
     * {@code withX} methods throw {@link IllegalStateException}.
     *
     * @return resource pool
     */
    static ResourcePool defaultResourcePool()
    {
        return FACTORY.defaultResourcePool();
    }

    /**
     * Use a resource pool the user has already configured in Burp, identified by name, sharing
     * its capacity with every other task assigned to it. The returned pool is immutable - its
     * {@code withX} methods throw {@link IllegalStateException} - and the run fails to start if
     * no pool with that name exists.
     *
     * @param name The name of the existing resource pool.
     *
     * @return resource pool
     */
    static ResourcePool existingResourcePool(String name)
    {
        return FACTORY.existingResourcePool(name);
    }
}
