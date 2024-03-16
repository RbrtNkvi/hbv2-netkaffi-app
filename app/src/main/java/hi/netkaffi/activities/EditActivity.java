package hi.netkaffi.activities;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import hi.netkaffi.R;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Retrieve the selected item's data from the intent extras
        String selectedItem = getIntent().getStringExtra("selectedItem");

        // Assuming you have EditText fields in your layout to edit the data
        EditText editText = findViewById(R.id.editText);
        // Set the retrieved data to the EditText
        editText.setText(selectedItem);
    }
}
