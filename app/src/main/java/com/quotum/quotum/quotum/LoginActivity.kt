package com.quotum.quotum.quotum

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)
        setVideoBackground()
        hideSystemUI()

        val bottomSheet = findViewById<LinearLayout>(R.id.bottom_sheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {

                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {

                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })

        val textViewLogin = findViewById(R.id.text_view_login) as TextView
        val view = layoutInflater.inflate(R.layout.login_signup_bottom_sheet, null)
        val dialog = BottomSheetDialog(this)
        textViewLogin.setOnClickListener {
            if (view.getParent() != null) {
                (view.getParent() as ViewGroup).removeView(view)
            }
            dialog.setContentView(view)
            dialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

        }

        val relativeLayoutLogin = view.findViewById(R.id.rl_login) as RelativeLayout
        val relativeLayoutSignup = view.findViewById(R.id.rl_signup) as RelativeLayout
        val buttonLoginSwitch = view.findViewById(R.id.login_switch) as Button
        val buttonSignupSwitch = view.findViewById(R.id.signup_switch) as Button
        val buttonLogin = view.findViewById(R.id.loginButton) as Button
        relativeLayoutSignup.visibility = View.GONE
        buttonLoginSwitch.setOnClickListener {
            buttonLoginSwitch.setBackgroundResource(R.drawable.login_background)
            buttonSignupSwitch.setBackgroundResource(R.drawable.login_btton_unpressed_background)
            relativeLayoutLogin.visibility = View.VISIBLE
            relativeLayoutSignup.visibility = View.GONE
        }

        buttonSignupSwitch.setOnClickListener {
            buttonLoginSwitch.setBackgroundResource(R.drawable.login_btton_unpressed_background)
            buttonSignupSwitch.setBackgroundResource(R.drawable.login_background)
            relativeLayoutLogin.visibility = View.GONE
            relativeLayoutSignup.visibility = View.VISIBLE
        }

        buttonLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent);
        }

    }

    private fun setVideoBackground(){
        val videoView = findViewById<VideoView>(R.id.videoView)
        val path = "android.resource://" + packageName + "/" + R.raw.beach
        videoView?.setVideoURI(Uri.parse(path))
        videoView.start()
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private fun hideSystemUI() {
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}
