package com.nkosi.buildingmanteinancefront.views;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nkosi.buildingmanteinancefront.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void cancel(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void register(View view){
        EditText editFirstName = (EditText) findViewById(R.id.edit_first_name);
        EditText editLastName = (EditText) findViewById(R.id.edit_last_name);
        EditText editEmail = (EditText) findViewById(R.id.edit_email);
        EditText editPassword = (EditText) findViewById(R.id.edit_password);
        EditText editUserName = (EditText) findViewById(R.id.edit_user_username);

        String firstName = editFirstName.getText().toString();
        String lastName = editLastName.getText().toString();
        String email =  editEmail.getText().toString();
        String password = editPassword.getText().toString();
        String userName = editUserName.getText().toString();


        /*if (firstName == "" || lastName == "" || email == "" || password == "" || userName == ""){
            Context context = getApplicationContext();
            CharSequence text = "Please Complete All fields";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
            builder.setMessage(R.string.dialog_message);

            AlertDialog dialog = builder.create();
        }*/
    }
}
