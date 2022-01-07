package com.example.infowisatajogja

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.infowisatajogja.databinding.ActivityLoginBinding
import com.example.infowisatajogja.databinding.ActivityRegisterBinding
import com.example.infowisatajogja.model.ResponseRegister
import com.example.infowisatajogja.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private var binding : ActivityRegisterBinding? = null
    private var nama : String = ""
    private var email : String = ""
    private var pass : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val btnLogRegister : androidx.appcompat.widget.AppCompatButton? = findViewById(R.id.btnLogRegiser)
        btnLogRegister?.setOnClickListener {
            onBackPressed()
        }
        binding!!.btnRegister.setOnClickListener {
            nama = binding!!.userName.text.toString()
            email = binding!!.userEmail.text.toString()
            pass = binding!!.userPassword.text.toString()

            when {
                nama == "" -> {
                    binding!!.userName.error = "Nama tidak boleh kosong!"
                }
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

        api.register(nama, email, pass).enqueue(object : Callback<ResponseRegister> {
            override fun onResponse(
                call: Call<ResponseRegister>,
                response: Response<ResponseRegister>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
    }
}