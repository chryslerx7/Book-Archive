package com.example.bookarchive;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText etBookName, etAuthor, etDatePublished;
    private Spinner spCategory;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etBookName = findViewById(R.id.etBookName);
        etAuthor = findViewById(R.id.etAuthor);
        etDatePublished = findViewById(R.id.etDatePublished);
        spCategory = findViewById(R.id.spCategory);
        btnSave = findViewById(R.id.btnSave);
        Button btnHome = findViewById(R.id.btnHome);
        Button btnData = findViewById(R.id.btnData);

        // Setup Spinner categories
        String[] categories = {"Fiction", "Non-Fiction", "Science & Tech", "History", "Biography", "Fantasy"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);

        // Date Picker Dialog
        etDatePublished.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String dateStr = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);
                        etDatePublished.setText(dateStr);
                    }, year, month, day);
            datePickerDialog.show();
        });

        // Save to Archive (Push operation)
        btnSave.setOnClickListener(v -> {
            String title = etBookName.getText().toString().trim();
            String author = etAuthor.getText().toString().trim();
            String date = etDatePublished.getText().toString().trim();
            String category = spCategory.getSelectedItem().toString();

            // Validation: Notification toast when fields are empty
            if (title.isEmpty() || author.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Please fill in all input fields before saving!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create book object and push to stack
            Book newBook = new Book(title, author, date, category);
            BookStack.getInstance().push(newBook);

            Toast.makeText(this, "Book pushed to Stack archive!", Toast.LENGTH_SHORT).show();

            // Clear input fields
            etBookName.setText("");
            etAuthor.setText("");
            etDatePublished.setText("");
            spCategory.setSelection(0);

            // Navigate to DataActivity to view stack contents
            Intent intent = new Intent(MainActivity.this, DataActivity.class);
            startActivity(intent);
        });

        btnData.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DataActivity.class);
            startActivity(intent);
        });

        btnHome.setOnClickListener(v -> {
            Toast.makeText(this, "Already on Add Book screen", Toast.LENGTH_SHORT).show();
        });
    }
}
