package com.bimalghara.cleanarchitecture.presentation.auth

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bimalghara.cleanarchitecture.databinding.ItemUserBinding
import com.bimalghara.cleanarchitecture.domain.model.auth.AuthData
import com.bimalghara.cleanarchitecture.presentation.base.OnRecyclerViewItemClick

/**
 * Created by BimalGhara
 */

class AllUsersAdapter(
    val context: Context
): RecyclerView.Adapter<AllUsersAdapter.ViewHolder>() {

    //private var listOfAllUsers = mutableListOf<AuthData>()
    private var onItemClickListener: OnRecyclerViewItemClick<AuthData>? = null

    fun setOnItemClickListener(onRecyclerViewItemClick: OnRecyclerViewItemClick<AuthData>){
        this.onItemClickListener = onRecyclerViewItemClick
    }
    /*fun updateData(data: List<AuthData>) {
        listOfAllUsers.clear()
        listOfAllUsers.addAll(data)
        notifyDataSetChanged()
    }*/

    private val differCallback = object : DiffUtil.ItemCallback<AuthData>(){
        override fun areItemsTheSame(oldItem: AuthData, newItem: AuthData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AuthData, newItem: AuthData): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    inner class ViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllUsersAdapter.ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllUsersAdapter.ViewHolder, position: Int) {
        //val item = listOfAllUsers[position]
        val item = differ.currentList[position]

        holder.binding.txtFullName.text = "${item.firstName} ${item.lastName}"

        holder.binding.root.setOnClickListener {
            onItemClickListener?.onItemClick(position, item)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
        //return listOfAllUsers.size
    }



}