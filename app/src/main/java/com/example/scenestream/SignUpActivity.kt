// This file is completed
package com.example.scenestream

import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val name = findViewById<EditText>(R.id.editName)
        val email = findViewById<EditText>(R.id.editEmail)
        val password = findViewById<EditText>(R.id.editPassword)
        val signUpBtn = findViewById<Button>(R.id.btnSignUp)
        val togglePassword = findViewById<ImageView>(R.id.toggleSignUpPassword)

        //  Toggle visibility
        togglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            password.inputType = if (isPasswordVisible)
                InputType.TYPE_CLASS_TEXT
            else
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            password.setSelection(password.text.length)
            togglePassword.setImageResource(
                if (isPasswordVisible) R.drawable.toggle_open
                else R.drawable.toggle_closed
            )
        }

        signUpBtn.setOnClickListener {
            val nameText = name.text.toString()
            val emailText = email.text.toString()
            val passwordText = password.text.toString()

            if (nameText.isNotEmpty() && emailText.isNotEmpty() && passwordText.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val uid = auth.currentUser?.uid
                        val user = hashMapOf("name" to nameText, "email" to emailText)
                        FirebaseFirestore.getInstance().collection("users").document(uid!!).set(user)
                        Toast.makeText(this, "Account created 🎉", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Sign-up failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
