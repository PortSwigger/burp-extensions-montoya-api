package burp.api.montoya.http.execution;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * Engine-level options for a {@link RequestExecutionEngine} run: the dashboard task name and
 * the {@link ResourcePool} it draws on for concurrency, throttle and retries. The per-request
 * timeout is given when the run is started, on {@link RequestExecutionEngine#sendAll}.
 *
 * <p>Instances are immutable; each {@code withX} method returns a new options value.
 */
public interface RequestEngineOptions
{
    /**
     * Set the name shown for the run's task on the Burp dashboard. When unset, the
     * task is named "Bulk send".
     *
     * @param name The dashboard task name.
     *
     * @return request engine options
     */
    RequestEngineOptions withName(String name);

    /**
     * Set the resource pool the run draws on. When unset, the run uses a private pool at
     * {@link ResourcePool#resourcePool() default} settings.
     *
     * @param resourcePool The resource pool, from {@link ResourcePool#resourcePool()},
     *                     {@link ResourcePool#defaultResourcePool()} or
     *                     {@link ResourcePool#existingResourcePool(String)}.
     *
     * @return request engine options
     */
    RequestEngineOptions withResourcePool(ResourcePool resourcePool);

    /**
     * Use to obtain a new RequestEngineOptions instance.
     *
     * @return request engine options
     */
    static RequestEngineOptions requestEngineOptions()
    {
        return FACTORY.requestEngineOptions();
    }
}
