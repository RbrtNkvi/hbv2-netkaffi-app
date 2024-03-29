package hi.netkaffi.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hi.netkaffi.R
import hi.netkaffi.entities.User
import hi.netkaffi.service.UserService
import hi.netkaffi.service.api.UserCallback
import hi.netkaffi.service.dummyData

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val buttonSignup = findViewById<Button>(R.id.signup_button);
        buttonSignup.setOnClickListener{

            val intentSignup = Intent(this, SignupActivity::class.java)
            startActivity(intentSignup)
        }
        //val tempuser = User("123","123", true)
        //dummyData.Users.addUsers(tempuser)

        val userService = UserService()
        userService.initialize(this)

        val buttonMain = findViewById<Button>(R.id.login_button);
        buttonMain.setOnClickListener{

            val  username = findViewById<EditText>(R.id.login_username).getText().toString()
            val password = findViewById<EditText>(R.id.login_password).getText().toString()

            val user = User(username,password)

            userService.login(user, callback = UserCallback {
                val intentMain = Intent(this, MainActivity::class.java)
                startActivity(intentMain)
            })

        }


    }

}