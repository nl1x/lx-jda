package fr.nl1x.lxjda.exceptions.command;

/**
 * An error caused by the command.
 */
public class CommandExecutorError extends Exception {

    /**
     * Throw a new error caused by the user.
     *
     * @param message The error message.
     */
    public CommandExecutorError(String message) {
        super(message);
    }

}
