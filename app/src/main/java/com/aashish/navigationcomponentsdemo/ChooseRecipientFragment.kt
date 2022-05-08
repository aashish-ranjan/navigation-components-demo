package com.aashish.navigationcomponentsdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation

class ChooseRecipientFragment: Fragment(), View.OnClickListener {

    lateinit var navController: NavController
    lateinit var inputRecipent: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_recipient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.next_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener(this)
        inputRecipent = view.findViewById(R.id.input_recipient)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.next_btn -> {
                val recipient = inputRecipent.text
                if (!recipient.isNullOrEmpty()) {
                    val bundle = bundleOf("key_recipient" to recipient.toString())
                    navController.navigate(R.id.action_chooseRecipientFragment_to_specifyAmountFragment, bundle)
                } else {
                    Toast.makeText(context, "Enter a recipient", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.cancel_btn -> activity?.onBackPressed()
        }
    }
}