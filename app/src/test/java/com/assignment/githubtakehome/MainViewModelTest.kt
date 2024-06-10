package com.assignment.githubtakehome

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.assignment.githubtakehome.model.RepoData
import com.assignment.githubtakehome.model.UserData
import com.assignment.githubtakehome.network.GitHubApiService
import com.assignment.githubtakehome.ui.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

/**
 * @Author: Jijo
 * @Date: 10-06-2024
 */
@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: GitHubApiService

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var userObserver: Observer<UserData>

    @Mock
    private lateinit var repoObserver: Observer<List<RepoData>>

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(apiService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun fetchUser_success() = runBlockingTest {
        val mockUser = UserData("The Octocat", "https://avatars3.githubusercontent.com/u/583231?v=4")

        Mockito.`when`(apiService.getUserData("octocat"))
            .thenReturn(Response.success(mockUser))

        viewModel.user.observeForever(userObserver)

        viewModel.fetchUser("octocat")

        Mockito.verify(userObserver).onChanged(mockUser)
        assertEquals(mockUser, viewModel.user.value)
    }

    @Test
    fun fetchUserRepos_success() = runBlockingTest {
        val mockRepos = listOf(
            RepoData("Hello-World", "My first repository on GitHub!", "2017-08-14T08:08:10Z", "1421", "1176")
        )

        Mockito.`when`(apiService.getUserRepoDetails("octocat"))
            .thenReturn(Response.success(mockRepos))

        viewModel.repos.observeForever(repoObserver)

        viewModel.fetchUserRepos("octocat")

        Mockito.verify(repoObserver).onChanged(mockRepos)
        assertEquals(mockRepos, viewModel.repos.value)
    }

    @Test
    fun fetchUserRepos_error() = runBlockingTest {
        Mockito.`when`(apiService.getUserRepoDetails("octocat"))
            .thenReturn(Response.error(404, ResponseBody.create(null, "")))

        viewModel.repos.observeForever(repoObserver)

        viewModel.fetchUserRepos("octocat")

        assertEquals(emptyList<RepoData>(), viewModel.repos.value)
    }
}