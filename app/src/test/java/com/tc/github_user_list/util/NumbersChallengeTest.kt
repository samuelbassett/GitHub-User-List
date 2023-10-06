package com.tc.github_user_list.util

import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class NumbersChallengeTest {
    private lateinit var numbersLogic: NumbersChallenge

    @Before  // execute before every test case
    fun setup() {
        numbersLogic = NumbersChallenge()
    }

    @Test
    fun `Check if replacing numbers is valid`() {
        val totalDigits = "6"
        val digits = "2 3 6 2 3 2"
        val replaceWith = "2"

        assertEquals("111363", numbersLogic.replaceNumber(totalDigits, digits, replaceWith))
    }

    @Test
    fun `Check if replacing numbers is invalid`() {
        val totalDigits = "7"
        val digits = "2 3 6 2 3 2"
        val replaceWith = "7"

        assertNotEquals("111363", numbersLogic.replaceNumber(totalDigits, digits, replaceWith))
        assertNotEquals("111363", numbersLogic.replaceNumber(totalDigits, digits, replaceWith))
    }
}