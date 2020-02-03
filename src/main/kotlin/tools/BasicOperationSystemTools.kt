package tools

/**
 * Interface helps manipulate OS GUI
 */

interface BasicOperationSystemTools {

    /**
     * Choose file with text source using JChooser
     *
     *      MAIN OPTION: from GUI (JChooser)
     *      BACKUP OPTION: console readLine()
     *      ADDITIONAL: check on common text types
     */

    fun chooseTextFile(): String {
        return toString()
    }
}