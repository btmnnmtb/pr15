package com.example.artnebulo;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class editBook extends AppCompatActivity {

    private EditText editTextName, editTextAuthor , editId;
    private Button editButton;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_book);

        editTextName = findViewById(R.id.editTextName);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        editId = findViewById(R.id.id_id);
        editButton = findViewById(R.id.edit);

        dbHelper = new DataBaseHelper(this);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editBookBookToDatabase();
            }
        });
    }

    private void editBookBookToDatabase() {
        String bookName = editTextName.getText().toString().trim();
        String bookAuthor = editTextAuthor.getText().toString().trim();
        String editID = editId.getText().toString().trim();


        if (bookName.isEmpty() || bookAuthor.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = dbHelper.editBookk(bookName, bookAuthor, editID);

        if (result > 0) {
            Toast.makeText(this, "Книга добавлена", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(editBook.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Ошибка добавления книги", Toast.LENGTH_SHORT).show();
        }
    }
}