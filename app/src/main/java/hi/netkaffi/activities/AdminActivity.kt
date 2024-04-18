package hi.netkaffi.activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import hi.netkaffi.R
import hi.netkaffi.databinding.ActivityMainBinding
import hi.netkaffi.databinding.ActivityQrBinding

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.qrButtonMain.setOnClickListener{
            val binding2 = ActivityQrBinding.inflate(layoutInflater)
            val view = binding2.root
            setContentView(view)

            binding2.scanButton.setOnClickListener{
                val intentQr = Intent(this, QRActivity::class.java)
                startActivity(intentQr)
            }
        }
        
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        setContentView(binding.root)
    }
}

