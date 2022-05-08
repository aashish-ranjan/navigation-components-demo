package com.aashish.navigationcomponentsdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.math.BigDecimal

class ConfirmationFragment: Fragment() {

    var recipient: String? = null
    var amount: BigDecimal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipient = arguments?.getString("key_recipient") ?: "None"
        amount = arguments?.getParcelable<Money?>("key_amount")?.amount ?: BigDecimal(0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.confirmation_message).text = "$amount sent to $recipient"
    }
}