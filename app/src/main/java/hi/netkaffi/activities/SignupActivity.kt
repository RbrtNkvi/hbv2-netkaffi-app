package hi.netkaffi.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hi.netkaffi.R
import hi.netkaffi.entities.User
import hi.netkaffi.service.DummyData

class SignupActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val button = findViewById<Button>(R.id.back_login_button)
        button.setOnClickListener{

            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
        }
        val buttonSignup = findViewById<Button>(R.id.signup_button)
        buttonSignup.setOnClickListener{
            val username = findViewById<EditText>(R.id.signup_username).text.toString()
            val password = findViewById<EditText>(R.id.signup_password).text.toString()

            val user = User(username, password, false) //TODO: FIX LATER
            if(DummyData.Users.isUsername(user)){
                DummyData.Users.addUsers(user)

                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                val intentLogin = Intent(this, LoginActivity::class.java)
                startActivity(intentLogin)
            }
            val errorMsg = "Username already exists"
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()


        }

    }



}
