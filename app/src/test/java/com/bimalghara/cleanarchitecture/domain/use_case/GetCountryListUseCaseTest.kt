package com.bimalghara.cleanarchitecture.domain.use_case

import com.bimalghara.cleanarchitecture.DataStatus
import com.bimalghara.cleanarchitecture.FailureType
import com.bimalghara.cleanarchitecture.MainCoroutineRule
import com.bimalghara.cleanarchitecture.TestUtil.dataStatus
import com.bimalghara.cleanarchitecture.TestUtil.failureType
import com.bimalghara.cleanarchitecture.data.repository.FakeCountryRepository
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by BimalGhara
 */

@ExperimentalCoroutinesApi
class GetCountryListUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule(testDispatcher)

    private lateinit var getCountryListUseCase: GetCountryListUseCase
    private lateinit var fakeCountryRepository: FakeCountryRepository



    @Before
    fun setUp() {
        fakeCountryRepository = FakeCountryRepository()
        getCountryListUseCase = GetCountryListUseCase(testDispatcher, fakeCountryRepository)

    }



    @Test
    fun `Country List expected Empty`() = runTest {
        //Arrange
        dataStatus = DataStatus.EmptyResponse

        //Act
        val countries = getCountryListUseCase().last()

        //Assert
        assertThat(countries.data?.size).isEqualTo(0)
    }

    @Test
    fun `Country List expected Success`() = runTest {
        //Arrange
        dataStatus = DataStatus.Success

        //Act
        val countries = getCountryListUseCase().last()

        //Assert
        assertThat(countries.data?.size).isAtLeast(2)
    }

    @Test
    fun `Country List expected Fail of Network`() = runTest {
        //Arrange
        dataStatus = DataStatus.Success
        failureType = FailureType.Network


        //Act
        val countries = getCountryListUseCase().last()

        //Assert

    }

}