package com.example.bookarchive;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class DataActivity extends AppCompatActivity {

    private TextView tvArchiveCount, tvEmpty;
    private TableLayout tableBooks;
    private ScrollView scrollViewTable;
    private Button btnPopLatest, btnHome, btnData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvArchiveCount = findViewById(R.id.tvArchiveCount);
        tvEmpty = findViewById(R.id.tvEmpty);
        tableBooks = findViewById(R.id.tableBooks);
        scrollViewTable = findViewById(R.id.scrollViewTable);
        btnPopLatest = findViewById(R.id.btnPopLatest);
        btnHome = findViewById(R.id.btnHome);
        btnData = findViewById(R.id.btnData);

        btnHome.setOnClickListener(v -> finish());

        btnData.setOnClickListener(v -> Toast.makeText(this, "Already on Archive Data screen", Toast.LENGTH_SHORT).show());

        btnPopLatest.setOnClickListener(v -> {
            if (!BookStack.getInstance().isEmpty()) {
                Book popped = BookStack.getInstance().pop();
                Toast.makeText(this, "Popped latest book: " + popped.getTitle(), Toast.LENGTH_SHORT).show();
                updateArchiveUI();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateArchiveUI();
    }

    private void updateArchiveUI() {
        BookStack bookStack = BookStack.getInstance();
        int size = bookStack.size();

        if (size == 0) {
            tvArchiveCount.setText("0 books · newest on top");
            tvEmpty.setVisibility(View.VISIBLE);
            scrollViewTable.setVisibility(View.GONE);
            btnPopLatest.setVisibility(View.GONE);
        } else {
            tvArchiveCount.setText(size + (size == 1 ? " book · newest on top" : " books · newest on top"));
            tvEmpty.setVisibility(View.GONE);
            scrollViewTable.setVisibility(View.VISIBLE);
            btnPopLatest.setVisibility(View.VISIBLE);

            // Remove all dynamic rows (keep index 0 which is the header)
            int childCount = tableBooks.getChildCount();
            if (childCount > 1) {
                tableBooks.removeViews(1, childCount - 1);
            }

            // Add rows top to bottom (newest first - LIFO stack order)
            List<Book> booksTopToBottom = bookStack.getBooksTopToBottom();
            for (Book book : booksTopToBottom) {
                TableRow row = new TableRow(this);
                row.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

                row.addView(createCell(book.getTitle()));
                row.addView(createCell(book.getAuthor()));
                row.addView(createCell(book.getDatePublished()));
                row.addView(createCell(book.getCategory()));

                tableBooks.addView(row);
            }
        }
    }

    private TextView createCell(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setPadding(12, 12, 12, 12);
        tv.setTextColor(getResources().getColor(android.R.color.black, getTheme()));
        return tv;
    }
}
