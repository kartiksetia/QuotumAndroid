package com.quotum.quotum.quotum.ui.instagram

interface AuthenticationListener {
    fun onTokenReceived(auth_token: String?)
}