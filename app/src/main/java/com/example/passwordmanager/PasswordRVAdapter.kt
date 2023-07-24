package com.example.passwordmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PasswordRVAdapter(var list:List<PasswordItems>, val passwordItemClickInterface : PasswordItemClickInterface) : RecyclerView.Adapter<PasswordRVAdapter.PasswordViewHolder>() {

    inner class PasswordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTV = itemView.findViewById<TextView>(R.id.idTVName)
        val passwordTV = itemView.findViewById<TextView>(R.id.idTVPassword)
        val deleteTV = itemView.findViewById<ImageView>(R.id.idTVDelete)
    }

    interface PasswordItemClickInterface{
        fun onItemClick(passwordItems: PasswordItems)
        fun openDialog()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PasswordRVAdapter.PasswordViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.password_rv_item, parent, false)
        return PasswordViewHolder(view)
    }

    override fun onBindViewHolder(holder: PasswordRVAdapter.PasswordViewHolder, position: Int) {
        holder.nameTV.text = list.get(position).itemName
        holder.passwordTV.text = list.get(position).itemPassword
        holder.deleteTV.setOnClickListener{
            passwordItemClickInterface.onItemClick(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}