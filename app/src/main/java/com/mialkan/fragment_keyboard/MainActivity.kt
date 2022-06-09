package com.mialkan.fragment_keyboard

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.mialkan.fragment_keyboard.fragment.InputDialogFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btnOpenInputFragmentWithDelay).setOnClickListener {
            openFragment(true)
        }

        findViewById<View>(R.id.btnOpenInputFragmentWithoutDelay).setOnClickListener {
            openFragment(false)
        }

        findViewById<View>(R.id.btnShowCalendar).setOnClickListener {
            MaterialDatePicker.Builder.datePicker()
                .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
                .build().show(this.supportFragmentManager, MaterialDatePicker::class.java.name)
        }
    }

    private fun openFragment(applyDelay: Boolean = false) {
        InputDialogFragment.newInstance(applyDelay).show(this.supportFragmentManager, InputDialogFragment::class.java.name)
    }
}
