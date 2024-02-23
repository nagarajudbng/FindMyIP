package com.codelabs.findmyip.viewmodel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.codelabs.findmyip.client.RepositoryInstance
import com.codelabs.findmyip.model.IpModel
import com.codelabs.findmyip.utilities.ResourceState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class Test2 {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset main dispatcher to the original Main dispatcher
        testDispatcher.cleanupTestCoroutines() // Cleanup the TestCoroutineDispatcher to avoid resource leaks
    }
    @Test
    fun `test initial state is loading`() = testDispatcher.runBlockingTest {
        // Arrange
        val viewModel = MainViewModel(RepositoryInstance())

        // Act - No need to act here, as the ViewModel is initialized in the arrange step.

        // Assert
        assertEquals(ResourceState.Loading<IpModel>(), viewModel.ip.value)
    }

    @Test
    fun `test success response`() = testDispatcher.runBlockingTest {
        // Arrange
        val expectedResponse = IpModel("127.0.0.1")
        val mockRepository = mockk<RepositoryInstance>()

        coEvery { mockRepository.getMyIPDetails() } returns flow {
            emit(ResourceState.Success(expectedResponse))
        }

        val viewModel = MainViewModel(RepositoryInstance())
        // Act - No need to act here, as the ViewModel is initialized in the arrange step.
        var IpMode = ResourceState.Success(expectedResponse)
        var ip:ResourceState.Success<IpModel> = viewModel.ip.value as ResourceState.Success<IpModel>
//        val ipRes: ResourceState<IpModel> by viewModel.ip.collectAsState()
        assertEquals(IpMode,ip )
        // Assert
//        assertEquals(ResourceState.Success(expectedResponse), viewModel.ip.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test error response`() = testDispatcher.runBlockingTest {
        // Arrange
        val expectedError = "Failed to retrieve IP"
        val mockRepository = mockk<RepositoryInstance>()

        coEvery { mockRepository.getMyIPDetails() } returns flow {
            emit(ResourceState.Error<IpModel>(expectedError))
        }
        val viewModel = MainViewModel(RepositoryInstance())
        // Act - No need to act here, as the ViewModel is initialized in the arrange step.

        // Assert
        assertEquals(ResourceState.Error<IpModel>(expectedError), viewModel.ip.value)
    }
}