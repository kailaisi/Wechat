package com.example.wechat

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wechat.ui.theme.WechatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WechatTheme {
                // A surface container using the 'background' color from the theme
                Column(Modifier.padding(8.dp)) {
                    WeBottomBar(selectIndex = 0)
                }
            }
        }
    }

}

@Composable
private fun WeBottomBar(selectIndex: Int) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        TabItem(
            if (selectIndex == 0) R.drawable.ic_chat_filled else R.drawable.ic_chat_outlined,
            "微信",
            modifier=Modifier.weight(1.0f),
            tint=if(selectIndex==0) Color.Green else Color.Black,
        )
        TabItem(
            if (selectIndex == 1) R.drawable.ic_contacts_filled else R.drawable.ic_contacts_outlined,
            "通讯录",
            modifier=Modifier.weight(1.0f),
            tint=if(selectIndex==1) Color.Green else Color.Black,
        )
        TabItem(
            if (selectIndex == 2) R.drawable.ic_discover_filled else R.drawable.ic_discover_outlined,
            "发现",
            modifier=Modifier.weight(1.0f),
            tint=if(selectIndex==2) Color.Green else Color.Black,
        )
        TabItem(
            if (selectIndex == 3) R.drawable.ic_me_filled else R.drawable.ic_me_outlined,
            "微信",
            modifier=Modifier.weight(1.0f),
            tint=if(selectIndex==3) Color.Green else Color.Black,
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
    WechatTheme {
        TabItem(R.drawable.ic_chat_outlined, "微信", tint = Color.Blue)
    }
}

@Preview(showBackground = true)
@Composable
fun TWeBottomBarPreview() {
    WechatTheme {
        WeBottomBar(selectIndex = 1)
    }
}