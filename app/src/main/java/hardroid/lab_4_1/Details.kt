package hardroid.lab_4_1

import android.content.res.Configuration
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Details : AppCompatActivity() {
    private lateinit var LicenseLink: TextView
    private lateinit var RepoLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)
        LicenseLink = findViewById(R.id.link_license)
        RepoLink = findViewById(R.id.link_repo)
        LicenseLink.movementMethod = LinkMovementMethod.getInstance()
        RepoLink.movementMethod = LinkMovementMethod.getInstance()

        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                Toast.makeText(this,"Also, try to use DARK MODE theme!", Toast.LENGTH_LONG).show();
            } // Night mode is not active, we're using the light theme
            Configuration.UI_MODE_NIGHT_YES -> {
                Toast.makeText(this,"Also, try to use LIGHT MODE theme!", Toast.LENGTH_LONG).show();
            } // Night mode is active, we're using dark theme
        }
    }




    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setTheme(R.style.Theme_Lab_41)
        recreate()
    }
}

