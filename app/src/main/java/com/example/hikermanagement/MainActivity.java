package com.example.hikermanagement;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputEditText editTextHikeName, editTextLocation, editTextDate, editTextParking, editTextLength, editTextDifficulty, editTextDescription, editTextCustom1, editTextCustom2;
    TextInputLayout layoutHikeName, layoutLocation, layoutDate, layoutParking, layoutLength, layoutDifficulty, layoutDescription, layoutCustom1, layoutCustom2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextHikeName = findViewById(R.id.editTextHikeName);
        editTextLocation = findViewById(R.id.editTextLocation);
        editTextDate = findViewById(R.id.editTextDate);
        editTextParking = findViewById(R.id.editTextParking);
        editTextLength = findViewById(R.id.editTextLength);
        editTextDifficulty = findViewById(R.id.editTextDifficulty);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextCustom1 = findViewById(R.id.editTextCustom1);
        editTextCustom2 = findViewById(R.id.editTextCustom2);

        layoutHikeName = findViewById(R.id.layoutHikeName);
        layoutLocation = findViewById(R.id.layoutLocation);
        layoutDate = findViewById(R.id.layoutDate);
        layoutParking = findViewById(R.id.layoutParking);
        layoutLength = findViewById(R.id.layoutLength);
        layoutDifficulty = findViewById(R.id.layoutDifficulty);
        layoutDescription = findViewById(R.id.layoutDescription);
        layoutCustom1 = findViewById(R.id.layoutCustom1);
        layoutCustom2 = findViewById(R.id.layoutCustom2);

        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndSubmit();
            }
        });
    }

    private void validateAndSubmit() {
        boolean allFieldsValid = validateAllFields();

        if (allFieldsValid) {
            // All required fields are filled, display confirmation
            displayConfirmation();
        } else {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateAllFields() {
        boolean allFieldsValid = true;

        allFieldsValid &= validateField(layoutHikeName);
        allFieldsValid &= validateField(layoutLocation);
        allFieldsValid &= validateField(layoutDate);
        allFieldsValid &= validateField(layoutParking);
        allFieldsValid &= validateField(layoutLength);
        allFieldsValid &= validateField(layoutDifficulty);

        allFieldsValid &= validateField(layoutDescription);
        allFieldsValid &= validateField(layoutCustom1);
        allFieldsValid &= validateField(layoutCustom2);

        return allFieldsValid;
    }

    private boolean validateField(TextInputLayout layout) {
        String text = layout.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(text)) {
            layout.setError("This field is required");
            return false;
        } else {
            layout.setError(null);
            return true;
        }
    }

    private void displayConfirmation() {
        // Get values from EditText fields
        String hikeName = editTextHikeName.getText().toString().trim();
        String location = editTextLocation.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();
        String parking = editTextParking.getText().toString().trim();
        String length = editTextLength.getText().toString().trim();
        String difficulty = editTextDifficulty.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String custom1 = editTextCustom1.getText().toString().trim();
        String custom2 = editTextCustom2.getText().toString().trim();

        // Create a confirmation dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Name: " + hikeName + "\nLocation: " + location + "\nDate: " + date +
                        "\nParking: " + parking + "\nLength: " + length + "\nDifficulty: " + difficulty +
                        "\nDescription: " + description + "\nCustom 1: " + custom1 + "\nCustom 2: " + custom2)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle submission (e.g., save to database)
                        Toast.makeText(MainActivity.this, "Details submitted!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User wants to edit details, do nothing or handle accordingly
                    }
                })
                .show();
    }
}
