package com.example.custom_navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class Massage_Duration_Gift extends AppCompatActivity {
    NumberPicker numberPicker;
    Button massage_next_btn;
    TextView durantion_changeable_view,massage_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massage__duration__gift);
        numberPicker=(NumberPicker) findViewById(R.id.massage_duration_selector_id);

        Intent setText=getIntent();
        Bundle bundle=setText.getExtras();
        final String string=(String) bundle.get("country");
        final String massage1=(String) bundle.get("massage1");
        final String gift=(String) bundle.get("gift1");
        if (gift.equals("1"))
        {
            final String duration_string[]={"60min-US$129","75min-US$152","900min-US$176"};
            durantion_changeable_view=(TextView) findViewById(R.id.duration_time_changeable_textview);
            massage_type=(TextView)findViewById(R.id.massage_type_id);
            massage_next_btn=findViewById(R.id.massage_duration_next_btn);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            massage_next_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(getApplicationContext(),After_Appropriate_Gift_Selected_Personal_Detail.class);
                    intent.putExtra("description","Single Massage");
                    startActivity(intent);
                }
            });
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(duration_string.length-1);
            numberPicker.setDisplayedValues(duration_string);
            numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            massage_type.setText(massage1);
            if (string!=null)
            {
                durantion_changeable_view.setText("Your recipient will be....  ");
            }
            NumberPicker.OnValueChangeListener myvaluechange = new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    Intent setText=getIntent();
                    Bundle bundle=setText.getExtras();
                    final String string=(String) bundle.get("country");
                    durantion_changeable_view.setText("Your recipient will recieve " + duration_string[newVal] + " in "+string);
                }
            };
            numberPicker.setOnValueChangedListener(myvaluechange);
        }
        else if (gift.equals("2"))
        {
            final String duration_string[]={"60min-US$257","75min-US$304","900min-US$352"};
            durantion_changeable_view=(TextView) findViewById(R.id.duration_time_changeable_textview);
            massage_type=(TextView)findViewById(R.id.massage_type_id);
            massage_next_btn=findViewById(R.id.massage_duration_next_btn);
            massage_next_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(getApplicationContext(),After_Appropriate_Gift_Selected_Personal_Detail.class);
                    intent.putExtra("description","Couple Massage");
                    startActivity(intent);
                }
            });
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(duration_string.length-1);
            numberPicker.setDisplayedValues(duration_string);
            numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            massage_type.setText(massage1);
            if (string!=null)
            {
                durantion_changeable_view.setText("Your recipient will be....  ");
            }
            NumberPicker.OnValueChangeListener myvaluechange = new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    Intent setText=getIntent();
                    Bundle bundle=setText.getExtras();
                    final String string=(String) bundle.get("country");
                    durantion_changeable_view.setText("Your recipient will recieve " + duration_string[newVal] + " in "+string);
                }
            };
            numberPicker.setOnValueChangedListener(myvaluechange);
        }
        else if (gift.equals("3"))
        {
            final String duration_string[]={"Weekly Billing-US$30","Monthly Billing-US$105","One_Time Purchase-US$1,260"};
            durantion_changeable_view=(TextView) findViewById(R.id.duration_time_changeable_textview);
            massage_type=(TextView)findViewById(R.id.massage_type_id);
            massage_next_btn=findViewById(R.id.massage_duration_next_btn);
            massage_next_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(getApplicationContext(),After_Appropriate_Gift_Selected_Personal_Detail.class);
                    intent.putExtra("description","Massage Membership");
                    startActivity(intent);
                }
            });
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(duration_string.length-1);
            numberPicker.setDisplayedValues(duration_string);
            numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            massage_type.setText(massage1);
            if (string!=null)
            {
                durantion_changeable_view.setText("Your recipient will be....  ");
            }
            NumberPicker.OnValueChangeListener myvaluechange = new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    Intent setText=getIntent();
                    Bundle bundle=setText.getExtras();
                    final String string=(String) bundle.get("country");
                    durantion_changeable_view.setText("Your recipient will recieve " + duration_string[newVal] + " a home spa gift that includes a luxurious robe ");
                }
            };
            numberPicker.setOnValueChangedListener(myvaluechange);
        }
        else if (gift.equals("4"))
        {
            final String duration_string[]={"3-pack-US$383","6-pack-US$493","9-pack-US$523","12-pack-US$630"};
            durantion_changeable_view=(TextView) findViewById(R.id.duration_time_changeable_textview);
            massage_type=(TextView)findViewById(R.id.massage_type_id);
            massage_next_btn=findViewById(R.id.massage_duration_next_btn);
            massage_next_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(getApplicationContext(),After_Appropriate_Gift_Selected_Personal_Detail.class);
                    intent.putExtra("description","Massage Package");
                    startActivity(intent);
                }
            });
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(duration_string.length-1);
            numberPicker.setDisplayedValues(duration_string);
            numberPicker.setWrapSelectorWheel(true);
            numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            massage_type.setText(massage1);
            if (string!=null)
            {
                durantion_changeable_view.setText("Your recipient will be....  ");
            }
            NumberPicker.OnValueChangeListener myvaluechange = new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    Intent setText=getIntent();
                    Bundle bundle=setText.getExtras();
                    final String string=(String) bundle.get("country");
                    durantion_changeable_view.setText("Your recipient will recieve " + duration_string[newVal] + ", 60 minute massage in "+string);
                }
            };
            numberPicker.setOnValueChangedListener(myvaluechange);
        }
        else if (gift.equals("5"))
        {
            final String duration_string[]={"$25-US$25","$50-US$50","$75-US$75","$100-US$100","$150-US$150","$200-US$200","$300-US$300"};
            durantion_changeable_view=(TextView) findViewById(R.id.duration_time_changeable_textview);
            massage_type=(TextView)findViewById(R.id.massage_type_id);
            massage_next_btn=findViewById(R.id.massage_duration_next_btn);
            massage_next_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(getApplicationContext(),After_Appropriate_Gift_Selected_Personal_Detail.class);
                    intent.putExtra("description","Other Amount");
                    startActivity(intent);
                }
            });
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(duration_string.length-1);
            numberPicker.setDisplayedValues(duration_string);
            numberPicker.setWrapSelectorWheel(true);
            numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            massage_type.setText(massage1);
            if (string!=null)
            {
                durantion_changeable_view.setText("Your recipient will be....  ");
            }
            NumberPicker.OnValueChangeListener myvaluechange = new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    Intent setText=getIntent();
                    Bundle bundle=setText.getExtras();
                    final String string=(String) bundle.get("country");
                    durantion_changeable_view.setText("Your recipient will recieve " + duration_string[newVal] + " towards zeel massage  in "+string);
                }
            };
            numberPicker.setOnValueChangedListener(myvaluechange);
        }
    }

//    public void gift1()
//        {
////        Toast.makeText(,"jdshcuihids",Toast.LENGTH_SHORT).show();
//    }
}
