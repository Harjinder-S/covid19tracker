package algonquin.cst2335.sing1477;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.sing1477.storage.SqlLiteDB;
import algonquin.cst2335.sing1477.storage.StorageDb;

public class DetailsActivity extends AppCompatActivity {

    private DbDataAdapter adapter;
    List<BeanResponse.Data> getALlDatafromdb;
    RecyclerView recyclerView;
    SQLiteDatabase db;
    ProgressBar progressBar2;
    ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);

        recyclerView = findViewById(R.id.recyclerview);
        progressBar2 = findViewById(R.id.progressBar2);
        constraintLayout = findViewById(R.id.constrain);
        /**
         * declaring an arraylist
         */
        getALlDatafromdb = new ArrayList<>();
        adapter = new DbDataAdapter(getALlDatafromdb, this);
        recyclerView.setAdapter(adapter);
        StorageDb dbHelper = new StorageDb(getApplicationContext());
        hitgetALlDatafromdb(dbHelper);

    }
    /**
     * this function saves the data into local database to view as offline. plus this also saves the data into the database
     * @param dbHelper
     */
    private void hitgetALlDatafromdb(StorageDb dbHelper) {
        progressBar2.setVisibility(View.VISIBLE);
        db = dbHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                SqlLiteDB.CovidDatabase.COLUMN_NAME_total_cases,
                SqlLiteDB.CovidDatabase.COLUMN_NAME_total_fatalities,
                SqlLiteDB.CovidDatabase.COLUMN_NAME_total_recoveries,
                SqlLiteDB.CovidDatabase.COLUMN_NAME_total_vaccinations};


        Cursor cursor = db.query(SqlLiteDB.CovidDatabase.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            BeanResponse.Data data = new BeanResponse.Data();
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(SqlLiteDB.CovidDatabase._ID));
            data.setId(String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(SqlLiteDB.CovidDatabase._ID))));
            data.setTotal_cases(cursor.getInt(cursor.getColumnIndexOrThrow(SqlLiteDB.CovidDatabase.COLUMN_NAME_total_cases)));
            data.setTotal_fatalities(cursor.getInt(cursor.getColumnIndexOrThrow(SqlLiteDB.CovidDatabase.COLUMN_NAME_total_fatalities)));
            data.setTotal_recoveries(cursor.getInt(cursor.getColumnIndexOrThrow(SqlLiteDB.CovidDatabase.COLUMN_NAME_total_recoveries)));
            data.setTotal_vaccinations(cursor.getInt(cursor.getColumnIndexOrThrow(SqlLiteDB.CovidDatabase.COLUMN_NAME_total_vaccinations)));
            getALlDatafromdb.add(data);
        }
        /**
         * if the size from database is grater than zero than it means data is available and it will look for data otherwise it will show
         * no data is found
         */
        if (getALlDatafromdb.size() > 0) {
            Log.d("TAG", "hitgetALlDatafromdb: " + getALlDatafromdb);
            progressBar2.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "no data found", Toast.LENGTH_SHORT).show();
        }
        cursor.close();

    }

}


/**
 *
 */
class DbDataAdapter extends RecyclerView.Adapter<DbDataAdapter.viewHoldervh> {

    private List<BeanResponse.Data> getAllData;
    StorageDb storageDb;
    /**
     * this method get called upon to list the data in recyclerview
     * @param getAllData
     * @param activity
     */

    public DbDataAdapter(List<BeanResponse.Data> getAllData, Activity activity) {
        this.getAllData = getAllData;
        this.activity = activity;
    }

    private Activity activity;
/**
 * OnCreateViewHolder create a new view for recyclerview whenever it needs a new one
 * @param parent
 * @param viewType
 * @return new viewholder
 */

    @NonNull
    @Override
    public DbDataAdapter.viewHoldervh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.details_dialouge, parent, false);
        storageDb = new StorageDb(activity);
        return new DbDataAdapter.viewHoldervh(view);
    }
    /**
     * RecylcerView calls this method internally to update the recyclerview.
     * @param holder
     * @param position
     */

    @Override
    public void onBindViewHolder(@NonNull DbDataAdapter.viewHoldervh holder, @SuppressLint("RecyclerView") int position) {
        BeanResponse.Data data = getAllData.get(position);

        holder.total_cases.setText("Total Cases :" + data.getTotal_cases());
        holder.total_fatalities.setText("Total Fatalities:" + data.getTotal_fatalities());
        holder.total_recoveries.setText("Total Recoveries:" + data.getTotal_recoveries());
        holder.total_vaccinations.setText("Total Vaccination:" + data.getTotal_vaccinations());

        holder.button2.setText("Delete");
        /**
         * this function deletes the data
         */

        holder.button2.setOnClickListener(v -> {
            new AlertDialog.Builder(activity)
                    .setTitle("Deletion Confirmation")
                    .setMessage("Do you really want to Delete?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            delete(data);
                            getAllData.remove(position);
                            notifyItemRemoved(position);
                        }
                    })
                    .setNegativeButton(android.R.string.no, null).show();

        });


    }
    /**
     * This function is responsible to delete data from the database when we hit ok from the alert dialogue(in DbDataAdapter class) box
     * it will delete it
     */
    private void delete(BeanResponse.Data databean) {
        BeanResponse.Data data = (BeanResponse.Data) databean;
        SQLiteDatabase db = storageDb.getWritableDatabase();
        String selection = databean.getId();

        int deletedRows = db.delete(SqlLiteDB.CovidDatabase.TABLE_NAME, "_id= " + selection + "", null);
        Toast toast = Toast.makeText(activity, "Deleted", Toast.LENGTH_LONG);
        toast.show();
    }
    /**
     * @return the size of the data
     */
    @Override
    public int getItemCount() {
        return getAllData.size();
    }

    public class viewHoldervh extends RecyclerView.ViewHolder {
        TextView total_cases, total_fatalities, total_hospitalizations, total_vaccinations, total_recoveries;
        Button button2;
        /** view holder describes an itemview about its place within the recyclerview. it helps to find the item in recycler view
         * @param itemView
         */

        public viewHoldervh(@NonNull View itemView) {
            super(itemView);
            total_cases = itemView.findViewById(R.id.textView3);
            total_fatalities = itemView.findViewById(R.id.textView4);
            total_vaccinations = itemView.findViewById(R.id.textView6);
            total_recoveries = itemView.findViewById(R.id.textView7);
            button2 = itemView.findViewById(R.id.button2);
        }
    }
}
