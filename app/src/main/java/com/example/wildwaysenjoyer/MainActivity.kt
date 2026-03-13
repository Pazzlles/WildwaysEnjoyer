package com.example.wildwaysenjoyer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.wildwaysenjoyer.ui.WildwaysRoute
import com.example.wildwaysenjoyer.ui.theme.WildwaysEnjoyerTheme

// Activity - это стандартная точка входа Android-приложения.
// В Compose мы не подключаем XML, а сразу описываем UI в Kotlin-коде.
class MainActivity : ComponentActivity() {
    // override означает, что мы переопределяем метод родительского класса.
    override fun onCreate(savedInstanceState: Bundle?) {
        // super.onCreate(...) запускает базовую логику Activity.
        super.onCreate(savedInstanceState)
        // Позволяет контенту рисоваться "под" системными барами.
        enableEdgeToEdge()
        // setContent { ... } - точка входа в Compose UI вместо setContentView(...).
        setContent {
            // Тема задает цвета и типографику для всех дочерних composable.
            WildwaysEnjoyerTheme {
                // Route связывает экран и логику состояния.
                WildwaysRoute()
            }
        }
    }
}
