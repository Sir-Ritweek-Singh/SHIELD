package com.example.shield;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
     DATABASE DB;
     TextView ritweek;
   //  EditText  editText2, editText3, editText4;
     Button button,master,up, del;
     ListView LS;
     ArrayList<String> idlist=new ArrayList<>();
     ArrayAdapter adapter1;
     int a;
     String s1,s2,s3, b;
     RadioButton r1,r2,r3,
                  r4,r5,r6,
                   r7,r8,r9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //editText1=(EditText)findViewById(R.id.editText);
       // editText2=(EditText)findViewById(R.id.editText2);
       // editText3=(EditText)findViewById(R.id.editText3);
        //editText4=(EditText)findViewById(R.id.editText4);
        r1=(RadioButton) findViewById(R.id.radio1);
        r2=(RadioButton) findViewById(R.id.radio2);
        r3=(RadioButton) findViewById(R.id.radio3);
        r4=(RadioButton) findViewById(R.id.radio4);
        r5=(RadioButton) findViewById(R.id.radio5);
        r6=(RadioButton) findViewById(R.id.radio6);
        r7=(RadioButton) findViewById(R.id.radio7);
        r8=(RadioButton) findViewById(R.id.radio8);
        r9=(RadioButton) findViewById(R.id.radio9);
         ritweek = (TextView) findViewById(R.id.textView2);
        button =(Button)findViewById(R.id.button1);
        master =(Button)findViewById(R.id.button2);
        up =(Button)findViewById(R.id.button3);
        del =(Button)findViewById(R.id.button4);
        LS =(ListView) findViewById(R.id.listwiew1);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String currentDateandTime = sdf.format(new Date());
        ritweek.setText(currentDateandTime);
       view();
      paytm();
       LS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
               String text = LS.getItemAtPosition(i).toString();
               a= Integer.parseInt(text);
               b=text;
               Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
           }
       });

        DB= new DATABASE( this );
      insert();
      bbq();
        dle();

    }

    public  void dle()
    {
        del.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean msg = DB.hatmc(b);
                        if(msg) {
                            Toast.makeText(getApplicationContext(), b +"is DELETED", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "an error occoured :(", Toast.LENGTH_SHORT).show();
                        }
                        bbq();

                    }
                }
        );
    }

    public void insert()
    {
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                 boolean msg=       DB.insertData(
                                ritweek.getText().toString(),s1,s2,s3);
                                //editText2.getText().toString(),
                              //  editText3.getText().toString(),
                                //editText4.getText().toString());
                 if(msg) {
                     Toast.makeText(getApplicationContext(), "INSERTED", Toast.LENGTH_SHORT).show();
                 }
                 else {
                     Toast.makeText(getApplicationContext(), "an error occoured :(", Toast.LENGTH_SHORT).show();
                 }
                 bbq();
                    }
                }
        );
    }

    public void view()
    {
        master.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getApplicationContext(), "ghus to taha hei :(", Toast.LENGTH_SHORT).show();
                     Cursor mc= DB.showall();
                     if(mc.getCount()==0){
                         message("ERROR", "lag gaye bro");
                         return;
                     }
                        StringBuffer buffer = new StringBuffer();
                        while(mc.moveToNext())
                        {
                            buffer.append("Id1   " + mc.getString(0)+"\n");
                            buffer.append("date  " + mc.getString(1)+"\n");
                            buffer.append("sub1  " + mc.getString(2)+"\n");
                            buffer.append("sub2  " + mc.getString(3)+"\n");
                            buffer.append("sub3  " + mc.getString(4)+"\n \n \n");
                        }
                        message("data",buffer.toString());
                    }
                }
        );
    }


    private void bbq(){
        Cursor mc= DB.showall();
        while (mc.moveToNext())
        {
            idlist.add(mc.getString(0));
        }


        adapter1= new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, idlist);
        LS.setAdapter(adapter1);

    }

    public void paytm()
    {
        up.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     boolean isworking=
                             DB.updatejk( b,s1,s2,s3
                               //editText1.getText().toString(),
                               //editText2.getText().toString(),
                               //editText3.getText().toString(),
                               //editText4.getText().toString()
                               );
                        if(isworking) {
                            Toast.makeText(getApplicationContext(), b+"  is UPDATED", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "an error occoured :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }



    public void message(String title, String body)
    {
        AlertDialog.Builder dog = new AlertDialog.Builder(this);
        dog.setTitle(title);
        dog.setMessage(body);
        dog.show();

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio1:
                if (checked)
                    r2.setChecked(false);
                r3.setChecked(false);
                    s1="PRESENT";
                    // Pirates are the best
                    break;
            case R.id.radio2:
                if (checked)
                    r1.setChecked(false);
                r3.setChecked(false);
                    s1="CANCELLED";
                    // Pirates are the best
                    break;
            case R.id.radio3:
                if (checked)
                    r2.setChecked(false);
                r1.setChecked(false);
                    s1="ABSENT";
                    // Pirates are the best
                    break;
            case R.id.radio4:
                if (checked)
                    r5.setChecked(false);
                r6.setChecked(false);
                    s2="PRESENT";
                // Pirates are the best
                break;
            case R.id.radio5:
                if (checked)
                    r4.setChecked(false);
                r6.setChecked(false);
                    s2="CANCELLED";
                // Pirates are the best
                break;
            case R.id.radio6:
                if (checked)
                    r5.setChecked(false);
                r4.setChecked(false);
                    s2="ABSENT";
                // Pirates are the best
                break;
            case R.id.radio7:
                if (checked)
                    r8.setChecked(false);
                r9.setChecked(false);
                    s3="PRESENT";
                // Pirates are the best
                break;
            case R.id.radio8:
                if (checked)
                    r7.setChecked(false);
                r9.setChecked(false);
                    s3="CANCELLED";
                // Pirates are the best
                break;
            case R.id.radio9:
                if (checked)
                    r8.setChecked(false);
                r7.setChecked(false);
                    s3="ABSENT";
                // Pirates are the best
                break;
        }

    }
}
