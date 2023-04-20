package uz.behzod.eightytwenty.utils.helper

object FtsHelper {

    /**
     * Escape FTS special characters - and "
     */
    fun ftsQuery(query: String): String {
        val replacedQuery = query.replace("\"", "\"\"")
        return "\"*$replacedQuery*\""
    }

    /**
     * Calculates the relevance score of a search result from its `matchinfo` blob
     * value returned by SQLite.
     *
     * The `matchinfo` function in SQLite returns a sequence of bytes whose size depends
     * on the configuration string supplied to it. The default configuration string is "pcx".
     *
     * - 'p' represents one integer value corresponding to the number of matchable phrases
     * in the query
     * - 'c' represents one integer value corresponding to the number of matchable columns
     * in the FTS table
     * - 'x' represents a series of bytes which contain various parameters describing the
     * match.
     *
     * The series of integers returned by 'x' can be used to calculate the relevance score
     * of the match. The series can be interpreted as chunks of three integers:
     *
     * - The first int represents the number of matches of the phrase in the current column
     * for this row
     * - The second int represents the total number of matches of the phrase in the current
     * column for all rows
     *
     * These two values can be used to calculate a score for each column in each row using the
     * following equation:
     *  score = (hits in this row) / (hits in all rows)
     *
     * Adding this score for each column in a row gives the score for the entire row.
     *
     * For more information on the implementation, see https://www.sqlite.org/fts3.html#matchinfo
     */
    fun calculateScore(matchInfo: ByteArray): Double {
        val info = matchInfo.toIntArray()

        val numPhrases = info[0]
        val numColumns = info[1]

        var score = 0.0
        for (phrase in 0 until numPhrases) {
            val offset = 2 + phrase * numColumns * 3
            for (column in 0 until numColumns) {
                val numHitsInRow = info[offset + 3 * column]
                val numHitsInAllRows = info[offset + 3 * column + 1]
                if (numHitsInAllRows > 0) {
                    score += numHitsInRow.toDouble() / numHitsInAllRows.toDouble()
                }
            }
        }

        return score
    }

    /**
     * Converts the array of bytes representing the `matchinfo` for a search result
     * into an array of integers for easy consumption.
     *
     * This sequence returned by SQLite represents 32 bit integers using 4 bytes,
     * which can be converted to an [Int] value using a [java.nio.ByteBuffer].
     *
     * However, Room already does the parsing for us and gives us an [ByteArray]
     * in which the first byte represents the int value of the 32-bit integer,
     * while the remaining three bytes are 0.
     *
     * Therefore to convert this [ByteArray] to an [IntArray], we only
     * need to skip the three zeroes after each byte and return the result.
     */

    private fun ByteArray.toIntArray(skipSize: Int = 4): IntArray {
        /**
         * IntArray is an array that contains only integer elements.
         * We tell the compiler that the array should contain only integers.
         */
        val targetArray = IntArray(size = this.size / skipSize)

        for ((item, index) in (this.indices step skipSize).withIndex()) {
            targetArray[item] = this[index].toInt()
        }

        return targetArray
    }
}
