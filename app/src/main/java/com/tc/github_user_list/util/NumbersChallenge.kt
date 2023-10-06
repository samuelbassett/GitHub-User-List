package com.tc.github_user_list.util

import java.lang.StringBuilder

class NumbersChallenge {

//Three inputs where
//-> Number of digits
//-> Array of digits exactly count of first input separated by space
//-> Digit to replace/sort to start
//Given:
//-> All the inputs will be in String
//-> The Array passed in second input will be separated by space
//-> All the inputs will be digits
//Requirements:
//-> You replace the number/digit in ARRAY with 1
//-> All the 1s should be at start of the list (Sorted)

//Sample Input:
//6
//2 3 6 2 3 2
//2

    //Expected Output:
//111363
//Steps for example only
//(2 3 6 2 3 2)
//(1 3 6 2 3 2)
//(1 1 3 6 3 2)
//(1 1 1 3 6 3)
    fun replaceNumber(totalDigits: String, digits: String, replaceWith: String): String {
        val newNumbers = StringBuilder()
        var originalNumbers = ""
        var counter = 0
        var digitsInt = digits.replace(" ", "")
        if(totalDigits.toInt() != digitsInt.length) {
            return "Failed"
        }
        for (i in 0 until totalDigits.toInt()) {
            digitsInt = digitsInt.replace(replaceWith, "1")
            if (digitsInt.elementAt(i) != '1') {
                originalNumbers += digitsInt.elementAt(i)
                counter++
            }
        }
        when (counter) {
            in 1..totalDigits.toInt() -> {
                repeat(counter) {
                    newNumbers.append("1")
                }
            }

            else -> return originalNumbers
        }
        val result = newNumbers.toString() + originalNumbers
        return result
    }
}