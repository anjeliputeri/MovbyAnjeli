 package com.app.mov.signs.signup

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import com.app.mov.home.HomeActivity
import com.app.mov.R
import com.app.mov.util.Preferences
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.util.*

 class SignUpPhotoscreenActivity : AppCompatActivity(), PermissionListener {

     val REQUEST_IMAGE_CAPTURE = 1
     var statusAdd:Boolean = false
     lateinit var filePath: Uri
     lateinit var signWelcome : TextView
     lateinit var signAdd : ImageView
     lateinit var bSave : Button
     lateinit var photoAdd : ImageView
     lateinit var photoSkip : Button

     lateinit var storage : FirebaseStorage
     lateinit var storageReferensi : StorageReference
     lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_photoscreen)
        signWelcome = findViewById<TextView>(R.id.Twelcome)
        signAdd = findViewById<ImageView>(R.id.addSign)
        bSave = findViewById<Button>(R.id.Bsave)
        photoAdd = findViewById<ImageView>(R.id.photoSign)
        photoSkip = findViewById<Button>(R.id.Bphoto)

        preferences = Preferences(this)
        storage = FirebaseStorage.getInstance()
        storageReferensi = storage.getReference()

        signWelcome.text = "Selamat Datang\n" + intent.getStringExtra("nama")
        signAdd.setOnClickListener{
            if (statusAdd) {
                statusAdd = false
                bSave.visibility = View.VISIBLE
                signAdd.setImageResource(R.drawable.ic_baseline_add_photo)
                photoAdd.setImageResource(R.drawable.ic_photouser)
            }else{
                Dexter.withActivity(this)
                    .withPermission(android.Manifest.permission.CAMERA)
                    .withListener(this)
                    .check()

            }
        }

        photoSkip.setOnClickListener{
            finishAffinity()

            var goHome = Intent(this, HomeActivity::class.java)
            startActivity(goHome)
        }

        bSave.setOnClickListener{
            if (filePath != null) {
                var progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading...")
                progressDialog.show()

                var ref = storageReferensi.child("images/"+UUID.randomUUID().toString())
                ref.putFile(filePath)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        Toast.makeText(this, "Uploaded", Toast.LENGTH_LONG).show()

                        ref.downloadUrl.addOnSuccessListener {
                            preferences.setValues("url", it.toString())
                        }

                        finishAffinity()
                        var goHome = Intent(this, HomeActivity::class.java)
                        startActivity(goHome)
                    }
                    .addOnFailureListener{
                        progressDialog.dismiss()
                        Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
                    }
                    .addOnProgressListener {
                        taskSnapshot -> var progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        progressDialog.setMessage("Upload"+progress.toInt()+" %")
                    }

            }else {

            }
        }


    }

     override fun onPermissionGranted(response: PermissionGrantedResponse?) {
         Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
             takePictureIntent ->
             takePictureIntent.resolveActivity(packageManager)?.also {
                 startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
             }
         }
     }

     override fun onPermissionDenied(response: PermissionDeniedResponse?) {
         Toast.makeText(this, "Anda tidak bisa menambahkan photo Profile", Toast.LENGTH_LONG).show()
     }

     override fun onPermissionRationaleShouldBeShown(
         permission: PermissionRequest?,
         token: PermissionToken?
     ) {
     }

     override fun onBackPressed() {
         Toast.makeText(this, "Klik tombol upload nanti aja", Toast.LENGTH_LONG).show()
     }

     @SuppressLint("MissingSuperCall")     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){
             var bitmap = data?.extras?.get("data") as Bitmap
             statusAdd = true

             filePath = data.getData()!!
             Glide.with(this)
                 .load(bitmap)
                 .apply(RequestOptions.circleCropTransform())
                 .into(signAdd)

             bSave.visibility = View.VISIBLE
             photoAdd.setImageResource(R.drawable.ic_btn_delete)
         }
     }
 }