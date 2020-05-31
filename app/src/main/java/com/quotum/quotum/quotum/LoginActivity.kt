package com.quotum.quotum.quotum

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk.getApplicationContext
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.quotum.quotum.quotum.localdatabase.LocalDB
import com.quotum.quotum.quotum.models.LoginRequestModel
import com.quotum.quotum.quotum.models.LoginResponseModel
import com.quotum.quotum.quotum.network.QuotumClient
import com.quotum.quotum.quotum.ui.instagram.AuthenticationListener
import com.quotum.quotum.quotum.ui.instagram.dialog.AuthenticationDialog
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.util.EntityUtils
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*


public class LoginActivity : AppCompatActivity(), View.OnClickListener, AuthenticationListener {

    private val TAG = LoginActivity::class.java.simpleName

    val CONTEXT: LoginActivity = this@LoginActivity
    var editTextEmail : EditText? = null
    var editTextPassword : EditText? = null

    private var userToken: String? = null

    val loginManager: LoginManager = LoginManager.getInstance()
    val callbackManager: CallbackManager = CallbackManager.Factory.create()

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_login)

        userToken = LocalDB.getUserToken(CONTEXT)

        if (userToken != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val buttonFbParent = findViewById<Button>(R.id.fb)
        val buttonInstaParent = findViewById<Button>(R.id.insta)

        buttonFbParent.setOnClickListener(this)
        buttonInstaParent.setOnClickListener(this)

        setVideoBackground()
        hideSystemUI()

        val bottomSheet = findViewById<LinearLayout>(R.id.bottom_sheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        bottomSheetBehavior.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })

        val textViewLogin = findViewById<TextView>(R.id.text_view_login)
        val view = layoutInflater.inflate(R.layout.login_signup_bottom_sheet, null)

        val dialog = BottomSheetDialog(this)

        textViewLogin.setOnClickListener {
            if (view.parent != null) {
                (view.parent as ViewGroup).removeView(view)
            }
            dialog.setContentView(view)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }

        val relativeLayoutLogin = view.findViewById(R.id.rl_login) as RelativeLayout
        val relativeLayoutSignup = view.findViewById(R.id.rl_signup) as RelativeLayout
        val buttonLoginSwitch = view.findViewById(R.id.login_switch) as Button
        val buttonSignupSwitch = view.findViewById(R.id.signup_switch) as Button
        val buttonLogin = view.findViewById(R.id.loginButton) as Button
        val buttonFb = view.findViewById(R.id.login_fb) as Button
        val buttonInsta = view.findViewById(R.id.login_insta) as Button
        editTextEmail = view.findViewById(R.id.editTextEmail) as EditText
        editTextPassword = view.findViewById(R.id.editTextPassword) as EditText


        buttonFb.setOnClickListener(this)
        buttonInsta.setOnClickListener(this)

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
            loginWithEmailandPassword()
        }
    }

    private fun loginWithEmailandPassword() {
        val email = editTextEmail?.text.toString().trim()
        val password = editTextPassword?.text.toString().trim()

        if(email.isEmpty()){
            return
        }
        if(password.isEmpty()){
            return
        }

        val loginRequestModel = LoginRequestModel();
        loginRequestModel.setEmail(email)
        loginRequestModel.setPassword(password)
        QuotumClient.instance.emailLogin(loginRequestModel)
            .enqueue(object: Callback<LoginResponseModel> {
                override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }
                override fun onResponse(call: Call<LoginResponseModel>, response: Response<LoginResponseModel>) {
                    val loginResponseModel : LoginResponseModel = response.body()!!
                    LocalDB.setUserToken(applicationContext, loginResponseModel.result!!.id)
                    val intent  = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
            })
    }

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

    private fun setVideoBackground() {
        val videoView = findViewById<VideoView>(R.id.videoView)
        val path = "android.resource://" + packageName + "/" + R.raw.beach
        videoView?.setVideoURI(Uri.parse(path))
        videoView.start()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fb, R.id.login_fb -> {
                loginWithFb()            }
            R.id.insta, R.id.login_insta -> {
                loginWithInstagram()
            }
        }
    }

    private fun loginWithInstagram(){
        val authenticationDialog = AuthenticationDialog(this, this)
        authenticationDialog.setCancelable(true)
        authenticationDialog.show()
    }

    private fun loginWithFb(){
        loginManager.logInWithReadPermissions(CONTEXT, listOf("public_profile"))

        loginManager.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    val token = loginResult.accessToken.token
                    LocalDB.setUserToken(CONTEXT, token)
                }

                override fun onCancel() {
                    Log.d(TAG, "Facebook Login is Canceled.")
                }

                override fun onError(error: FacebookException) {
                    Log.d(
                        TAG,
                        Objects.requireNonNull(error.localizedMessage)
                    )
                }
            })

    }

    internal class RequestInstagramAPI(
        var loginActivity: LoginActivity
    ) :
        AsyncTask<Void?, String?, String?>() {

        override fun onPostExecute(response: String?) {
            super.onPostExecute(response)
            if (response != null) {
                try {
                    val jsonObject = JSONObject(response)
                    Log.e("response", jsonObject.toString())
                    val jsonData = jsonObject.getJSONObject("data")
                    if (jsonData.has("id")) {

                        val intent = Intent(loginActivity, MainActivity::class.java)
                        loginActivity.startActivity(intent)
                        loginActivity.finish()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            } else {
                val toast =
                    Toast.makeText(
                        getApplicationContext(),
                        "Something went wrong.",
                        Toast.LENGTH_LONG
                    )
                toast.show()
            }
        }

        override fun doInBackground(vararg params: Void?): String? {
            val httpClient: HttpClient = DefaultHttpClient()
            val httpGet = HttpGet(
                "https://api.instagram.com/v1/users/self/?access_token=${LocalDB.getUserToken(
                    loginActivity
                )}"
            )
            try {
                val response: HttpResponse = httpClient.execute(httpGet)
                val httpEntity: HttpEntity = response.entity
                return EntityUtils.toString(httpEntity)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return null
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onTokenReceived(auth_token: String?) {
        if (auth_token == null) return
        LocalDB.setUserToken(CONTEXT, auth_token)
        RequestInstagramAPI(CONTEXT).execute()
    }
}