package com.bravo.assignment.ui.smsFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bravo.assignment.backend.SMS
import com.bravo.assignment.databinding.SmsRowBinding

class SMSAdapter : ListAdapter<SMS, SMSAdapter.ViewHolder>(ItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(val binding : SmsRowBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(sms: SMS)
        {
            binding.address.text = sms.address
            binding.body.text = sms.body
            binding.date.text = sms.date.toString()
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder
            {
                return ViewHolder(SmsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    class ItemCallback : DiffUtil.ItemCallback<SMS>()
    {
        override fun areItemsTheSame(oldItem: SMS, newItem: SMS): Boolean {
            return oldItem === newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: SMS, newItem: SMS): Boolean {
            return oldItem == newItem
        }
    }
}