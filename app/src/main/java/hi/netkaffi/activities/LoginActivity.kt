package hi.netkaffi.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import hi.netkaffi.R
import hi.netkaffi.entities.User
import hi.netkaffi.service.UserService
import hi.netkaffi.service.api.UserCallback

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle(R.string.login_button);

        val buttonSignup = findViewById<Button>(R.id.signup_button);
        buttonSignup.setOnClickListener{

            val intentSignup = Intent(this, SignupActivity::class.java)
            startActivity(intentSignup)
        }

        val userService = UserService()
        userService.initialize(this)

        val buttonMain = findViewById<Button>(R.id.login_button);
        buttonMain.setOnClickListener{

            val  username = findViewById<EditText>(R.id.login_username).getText().toString()
            val password = findViewById<EditText>(R.id.login_password).getText().toString()

            val user = User(username,password)

            userService.login(user, callback = UserCallback {
                UserService.ActiveUser.setUser(it[0])
                if( it[0].isAdmin == true ){
                    val intentAdmin = Intent(this, AdminActivity::class.java)
                    startActivity(intentAdmin)
                } else {
                    val intentMain = Intent(this, UserActivity::class.java)
                    intentMain.putExtra("user", username)
                    startActivity(intentMain)
                }
            })

        }


    }

}