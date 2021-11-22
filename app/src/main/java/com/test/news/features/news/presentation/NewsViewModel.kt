package com.test.news.features.news.presentation

import com.test.news.base.BaseViewModel
import com.test.news.base.SchedulersProvider
import com.test.news.features.news.EspressoIdlingResource
import com.test.news.features.news.domain.NewsInteractor
import com.test.news.features.news.domain.NewsResult
import io.reactivex.Observable
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    schedulersProvider: SchedulersProvider,
    private val newsInteractor: NewsInteractor
) :
    BaseViewModel<NewsResult, NewsViewState, NewsIntent>(schedulersProvider) {

    override fun handleIntent(intent: NewsIntent): Observable<NewsResult> = when (intent) {
        is NewsIntent.GetNews -> newsInteractor.getNews(intent.isPremium)
    }

    /* using EspressoIdlingResource in this place is not correct,
    but I want to highlight how useful it is and with developer support,
    it will be put in a right place
     */
    override fun reduceViewState(result: NewsResult): NewsViewState {
        return when (result) {
            NewsResult.Loading -> {
                EspressoIdlingResource.increment()
                NewsViewState(isLoading = true)
            }
            NewsResult.Error -> {
                EspressoIdlingResource.decrement()
                NewsViewState(isError = true)
            }
            is NewsResult.News -> {
                EspressoIdlingResource.decrement()
                NewsViewState(newsList = result.photosList)
            }
        }
    }
}
