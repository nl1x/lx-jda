package fr.nl1x.lxjda.exceptions.command;

/**
 * An error caused by the user.
 */
public class UserError extends Exception {

    /**
     * Throw a new error caused by the user.
     *
     * @param message The error message that will be shown to the user.
     */
    public UserError(String message)
    {
        super(message);
    }

}
