package com.example.agecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
EditText dateText;
Button datePick,calcBtn,clearBtn;
LinearLayout datePickLay;
DatePicker dateSelect;
TextView resText;
Calendar calendar = Calendar.getInstance();
int date,month,year;
int thisYear = calendar.get(Calendar.YEAR);
int thisMonth = calendar.get(Calendar.MONTH);
int thisDate = calendar.get(Calendar.DATE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateText = findViewById(R.id.dateText);
        datePick = findViewById(R.id.datePick);
        calcBtn = findViewById(R.id.calcBtn);
        datePickLay = findViewById(R.id.datepicklay);
        dateSelect = findViewById(R.id.dateSelect);
        resText = findViewById(R.id.resBox);
        clearBtn = findViewById(R.id.clrBtn);

        dateText.setEnabled(false);
        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickLay.setVisibility(View.VISIBLE);
            }
        });
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = dateSelect.getDayOfMonth();
                month = dateSelect.getMonth();
                year = dateSelect.getYear();
                datePickLay.setVisibility(View.GONE);
                resText.setVisibility(View.VISIBLE);
                dateText.setText(date+"/"+month+"/"+year);
                findAge(thisDate,thisMonth,thisYear,date,month,year);
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = 0;
                month = 0;
                year = 0;
                datePickLay.setVisibility(View.GONE);
                resText.setVisibility(View.GONE);
            }
        });

    }
    void findAge(int current_date, int current_month,
                 int current_year, int birth_date,
                 int birth_month, int birth_year)
    {
        // days of every month
        int month[] = { 31, 28, 31, 30, 31, 30, 31,
                31, 30, 31, 30, 31 };

        // if birth date is greater then current birth
        // month then do not count this month and add 30
        // to the date so as to subtract the date and
        // get the remaining days
        if (birth_date > current_date) {
            current_date = current_date + month[birth_month - 1];
            current_month = current_month - 1;
        }

        // if birth month exceeds current month, then do
        // not count this year and add 12 to the month so
        // that we can subtract and find out the difference
        if (birth_month > current_month) {
            current_year = current_year - 1;
            current_month = current_month + 12;
        }

        // calculate date, month, year
        int calculated_date = current_date - birth_date;
        int calculated_month = current_month - birth_month;
        int calculated_year = current_year - birth_year;

        // print the present age
        resText.setText(calculated_year+" years "+calculated_month+" months and "+calculated_date+" days.");
    }
}