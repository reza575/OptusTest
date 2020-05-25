package com.moeiny.reza.optustest


import com.moeiny.reza.optustest.mock.UserAPISearchMock
import com.moeiny.reza.optustest.repository.model.user.User
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class APIgetModelTest {
    lateinit var userMock: User

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        userMock = UserAPISearchMock.userMock
    }

    @Test
    fun testUserInfo() {
        
        Assert.assertEquals(userMock.id, 1)
        Assert.assertEquals(userMock.name, "Leanne Graham")
        Assert.assertEquals(userMock.username, "Bret")
        Assert.assertEquals(userMock.email, "Sincere@april.biz")
        Assert.assertEquals(userMock.phone, "1-770-736-8031 x56442")
        Assert.assertEquals(userMock.website, "hildegard.org")
    }

    @Test
    fun testAddewssInfo() {

        Assert.assertEquals(userMock.address.street, "Kulas Light")
        Assert.assertEquals(userMock.address.suite, "Apt. 556")
        Assert.assertEquals(userMock.address.city, "Gwenborough")
        Assert.assertEquals(userMock.address.zipcode, "92998-3874")
        Assert.assertEquals(userMock.address.geo.lat, "-37.3159")
        Assert.assertEquals(userMock.address.geo.lng, "81.1496")

    }

    @Test
    fun testCompanyWrongInfo() {

        Assert.assertNotEquals(userMock.company.name, "Romaguera")
        Assert.assertNotEquals(userMock.company.catchPhrase, "Multi-layered client-server ")
        Assert.assertNotEquals(userMock.company.bs, "harness real-time ")
    }

}