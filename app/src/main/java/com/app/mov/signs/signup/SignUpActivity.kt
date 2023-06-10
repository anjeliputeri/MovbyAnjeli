 package com.app.mov.signs.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.app.mov.R
import com.app.mov.signs.signin.SignInActivity
import com.app.mov.signs.signin.User
import com.google.firebase.database.*

 class SignUpActivity : AppCompatActivity() {
     lateinit var bSignUp : Button
     lateinit var sUsername : EditText
     lateinit var sPassword : EditText
     lateinit var sNama : EditText
     lateinit var sEmail : EditText
     lateinit var toSignIn :ImageView

     lateinit var mDatabaseReference: DatabaseReference
     lateinit var mFirebaseInstance : FirebaseDatabase
     lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        bSignUp = findViewById<Button>(R.id.BSignUp)
        sUsername = findViewById<EditText>(R.id.Signusername)
        sPassword = findViewById<EditText>(R.id.Signpassword)
        sNama = findViewById<EditText>(R.id.Signnama)
        sEmail = findViewById<EditText>(R.id.Signemail)
        toSignIn = findViewById(R.id.backSignIn)

        toSignIn.setOnClickListener {
            val signIn = Intent(this@SignUpActivity, SignInActivity::class.java)
            startActivity(signIn)
        }

        bSignUp.setOnClickListener {
            val usernameSign = sUsername.text.toString().trim()
            val passwordSign = sPassword.text.toString().trim()
            val namaSign = sNama.text.toString().trim()
            val emailSign = sEmail.text.toString().trim()

            if (usernameSign.isEmpty()){
                sUsername.error = "Silahkan isi username anda"
                sUsername.requestFocus()
                return@setOnClickListener
            }else if (passwordSign.isEmpty()){
                sPassword.error = "Silahkan isi password anda"
                sPassword.requestFocus()
                return@setOnClickListener
            }else if(namaSign.isEmpty()){
                sNama.error = "Silahkan isi nama anda"
                sNama.requestFocus()
                return@setOnClickListener
            }else if(emailSign.isEmpty()){
                sEmail.error = "Silahkan isi email anda"
                sEmail.requestFocus()
                return@setOnClickListener
            } else {
                saveUsername(usernameSign,passwordSign,namaSign,emailSign)
            }
        }
    }

     private fun saveUsername(usernameSign: String, passwordSign: String, namaSign: String, emailSign: String) {
         var user = User()
         user.email = emailSign
         user.username = usernameSign
         user.nama = namaSign
         user.password = passwordSign

         if (usernameSign != null) {
             checkingUsername(usernameSign, user)
         }
     }

     private fun checkingUsername(usernameSign: String, data: User) {
         mDatabaseReference.child(usernameSign).addValueEventListener(object : ValueEventListener{
             override fun onDataChange(dataSnapshot: DataSnapshot) {
                 var user = dataSnapshot.getValue(User::class.java)
                 if (user == null){
                     mDatabaseReference.child(usernameSign).setValue(data)

                     var goSignUpPhotoscreen = Intent(this@SignUpActivity,
                         SignUpPhotoscreenActivity::class.java).putExtra("nama", data.nama)
                     startActivity(goSignUpPhotoscreen)

                 } else {
                     Toast.makeText(
                         this@SignUpActivity, "User sudah digunakan",
                         Toast.LENGTH_LONG).show()
                 }
             }

             override fun onCancelled(databaseError: DatabaseError) {
                 Toast.makeText(
                     this@SignUpActivity, ""+databaseError.message,
                     Toast.LENGTH_LONG).show()
             }

         })
     }
 }
