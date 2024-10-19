package com.example.flashly.extension

import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import com.example.flashly.R

object ExtensionFunctions {

    fun String.isValidEmail(): Boolean {
        // Regular expression for email pattern
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return this.matches(Regex(emailPattern))
    }

    // Extension function to toggle password visibility
    fun EditText.togglePasswordVisibility(eyeIcon: ImageView, isPasswordVisible: Boolean): Boolean {
        return if (isPasswordVisible) {
            // If the password is visible, hide it
            this.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            eyeIcon.setImageResource(R.drawable.eye_closed) // Change to closed eye icon
            false // Return new state (password hidden)
        } else {
            // If the password is hidden, show it
            this.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            eyeIcon.setImageResource(R.drawable.eye_open) // Change to open eye icon
            true // Return new state (password visible)
        }.also {
            // Move the cursor to the end of the text
            this.setSelection(this.text.length)
        }
    }
}