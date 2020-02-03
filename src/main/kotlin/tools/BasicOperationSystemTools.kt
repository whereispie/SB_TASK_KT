package tools

/**
 * Interface helps manipulate OS
 */
interface BasicOperationSystemTools {
    /**
     * Choose file with text source using JChooser
     */
    fun chooseTextFile(): String {
        return toString()
    }
}