package com.example.infowisatajogja

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.infowisatajogja.databinding.ActivityLoginBinding
import com.example.infowisatajogja.model.ResponseLogin
import com.example.infowisatajogja.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
                    getData()
                }
            }
        }
    }

    private fun getData() {
        val api = RetrofitClient().getInstance()
        api.login(email, pass).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.isSuccessful) {
                    if (response.body()?.response == true) {
                        binding!!.loadingBar.visibility = View.GONE
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    } else {
                        binding!!.loadingBar.visibility = View.GONE
                        Toast.makeText(
                            this@LoginActivity,
                            "Login gagal, username/password salah",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    binding!!.loadingBar.visibility = View.GONE
                    Toast.makeText(
                        this@LoginActivity,
                        "Login gagal, terjadi kesalahan",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.e("pesan error", "${t.message}")
            }
        })
    }
}