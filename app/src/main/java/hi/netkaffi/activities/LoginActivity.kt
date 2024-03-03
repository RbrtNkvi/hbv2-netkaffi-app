package hi.netkaffi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import hi.netkaffi.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val buttonSignup = findViewById<Button>(R.id.signup_button);
        buttonSignup.setOnClickListener{

            val intentSignup = Intent(this, SignupActivity::class.java)
            startActivity(intentSignup)
        }

        val buttonMain = findViewById<Button>(R.id.login_button);
        buttonMain.setOnClickListener{

            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }
    }

}