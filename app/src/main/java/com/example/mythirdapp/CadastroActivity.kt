package com.example.mythirdapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

class CadastroActivity : AppCompatActivity() {

    data class User(val nome: String , val user: String , val senha: String , val senhaConfirm: String)

    companion object {
        val users = mutableListOf<User>()
    }

    private lateinit var cadastroBtn : Button
    private lateinit var nomeInput : EditText
    private lateinit var userInput : EditText
    private lateinit var senhaInput : EditText
    private lateinit var senhaConfirm : EditText
    private lateinit var backArrow : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        cadastroBtn = findViewById(R.id.cadastroBtnCadastro)
        nomeInput = findViewById(R.id.nomeInputCadastro)
        userInput = findViewById(R.id.userInputCadastro)
        senhaInput = findViewById(R.id.senhaInputCadastro)
        senhaConfirm = findViewById(R.id.senhaConfirmInputCadastro)
        backArrow = findViewById(R.id.backArrowCadastro)

        backArrow.setOnClickListener {
            val intent = Intent (this@CadastroActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
       cadastroBtn.setOnClickListener {
            realizarCadastro()
        }

    }

    private fun realizarCadastro () {

        // respectivas vals para busca de campos de digitação

        val nome = nomeInput.text.toString()
        val user = userInput.text.toString()
        val senha = senhaInput.text.toString()
        val senhaConfirmed = senhaConfirm.text.toString()

        // declara as respectivas vars para alternância de cor de campo de texto

        var errorSenha = false
        var errorNome = false
        var errorUser = false

        // Para mudar a cor de cada borada de campo de texto de acordo com os erros específicos

        if (errorSenha) {
            senhaInput.background = ContextCompat.getDrawable(this, R.drawable.campo_error)
            senhaConfirm.background = ContextCompat.getDrawable(this, R.drawable.campo_error)
            return
        }

        if (errorNome) {
            nomeInput.background = ContextCompat.getDrawable(this, R.drawable.campo_error)
            return
        }

        if (errorUser) {
            userInput.background = ContextCompat.getDrawable(this, R.drawable.campo_error)
            return
        }

        // estabelecimento de padrão de senha

        val senhaRegex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%&*();;~]).{6,}\$")

        // SE o campo de texto de senha não conter os pré-requisitos de senhaRegex , uma caixa de diálogo será exibida

        if (!senhaRegex.matches(senha)) {
            exibirDialogo("A senha deve conter no mínimo 6 caractéres; pelo menos uma letra maiúscula; pelo menos uma letra minúscula; pelo menos um número; pelo menos um caractére especial.")
            errorSenha = true
        }

        // para tratar cada campo que possa estar vazio

        if (nome.isEmpty()) {
            errorNome = true
        }

        if (user.isEmpty()) {
            errorUser = true
        }

        if (senha.isEmpty()) {
            errorSenha = true
        }

        if (senha.isEmpty() && nome.isEmpty() && user.isEmpty() && senhaConfirmed.isEmpty()) {
           exibirDialogo("Todos os campos devem ser preenchidos.")
        }

        // definição de acesso fixo (admin)

        val acessoAdmin = User("Kristyson Alpino", "krisalpino03" ,"krisAlpin0@03", "krisAlpin0@03")
        users.add(acessoAdmin)

        // definição de acesso global (criação de novos acessos)

        val newUser = User(nome, user, senha, senhaConfirmed)
        users.add(newUser)

        // troca de tela após cadastro

        Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()

        val intent = Intent (this@CadastroActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()

    }
    private fun exibirDialogo(mensagem: String) {
        AlertDialog.Builder(this)
            .setTitle("ATENÇÃO")
            .setMessage(mensagem)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}