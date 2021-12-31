package com.example.wechat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wechat.ui.theme.WeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeTheme(theme = WeTheme.Theme.NewYear) {
                Column(Modifier.padding(8.dp)) {
                    val viewModel: WeViewModel = viewModel()
                    HorizontalPager(PagerState(4, currentPage = viewModel.selectedTab),Modifier.weight(1f), verticalAlignment = Alignment.Top){ page: Int ->
                        when(page){
                            0-> ChatList(viewModel.chats)
                            1->Box(Modifier.fillMaxWidth())
                            2->Box(Modifier.fillMaxWidth())
                            3->Box(Modifier.fillMaxWidth())
                        }
                    }
                    WeBottomBar(
                        selectIndex = viewModel.selectedTab
                    ) { viewModel.selectedTab = it }
                }
            }
        }
    }


}
