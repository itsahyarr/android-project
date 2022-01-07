package com.example.infowisatajogja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.infowisatajogja.databinding.ActivityLoginBinding
import com.example.infowisatajogja.model.ResponseLogin
import com.example.infowisatajogja.network.RetrofitClient
import javax.security.auth.callback.Callback

class LoginActivity : AppCompatActivity() {
    private var binding : ActivityLoginBinding? = null
    private var email : String = ""
    private var pass : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val btnRegLogin: androidx.appcompat.widget.AppCompatButton? = findViewById(R.id.btnRegLogin)
        btnRegLogin?.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
        }

        binding!!.btnLogin.setOnClickListener {
            email = binding!!.userEmail.text.toString()
            pass = binding!!.userPassword.text.toString()

            when {
                email == "" -> {
                    binding!!.userEmail.error = "Email tidak boleh kosong!"
                }
                pass == "" -> {
                    binding!!.userPassword.error = "Password tidak boleh kosong!"
                }
                else -> {
                    binding!!.loadingBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun getData() {
        val api = RetrofitClient().getInstance()
        api.login(email, pass).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.body()?.response == true) {
                    binding!!.loadingBar.visibility = View.GONE
                }
            }
        })
    }
}