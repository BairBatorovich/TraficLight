package com.example.traficlight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    lateinit var red: LinearLayout
    lateinit var yellow: LinearLayout
    lateinit var green: LinearLayout
    lateinit var button: Button
    var startstop = false
    var counter:Int = 0
    var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        red = findViewById(R.id.red)
        yellow = findViewById(R.id.yelow)
        green = findViewById(R.id.green)
        button = findViewById(R.id.button)
    }

    public fun changecolor1() {
        timer?.schedule(object :TimerTask(){
            override fun run() {
                runOnUiThread {
                        when(counter) {
                            0 ->  { red.setBackgroundColor(getColor(R.color.red))
                                green.setBackgroundColor(getColor(R.color.lightgray))
                            }
                            1 -> { yellow.setBackgroundColor(getColor(R.color.yellow))
                                red.setBackgroundColor(getColor(R.color.lightgray))
                            }
                            2 -> { green.setBackgroundColor(getColor(R.color.green))
                                yellow.setBackgroundColor(getColor(R.color.lightgray))
                            }
                        }
                        counter++
                        if(counter == 3) counter = 0
                }
            }
        },0,1000)
    }
    public fun onClickStartStop(view: View) {

        if(!startstop) {
            timer = Timer()
            startstop = true
            button.setBackgroundColor(getColor(R.color.red))
            button.text = getText(R.string.stop)
            changecolor1()
        } else {
            startstop = false
            button.setBackgroundColor(getColor(R.color.green))
            button.text = getText(R.string.start)
            yellow.setBackgroundColor(getColor(R.color.lightgray))
            red.setBackgroundColor(getColor(R.color.lightgray))
            green.setBackgroundColor(getColor(R.color.lightgray))
            timer?.cancel()
            counter = 0
        }
    }
}