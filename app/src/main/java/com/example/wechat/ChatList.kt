package com.example.wechat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wechat.R.drawable
import com.example.wechat.data.Chat
import com.example.wechat.data.Msg
import com.example.wechat.data.User
import com.example.wechat.ui.theme.WeTheme
import com.example.wechat.ui.theme.WeTheme.Theme.Light

@Composable
fun ChatList(chats: List<Chat>) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(WeTheme.colors.background)
    ) {
        WeTopBar(title = "开", onBack = {})
        LazyColumn(Modifier.background(WeTheme.colors.listItem)) {
            itemsIndexed(chats) { index, chat ->
                ChatListItem(chat)
                if (index < chats.lastIndex) {
                    Divider()
                }
            }
        }
    }
}

@Composable
private fun ChatListItem(chat: Chat) {
    val viewModel:WeViewModel= viewModel()
    Row(Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = chat.friend.avatar),
            contentDescription = chat.friend.name,
            Modifier
                .clickable { viewModel.startChat(chat)}
                .padding(4.dp)
                .size(48.dp)
                .unread(!chat.msgs.last().read, WeTheme.colors.badge)
                .clip(
                    RoundedCornerShape(4.dp)
                )
        )
        Column(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = chat.friend.name,
                fontSize = 17.sp,
                color = WeTheme.colors.textPrimary
            )
            Text(
                text = chat.msgs.last().text,
                fontSize = 14.sp,
                color = WeTheme.colors.textSecondary
            )
        }
        Text(
            chat.msgs.last().text,
            Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
            fontSize = 11.sp,
            color = WeTheme.colors.textSecondary
        )
    }
}

fun Modifier.unread(show: Boolean, color: Color): Modifier = this.drawWithContent {
    drawContent()
    if (show) {
        drawCircle(
            color,
            5.dp.toPx(),
            Offset(size.width - 1.dp.toPx(), 1.dp.toPx())
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatListPreview() {
    WeTheme(Light) {
        ChatList(
            listOf(
                Chat(
                    friend = User("gaolaoshi", "高老师", drawable.avatar_gaolaoshi),
                    mutableListOf(
                        Msg(User("gaolaoshi", "高老师", drawable.avatar_gaolaoshi), "锄禾日当午"),
                        Msg(User.Me, "汗滴禾下土"),
                        Msg(User("gaolaoshi", "高老师", drawable.avatar_gaolaoshi), "谁知盘中餐"),
                        Msg(User.Me, "粒粒皆辛苦"),
                        Msg(
                            User("gaolaoshi", "高老师", drawable.avatar_gaolaoshi),
                            "唧唧复唧唧，木兰当户织。不闻机杼声，惟闻女叹息。"
                        ),
                        Msg(User.Me, "双兔傍地走，安能辨我是雄雌？"),
                        Msg(User("gaolaoshi", "高老师", drawable.avatar_gaolaoshi), "床前明月光，疑是地上霜。"),
                        Msg(User.Me, "吃饭吧？"),
                    )
                ),
                Chat(
                    friend = User("diuwuxian", "丢物线", drawable.avatar_diuwuxian),
                    mutableListOf(
                        Msg(User("diuwuxian", "丢物线", drawable.avatar_diuwuxian), "哈哈哈"),
                        Msg(User.Me, "你笑个屁呀"),
                        Msg(User("diuwuxian", "丢物线", drawable.avatar_diuwuxian), "哈哈哈"),
                    )
                ),
            )
        )
    }
}