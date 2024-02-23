package com.codelabs.findmyip.viewmodel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codelabs.findmyip.client.RepositoryInstance
import com.codelabs.findmyip.model.IpModel
import com.codelabs.findmyip.utilities.ResourceState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    private lateinit var repository: RepositoryInstance

    @Before
    fun setup() {
        repository = mockk()
        viewModel = MainViewModel(RepositoryInstance())
    }

    @Test
    fun `test getIP success`() = runBlockingTest {
        // Mock data
        val expectedIpModel = IpModel("192.168.0.1")
        val flow = flow<ResourceState<IpModel>> { emit(ResourceState.Success(expectedIpModel)) }

        // Mock repository behavior
        coEvery { repository.getMyIPDetails() } returns flow

        // Call function
        viewModel.getIP()

        // Assert
        assertEquals(viewModel.ip.value, ResourceState.Loading<IpModel>())
//        advanceUntilIdle()
        assertEquals(viewModel.ip.value, ResourceState.Success(expectedIpModel))
    }

    @Test
    fun `test getIP failure`() = runBlockingTest {
        // Mock data
        val expectedError = "Failed to retrieve IP"
        val flow = flow<ResourceState<IpModel>> { emit(ResourceState.Error<IpModel>(expectedError)) }

        // Mock repository behavior
        coEvery { repository.getMyIPDetails() } returns flow

        // Call function
        viewModel.getIP()

        // Assert
        assertEquals(viewModel.ip.value, ResourceState.Loading<IpModel>())
//        advanceUntilIdle()
        assertEquals(viewModel.ip.value, ResourceState.Error<IpModel>(expectedError))
    }
}