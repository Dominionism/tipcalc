package edu.uw.ischool.cainglet.tipcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var valueInput: EditText
    private lateinit var submit: Button
    private lateinit var toast: Toast

    @SuppressLint("MissingInflatedId", "SetTextI18n", "DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        valueInput = findViewById(R.id.text_primary)
        submit = findViewById(R.id.btn_primary)
        submit.isEnabled = false
        var output: String
        var displayed: Double
        var returned: String

        submit.setOnClickListener {
            output = valueInput.text.toString()
            displayed = output.toDouble() * 0.15 + output.toDouble()
            returned = String.format("%.2f", displayed)
            toast = Toast.makeText(this, "$$returned", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 100)
            toast.show()
        }

        valueInput.addTextChangedListener(textWatcher)
    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        @SuppressLint("SetTextI18n")
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val textValue = s.toString()
            val parts = textValue.split(".")

            if (parts.size > 2 || (parts.size == 2 && parts[1].length > 2)) {
                valueInput.setText(parts[0] + (if (parts.size == 2) "." + parts[1].take(2) else ""))
                valueInput.setSelection(valueInput.text.length)
            }

            submit.isEnabled = textValue.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable) {}
    }
}
