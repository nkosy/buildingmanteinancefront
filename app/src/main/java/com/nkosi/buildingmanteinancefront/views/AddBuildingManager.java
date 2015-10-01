package com.nkosi.buildingmanteinancefront.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nkosi.buildingmanteinancefront.R;
import com.nkosi.buildingmanteinancefront.model.Building;
import com.nkosi.buildingmanteinancefront.model.BuildingManager;
import com.nkosi.buildingmanteinancefront.repository.rest.RestBuildingManagerAPI;
import com.nkosi.buildingmanteinancefront.services.BuildingManagerService;
import com.nkosi.buildingmanteinancefront.services.Impl.BuildingServiceImpl;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AddBuildingManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_building_manager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_building_manager, menu);
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

    public void addManager(View view){
        EditText editManagerName = (EditText) findViewById(R.id.edit_manager_name);
        String managerName = editManagerName.getText().toString();
        RestBuildingManagerAPI api = new RestBuildingManagerAPI();
        TextView textResults = (TextView) findViewById(R.id.text_add_manager_validate);
        if(managerName.length() < 1){
            Context context = getApplicationContext();
            CharSequence text = "Please Fill in the manager Name";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else{
            List<Building> buildings = new ArrayList<Building>();
            BuildingManager newManager = new BuildingManager.Builder(managerName)
                    .buildings(buildings)
                    .build();
            String result;
            try{
                result = api.post(newManager);
            }catch(Exception ex){
                result = ex.getMessage();
            }

            textResults.setVisibility(View.VISIBLE);
            textResults.setText(result);

            Context context = getApplicationContext();
            CharSequence text = result;
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }
}
