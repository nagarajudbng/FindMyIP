package com.codelabs.findmyip.presentation

import com.codelabs.findmyip.domain.repository.Repository
import com.codelabs.findmyip.model.IPModel
import com.codelabs.findmyip.utiles.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    @Mock
    private lateinit var mockRepository: Repository

    private var testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)

        mainViewModel = MainViewModel(mockRepository)

    }

    //    @Test
//    fun `test initial state is loading`() = testDispatcher.runBlockingTest {
//        assertEquals(ResponseState.Loading<IPModel>(), mainViewModel.ip.value)
//    }
    @Test
    fun `test success IP fetch`() = testDispatcher.runBlockingTest {
        val mockIPModel = IPModel("192.168.0.1")
        `when`(mockRepository.getIPDetails()).thenReturn(flowOf(ResponseState.Success(mockIPModel)))

        mainViewModel.getIP()
        assertEquals(ResponseState.Success(mockIPModel), mainViewModel.ip.value)
    }

    @Test
    fun `test fail IP fetch`() = testDispatcher.runBlockingTest {
        `when`(mockRepository.getIPDetails()).thenReturn(flowOf(ResponseState.Fail<IPModel>("Fail")))
        mainViewModel.getIP()
        assertEquals(ResponseState.Fail<IPModel>("Fail"), mainViewModel.ip.value)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }
}