package com.apparel.offprice

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.apparel.offprice.common.theme.OffPriceTheme
import com.apparel.offprice.routes.AppRoutes
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val widthSizeClass: WindowSizeClass = calculateWindowSizeClass(this)
            OffPriceTheme (darkTheme = false){
                Surface(
                    modifier = Modifier.fillMaxSize(1f)
                ) {
                    AppRoutes(windowSizeClass = widthSizeClass)
                }
            }
        }
    }
}