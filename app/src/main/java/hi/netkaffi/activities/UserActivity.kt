package hi.netkaffi.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import hi.netkaffi.R
import hi.netkaffi.databinding.ActivityMainUserBinding
import hi.netkaffi.databinding.ActivityQrBinding

class UserActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.qrButton.setOnClickListener{
            val binding2 = ActivityQrBinding.inflate(layoutInflater)
            val view = binding2.root
            setContentView(view)

            binding2.scanButton.setOnClickListener{
                val intentQr = Intent(this, QRActivity::class.java)
                startActivity(intentQr)
            }
        }

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main_user)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.dashboardFragment
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