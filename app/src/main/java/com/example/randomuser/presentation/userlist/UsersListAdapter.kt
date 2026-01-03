package com.example.randomuser.presentation.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.randomuser.R
import com.example.randomuser.databinding.UserListItemBinding
import com.example.randomuser.presentation.models.UserForList

class UsersListAdapter(
    private var usersList: List<UserForList>,
    private val onListItemClick: (Int) -> Unit,
    private val deleteUser: (Int) -> Unit
) : RecyclerView.Adapter<UsersListAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(UserListItemBinding.
            inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.binding.name.text = usersList[position].name
        holder.binding.telephone.text = usersList[position].telephoneNumber
        holder.binding.nat.text = usersList[position].codedNationality
        Glide.with(holder.itemView.context)
            .load(usersList[position].picture)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.empty_user_image)
            .into(holder.binding.image)
        val flagImageId: Int = when(usersList[position].codedNationality) {
            "AU" -> R.drawable.flag_au
            "BR" -> R.drawable.flag_br
            "CA" -> R.drawable.flag_ca
            "CH" -> R.drawable.flag_ch
            "DE" -> R.drawable.flag_de
            "DK" -> R.drawable.flag_dk
            "ES" -> R.drawable.flag_es
            "FI" -> R.drawable.flag_fi
            "FR" -> R.drawable.flag_fr
            "GB" -> R.drawable.flag_gb
            "IE" -> R.drawable.flag_ie
            "IN" -> R.drawable.flag_in
            "IR" -> R.drawable.flag_ir
            "MX" -> R.drawable.flag_mx
            "NL" -> R.drawable.flag_nl
            "NO" -> R.drawable.flag_no
            "NZ" -> R.drawable.flag_nz
            "RS" -> R.drawable.flag_rs
            "TR" -> R.drawable.flag_tr
            "UA" -> R.drawable.flag_ua
            "US" -> R.drawable.flag_us
            else -> R.drawable.placeholder
        }
        holder.binding.flag.setImageResource(flagImageId)

        holder.binding.userCard.setOnClickListener {
            onListItemClick(usersList[position].id)
        }
        holder.binding.delete.setOnClickListener {
            deleteUser(usersList[position].id)
        }
    }

    override fun getItemCount(): Int {
        return usersList.count()
    }
}