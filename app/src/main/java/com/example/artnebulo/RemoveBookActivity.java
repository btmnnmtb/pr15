package com.example.artnebulo;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RemoveBookActivity extends AppCompatActivity {

    private EditText editTextName, editTextAuthor;
    private Button removeButton;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_book);


        removeButton = findViewById(R.id.remove_book);
        editTextName = findViewById(R.id.id_for_remove);

        dbHelper = new DataBaseHelper(this);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeBookToDatabase();
            }
        });
    }

    private void removeBookToDatabase() {
        String bookIdStr = editTextName.getText().toString().trim();

        if (bookIdStr.isEmpty()) {
            Toast.makeText(this, "Введите ID книги", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int bookId = Integer.parseInt(bookIdStr);
            long result = dbHelper.deleteBookById(bookId);

            if (result > 0) {
                Toast.makeText(this, "Книга удалена", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RemoveBookActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Ошибка удаления книги", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Введите корректный ID", Toast.LENGTH_SHORT).show();
        }
    }
}