package com.prabhakaran.bookstore.ui.fragment.profile

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.prabhakaran.bookstore.R
import com.prabhakaran.bookstore.data.local.prefrences.AppPreferences
import com.prabhakaran.bookstore.databinding.FragmentNotificationsBinding
import com.prabhakaran.bookstore.databinding.ProfileFragmentBinding
import com.prabhakaran.bookstore.databinding.WishListFragmentBinding
import com.prabhakaran.bookstore.model.WishList
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var preferences: AppPreferences


    private lateinit var viewModel: ProfileViewModel

    private lateinit var binding: ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         binding = ProfileFragmentBinding.inflate(layoutInflater)
        preferences =
                AppPreferences(requireContext())

         setLayout()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }


    private fun  setLayout(){

     loadPreferences()

       binding.btnUpdate.setOnClickListener {

      if(binding.edtUsername.text.toString().isEmpty()){   showAlert("Profile name should not be empty")}
      else if(binding.edtEmail.text.toString().isEmpty()){  showAlert("Email Id should not be empty")}
      else if(!(binding.edtEmail.text.toString().isEmailValid())){  showAlert("Please enter a valid email id")}
      else if(binding.edtPhone.text.toString().isEmpty()){  showAlert("Phone Number should not be empty")}
      else if(binding.edtAddress.text.toString().isEmpty()){  showAlert("Address should not be empty")}
           else{
               saveToPreferences()
           }

       }

    }

    private fun loadPreferences(){

        preferences.userName.asLiveData().observe(viewLifecycleOwner, Observer {value ->

            binding.edtUsername.setText(value)

        })

        preferences.email.asLiveData().observe(viewLifecycleOwner, Observer {value ->

            binding.edtEmail.setText(value)

        })

        preferences.phone.asLiveData().observe(viewLifecycleOwner, Observer {value ->

            binding.edtPhone.setText(value)

        })

        preferences.address.asLiveData().observe(viewLifecycleOwner, Observer {value ->

            binding.edtAddress.setText(value)

        })


    }

    private fun saveToPreferences(){

        lifecycleScope.launch {
            preferences.saveUserName(binding.edtUsername.text.toString())
        }

        lifecycleScope.launch {
            preferences.saveEmail(binding.edtEmail.text.toString())
        }

        lifecycleScope.launch {
            preferences.savePhonenumber(binding.edtPhone.text.toString())
        }

        lifecycleScope.launch {
            preferences.saveAddress(binding.edtAddress.text.toString())
        }

        showAlert("Profile details saved successfully.")


    }


    private fun showAlert(string: String){

        val builder = AlertDialog.Builder(context)
        builder.setTitle(string)
        builder.setPositiveButton("Okay") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()

    }


    fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }



}