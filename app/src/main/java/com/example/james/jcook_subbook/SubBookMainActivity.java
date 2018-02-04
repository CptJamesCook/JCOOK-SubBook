package com.example.james.jcook_subbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class SubBookMainActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "com.example.james.jcook_subbook.NAME";
    public static final String EXTRA_DATE = "com.example.james.jcook_subbook.DATE";
    public static final String EXTRA_COST = "com.example.james.jcook_subbook.COST";
    public static final String EXTRA_COMMENT = "com.example.james.jcook_subbook.COMMENT";

    private static final String FILENAME = "newFile.sav";
    private ListView currSubsList;

    private ArrayList<BasicSubscription> sublist;
    private ArrayAdapter<BasicSubscription> adapter;

    private static final int SELECTOR_CONST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_book_main);

        Button addSubBtn = findViewById(R.id.addSub);
        currSubsList = findViewById(R.id.currSubs);

        //define behavior for the add subscriptions button
        addSubBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setResult(RESULT_OK);
                BasicSubscription sub = new BasicSubscription();
                openSubDetailsActivity(sub);
            }
        });

        //define behavior for when an item is clicked on
        currSubsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BasicSubscription entry = (BasicSubscription) parent.getItemAtPosition(position);
                openSubDetailsActivity(entry);
            }
        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<BasicSubscription>(this,
                                                       R.layout.list_item, sublist);
        currSubsList.setAdapter(adapter);
    }

    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2018-01-24

            Type listType = new TypeToken<ArrayList<BasicSubscription>>(){}.getType();

            sublist = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            sublist = new ArrayList<BasicSubscription>();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();

            gson.toJson(sublist, out);

            out.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void openSubDetailsActivity(BasicSubscription sub){
        Intent intent = new Intent(this, SubDetailsActivity.class);
        intent.putExtra(EXTRA_NAME, sub.getName());
        intent.putExtra(EXTRA_DATE, sub.getDate().getTime());
        intent.putExtra(EXTRA_COST, sub.getCost());
        intent.putExtra(EXTRA_COMMENT, sub.getComment());
        startActivityForResult(intent, SELECTOR_CONST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode){
            case SELECTOR_CONST:
                if(resultCode == RESULT_OK){
                    //get the sub data from intent
                    //Get info from bundle
                    Bundle bundle = data.getExtras();
                    String subName = bundle.getString(EXTRA_NAME);
                    Long timeInMilli = bundle.getLong(EXTRA_DATE);
                    Date subDate = new Date(timeInMilli);
                    float subCost = bundle.getFloat(EXTRA_COST);
                    String subComment = bundle.getString(EXTRA_COMMENT);

                    BasicSubscription sub = new BasicSubscription(subName, subDate,
                                                                  subCost, subComment);
                    sublist.add(sub);
                    adapter.notifyDataSetChanged();
                    saveInFile();
                }
        }
    }

    protected void onDestroy(){
		super.onDestroy();
		Log.i("In Destroy method","The app is closing");
	}
}
