package burp.api.montoya.ui.menu;

import static burp.api.montoya.internal.ObjectFactoryLocator.FACTORY;

/**
 * An item to be displayed in a {@link Menu}.
 */
public interface MenuItem
{
    /**
     * The caption of the {@link MenuItem}.
     *
     * @return The caption.
     */
    String caption();

    /**
     * The action performed when the {@link MenuItem} is clicked.
     */
    void action();

    /**
     * Create a copy of {@link MenuItem} with a new caption.
     *
     * @param caption The new caption.
     *
     * @return An updated copy of {@link MenuItem}
     */
    MenuItem withCaption(String caption);

    /**
     * Create a copy of {@link MenuItem} with a new {@link Runnable} action.
     *
     * @param action The new {@link Runnable} action.
     *
     * @return An updated copy of {@link MenuItem}.
     */
    MenuItem withAction(Runnable action);

    /**
     * Create a new instance of {@link MenuItem} with a caption.
     *
     * @param caption The caption for the {@link MenuItem}.
     *
     * @return A new instance of the {@link MenuItem}.
     */
    static MenuItem menuItem(String caption)
    {
        return FACTORY.menuItem(caption);
    }
}
