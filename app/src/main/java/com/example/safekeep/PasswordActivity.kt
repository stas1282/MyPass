package com.example.safekeep

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.safekeep.data.entity.PasswordEntity
import com.example.safekeep.ui.adapter.PasswordAdapter
import com.example.safekeep.ui.viewmodel.PasswordViewModel
import com.google.android.material.tabs.TabLayout

class PasswordActivity : AppCompatActivity() {
    private lateinit var viewModel: PasswordViewModel
    private lateinit var adapter: PasswordAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: TextView
    private lateinit var tabLayout: TabLayout

    private var currentType = "SITE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        viewModel = ViewModelProvider(this)[PasswordViewModel::class.java]

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        tabLayout = findViewById(R.id.tabLayout)
        recyclerView = findViewById(R.id.recyclerView)
        emptyView = findViewById(R.id.emptyView)
        val fabAdd = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fabAdd)

        adapter = PasswordAdapter(this, emptyList()) { entity ->
            AlertDialog.Builder(this)
                .setTitle("Удалить?")
                .setMessage("Удалить «${entity.title}»?")
                .setPositiveButton("Да") { _, _ -> viewModel.delete(entity) }
                .setNegativeButton("Нет", null)
                .show()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.passwords.observe(this) { list ->
            adapter.updateList(list)
            emptyView.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                currentType = when (tab?.position) {
                    0 -> "SITE"
                    1 -> "APP"
                    2 -> "PIN_CODE"
                    else -> "SITE"
                }
                viewModel.setType(currentType)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        fabAdd.setOnClickListener {
            showAddDialog()
        }
    }

    private fun showAddDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_password, null)
        val titleInput = dialogView.findViewById<EditText>(R.id.etTitle)
        val usernameInput = dialogView.findViewById<EditText>(R.id.etUsername)
        val passwordInput = dialogView.findViewById<EditText>(R.id.etPassword)
        val urlInput = dialogView.findViewById<EditText>(R.id.etUrl)
        val notesInput = dialogView.findViewById<EditText>(R.id.etNotes)
        val usernameLayout = dialogView.findViewById<LinearLayout>(R.id.layoutUsername)
        val urlLayout = dialogView.findViewById<LinearLayout>(R.id.layoutUrl)

        // Скрываем поля в зависимости от типа
        when (currentType) {
            "PIN_CODE" -> {
                usernameLayout.visibility = View.GONE
                urlLayout.visibility = View.GONE
                titleInput.hint = "Название (например, Карта Мир)"
                passwordInput.hint = "Пин-код"
            }
            "APP" -> {
                urlLayout.visibility = View.GONE
                titleInput.hint = "Название приложения"
            }
            else -> {
                titleInput.hint = "Название сайта"
            }
        }

        AlertDialog.Builder(this)
            .setTitle("Добавить")
            .setView(dialogView)
            .setPositiveButton("Сохранить") { _, _ ->
                val title = titleInput.text.toString()
                val password = passwordInput.text.toString()
                if (title.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.insert(
                        title = title,
                        username = usernameInput.text.toString(),
                        password = password,
                        url = urlInput.text.toString(),
                        notes = notesInput.text.toString(),
                        type = currentType
                    )
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }
}
