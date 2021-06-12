package com.zmide.userssearch

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zmide.userssearch.ui.theme.UsersSearchTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import dev.chrisbanes.accompanist.glide.GlideImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UsersSearchTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    App()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun App() {

    val TAG = "tzmax"

    var text = remember {
        mutableStateOf("")
    }

    var users = remember {
        mutableListOf<UserData>()
    }

    Column(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5))

    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFFFF)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = text.value,
                onValueChange = {
                    text.value = it
                    // Log.i(TAG, "AppTab: " + it)
                },
                label = { },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp, vertical = 20.dp)
            )

            Button(
                onClick = fun() {

                    users.add(
                        UserData(
                            text.value + "这是用户名",
                            "https://cos.haxibiao.com/storage/app-haxibiao/images/60c36f9b11e71.jpeg",
                            "这是介绍文字"
                        )
                    )

                    Log.d(TAG, "App: " + text.value)
                },
                modifier = Modifier
                    .height(45.dp)
                    .padding(end = 10.dp)
            ) {
                Text("搜索")
            }

        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.padding(10.dp))

            for (user in users) {
                UserItem(user)
            }
        }
    }
}

@Composable
fun UserItem(user: UserData) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .padding(horizontal = 15.dp)
            .background(Color(0xFFFFFFFF), RoundedCornerShape(5.dp))
            .padding(vertical = 15.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        GlideImage(
            data = user.avatar,
            contentDescription = "avatar image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .clip(RoundedCornerShape(20.dp)),
        )

        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(user.name, color = Color(0xFF000000), modifier = Modifier.padding(bottom = 3.dp))
            Text(user.info, color = Color(0x66000000))
        }
    }
}

data class UserData(val name: String, val avatar: String, val info: String)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UsersSearchTheme {
        App()
    }
}