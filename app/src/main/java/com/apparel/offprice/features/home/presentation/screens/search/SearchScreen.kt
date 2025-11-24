package com.apparel.offprice.features.home.presentation.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.home.presentation.component.CategoryChips
import com.apparel.offprice.features.home.presentation.component.SearchBar
import com.apparel.offprice.features.home.presentation.component.TagList
import java.util.Locale

@Composable
fun SearchScreen(
    onSearchSubmit: (String) -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            SearchContract.UiEffect.NavigateToHome -> {
                onNavigateToHome()
            }

            is SearchContract.UiEffect.NavigateToSearchResult -> {
                onSearchSubmit(it.productId)
            }

            is SearchContract.UiEffect.ShowError -> {
                TODO()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SearchBar(
                text = state.query,
                onTextChange = {
                    event.invoke(SearchContract.UiEvent.OnQueryChanged(it))
                },
                onCleared = {
                    event.invoke(SearchContract.UiEvent.OnQueryChanged(""))
                }
            )
            Text(
                text = stringResource(R.string.label_cancel),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        if (state.query.isEmpty()) {
                            event.invoke(SearchContract.UiEvent.OnCleared)
                        } else {
                            event.invoke(SearchContract.UiEvent.OnQueryChanged(""))
                        }
                    },
            )
        }
        Spacer(Modifier.height(12.dp))
        CategoryChips(
            selected = state.selectedCategory,
            onSelect = { event.invoke(SearchContract.UiEvent.OnCategorySelected(it)) }
        )
        HorizontalDivider(thickness = 1.dp)
        Spacer(Modifier.height(20.dp))
        SectionTitle(
            title = if (state.query.isEmpty()) stringResource(R.string.title_recent_search) else stringResource(R.string.title_all_result)
        )
        if (state.query.isNotEmpty()) {
            SearchResultSection(
                query = state.query,
                results = state.searchResults,
                onClick = { result ->
                    event.invoke(SearchContract.UiEvent.Submit(result))
                }
            )
        } else {
            TagList(
                items = state.recentSearches,
                removable = true,
                onRemove = { event.invoke(SearchContract.UiEvent.RemoveRecent(it)) },
                onClick = {
                    event.invoke(SearchContract.UiEvent.OnRecentSearched(it))
                }
            )
        }
        Spacer(Modifier.height(20.dp))
        SectionTitle(title = stringResource(R.string.title_trending_search))
        TagList(
            items = state.trendingSearches,
            icon = Icons.Default.TrendingUp,
            onClick = {
                event.invoke(SearchContract.UiEvent.Submit(it))
            }
        )
    }
}

@Composable
fun SearchResultSection(
    query: String,
    results: List<String>,
    onClick: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        results.forEach { product ->
            val annotatedString = buildAnnotatedString {
                val stringBefore = product.lowercase().substringBefore(query.lowercase())
                val stringAfter = product.lowercase().substringAfter(query.lowercase())
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                ) {
                    append((stringBefore + query.lowercase()).capitalize(Locale.ROOT))
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.primary
                    )
                ) {
                    append(stringAfter)
                }
            }
            Text(
                text = annotatedString,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(product) }
                    .padding(vertical = 6.dp),
            )
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}