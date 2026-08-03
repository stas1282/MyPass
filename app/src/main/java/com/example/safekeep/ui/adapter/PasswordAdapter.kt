package com.example.safekeep.ui.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.safekeep.R
import com.example.safekeep.data.entity.PasswordEntity

class PasswordAdapter(
    private val context: Context,
    private var items: List<PasswordEntity>,
    private val onDelete: (PasswordEntity) -> Unit
) : RecyclerView.Adapter<PasswordAdapter.ViewHolder>() {

    // Все пароли скрыты по умолчанию
    private val hiddenPasswords = mutableSetOf<Long>()

    fun updateList(newItems: List<PasswordEntity>) {
        items = newItems
        hiddenPasswords.clear()
        hiddenPasswords.addAll(newItems.map { it.id })
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvTitle)
        val subtitle: TextView = view.findViewById(R.id.tvSubtitle)
        val btnToggle: ImageButton = view.findViewById(R.id.btnToggle)
        val btnCopy: ImageButton = view.findViewById(R.id.btnCopy)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_password, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title

        val isHidden = hiddenPasswords.contains(item.id)
        val displayPassword = if (isHidden) "••••••••" else item.password

        holder.subtitle.text = when (item.type) {
            "SITE" -> "${item.username} | $displayPassword"
            "APP" -> "${item.username} | $displayPassword"
            "PIN_CODE" -> displayPassword
            else -> displayPassword
        }

        holder.btnToggle.setOnClickListener {
            if (hiddenPasswords.contains(item.id)) hiddenPasswords.remove(item.id)
            else hiddenPasswords.add(item.id)
            notifyItemChanged(position)
        }

        holder.btnCopy.setOnClickListener {
            copyToClipboard(item.password)
        }

        holder.itemView.setOnLongClickListener {
            onDelete(item)
            true
        }
    }

    private fun copyToClipboard(text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("password", text)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            clip.description.extras?.putBoolean("android.content.extra.IS_SENSITIVE", true)
        }
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "✅ Скопировано", Toast.LENGTH_SHORT).show()
    }

    override fun getItemCount() = items.size
}
