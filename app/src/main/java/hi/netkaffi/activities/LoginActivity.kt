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

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val buttonSignup = findViewById<Button>(R.id.signup_button)
        buttonSignup.setOnClickListener{

            val intentSignup = Intent(this, SignupActivity::class.java)
            startActivity(intentSignup)
        }
        val tempUser = User("123","123", true) //TODO: FIX LATER
        DummyData.Users.addUsers(tempUser)

        val buttonMain = findViewById<Button>(R.id.login_button)
        buttonMain.setOnClickListener{

            val username = findViewById<EditText>(R.id.login_username).text.toString()
            val password = findViewById<EditText>(R.id.login_password).text.toString()

            if (DummyData.Users.isUser(username, password)){
                val user = DummyData.Users.getUser(username);
                val intentMain = Intent(this, MainActivity::class.java)
                if (user != null && user.isAdmin == true) {
                    intent.putExtra("isAdmin", true)
                }
                else {
                    intent.putExtra("isAdmin", false)
                }
                startActivity(intentMain)
            }
            else {
                Toast.makeText(this,"Wrong Username or Password",Toast.LENGTH_LONG).show()
            }
        }
    }
}