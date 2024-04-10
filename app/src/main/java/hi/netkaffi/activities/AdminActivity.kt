package hi.netkaffi.activities

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import hi.netkaffi.R
import hi.netkaffi.databinding.ActivityLoginBinding
import hi.netkaffi.databinding.ActivityMainBinding

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.qrButtonMain.setOnClickListener{
            Toast.makeText(this, "Tester", Toast.LENGTH_LONG).show()
            val intentQr = Intent(this, QRActivity::class.java)
            startActivity(intentQr)
        }

        //binding2 = ActivityLoginBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_login)
        
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}

