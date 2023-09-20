package com.example.composeappcodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeappcodelab.ui.theme.ComposeAppCodeLabTheme
import org.w3c.dom.NameList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppCodeLabTheme {
                    MyApp()
                   //OnboardingScreen()
            }
        }
    }
}

@Composable
fun MyApp() {
    var shouldShowOnboarding by remember { mutableStateOf(true) }
    if (shouldShowOnboarding){
        OnboardingScreen(onContinueClicked = {shouldShowOnboarding = false})
    } else {
        Greetings()
    }
    
}

@Composable
//fun Greetings  (names: List<String> = listOf("World", "Compose")) {
fun Greetings  (names: List<String> = List(100) { "$it" }) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    )
    {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            //for (name in names) {
            LazyColumn {
                items(names){ name ->    //DSL Direcly calling composable
                    Greeting(name)
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

var expended by  remember { mutableStateOf(false) }
    val extraPadding  by  animateDpAsState(
        targetValue =   if (expended) 48.dp else 0.dp,
        animationSpec = tween( durationMillis = 2000)
    )

//bg color
    Surface(color = MaterialTheme.colorScheme.primary,
           modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
           )
    {
        Row (modifier= Modifier.padding(24.dp)){
            Column(
                modifier = Modifier
                    .weight(1f)  //weight does not overlap with other child
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello, ")
                Text(text = name)
            }
            ElevatedButton(onClick = { expended = !expended })
            {
                Text(if(expended) "Show less <--" else "Show more -->")
            }
        }
}

}
@Composable
fun OnboardingScreen(
    onContinueClicked: ()-> Unit
) {
    // TODO: This state should be hoisted

  // var shouldShowOnboarding by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            //onClick = { shouldShowOnboarding = false }
            onClick = onContinueClicked

        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    ComposeAppCodeLabTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun GreetingPreview() {
    ComposeAppCodeLabTheme {
        MyApp()
    }
}