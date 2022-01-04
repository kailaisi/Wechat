package com.example.wechat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.wechat.ui.theme.WeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

class MainActivity : ComponentActivity() {
    val viewModel: WeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeTheme(theme = viewModel.theme) {
                Box {
                    HomePage()
                    ChatPage(chat = viewModel.currentChat, onBack = { viewModel.endChat() })
                }
            }
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    private fun HomePage() {
        Column {
            HorizontalPager(
                PagerState(4, currentPage = viewModel.selectedTab),
                Modifier.weight(1f),
                verticalAlignment = Alignment.Top
            ) { page: Int ->
                when (page) {
                    0 -> ChatList(viewModel.chats)
                    1 -> Box(Modifier.fillMaxWidth())
                    2 -> Box(Modifier.fillMaxWidth())
                    3 -> Box(Modifier.fillMaxWidth())
                }
            }
            WeBottomBar(
                selectIndex = viewModel.selectedTab
            ) { viewModel.selectedTab = it }
        }
    }

    override fun onBackPressed() {
        if (viewModel.currentChat != null) {
            viewModel.endChat()
        } else {
            super.onBackPressed()
        }
    }
}
