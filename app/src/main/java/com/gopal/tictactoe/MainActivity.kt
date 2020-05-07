package com.gopal.tictactoe
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button11.setOnClickListener {
            val i= Intent(this,Main2Activity::class.java)
            var f=editText.text.toString()
            var s=editText2.text.toString()
            if(f.length==0)
                f="X"
            if(s.length==0)
                s="0"
            i.putExtra("play1",f)
            i.putExtra("play2",s)
            startActivity(i)
        }
    }
}


