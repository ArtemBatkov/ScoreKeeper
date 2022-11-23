package hardroid.lab_4_1

import android.content.Context
import android.content.SharedPreferences
import android.location.GnssAntennaInfo.Listener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference


class SettingsFragment : PreferenceFragmentCompat() {
    private lateinit var ShrPrf: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val keyPref = "LAB_8_PREF"
    private val keySwitchSave = "SWTICH_SAVE"
    private val keyTeamAscore = "TEAM_A_SCORE"
    private val keyTeamBscore = "TEAM_B_SCORE"
    private val keySwitchTeam = "SWITCH_TEAM"
    private val keyRadioButton = "RADIO_BUTTON"

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings,rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
            SaveData()
        return super.onPreferenceTreeClick(preference)
    }

    override fun onResume() {
        super.onResume()
        SaveData()
    }

    override fun onPause() {
        super.onPause()
        SaveData()
    }


    private fun SaveData() {
        if (context != null) {
            ShrPrf = requireContext().getSharedPreferences(keyPref, Context.MODE_PRIVATE)
            editor = ShrPrf.edit()
            editor.apply {
                val element: SwitchPreference =
                    findPreference<SwitchPreference>("save_score_preference")!!
                val switchBool: Boolean = element.isChecked
                if (switchBool) {
                    val ScoreA = activity?.intent?.getIntExtra(keyTeamAscore, 0)
                    val ScoreB = activity?.intent?.getIntExtra(keyTeamBscore, 0)
                    val TeamSwitch = activity?.intent?.getBooleanExtra(keySwitchTeam,false)
                    val RadioValue = activity?.intent?.getIntExtra(keyRadioButton,1)
                    if (ScoreA != null && ScoreB != null && TeamSwitch!=null && RadioValue!=null) {
                        putInt(keyTeamAscore, ScoreA.toInt())
                        putInt(keyTeamBscore, ScoreB)
                        putBoolean(keySwitchTeam,TeamSwitch)
                        putInt(keyRadioButton,RadioValue)
                    }
                } else {
                    editor.clear()
                }
                putBoolean(keySwitchSave, switchBool)
            }.apply()
        }
    }



}


