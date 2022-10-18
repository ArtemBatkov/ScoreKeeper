package hardroid.lab_4_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var Score_A:TextView = TODO()
    private var Score_B:TextView = TODO()
    private var Switch:Switch = TODO()
    private var Plus: Button = TODO()
    private var Minus:Button = TODO()
    private var RG: RadioGroup = TODO()
    private var Score = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Score_A = findViewById<TextView>(R.id.a_score)
        Score_B = findViewById<TextView>(R.id.b_score)
        Switch = findViewById<Switch>(R.id.toggle)
        Plus = findViewById<Button>(R.id.plus)
        Minus =  findViewById<Button>(R.id.minus)
        RG = findViewById<RadioGroup>(R.id.RG)






    }

}