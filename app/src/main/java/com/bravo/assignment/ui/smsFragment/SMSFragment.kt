package com.bravo.assignment.ui.smsFragment

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SimpleCursorAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bravo.assignment.R
import com.bravo.assignment.backend.SMS
import com.bravo.assignment.databinding.SmsListFragmentBinding

class SMSFragment : Fragment()
{
    lateinit var binding : SmsListFragmentBinding
    lateinit var viewModel : SMSReaderViewModel
    lateinit var adapter : SMSAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        binding = DataBindingUtil.inflate(inflater, R.layout.sms_list_fragment, container, false)
        binding.viewModel = ViewModelProvider(this).get(SMSReaderViewModel::class.java)
        adapter = SMSAdapter()
        binding.smsList.adapter = adapter
        if(ContextCompat.checkSelfPermission(requireContext(), "android.permission.READ_SMS") != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf("android.permission.READ_SMS"), 1)
        }
        else {
            readAndShowSMS()
        }
        return binding.root
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            readAndShowSMS()
        }
    }

    fun readAndShowSMS()
    {
        val smsList = binding.viewModel?.readSMS(requireActivity().contentResolver)
        adapter.submitList(smsList)
    }
}