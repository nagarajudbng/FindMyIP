//package com.codelabs.findmyip.viewmodel
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.ViewModelProvider
//import com.codelabs.findmyip.MainActivity
//import com.codelabs.findmyip.client.RepositoryInstance
//import com.codelabs.findmyip.model.IpModel
//import com.codelabs.findmyip.utilities.ResourceState
//import io.mockk.coEvery
//import io.mockk.mockk
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.flowOf
//import kotlinx.coroutines.test.TestCoroutineDispatcher
//import kotlinx.coroutines.test.TestCoroutineScope
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.runBlockingTest
//import kotlinx.coroutines.test.setMain
//import org.junit.After
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mock
//
//
//class MainViewModelTest{
//
//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    private val testDispatcher = TestCoroutineDispatcher()
//    private val testScope = TestCoroutineScope(testDispatcher)
//
//
////    private val repository: RepositoryInstance = mockk()
//    private lateinit var viewModel: MainViewModel
////    @Mock
////    private var responseState: MutableStateFlow<Any> ?=null
//
//    @Before
//    fun setup(){
//        Dispatchers.setMain(testDispatcher)
//        viewModel = MainViewModel()
//    }
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//        testScope.cleanupTestCoroutines()
//    }
//    @Test
//    fun `test initial state is loading`() {
////        val observer = viewModel.ip.
//
////        assert(observer.awaitValue().peekContent() is ResourceState.Loading<*>)
//    }
//
//
//    @Test
//    fun `test success response`() = testScope.runBlockingTest {
//        val expectedResponse = IpModel("127.0.0.1")
//        val mockRepository = mockk<RepositoryInstance>()
//
//        coEvery { mockRepository.getMyIPDetails() } returns flowOf(ResourceState.Success(expectedResponse))
//
//        viewModel = MainViewModel()
//        viewModel.getIP()
//
//        val observer = viewModel.ip.value
//
//        assert(observer.awaitValue().peekContent() is ResourceState.Success)
//        assert((observer.awaitValue().peekContent() as ResourceState.Success).data == expectedResponse)
//    }
//
//}