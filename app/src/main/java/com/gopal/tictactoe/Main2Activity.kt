package com.gopal.tictactoe



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity(),View.OnClickListener {


    lateinit var board:Array<Array<Button>>
    var PLAYER=true;
    var player1="X"
    var player2="0"

    var boardStatus=Array(3){IntArray(3)}
    var turn_Count=0
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        player1=intent.getStringExtra("play1")
        player2=intent.getStringExtra("play2")
        textView.text="$player1 Turn"
        board= arrayOf(arrayOf(button,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )

        for(i in board)
        {
            for(but in i)
                but.setOnClickListener(this)
        }

        initializedBoard()
        button10.setOnClickListener {
            PLAYER=true
            turn_Count=0
            initializedBoard()
        }
    }

    private fun initializedBoard() {
        for(i in 0..2)
        {
            for(j in 0..2){
                boardStatus[i][j]=-1;
                board[i][j].isEnabled=true;
                board[i][j].text=""
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.button->{
                updateValue(row=0,col=0,player=PLAYER)
            }
            R.id.button2->{
                updateValue(row=0,col=1,player=PLAYER)

            }
            R.id.button3->{
                updateValue(row=0,col=2,player=PLAYER)
            }
            R.id.button4->{
                updateValue(row=1,col=0,player=PLAYER)
            }
            R.id.button5->{
                updateValue(row=1,col=1,player=PLAYER)
            }
            R.id.button6->{
                updateValue(row=1,col=2,player=PLAYER)
            }
            R.id.button7->{
                updateValue(row=2,col=0,player=PLAYER)
            }
            R.id.button8->{
                updateValue(row=2,col=1,player=PLAYER)
            }
            R.id.button9->{
                updateValue(row=2,col=2,player=PLAYER)
            }

        }
        PLAYER=!PLAYER
        turn_Count++
        if(PLAYER)
            updateDisplay("$player1 Turn")
        else
            updateDisplay("$player2 Turn")

        if(turn_Count==9)
            updateDisplay("Game Draw")
        else
            checkWinner()
    }

    private fun checkWinner() {
        //row check
        var check=0
        for(i in 0..2)
        {
            if(boardStatus[i][0]==boardStatus[i][1]&&boardStatus[i][2]==boardStatus[i][1]&&boardStatus[i][0]!=-1)
            {
                if(boardStatus[i][0]==1){
                    textView.text="${player1} is Winner"
                }
                else
                    textView.text="$player2 is Winner"
                check=1
                disableButton()
            }
        }
        //col check
        if(check==0){
            for(i in 0..2)
            {
                if(boardStatus[0][i]==boardStatus[1][i]&&boardStatus[2][i]==boardStatus[1][i]&&boardStatus[0][i]!=-1)
                {
                    if(boardStatus[0][i]==1){
                        textView.text="$player1 is Winner"
                    }
                    else
                        textView.text="$player2 is Winner"
                    disableButton()
                    check=1
                }
            }
        }
        //Diagonal Check
        if(check==0){

            if(boardStatus[0][0]==boardStatus[1][1]&&boardStatus[2][2]==boardStatus[1][1]&&boardStatus[0][0]!=-1)
            {
                if(boardStatus[0][0]==1){
                    textView.text="$player1 is Winner"
                }
                else
                    textView.text="$player2 is Winner"
                disableButton()
                check=1
            }
            if(boardStatus[0][2]==boardStatus[1][1]&&boardStatus[2][0]==boardStatus[1][1]&&boardStatus[0][2]!=-1)
            {
                if(boardStatus[0][2]==1){
                    textView.text="$player1 is Winner"
                }
                else
                    textView.text="$player2 is Winner"
                disableButton()
                check=1
            }

        }
    }

    private fun updateDisplay(s: String) {
        textView.text=s
        if(s.contentEquals("Winner"))
            disableButton()
    }

    private fun disableButton() {
        for(i in board)
            for(j in i)
                j.isEnabled=false
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text=if(player) "X" else "0"
        val value=if(player) 1 else 0
        board[row][col].isEnabled=false
        boardStatus[row][col]=value
        board[row][col].text=text

    }
}
