package com.example.gamenewslampa.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gamenewslampa.data.models.News
import com.example.gamenewslampa.data.models.NewsItem

class NewsDataSource(
    private val api: ApiService
) : PagingSource<Int, NewsItem>() {

    override fun getRefreshKey(state: PagingState<Int, NewsItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsItem> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response: News = api.getNews(nextPageNumber)

            val nextKey =
                if (response.isEmpty()) {
                    null
                } else {
                    nextPageNumber + 1
                }

            LoadResult.Page(
                data = response,
                prevKey = if (nextPageNumber == 0) null else nextPageNumber - 1,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
