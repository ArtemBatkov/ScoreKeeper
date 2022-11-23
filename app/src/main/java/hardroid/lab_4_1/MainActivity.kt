package hardroid.lab_4_1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.get
import androidx.core.view.size
import androidx.preference.SwitchPreference


/*
THIS FILE IS SCOREKEEPER LOGIC
 */

class MainActivity : AppCompatActivity() {
    lateinit var ShrPrf: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    val keyPref = "LAB_8_PREF"
    val keySwitchSave = "SWTICH_SAVE"
    val keyTeamAscore = "TEAM_A_SCORE"
    val keyTeamBscore = "TEAM_B_SCORE"
    val keySwitchTeam = "SWITCH_TEAM"
    val keyRadioButton = "RADIO_BUTTON"
    private var TeamAscore = 0
    private var TeamBscore = 0

    private lateinit var Score_A: TextView
    private lateinit var Score_B:TextView
    private lateinit var Switch: SwitchCompat
    private lateinit var RG: RadioGroup

    private fun UploadData(){
        ShrPrf = getSharedPreferences(keyPref, Context.MODE_PRIVATE)
        val IsSwitchSaveChecked:Boolean = ShrPrf.getBoolean(keySwitchSave,false)
        if(IsSwitchSaveChecked){
            TeamAscore = ShrPrf.getInt(keyTeamAscore,0)
            TeamBscore = ShrPrf.getInt(keyTeamBscore,0)
            Switch.isChecked = ShrPrf.getBoolean(keySwitchTeam,false)
            if (!Switch.isChecked)  Switch.text = getString(R.string.team_a)
            else Switch.text = getString(R.string.team_b)
            val RadioValueSaved = ShrPrf.getInt(keyRadioButton,1)
                var CurrentValue = 1
                for (i in 0  until RG.size){
                    CurrentValue = findViewById<RadioButton>(RG.get(i).id).text.toString().toInt()
                    if(CurrentValue == RadioValueSaved){
                        findViewById<RadioButton>(RG.get(i).id).isChecked = true
                        break
                    }
                }

                if(TeamBscore!=null && TeamAscore!=null){
                    Score_A.setText(TeamAscore.toString())
                    Score_B.setText(TeamBscore.toString())
                }
            }
        }

    private fun SaveData() {
            val switchBool:Boolean = ShrPrf.getBoolean(keySwitchSave,false)
            ShrPrf = getSharedPreferences(keyPref, Context.MODE_PRIVATE)
            editor = ShrPrf.edit()
            editor.apply {
                if (switchBool) {
                    if (Score_A != null && Score_B != null) {
                        putInt(keyTeamAscore, Score_A.text.toString().toInt())
                        putInt(keyTeamBscore, Score_B.text.toString().toInt())
                        putBoolean(keySwitchTeam,Switch.isChecked)
                        val RadioButton = findViewById<RadioButton>(RG.checkedRadioButtonId)
                        val RadioValue = RadioButton.text.toString().toInt()
                        putInt(keyRadioButton,RadioValue)
                    }
                } else {
                    editor.clear()
                }
            }.apply()
        }


    private  fun gotoDetails(){
        try{
            val intent = Intent(this@MainActivity,Details::class.java)
            startActivity(intent)
        }catch(_:Exception){}
    }
    private  fun gotoSettings(){
        try{
            val intent = Intent(this@MainActivity,SettingsActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra(keyTeamAscore,Score_A.text.toString().toInt())
            intent.putExtra(keyTeamBscore,Score_B.text.toString().toInt())
            intent.putExtra(keySwitchTeam,Switch.isChecked)
            intent.putExtra(keyRadioButton,findViewById<RadioButton>(RG.checkedRadioButtonId).text.toString().toInt())
            startActivity(intent)
        }catch(_:Exception){}
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
             R.id.details -> {
                 Toast.makeText(this,"Details were pressed!",Toast.LENGTH_LONG).show()
                 gotoDetails()
             }
            R.id.settings -> {
                Toast.makeText(this,"Settings were pressed!",Toast.LENGTH_LONG).show()
                gotoSettings()
            }

        }
        return super.onOptionsItemSelected(item)
    }

     private var Score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize ALL Views
        var TeamA: TextView = findViewById(R.id.txt_team_a)
        var TeamB: TextView = findViewById(R.id.txt_team_b)
        Score_A = findViewById<TextView>(R.id.a_score)
        Score_B = findViewById<TextView>(R.id.b_score)
        Switch = findViewById<SwitchCompat>(R.id.toggle)
        var Plus: View =  findViewById(R.id.plus)
        var Minus: Button = findViewById<Button>(R.id.minus)
        RG = findViewById<RadioGroup>(R.id.RG)



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


        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                Toast.makeText(this,"Also, try to use DARK MODE theme!",Toast.LENGTH_LONG).show();
            } // Night mode is not active, we're using the light theme
            Configuration.UI_MODE_NIGHT_YES -> {
                Toast.makeText(this,"Also, try to use LIGHT MODE theme!",Toast.LENGTH_LONG).show();

            } // Night mode is active, we're using dark theme
        }

            UploadData()
    }

    override fun onResume() {
        super.onResume()
        SaveData()
    }

    override fun onPause() {
        super.onPause()
        SaveData()
    }



    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val currentSystemMode = newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK

        when (currentSystemMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                // Night mode is not active
                Toast.makeText(this,"Also, try to use DARK MODE theme!",Toast.LENGTH_LONG).show();
            }
                Configuration.UI_MODE_NIGHT_YES -> {
                // Night mode is active
                    Toast.makeText(this,"Also, try to use LIGHT MODE theme!",Toast.LENGTH_LONG).show();
                }

        }
     }

    fun getMainActivityData(): List<Int> {
        val data:List<Int> = listOf(Score_A.text.toString().toInt(),Score_B.text.toString().toInt())
        return data
    }

    }

