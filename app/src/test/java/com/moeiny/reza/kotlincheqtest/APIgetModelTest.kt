package com.moeiny.reza.kotlincheqtest

import com.moeiny.reza.cheqtest.data.Cheq
import com.moeiny.reza.kotlincheqtest.mock.CheqAPISearchMock
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class APIgetModelTest {
    lateinit var cheqMock: Cheq

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        cheqMock = CheqAPISearchMock.cheqMock
    }



    @Test
    fun testFirstCheqInfo() {

        Assert.assertEquals(cheqMock.cheq.providers.size, 3)
        Assert.assertEquals(cheqMock.cheq.providers.get(0).name, "CommomBank")
        Assert.assertEquals(cheqMock.cheq.providers.get(0).months[0].name, "June")
        Assert.assertEquals(cheqMock.cheq.providers.get(0).months[0].amount, "200")
        Assert.assertEquals(cheqMock.cheq.providers.get(0).months[0].percentage, "25")
        Assert.assertEquals(cheqMock.cheq.providers.get(0).months[3].name, "September")
        Assert.assertEquals(cheqMock.cheq.providers.get(0).months[3].amount, "800")
        Assert.assertEquals(cheqMock.cheq.providers.get(0).months[3].percentage, "40")


    }

    @Test
    fun testSecondCheqWrongInfo() {

        Assert.assertNotEquals(cheqMock.cheq.providers.size, 2)
        Assert.assertNotEquals(cheqMock.cheq.providers.get(1).name, "CommomBank")
        Assert.assertNotEquals(cheqMock.cheq.providers.get(1).months[0].name, "July")
        Assert.assertNotEquals(cheqMock.cheq.providers.get(1).months[0].amount, "200")
        Assert.assertNotEquals(cheqMock.cheq.providers.get(1).months[0].percentage, "40")
        Assert.assertNotEquals(cheqMock.cheq.providers.get(1).months[3].name, "Jun")
        Assert.assertNotEquals(cheqMock.cheq.providers.get(1).months[3].amount, "300")
        Assert.assertNotEquals(cheqMock.cheq.providers.get(1).months[3].percentage, "10")
    }

    @Test
    fun testLastcheqInfo() {

        Assert.assertEquals(cheqMock.cheq.category.size, 4)
        Assert.assertEquals(cheqMock.cheq.category.get(0).name, "household")
        Assert.assertEquals(cheqMock.cheq.category.get(0).amount, "500")
        Assert.assertEquals(cheqMock.cheq.category.get(0).percentage, "20")
        Assert.assertEquals(cheqMock.cheq.category.get(3).name, "Food")
        Assert.assertEquals(cheqMock.cheq.category.get(3).amount, "300")
        Assert.assertEquals(cheqMock.cheq.category.get(3).percentage, "80")
    }
}