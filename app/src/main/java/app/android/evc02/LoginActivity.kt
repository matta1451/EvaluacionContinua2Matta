package app.android.evc02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import app.android.evc02.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val usuarioEmail: String = "ejemplo@idat.edu.pe"
    private val passwordEmail: String = "123456"

    private var emailValido: Boolean = false
    private var passwordValido: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tilEmail.editText?.addTextChangedListener { text ->
            emailValido = validarEmail(text.toString())
            binding.btnLogin.isEnabled = emailValido && passwordValido
        }
        binding.tilPassword.editText?.addTextChangedListener { text ->
            passwordValido = validarPassword(text.toString())
            binding.btnLogin.isEnabled = emailValido && passwordValido
        }
        binding.btnLogin.setOnClickListener {
            if(validarCredenciales(binding.tilEmail.editText?.text.toString(),
                    binding.tilPassword.editText?.text.toString())){
                val principal = Intent(this, PrincipalActivity::class.java)
                startActivity(principal)
                finish()
            }
            else{
                Toast.makeText(this, "Credenciales Inválidas", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun validarEmail(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
    }

    fun validarPassword(password: String): Boolean{
        return password.length >= 6
    }

    fun validarCredenciales(email: String, password: String): Boolean{
        return email == usuarioEmail && password == passwordEmail
    }
}