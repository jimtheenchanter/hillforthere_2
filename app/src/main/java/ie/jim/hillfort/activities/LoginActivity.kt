package ie.jim.hillfort.activities



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import ie.jim.hillfort.R
import ie.jim.hillfort.models.UserModel
import org.jetbrains.anko.info  // allow info windows
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

////
class LoginActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        _emailText =   findViewById(R.id.edtUsername) as EditText
        _loginButton = findViewById(R.id.btnLogin) as Button
        _passwordText = findViewById(R.id.edtPassword) as EditText
        _loginButton!!.setOnClickListener { login() }
    }

}

    var _emailText: EditText? = null
    var _passwordText: EditText? = null
    var _loginButton: Button? = null

    fun login() {
        _loginButton!!.isEnabled = false
        val email = _emailText!!.text.toString()
        val password = _passwordText!!.text.toString()
//     startActivityForResult(<HillfortListActivity>)
             if (email == "homer@simpson.com") {
//            finish()
        }
    }
//
fun onLoginSuccess() {
    _loginButton!!.isEnabled = true
//    finish()

//    startActivityForResult(intent( HillfortListActivity::class.java))
}



