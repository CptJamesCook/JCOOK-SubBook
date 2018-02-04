package com.example.james.jcook_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "com.example.james.jcook_subbook.NAME";
    public static final String EXTRA_DATE = "com.example.james.jcook_subbook.DATE";
    public static final String EXTRA_COST = "com.example.james.jcook_subbook.COST";
    public static final String EXTRA_COMMENT = "com.example.james.jcook_subbook.COMMENT";

    private EditText fieldSubName;
    private EditText fieldSubDate;
    private EditText fieldSubCost;
    private EditText fieldSubComment;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_details);

        //Initialize Button's
        Button saveBtn = findViewById(R.id.saveButton);

        //Initialize EditText's
        fieldSubName = findViewById(R.id.SubName);
        fieldSubDate = findViewById(R.id.SubDate);
        fieldSubCost = findViewById(R.id.SubCost);
        fieldSubComment = findViewById(R.id.SubComment);

        //Get info from bundle
        Bundle bundle = getIntent().getExtras();
        String subName = bundle.getString(EXTRA_NAME);
        Long timeInMilli = bundle.getLong(EXTRA_DATE);
        Date subDate = new Date(timeInMilli);
        float subCost = bundle.getFloat(EXTRA_COST);
        String subComment = bundle.getString(EXTRA_COMMENT);

        //populate activity with subs details
        populateActivityFields(subName, subDate, subCost, subComment);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create the proper intent, then return to the main activity
                Intent intent = new Intent();
                intent.putExtra(EXTRA_NAME, fieldSubName.getText().toString());

                Long millisecs = getLongFromDateString(fieldSubDate.getText().toString());
                intent.putExtra(EXTRA_DATE, millisecs);

                intent.putExtra(EXTRA_COST, Float.parseFloat(fieldSubCost.getText().toString()));
                intent.putExtra(EXTRA_COMMENT, fieldSubComment.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void populateActivityFields(String subName, Date subDate,
                                        float subCost, String subComment){
        fieldSubName.setText(subName);
        fieldSubDate.setText(dateFormatter.format(subDate));
        fieldSubCost.setText(String.valueOf(subCost));
        fieldSubComment.setText(subComment);
    }

    private Long getLongFromDateString(String dateStr){
        // process dateStr into a Long representation  of millisecs
        Long millisecs = 1517616000021L; // default in case of failure
        try {
            Date date = dateFormatter.parse(dateStr);
            millisecs = date.getTime();
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return millisecs;
    }
}
