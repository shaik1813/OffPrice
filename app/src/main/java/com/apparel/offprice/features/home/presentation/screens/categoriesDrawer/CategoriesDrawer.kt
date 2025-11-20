package com.apparel.offprice.features.home.presentation.screens.categoriesDrawer


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import com.apparel.offprice.features.home.data.model.CategoryItem
import com.apparel.offprice.features.home.data.model.DrawerMode
import com.apparel.offprice.features.home.data.model.SubCategoryItem
import com.apparel.offprice.features.home.data.model.sampleCategoryItems
import com.apparel.offprice.features.home.data.model.sampleSubCategories
import com.apparel.offprice.features.home.data.model.sampleTopTabs

@Composable
fun CategoriesDrawer(
    drawerMode: DrawerMode,
    selectedCategory: CategoryItem?,
    selectedTopTab: String,
    onTopTabClick: (String) -> Unit,
    onCategoryClick: (CategoryItem) -> Unit,
    onSubCategoryClick: (SubCategoryItem) -> Unit,
    onBack: () -> Unit,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.White)
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth())
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // BACK icon only in SUBCATEGORY mode
            if (drawerMode == DrawerMode.SUBCATEGORY_LIST) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }

                // Title next to back arrow
                Text(
                    text = selectedCategory?.title ?: "",
                    style = MaterialTheme.typography.titleLarge
                )

            } else {
                // CATEGORY_LIST mode

                Text(
                    text = "OFF/PRICE",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            // Spacer to push Close icon to the right
            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }
        }


        Spacer(modifier = Modifier
            .height(8.dp)
            .fillMaxWidth())

        if (drawerMode == DrawerMode.CATEGORY_LIST) {

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                items(sampleTopTabs) { tab ->

                    val isSelected = selectedTopTab == tab.id

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                if (isSelected) Color.Black else Color.White
                            )
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .clickable {
                                onTopTabClick(tab.id)
                            }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = tab.label,
                            color = if (isSelected) Color.White else Color.Black,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }


        Spacer(modifier = Modifier
            .height(12.dp)
            .fillMaxWidth())

        when (drawerMode) {

            DrawerMode.CATEGORY_LIST -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(sampleCategoryItems) { item ->
                        CategoryCard(item) {
                            onCategoryClick(item)
                        }
                    }
                }
            }

            DrawerMode.SUBCATEGORY_LIST -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(sampleSubCategories) { subItem ->
                        SubCategoryCard(subItem) {
                            onSubCategoryClick(subItem)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CategoriesDrawerPreview(){
    CategoriesDrawer(
        drawerMode = DrawerMode.CATEGORY_LIST,
        selectedCategory = null,
        selectedTopTab = "men",
        onTopTabClick = { },
        onCategoryClick = { },
        onSubCategoryClick = { },
        onBack = { },
    ) { }
}
