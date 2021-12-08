package algonquin.cst2335.sing1477;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.sing1477.storage.SqlLiteDB;
import algonquin.cst2335.sing1477.storage.StorageDb;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Harjinder Singh
 * @since 2021
 * @version 2.0
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    EditText editText;
    ProgressBar progressBar;
    private ListAdapter adapter;
    List<BeanResponse.Data> getallData;
    private RecyclerView recyclerView;
    Button button;
    StorageDb storageDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storageDb = new StorageDb(getApplicationContext());
        drawerLayout = findViewById(R.id.my_drawer_layout);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.ett);
        button = findViewById(R.id.button);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        /**
         * Create an array list
         */

        getallData = new ArrayList<>();
        /**
         * calls the constructor from list adpater class
         */
        adapter = new ListAdapter(getallData, this);
        recyclerView.setAdapter(adapter);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        /**
         *  pass the Open and Close toggle for the drawer layout listener
         *  to toggle the button
         */
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        NavigationView navigationView = findViewById(R.id.navigationVIew);
        navigationView.setNavigationItemSelectedListener(this);
        editText = findViewById(R.id.ett);
        /**
         * Synchronize the state of the drawer indicator
         */
        actionBarDrawerToggle.syncState();

        getfrompref(editText);
        /**
         * method call open the covid api to track the cases
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitCovidApi();
            }
        });

        /**
         *  to make the Navigation drawer icon always appear on the action bar
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    /**
     * gets the search saved in previous search
     */
    private void getfrompref(EditText etName) {
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("CovidPref", MODE_APPEND);
        String s1 = sh.getString("searchTerm", "");
        etName.setText(s1);
    }
    /**
     * covid 19 tracker api method to generate the cases
     */
    private void hitCovidApi() {
       progressBar.setVisibility(View.VISIBLE);
        saveSearchTermInSharedPreference(editText.getText().toString().trim());
        /**
         * calling method from BaseUrlClass to reach the url
         */
        EndPointInterface iRestInterfaces = BaseUrlClass.getAPIService();
        Call<BeanResponse> call = iRestInterfaces.getCovidInfo(editText.getText().toString().trim());

        call.enqueue(new Callback<BeanResponse>() {
            @Override
            public void onResponse(Call<BeanResponse> call, Response<BeanResponse> response) {

                if (response.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);
                    getallData.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<BeanResponse> call, Throwable t) {

            }
        });

    }
    /**
     * used to save the search perfomed in the edit text
     * @param trim
     */

    private void saveSearchTermInSharedPreference(String trim) {
        SharedPreferences sharedPreferences = getSharedPreferences("CovidPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("searchTerm", trim);
        editor.commit();
    }
    /**
     * helps in navigation  to offline activity which is data stored locally
     * @param item
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            startActivity(new Intent(MainActivity.this, DetailsActivity.class));
            return true;
        }
        return true;
    }
    /**
     * @param item
     * @return output when a item is selected from menu bar.
     */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}


class ListAdapter extends RecyclerView.Adapter<ListAdapter.viewholderVh> {

    private List<BeanResponse.Data> getAllData;
    StorageDb storageDb;
    /**
     * constructor for list adaptor that is used in mainactivity
     * @param getAllData
     * @param activity
     *
     */
    public ListAdapter(List<BeanResponse.Data> getAllData, Activity activity) {
        this.getAllData = getAllData;
        this.activity = activity;
    }

    private Activity activity;
    /** this method loads the list of dates in recylerview
     * @param parent
     * @param viewType
     * @return the list of the dates
     */


    @NonNull
    @Override
    public viewholderVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.list_item_view, parent, false);
        storageDb = new StorageDb(activity);
        return new ListAdapter.viewholderVh(view);
    }
    /**
     *updates the recyclerview
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull viewholderVh holder, int position) {
        BeanResponse.Data valueModel = getAllData.get(position);

        holder.textView.setText(valueModel.getDate());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(valueModel);
            }
        });
    }
    /**
     *this method shows all the data for covid when we click on specific date. this gets data from the server for
     * showing in activity
     *
     */

    private void showPopUp(BeanResponse.Data valueModel) {

        BeanResponse.Data data = (BeanResponse.Data) valueModel;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.details_dialouge, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        TextView heading = dialogView.findViewById(R.id.textView2);
        TextView total_cases = dialogView.findViewById(R.id.textView3);
        TextView total_fatalities = dialogView.findViewById(R.id.textView4);
        TextView total_vaccinations = dialogView.findViewById(R.id.textView6);
        TextView total_recoveries = dialogView.findViewById(R.id.textView7);
        Button button2 = dialogView.findViewById(R.id.button2);
        heading.setText("Covid Information");
        total_cases.setText("Total Case :" + data.getTotal_cases());
        total_fatalities.setText("Total Fatalities :" + data.getTotal_fatalities());
        total_vaccinations.setText("Total Vaccination :" + data.getTotal_vaccinations());
        total_recoveries.setText("Total Recoveries :" + data.getTotal_recoveries());
        /**
         * this button get clicked to save the data
         */
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInDatabase(data);
            }
        });

        alertDialog.show();
    }
    /**
     * this method saves the data in database when the button2 is clicked
     * @param data
     */
    private void saveInDatabase(BeanResponse.Data data) {

        SQLiteDatabase db = storageDb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SqlLiteDB.CovidDatabase.COLUMN_NAME_total_fatalities, data.getTotal_fatalities());
        values.put(SqlLiteDB.CovidDatabase.COLUMN_NAME_total_cases, data.getTotal_cases());
        values.put(SqlLiteDB.CovidDatabase.COLUMN_NAME_total_recoveries, data.getTotal_recoveries());
        values.put(SqlLiteDB.CovidDatabase.COLUMN_NAME_total_vaccinations, data.getTotal_vaccinations());

        long row = db.insert(SqlLiteDB.CovidDatabase.TABLE_NAME, null, values);

        if (row > 0) {
            Toast.makeText(activity, "Saved Locally", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, "Something wrong! try again", Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * @return it returns the size
     */
    @Override
    public int getItemCount() {
        return getAllData.size();
    }

    public class viewholderVh extends RecyclerView.ViewHolder {
        TextView textView;


        public viewholderVh(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
