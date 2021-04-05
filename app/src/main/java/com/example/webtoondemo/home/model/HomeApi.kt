package com.example.webtoondemo.home.model

import com.example.webtoondemo.*
import com.example.webtoondemo.common.extension.mapDocument
import com.example.webtoondemo.common.remote.INetworkUseCase
import com.example.webtoondemo.common.remote.IRequest
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import com.example.webtoondemo.common.remote.Result

internal class HomeApi(
    private val networkUseCase: INetworkUseCase
) : HomeUseCase, INetworkUseCase by networkUseCase {

    companion object {
        private val TITLE = arrayOf("월", "화", "수", "목", "금", "토", "일", "완결")
    }

    override val currentTabs = TITLE

    @Suppress("PrivatePropertyName")
    private val URL_VALUE = arrayOf("mon", "tue", "wed", "thu", "fri", "sat", "sun", "fin")

    override suspend fun invoke(param: HomeRequest): Result<List<HomeInfo>> {
        val responseData: Document = requestApi(createApi(param))
            .mapDocument()
            .let { result ->
                when (result) {
                    is Result.Success -> result.data
                    is Result.Error -> return Result.Error(result.throwable)
                }
            }
        val pattern = "(?<=titleId=)\\d+".toRegex()
        val list = responseData.select(".list_toon .item a")
            .mapNotNull { element ->
                pattern.find(element.attr("href"))
                    ?.takeIf { it.value.isNotEmpty() }
                    ?.let { matchResult ->
                        createToon(matchResult.value, element)
                    }
            }
        return Result.Success(list)
    }

    private fun createToon(
        id: String,
        element: Element
    ): HomeInfo {
        val info = element.select(".info")
        return HomeInfo(
            id = id,
            title = info.select(".title").text(),
            image = element.select("img").first().attr("src"),
            homeStatus = when {
                info.select("span[class=bullet up]").isNotEmpty() -> HomeStatus.UPDATE // 최근 업데이트
                info.select("span[class=bullet break]").isNotEmpty() -> HomeStatus.BREAK // 휴재
                else -> HomeStatus.NONE
            },
            isAdult = !element.select("em[class=badge badge_adult]").isEmpty(),
            writer = info.select(".author")?.first()?.text().orEmpty(),
            rate = element.select(".txt_score").text().toDoubleOrNull() ?: 0.0,
            updateDate = element.select("span[class=if1]").text()
        )
    }

    private fun createApi(pos: Int): IRequest =
        IRequest(
            url = "https://m.comic.naver.com/webtoon/weekday.nhn",
            params = mapOf("week" to URL_VALUE[pos])
        )
}
