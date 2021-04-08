package com.prabhakaran.bookstore.ui.fragment.orderplacing

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prabhakaran.bookstore.ui.activity.MainActivity
import com.prabhakaran.bookstore.constant.Constant
import com.prabhakaran.bookstore.ui.adapter.CartItemAdapterLinear
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.prabhakaran.bookstore.R
import com.prabhakaran.bookstore.databinding.OrderPlacingFragmentBinding
import com.prabhakaran.bookstore.model.CartItem
import com.prabhakaran.bookstore.ui.fragment.cart.CartViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class OrderPlacingFragment : Fragment() {


      lateinit var  binding : OrderPlacingFragmentBinding

    private val viewModel: OrderPlacingViewModel by viewModel<OrderPlacingViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = OrderPlacingFragmentBinding.inflate(layoutInflater)

        viewModel.cartItems.observe(viewLifecycleOwner, Observer {


            val adapter = context?.let { context ->

                CartItemAdapterLinear(false,it,context) { product: CartItem, i: Int ->

                }
            }

            binding.cartList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            binding.cartList.adapter = adapter

            calculatePrice(it)



        })


        binding.seekbar.setProgress(10)

        binding.deliverHere.setOnClickListener {

            binding.address.visibility=(View.GONE)
            binding.orderSummary.visibility=(View.VISIBLE)

            binding.seekbar.progress = (48)

            clearBg()
            binding.textOrderSummary.background = resources.getDrawable(R.drawable.round_outlined)

        }

        binding.continueButton.setOnClickListener {

            binding.orderSummary.visibility=(View.GONE)
            binding.payment.visibility=(View.VISIBLE)
            binding.linearlayout2.visibility=(View.VISIBLE)
            binding.seekbar.progress = (100)

            clearBg()
            binding.textPayment.background = resources.getDrawable(R.drawable.round_outlined)


        }


        binding.textAddress.setOnClickListener {

            binding.orderSummary.visibility=(View.GONE)
            binding.payment.visibility=(View.GONE)
            binding.linearlayout2.visibility=(View.GONE)
            binding.address.visibility=(View.VISIBLE)

            binding.seekbar.setProgress(10)


            clearBg()
            binding.textAddress.background = resources.getDrawable(R.drawable.round_outlined)


        }

        binding.textOrderSummary.setOnClickListener {
            binding.address.visibility=(View.GONE)
            binding.payment.visibility=(View.GONE)
            binding.linearlayout2.visibility=(View.GONE)

            binding.orderSummary.visibility=(View.VISIBLE)

            binding.seekbar.progress = (48)


            clearBg()
            binding.textOrderSummary.background = resources.getDrawable(R.drawable.round_outlined)

        }

        binding.textPayment.setOnClickListener {

            binding.address.visibility=(View.GONE)
            binding.orderSummary.visibility=(View.GONE)
            binding.payment.visibility=(View.VISIBLE)
            binding.linearlayout2.visibility=(View.VISIBLE)
            binding.seekbar.progress = (100)


            clearBg()
            binding.textPayment.background = resources.getDrawable(R.drawable.round_outlined)

        }

        binding.seekbar.setOnTouchListener({ v, event -> true })

        binding.addAddress.setOnClickListener {
            val mBottomSheetDialog = activity?.let { it1 -> BottomSheetDialog(it1) }
            val sheetView: View? =
               activity?.layoutInflater?.inflate(R.layout.address_bottom_sheet, null)
            if (sheetView != null) {
                mBottomSheetDialog?.setContentView(sheetView)
            }

            val dialog_title = sheetView?.findViewById<TextView>(R.id.dialog_title)
            dialog_title?.setText("Add Address")

            mBottomSheetDialog?.show()
        }

        binding.editAddress.setOnClickListener {

            val mBottomSheetDialog = activity?.let { it1 -> BottomSheetDialog(it1) }
            val sheetView: View? =
                activity?.layoutInflater?.inflate(R.layout.address_bottom_sheet, null)
            if (sheetView != null) {
                mBottomSheetDialog?.setContentView(sheetView)
            }

            val dialog_title = sheetView?.findViewById<TextView>(R.id.dialog_title)
            dialog_title?.setText("Edit Address")

            val edt_address = sheetView?.findViewById<EditText>(R.id.edt_address)
            edt_address?.setText("8,Al Ghurair properties, Deira, Dubai")

            mBottomSheetDialog?.show()

        }

        binding.changeAddAddress.setOnClickListener {

            binding.orderSummary.visibility=(View.GONE)
            binding.payment.visibility=(View.GONE)
            binding.linearlayout2.visibility=(View.GONE)
            binding.address.visibility=(View.VISIBLE)
            binding.seekbar.setProgress(10)

            clearBg()
            binding.textAddress.background = resources.getDrawable(R.drawable.round_outlined)

        }

        binding.continuePayment.setOnClickListener {
            showDialog()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideBottomNav()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun hideBottomNav(){
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        navBar.visibility=View.GONE
    }

    private fun showDialog(){

        val mDialogView = LayoutInflater.from(context).inflate(R.layout.success_dialog, null)
        val messageBoxBuilder = AlertDialog.Builder(activity).setView(mDialogView)
        val  messageBoxInstance = messageBoxBuilder.show()
        val btDismiss = mDialogView.findViewById<Button>(R.id.btn_done)
        btDismiss.setOnClickListener {
            messageBoxInstance.dismiss()

            viewModel.deleteAll()

            val intent = Intent (activity, MainActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()

        }

    }


    private fun clearBg(){

         binding.textAddress.background = null

         binding.textOrderSummary.background = null

         binding.textPayment.background = null

    }


    private fun calculatePrice(list : List<CartItem>) {

        var total = 0.0

        list.forEach {
            total += it.product?.price ?: 0.0
        }
        binding.tvCartPrice1.text = "AED $total"
        binding.tvCartPrice2.text = "AED $total"
        binding.tvCartPrice3.text = "AED $total"
        binding.tvCartPrice4.text = "AED $total"

    }

}