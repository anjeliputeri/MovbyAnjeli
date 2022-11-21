package com.app.mov.signs.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.mov.home.HomeActivity
import com.app.mov.R
import com.app.mov.signs.signup.SignUpActivity
import com.app.mov.util.Preferences
import com.google.firebase.database.*


class SignInActivity : AppCompatActivity() {
    lateinit var Blanjutin: Button
    lateinit var Eusername: EditText
    lateinit var Epasword: EditText
    lateinit var Bdaftar: Button

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var preference: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("User")
        preference = Preferences(this)

        preference.setValues("onboarding", "1")
        if (preference.getValues("status").equals("1")){
            finishAffinity()

            var goHome = Intent(this, HomeActivity::class.java)
            startActivity(goHome)
        }

        Blanjutin = findViewById<Button>(R.id.LanjutkanSignIn)
        Eusername = findViewById<EditText>(R.id.Eusername)
        Epasword = findViewById<EditText>(R.id.Epassword)
        Bdaftar = findViewById<Button>(R.id.BLewati)

        Blanjutin.setOnClickListener {
            val username = Eusername.text.toString().trim()
            val password = Epasword.text.toString().trim()

            if (username.isEmpty()){
                Eusername.error = "Silahkan masukkan username anda"
                Eusername.requestFocus()
                return@setOnClickListener
            }else if (password.isEmpty()){
                Epasword.error = "Silahkan masukkan password anda"
                Epasword.requestFocus()
                return@setOnClickListener
            } else {
                pushLogin(username, password)
            }
        }

        Bdaftar.setOnClickListener {
            var goSignUp = Intent(this, SignUpActivity::class.java)
            startActivity(goSignUp)
        }
    }

    private fun pushLogin(username: String, password: String) {
        mDatabaseReference.child(username).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(
                        this@SignInActivity, "User tidak ditemukan",
                        Toast.LENGTH_LONG).show()
                } else {

                    if (user.password.equals(password)) {

                        preference.setValues("nama", user.nama.toString())
                        preference.setValues("user", user.username.toString())
                        preference.setValues("url", user.url.toString())
                        preference.setValues("email", user.email.toString())
                        preference.setValues("saldo", user.saldo.toString())
                        preference.setValues("status", "1")

                        var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@SignInActivity, "Password anda salah",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@SignInActivity, databaseError.message,
                    Toast.LENGTH_LONG).show()
            }
        })
    }
}

