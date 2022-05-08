package com.aashish.navigationcomponentsdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import java.math.BigDecimal

class SpecifyAmountFragment: Fragment(), View.OnClickListener {

    lateinit var navController: NavController
    var recipient: String? = null
    lateinit var amountEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipient = arguments?.getString("key_recipient")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.send_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener(this)
        if (!recipient.isNullOrEmpty()) {
            view.findViewById<TextView>(R.id.recipient).text = "Sending Money to $recipient"
        }
        amountEditText = view.findViewById(R.id.input_amount)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.send_btn -> {
                val amount = amountEditText.text
                if (!amount.isNullOrEmpty()) {
                    val bundle = bundleOf(
                        "key_recipient" to recipient.toString(),
                        "key_amount" to Money(BigDecimal(amount.toString()))
                    )
                    navController.navigate(R.id.action_specifyAmountFragment_to_confirmationFragment, bundle)
                } else {
                    Toast.makeText(context, "Enter amount", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.cancel_btn -> activity?.onBackPressed()
        }
    }
}