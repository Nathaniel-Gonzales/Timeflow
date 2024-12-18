package com.example.timeflow;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileInputStream;

public class Pin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

    }

    // Compares user input to registered pin and displays error message if incorrect
    public void verifyPin(View v) {

        // Initializing all input fields , output fields, and variables
        View pinInput = findViewById(R.id.pinInput);
        TextView pinDirections = findViewById(R.id.pinDirections);
        String pinInputString = ((TextView)pinInput).getText().toString();
        int pin = 0;

        // Reads timeflowPin.txt file to find set pin and prepares it for comparison to user input pin in next section
        String fileName = "timeflowPin.txt";
        File filePath = getApplicationContext().getFilesDir();
        File pinFile = new File(filePath, fileName);
        byte[] pinByteArray = new byte[(int) pinFile.length()];

        // Throws error message if something goes wrong with pin file reading
        try {

            FileInputStream pinReadFile = new FileInputStream(pinFile);
            pinReadFile.read(pinByteArray);
            String pinByteArrayToString = new String(pinByteArray);
            pin = Integer.parseInt(pinByteArrayToString);

        } catch (Exception e) {

            pinDirections.setText("Error in file reader.");
            pinDirections.setTextColor(Color.RED);
            return;

        }

        // Throws error message if user input does not match registered pin
        try {

            if(Integer.parseInt(pinInputString) != pin) {

                throw new NumberFormatException();

            }
            else {

                // launches a new activity
                Intent i = new Intent(this, Main.class);
                startActivity(i);

            }

        } catch(NumberFormatException e) {

            pinDirections.setText("Invalid Input. Try Again.");
            pinDirections.setTextColor(Color.RED);
            return;

        }

    }

    // Changes activity to user registration screen
    public void enterPinRegister(View v) {

        Intent i = new Intent(this, PinRegister.class);
        startActivity(i);

    }

}