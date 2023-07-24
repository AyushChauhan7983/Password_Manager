package com.example.passwordmanager

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), PasswordRVAdapter.PasswordItemClickInterface {

    lateinit var itemsRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var list: List<PasswordItems>
    lateinit var passwordRVAdapter: PasswordRVAdapter
    lateinit var passwordViewModal: PasswordViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        itemsRV = findViewById(R.id.idRVItems)
        addFAB = findViewById(R.id.idFABAdd)
        list = ArrayList<PasswordItems>()
        passwordRVAdapter = PasswordRVAdapter(list, this)
        itemsRV.layoutManager = LinearLayoutManager(this)
        itemsRV.adapter = passwordRVAdapter
        val passwordRepository = PasswordRepository(PasswordDatabase(this))
        val factory = PasswordViewModalFactory(passwordRepository)
        passwordViewModal = ViewModelProvider(this, factory).get(PasswordViewModal::class.java)
        passwordViewModal.getAllGroceryItems().observe(this, Observer {
            passwordRVAdapter.list = it
            passwordRVAdapter.notifyDataSetChanged()
        })

        addFAB.setOnClickListener {
            openDialog()
        }
    }

    override fun openDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.password_add_dialog)
        val cancelBtn = dialog.findViewById<Button>(R.id.idBtnCancel)
        val addBtn = dialog.findViewById<Button>(R.id.idBtnAdd)
        val itemEdt = dialog.findViewById<EditText>(R.id.idEdtItemName)
        val itemPasswordEdt = dialog.findViewById<EditText>(R.id.idEdtItemPassword)
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        addBtn.setOnClickListener {
            val itemName: String = itemEdt.text.toString()
            val itemPassword: String = itemPasswordEdt.text.toString()
            if(itemName.isEmpty() && itemPassword.isEmpty())
            {
                Toast.makeText(applicationContext,"Please enter the name and password", Toast.LENGTH_SHORT).show()
            }
            else if(itemName.isNotEmpty() && itemPassword.isEmpty())
            {
                Toast.makeText(applicationContext,"Please enter the password", Toast.LENGTH_SHORT).show()
            }
            else if(itemName.isEmpty() && itemPassword.isNotEmpty())
            {
                Toast.makeText(applicationContext,"Please enter the name", Toast.LENGTH_SHORT).show()
            }
            else {
                val items = PasswordItems(itemName, itemPassword)
                passwordViewModal.insert(items)
                Toast.makeText(applicationContext, "Inserted..", Toast.LENGTH_SHORT).show()
                passwordRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }

        }
        dialog.show()
    }

    override fun onItemClick(passwordItems: PasswordItems) {
        passwordViewModal.delete(passwordItems)
        passwordRVAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext, "Deleted successfully", Toast.LENGTH_SHORT).show()
    }

}