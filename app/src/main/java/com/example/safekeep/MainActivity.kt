
package com.example.safekeep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.safekeep.databinding.ActivityMainBinding
import java.lang.Package

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    
    private val binding: ActivityMainBinding
      get() = checkNotNull(_binding) { "Activity has been destroyed" }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate and get instance of binding
        _binding = ActivityMainBinding.inflate(layoutInflater)

        // set content view to binding's root
        setContentView(binding.root)
       binding.btnPasswords.setOnClickListener {
    val intent = Intent(this, PasswordActivity::class.java)
    startActivity(intent)
}

       binding.btnSettings.setOnClickListener {
    // Пока ничего не делаем
}
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
