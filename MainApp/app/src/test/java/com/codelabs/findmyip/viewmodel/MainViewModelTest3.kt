package com.codelabs.findmyip.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codelabs.findmyip.client.Repository
import com.codelabs.findmyip.client.RepositoryInstance
import com.codelabs.findmyip.model.IpModel
import com.codelabs.findmyip.utilities.ResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class MainViewModelTest3 {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        // Set the main dispatcher for testing
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test success response`() = testDispatcher.runBlockingTest {
        // Arrange
        val expectedResponse = IpModel("127.0.0.1")
        val mockRepository = mock<Repository> {
            onBlocking { getMyIPDetails() } doReturn flow {
                emit(ResourceState.Success(expectedResponse))
            }
        }

        val viewModel = MainViewModel(mockRepository)

        // Act
        advanceUntilIdle() // Wait for all coroutines to complete

        // Assert
        assertEquals(ResourceState.Success(expectedResponse), viewModel.ip.value)
    }
    @ExperimentalCoroutinesApi
    @Test
    fun `test error response`() = testDispatcher.runBlockingTest {
        // Arrange
        val expectedError = "Failed to retrieve IP"
        val mockRepository = mock<Repository> {
            onBlocking { getMyIPDetails() } doReturn flow {
                emit(ResourceState.Error<IpModel>(expectedError))
            }
        }

        val viewModel = MainViewModel(mockRepository)

        // Assert
        assertEquals(ResourceState.Error<IpModel>(expectedError), viewModel.ip.value)
    }
}
