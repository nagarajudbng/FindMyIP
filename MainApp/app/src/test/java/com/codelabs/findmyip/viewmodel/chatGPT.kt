package com.codelabs.findmyip.viewmodel
import com.codelabs.findmyip.domain.repository.Repository
import com.codelabs.findmyip.model.IPModel
import com.codelabs.findmyip.utiles.ResponseState
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    @Mock
    private lateinit var mockRepository: Repository

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(mockRepository)
    }

    @Test
    fun `test initial state is loading`() = testDispatcher.runBlockingTest {
        // Verify that when the ViewModel is initialized, its initial state is loading
        assertEquals(ResponseState.Loading, mainViewModel.ip.value)
    }

    @Test
    fun `test successful IP fetch`() = testDispatcher.runBlockingTest {
        // Mock a successful IP fetch response from the repository
        val mockIpModel = IPModel("192.168.1.1")
        `when`(mockRepository.getIPDetails()).thenReturn(flowOf(ResponseState.Success(mockIpModel)))

        // Call the getIP method
        mainViewModel.getIP()

        // Verify that the IP state updates to success and contains the expected IP data
        assertEquals(ResponseState.Success(mockIpModel), mainViewModel.ip.value)
    }

    @Test
    fun `test failed IP fetch`() = testDispatcher.runBlockingTest {
        // Mock a failed IP fetch response from the repository
        `when`(mockRepository.getIPDetails()).thenReturn(flowOf(ResponseState.Error("Failed to fetch IP")))

        // Call the getIP method
        mainViewModel.getIP()

        // Verify that the IP state updates to error
        assertEquals(ResponseState.Error("Failed to fetch IP"), mainViewModel.ip.value)
    }
}
