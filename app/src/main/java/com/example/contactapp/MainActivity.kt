package com.example.contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.contactapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = ContactAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpData(binding)
    }
    private fun setUpData(binding: ActivityMainBinding){
        binding.contactsRv.adapter = adapter
        binding.contactsRv.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_contact_dialog,null)
        builder.setView(view)


        val name = view.findViewById<TextView>(R.id.nameEt)
        val number = view.findViewById<TextView>(R.id.numberEt)
        val saveBtn = view.findViewById<TextView>(R.id.saveBt)

        number.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                saveBtn.isEnabled = s?.length == 11
            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }

        })
        val alertDialog = builder.create()
        saveBtn.setOnClickListener {
            val contact = Contact(name.text.toString(),number.text.toString())
            val contacts = mutableListOf(contact)
            adapter.setupContacts(contacts)
            alertDialog.dismiss()
        }

        binding.fab.setOnClickListener{
            alertDialog.show();
        }


    }
}