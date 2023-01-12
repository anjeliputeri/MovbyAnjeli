package com.app.mov.setting

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.app.mov.EditProfileActivity
import com.app.mov.R
import com.app.mov.onboarding.OnboardingTwoActivity
import com.app.mov.util.Preferences
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var preferences: Preferences

    private lateinit var tv_nama: TextView
    private lateinit var tv_email: TextView
    private lateinit var iv_profile: ImageView
    private lateinit var tv_edit: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences  = Preferences(requireActivity().applicationContext)

        tv_nama = requireView().findViewById(R.id.tv_nama)
        tv_email = requireView().findViewById(R.id.tv_gmail)
        iv_profile = requireView().findViewById(R.id.iv_profile)
        tv_edit = requireView().findViewById(R.id.tv_edit)

        tv_nama.text = preferences.getValues("nama")
        tv_email.text = preferences.getValues("email")

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)

        tv_edit.setOnClickListener{
            var goEdit = Intent(context, EditProfileActivity::class.java)
            startActivity(goEdit)
        }


    }
}