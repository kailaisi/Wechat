package com.example.wechat

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wechat.R.drawable
import com.example.wechat.ui.theme.WeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun WeBottomBar(selectIndex: Int, onTap: (Int) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        TabItem(
            if (selectIndex == 0) drawable.ic_chat_filled else drawable.ic_chat_outlined,
            "微信",
            modifier = Modifier
                .weight(1.0f)
                .clickable {
                    onTap(0)
                },
            tint = if (selectIndex == 0) WeTheme.colors.iconCurrent else WeTheme.colors.icon,
        )
        TabItem(
            if (selectIndex == 1) drawable.ic_contacts_filled else drawable.ic_contacts_outlined,
            "通讯录",
            modifier = Modifier
                .weight(1.0f)
                .clickable {
                    onTap(1)
                },
            tint = if (selectIndex == 1) WeTheme.colors.iconCurrent else WeTheme.colors.icon,
        )
        TabItem(
            if (selectIndex == 2) drawable.ic_discover_filled else drawable.ic_discover_outlined,
            "发现",
            modifier = Modifier
                .weight(1.0f)
                .clickable {
                    onTap(2)
                },
            tint = if (selectIndex == 2) WeTheme.colors.iconCurrent else WeTheme.colors.icon,
        )
        TabItem(
            if (selectIndex == 3) drawable.ic_me_filled else drawable.ic_me_outlined,
            "微信",
            modifier = Modifier
                .weight(1.0f)
                .clickable {
                    onTap(3)
                },
            tint = if (selectIndex == 3) WeTheme.colors.iconCurrent else WeTheme.colors.icon,
        )
    }
}

@Composable
fun TabItem(@DrawableRes iconID: Int, title: String, modifier: Modifier = Modifier, tint: Color) {
    Column(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(30.dp)
                .padding(2.dp),
            painter = painterResource(id = iconID),
            contentDescription = title,
            tint = tint,
        )
        Text(text = title, fontSize = 11.sp, color = tint)
    }
}

@Preview(showBackground = true)
@Composable
fun TabItemPreview() {
    WeTheme(WeTheme.Theme.Light) {
        TabItem(drawable.ic_chat_outlined, "微信", tint = WeTheme.colors.background)
    }
}

@Preview(showBackground = true)
@Composable
fun WeBottomBarPreview() {
    WeTheme(WeTheme.Theme.Light) {
        var select by remember {
            mutableStateOf(0)
        }
        WeBottomBar(selectIndex = select, onTap = { select = it })
    }
}