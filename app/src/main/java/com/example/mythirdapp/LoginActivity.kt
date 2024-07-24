package com.example.mythirdapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mythirdapp.CadastroActivity.User

class LoginActivity : AppCompatActivity() {

    val users = mutableListOf<User>()

    private lateinit var cadastroBtn : Button
    private lateinit var loginBtn : Button
    private lateinit var userInput : EditText
    private lateinit var senhaInput : EditText

    private var tentativasNum = 0
    private val MAX_TENTATIVAS = 5
    private val blockTimer = 5000
    private var bloqueioAtivo = false
    private var bloqueioTime : CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        cadastroBtn = findViewById(R.id.cadastroBtnLogin)
        loginBtn = findViewById(R.id.loginBtnLogin)
        userInput = findViewById(R.id.userInputLogin)
        senhaInput = findViewById(R.id.senhaInputLogin)

        cadastroBtn.setOnClickListener {
            val intent = Intent (this@LoginActivity, CadastroActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginBtn.setOnClickListener {
            realizarLogin()
        }

    }

    private fun realizarLogin() {

        // declaração de vals para monitorar aquilo que é digitado no campo de digitação

        val user = userInput.text.toString()
        val senha = senhaInput.text.toString()

        // declaração de vars para monitorar os possíveis erros

        var errorUser = false
        var errorSenha = false

        if (errorSenha) {
            senhaInput.background = ContextCompat.getDrawable(this, R.drawable.campo_error)
            return
        }

        if (errorUser) {
            userInput.background = ContextCompat.getDrawable(this, R.drawable.campo_error)
            return
        }

        // para encontrar o usuário

        val userFinded = CadastroActivity.users.find { it.user == user && it.senha == senha }

        // para tratar casos de erros

        if (user.isEmpty() || senha.isEmpty()) {
            exibirDialogo("Todos os campos de texto devem ser preenchidos.")
        }

        if (user.isEmpty()) {
            errorUser = true
        }

        if (senha.isEmpty()) {
            errorSenha = true
        }

        if (userFinded != null) {
            Toast.makeText(this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show()
            val intent = Intent (this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun exibirDialogo(mensagem: String) {
        AlertDialog.Builder(this)
            .setTitle("ATENÇÃO")
            .setMessage(mensagem)
            .setPositiveButton("OK") { dialog,_ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun bloqueioTemporario() {

        bloqueioAtivo = true
        tentativasNum = 0

        loginBtn.isEnabled = false
        cadastroBtn.isEnabled = false

        bloqueioTime = object : CountDownTimer(blockTimer.toLong(), 1000) {
            override fun onTick
        }



    }
}