package com.mialkan.fragment_keyboard.fragment

import android.content.Context
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import com.mialkan.fragment_keyboard.databinding.FragmentInputDialogBinding

private const val ARG_APPLY_DELAY = "ARG_APPLY_DELAY"

class InputDialogFragment : DialogFragment() {

    private lateinit var viewBinding: FragmentInputDialogBinding
    private var applyDelay: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            applyDelay = it.getBoolean(ARG_APPLY_DELAY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentInputDialogBinding.inflate(inflater, container, false)

        viewBinding.requestFocus.setOnClickListener { _ ->
            requestFocusAndShowKeyboard(viewBinding.editText)
        }
        requestFocusAndShowKeyboard(viewBinding.editText)
        return viewBinding.root
    }

    private fun requestFocusAndShowKeyboard(view: View) {
        view.requestFocus()
        view.postDelayed({
            val inputMethodManager =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val result = inputMethodManager.showSoftInput(
                view,
                InputMethodManager.SHOW_IMPLICIT,
                object : ResultReceiver(null) {
                    override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
                        Log.e("onReceiveResult result", resultCode.toString())
                    }
                }
            )
            Log.e("Popup result", result.toString())
        }, if (applyDelay) { 100 } else { 0 })
    }

    companion object {
        @JvmStatic
        fun newInstance(applyDelay: Boolean) =
            InputDialogFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_APPLY_DELAY, applyDelay)
                }
            }
    }
}
