package com.nkosi.buildingmanteinancefront.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nkosi.buildingmanteinancefront.R;
import com.nkosi.buildingmanteinancefront.repository.slqlitedb.MyDBHandler;
import com.nkosi.buildingmanteinancefront.repository.slqlitedb.UsersDataSource;
import com.nkosi.buildingmanteinancefront.repository.slqlitedb.model.User;

import java.sql.SQLException;

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
        EditText editConfirmPassword = (EditText) findViewById(R.id.edit_confirm_password);
        MyDBHandler handler = new MyDBHandler(this, null, null, 1);
        TextView textview = (TextView) findViewById(R.id.text_validate);

        String firstName = editFirstName.getText().toString();
        String lastName = editLastName.getText().toString();
        String email =  editEmail.getText().toString();
        String password = editPassword.getText().toString();
        String userName = editUserName.getText().toString();
        String confirm_password = editConfirmPassword.getText().toString();
        String availableUserName = "";

        UsersDataSource datasource;
        datasource = new UsersDataSource(this);

        //Open DataSource
        try{
            datasource.open();}catch(SQLException ex){
            textview.setVisibility(View.VISIBLE);
            textview.setText("Failed to open Data source " + ex.getMessage());
        }

        try{
            availableUserName = datasource.findUser(userName);
        }catch(Exception ex){
            availableUserName = ex.getMessage();
        }

        //Close after Checking for userName availability
        datasource.close();

        if (firstName.length() < 1 || lastName.length() < 1 || email.length() < 1
                || password.length() < 1 || userName.length() < 1 || confirm_password.length() < 1){
            textview.setVisibility(View.VISIBLE);
            Context context = getApplicationContext();
            CharSequence text = "Please Fill in all the fields";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if(availableUserName != null){
            textview.setText("Ooops! user name " + userName + " Is already taken");
            textview.setVisibility(View.VISIBLE);

            Context context = getApplicationContext();
            CharSequence text = "User Name " + userName + " Already Taken";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else  if(password.trim().length() != confirm_password.trim().length()
                && !confirm_password.trim().contains(password.trim())) {
            textview.setText("Confirmation of password does not match with the password*");
            textview.setVisibility(View.VISIBLE);

            Context context = getApplicationContext();
            CharSequence text = "Confirmation of password does not match with the password";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else{

            User newUser = new User();
            newUser.setFirst_name(firstName);
            newUser.setLast_name(lastName);
            newUser.setEmail(email);
            newUser.setUser_name(userName);
            newUser.setPassword(password);

            //Open DataSource
            try{
                datasource.open();}catch(SQLException ex){
                textview.setVisibility(View.VISIBLE);
                textview.setText("Failed to open Data source " + ex.getMessage());
            }

            String creation = datasource.createUser(newUser);
            //close dataSource
            datasource.close();
            textview.setText(creation);
            textview.setVisibility(View.VISIBLE);
            Context context = getApplicationContext();
            CharSequence text = "Registered!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            BlankFileds();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

    }

    public void BlankFileds(){
        EditText editFirstName = (EditText) findViewById(R.id.edit_first_name);
        EditText editLastName = (EditText) findViewById(R.id.edit_last_name);
        EditText editEmail = (EditText) findViewById(R.id.edit_email);
        EditText editPassword = (EditText) findViewById(R.id.edit_password);
        EditText editUserName = (EditText) findViewById(R.id.edit_user_username);
        EditText editConfirmPassword = (EditText) findViewById(R.id.edit_confirm_password);

        editFirstName.setText("");
        editLastName.setText("");
        editEmail.setText("");
        editPassword.setText("");
        editUserName.setText("");
        editConfirmPassword.setText("");
    }
}
