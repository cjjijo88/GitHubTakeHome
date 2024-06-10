package com.assignment.githubtakehome

import org.mockito.Mockito.*
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.assignment.githubtakehome.databinding.ItemUserRepoBinding
import com.assignment.githubtakehome.model.RepoData
import com.assignment.githubtakehome.ui.adapter.UserRepoDataAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * @Author: Jijo
 * @Date: 10-06-2024
 */
class UserRepoDataAdapterTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var binding: ItemUserRepoBinding

    @Mock
    private lateinit var clickListener: (RepoData) -> Unit

    private lateinit var adapter: UserRepoDataAdapter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        adapter = UserRepoDataAdapter(emptyList(), clickListener)
    }

    @Test
    fun onCreateViewHolder_validData_returnsRepoViewHolder() {
        val parent: ViewGroup = mock()
        val inflater: LayoutInflater = mock()
        whenever(parent.context).thenReturn(mock())
        whenever(LayoutInflater.from(any())).thenReturn(inflater)
        whenever(ItemUserRepoBinding.inflate(inflater, parent, false)).thenReturn(binding)

        val viewHolder = adapter.onCreateViewHolder(parent, 0)

        assert(viewHolder is UserRepoDataAdapter.RepoViewHolder)
    }

    @Test
    fun onBindViewHolder_validData_callsBind() {
        val viewHolder: UserRepoDataAdapter.RepoViewHolder = mock()
        val repo: RepoData = mock()
        adapter.repos = listOf(repo)

        adapter.onBindViewHolder(viewHolder, 0)

        verify(viewHolder).bind(repo)
    }

    @Test
    fun getItemCount_emptyList_returnsZero() {
        assert(adapter.itemCount == 0)
    }

    @Test
    fun getItemCount_nonEmptyList_returnsSize() {
        val repos = listOf(RepoData("name", "description", "date", 0, 0))
        adapter.repos = repos

        assert(adapter.itemCount == repos.size)
    }

    @Test
    fun updateRepos_setsNewReposAndNotifiesDataSetChanged() {
        val newRepos = listOf(RepoData("name", "description", "date", 0, 0))

        adapter.updateRepos(newRepos)

        assert(adapter.repos == newRepos)
        verify(adapter).notifyDataSetChanged()
    }
}