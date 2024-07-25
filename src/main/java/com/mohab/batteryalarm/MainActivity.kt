package com.mohab.batteryalarm

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mohab.batteryalarm.ui.theme.BatteryAlarmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BatteryAlarmTheme {
                BatteryInterface()
            }
        }
    }
}


@Composable
fun BatteryInterface(modifier: Modifier = Modifier) {
val context = LocalContext.current
var image by rememberSaveable { mutableIntStateOf(R.drawable.battery_full) }
    val filter = IntentFilter().apply {
        addAction(Intent.ACTION_BATTERY_LOW)
        addAction(Intent.ACTION_BATTERY_OKAY)
    }
    val batteryPercentReceiver = BatteryPercentReceiver(){
        image = if (it){
            R.drawable.battery_low
        } else{
            R.drawable.battery_full
        }
    }

    LocalContext.current.registerReceiver(batteryPercentReceiver,filter)

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
Image(painter = painterResource(id = image), contentDescription = "")
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BatteryInterface()
}