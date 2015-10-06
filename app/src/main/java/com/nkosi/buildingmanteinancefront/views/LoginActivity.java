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
import com.nkosi.buildingmanteinancefront.repository.RestAPI;
import com.nkosi.buildingmanteinancefront.repository.rest.RestHomePageAPI;
import com.nkosi.buildingmanteinancefront.repository.slqlitedb.UsersDataSource;
import com.nkosi.buildingmanteinancefront.repository.slqlitedb.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);

        // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                startActivity(i);
            }
        });

        RestAPI rest = new RestHomePageAPI();
        TextView textResults = (TextView) findViewById(R.id.text_login_validate);
        String result = "did not execute";
        try{
            result = rest.home();
        }catch(Exception ex){
            result = ex.getMessage();
        }
        textResults.setVisibility(View.VISIBLE);
        textResults.setText(result);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void logIn(View view){
        String errormsg = "";
        UsersDataSource datasource;
        datasource = new UsersDataSource(this);

        //Open DataSource
        try{
            datasource.open();}catch(SQLException ex){
            errormsg += "Failed to open Data source " + ex.getMessage() + "\n";
        }

        List<User> users = new ArrayList<User>();
        users = datasource.getAllUsers();
        datasource.close();

        EditText editPassword = (EditText) findViewById(R.id.edit_login_password);
        EditText editUserName = (EditText) findViewById(R.id.edit_login_username);

        String password = editPassword.getText().toString();
        String userName = editUserName.getText().toString();
        String inDB = "";


        Boolean authenticate = false;
        for(User theUser: users){
            if(theUser.getUser_name().trim().length() == userName.trim().length()
                    && theUser.getPassword().trim().length() == password.trim().length()
                    && theUser.getUser_name().trim().contains(userName.trim())
                    && theUser.getPassword().trim().contains(password)){
                authenticate = true;
            }
        }

        if (password.length() < 1 || userName.length() < 1){
            Context context = getApplicationContext();
            CharSequence text = "Please Fill in all the fields";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if(!authenticate){
            Context context = getApplicationContext();
            CharSequence text = "Incorrect username or password ";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else{
            Intent intent = new Intent(this, DisplayMenu.class);
            startActivity(intent);
        }
    }
}
