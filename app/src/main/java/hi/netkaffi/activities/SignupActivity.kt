package hi.netkaffi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import hi.netkaffi.R
import hi.netkaffi.entities.User
import hi.netkaffi.service.dummyData

class SignupActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val button = findViewById<Button>(R.id.back_login_button);
        button.setOnClickListener{

            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
        }
        val buttonSignup = findViewById<Button>(R.id.signup_button)
        buttonSignup.setOnClickListener{
            val username = findViewById<EditText>(R.id.signup_username).getText().toString()
            val password = findViewById<EditText>(R.id.signup_password).getText().toString()

            val user = User(username,password)
            if(dummyData.Users.isUsername(user)){
                dummyData.Users.addUsers(user)

                Toast.makeText(this, "Sucess", Toast.LENGTH_LONG).show()
                val intentLogin = Intent(this, LoginActivity::class.java)
                startActivity(intentLogin)
            }
            val errormsg = "Username already exists"
            Toast.makeText(this, errormsg, Toast.LENGTH_LONG).show()


        }

    }



}
