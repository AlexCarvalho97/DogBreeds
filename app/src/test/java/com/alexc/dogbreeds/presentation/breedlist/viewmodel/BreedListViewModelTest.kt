package com.alexc.dogbreeds.presentation.breedlist.viewmodel

import com.alexc.dogbreeds.presentation.breedlist.viewmodel.repository.MockBreedRepository
import com.alexc.dogbreeds.presentation.breedlist.viewmodel.utils.hasDuplicates
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BreedListViewModelTest {

    private val PER_PAGE = 2

    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    private val mockRepo = MockBreedRepository().apply {
        perPage = PER_PAGE
    }
    private lateinit var viewModel: BreedListViewModel


    @Before
    fun setupDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `get items for first page`() {
        viewModel = BreedListViewModel(mockRepo)

        viewModel.onEvent(BreedListEvent.OnRefresh)

        val state = viewModel.state
        assert(!state.isError && state.breedList.isNotEmpty())
        // Should have 2 items -> first page loaded
        assert(state.breedList.size == PER_PAGE)
        // Should not have duplicated items
        assert(!state.breedList.hasDuplicates())
    }

    @Test
    fun `get load more items`() {
        viewModel = BreedListViewModel(mockRepo)

        // Load second page
        viewModel.onEvent(BreedListEvent.OnLoadMore)
        val state = viewModel.state

        assert(!state.isError && state.breedList.isNotEmpty())
        // Should have 4 items -> first + second page loaded
        assert(state.breedList.size == PER_PAGE * 2)
        // Should not have duplicated items
        assert(!state.breedList.hasDuplicates())
    }

    @Test
    fun `load more and refresh after`() {
        viewModel = BreedListViewModel(mockRepo)

        // Load second page
        viewModel.onEvent(BreedListEvent.OnLoadMore)
        // Refresh -> load first page
        viewModel.onEvent(BreedListEvent.OnRefresh)

        val state = viewModel.state

        assert(!state.isError && state.breedList.isNotEmpty())
        // Should have 2 items -> first page
        assert(state.breedList.size == PER_PAGE)
        // Should not have duplicated items
        assert(!state.breedList.hasDuplicates())
    }

    @Test
    fun `get items with no cache and no network`() {
        viewModel = BreedListViewModel(MockBreedRepository().apply {
            perPage = PER_PAGE
            shouldReturnCacheItems = false
            shouldReturnNetworkItems = false
        })

        val state = viewModel.state
        // No items retrieved so the state should be error
        assert(state.isError)
    }

    @Test
    fun `get items with cache and no network`() {
        val repository = MockBreedRepository().apply {
            perPage = PER_PAGE
            shouldReturnCacheItems = true
            shouldReturnNetworkItems = false
        }

        viewModel = BreedListViewModel(repository)

        val state = viewModel.state
        assert(!state.isError && state.breedList.isNotEmpty())
        // Should have the same items as cache
        assert(state.breedList.size == repository.breedLocalItems.size)
    }

    @Test
    fun `change list type to grid`() {
        viewModel = BreedListViewModel(MockBreedRepository())

        viewModel.onEvent(BreedListEvent.OnChangeListType(ListType.GRID))

        val state = viewModel.state
        assert(!state.isError && state.breedList.isNotEmpty())
        assert(state.listType == ListType.GRID)
    }

}