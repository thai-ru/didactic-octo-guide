package com.example.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val counterBtn = findViewById<Button>(R.id.counterButton)
        val counterTextView = findViewById<TextView>(R.id.counterTextView)

        SocketHandler.setSocket()

        val mSocket = SocketHandler.getSocket()

        mSocket.connect()



        counterBtn.setOnClickListener {
            mSocket.emit("counter")

        }

        mSocket.on("counter"){
            args ->
            if(args[0] != null){
                val counter = args[0] as Int

                runOnUiThread{
                    counterTextView.text = counter.toString()
                }
            }
        }

    }
}