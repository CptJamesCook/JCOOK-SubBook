/*
 * Copyright 2018 James Cook
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.example.james.jcook_subbook;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The subscription details activity.
 *
 * Responsible for modifying the opened subscriptions.
 * @author James Cook
 * @version 0.0
 */
public class SubDetailsActivity extends AppCompatActivity {

    private EditText fieldSubName;
    private TextView fieldSubDate;
    private EditText fieldSubCost;
    private EditText fieldSubComment;

    private int subIndex;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    public static final String EXTRA_INDEX = "com.example.james.jcook_subbook.INDEX";
    public static final String EXTRA_DEL = "com.example.james.jcook_subbook.DEL";
    public static final String EXTRA_NAME = "com.example.james.jcook_subbook.NAME";
    public static final String EXTRA_DATE = "com.example.james.jcook_subbook.DATE";
    public static final String EXTRA_COST = "com.example.james.jcook_subbook.COST";
    public static final String EXTRA_COMMENT = "com.example.james.jcook_subbook.COMMENT";

    /**
     * Constructs the activity.
     *
     * @param savedInstanceState The previously saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_details);

        //Initialize Buttons
        Button saveBtn = findViewById(R.id.saveButton);
        Button delBtn = findViewById(R.id.deleteButton);

        //Initialize EditTexts
        fieldSubName = findViewById(R.id.SubName);
        fieldSubDate = findViewById(R.id.SubDate);
        fieldSubCost = findViewById(R.id.SubCost);
        //create a filter so only proper decimals can be used
        fieldSubCost.setFilters(new InputFilter[]{
                new DecimalDigitsInputFilter(5,2)
        });
        fieldSubComment = findViewById(R.id.SubComment);

        //Get info from bundle
        Bundle bundle = getIntent().getExtras();
        subIndex = bundle.getInt(EXTRA_INDEX);
        String subName = bundle.getString(EXTRA_NAME);
        Long timeInMilli = bundle.getLong(EXTRA_DATE);
        Date subDate = new Date(timeInMilli);
        float subCost = bundle.getFloat(EXTRA_COST);
        String subComment = bundle.getString(EXTRA_COMMENT);

        //populate activity with subs details
        populateActivityFields(subName, subDate, subCost, subComment);

        //define on click behavior for the save button
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBtnOnClickBehavior();
            }
        });

        //define on click behavior for the delete button
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delBtnOnClickBehavior();
            }
        });

        //define behavior when selecting a date
        fieldSubDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SubDetailsActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        //initialize the date listener
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, day);
                Date date = cal.getTime();
                fieldSubDate.setText(dateFormatter.format(date));
            }
        };
    }

    /**
     * Populates the appropriate fields with subscription details.
     *
     * @param subName the subscription name
     * @param subDate the subscription date
     * @param subCost the subscription cost
     * @param subComment the subscription comment
     */
    private void populateActivityFields(String subName, Date subDate,
                                        float subCost, String subComment){
        fieldSubName.setText(subName);
        fieldSubDate.setText(dateFormatter.format(subDate));
        fieldSubCost.setText(String.valueOf(subCost));
        fieldSubComment.setText(subComment);
    }

    /**
     * Coverts a date string into a long.
     *
     * @param dateStr date string to be converted
     * @return A long number representing time from epoc to date string.
     */
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

    /**
     * Defines behavior for a clicked save button.
     */
    private void saveBtnOnClickBehavior(){
        //Create the proper intent, then return to the main activity
        Intent intent = new Intent();
        intent.putExtra(EXTRA_INDEX, subIndex);
        intent.putExtra(EXTRA_DEL, false);
        intent.putExtra(EXTRA_NAME, fieldSubName.getText().toString());

        Long millisecs = getLongFromDateString(fieldSubDate.getText().toString());
        intent.putExtra(EXTRA_DATE, millisecs);

        intent.putExtra(EXTRA_COST, Float.parseFloat(fieldSubCost.getText().toString()));
        intent.putExtra(EXTRA_COMMENT, fieldSubComment.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Defines behavior for a clicked delete button.
     */
    private void delBtnOnClickBehavior(){
        Intent intent = new Intent();
        intent.putExtra(EXTRA_INDEX, subIndex);
        intent.putExtra(EXTRA_DEL, true);
        setResult(RESULT_OK, intent);
        finish();
    }
}
