package hardroid.lab_4_1

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_settings)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.blank_settings_layout, SettingsFragment())
            .commit()

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setTheme(R.style.Theme_Lab_41)
        recreate()
    }
}

