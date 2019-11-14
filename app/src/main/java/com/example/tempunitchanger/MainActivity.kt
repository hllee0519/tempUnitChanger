package com.example.tempunitchanger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvResult.setText("Fuck android");
        ArrayAdapter.createFromResource(
            this,
            R.array.tempUnit,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spOriUnit.adapter = adapter
            spTargetUnit.adapter = adapter
        }
        btnChange.setOnClickListener{
            if(etOriTemp.text.toString().equals("")){
                Toast.makeText(this@MainActivity, "Enter the temperature first", Toast.LENGTH_SHORT).show();
                return@setOnClickListener;
            }
                var oriUnit = spOriUnit.getSelectedItem().toString();
                var tarUnit = spTargetUnit.getSelectedItem().toString();
                var ori: Int = etOriTemp.text.toString().toInt();
                if (oriUnit == "Kelvin") {
                    if (tarUnit == "Kelvin") {
                        tvResult.setText(ori.toString());
                    } else if (tarUnit == "Celsius") {
                        tvResult.setText((ori - 273.15).toString());
                    } else if (tarUnit == "Fahrenheit") {
                        tvResult.setText(((ori - 273.15) * 9 / 5 + 32).toString());
                    }
                }else if(oriUnit=="Celsius"){
                    if (tarUnit == "Kelvin") {
                        tvResult.setText((ori+273.15).toString());
                    } else if (tarUnit == "Celsius") {
                        tvResult.setText(ori.toString());
                    } else if (tarUnit == "Fahrenheit") {
                        tvResult.setText((ori * 9 / 5 + 32).toString());
                    }
                }else if(oriUnit=="Fahrenheit"){
                    if (tarUnit == "Kelvin") {
                        tvResult.setText((((ori-32)*5/9)+273.15).toString());
                    } else if (tarUnit == "Celsius") {
                        tvResult.setText(((ori-32)*5/9).toString());
                    } else if (tarUnit == "Fahrenheit") {
                        tvResult.setText((ori).toString());
                    }
                }

        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        spOriUnit.onItemSelectedListener = this
        spTargetUnit.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }


}
