package hardroid.lab_4_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat

/*
THIS FILE IS SCOREKEEPER LOGIC
 */

class MainActivity : AppCompatActivity() {

     private var Score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize ALL Views
        var TeamA: TextView = findViewById(R.id.txt_team_a)
        var TeamB: TextView = findViewById(R.id.txt_team_b)
        var Score_A: TextView = findViewById<TextView>(R.id.a_score)
        var Score_B: TextView = findViewById<TextView>(R.id.b_score)
        var Switch: SwitchCompat = findViewById<SwitchCompat>(R.id.toggle)
        var Plus: View =  findViewById(R.id.plus)
        var Minus: Button = findViewById<Button>(R.id.minus)
        var RG: RadioGroup = findViewById<RadioGroup>(R.id.RG)



        //Plus reaction
        Plus.setOnClickListener{
            var RadioID = findViewById<RadioButton>(RG.checkedRadioButtonId) // get the pressed radio button ID and find the view
            var incrementedBy = RadioID.text.toString().toInt() // translate from string to Int
            if(!Switch.isChecked){//if it isn't toggled
                Score = Score_A.text.toString().toInt()//get text and convert to Int
                Score_A.text = if (Score+incrementedBy >= 20) "20" else "${Score+incrementedBy}" // define what we are going to add to View
            }
            else{//all staff does the same things
                Score = Score_B.text.toString().toInt()
                Score_B.text = if (Score+incrementedBy >= 20) "20" else "${Score+incrementedBy}"
            }
        }

        Minus.setOnClickListener{
            var RadioID = findViewById<RadioButton>(RG.checkedRadioButtonId)
            var dicrementedBy = RadioID.text.toString().toInt()
            if(!Switch.isChecked){
                Score = Score_A.text.toString().toInt()
                Score_A.text = if (Score-dicrementedBy < 0) "0" else "${Score-dicrementedBy}"
            }
            else{
                Score = Score_B.text.toString().toInt()
                Score_B.text = if (Score-dicrementedBy < 0) "0" else "${Score-dicrementedBy}"
            }
        }

        //changes the text in toggle
        Switch.setOnClickListener {
            Switch.text = if(!Switch.isChecked)  getString(R.string.team_a) else  getString(R.string.team_b)
        }


    }

}