package com.shashanksingh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity{

    EditText first,last,email,mobile,pass,confpass;
    Button save,cancel;
    DatabaseHandler db;


    public void back(View view){
        Intent intent=new Intent(RegisterActivity.this,MainActivitysq.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        first=(EditText)findViewById(R.id.editfirstname);
        last=(EditText)findViewById(R.id.editlastname);
        email=(EditText)findViewById(R.id.editemail);
        mobile=(EditText)findViewById(R.id.editmobileno);
        pass=(EditText)findViewById(R.id.editpassword);
        confpass=(EditText)findViewById(R.id.editconformpassword);

        save=(Button)findViewById(R.id.btnsave);
        cancel=(Button)findViewById(R.id.btncancel);


        save.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0){
                // TODO Auto-generated method stub
                 if(first.getText().toString().length()==0){
                     first.setError("invalid entry");
                 }
                String edfirst=first.getText().toString();
                if(last.getText().toString().length()==0){
                    last.setError("invalid entry");
                }
                String edlast=last.getText().toString();
                 if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                     email.setError("invalid entry");
                 }
                String edemail=email.getText().toString();
                if(mobile.getText().toString().length()>10){
                    mobile.setError("invalid entry");
                }
                String edmobile=mobile.getText().toString();
                if(pass.getText().toString().length()==0){
                    pass.setError("invalid entry");
                }
                String edpass=pass.getText().toString();

                String edConf=confpass.getText().toString();

                if(edConf.equals(edpass)&&(edConf.length()!=0)){


                    db=new DatabaseHandler(RegisterActivity.this,null,null,2);
                    Registerdata reg=new Registerdata();

                    reg.setfirstName(edfirst);
                    reg.setlastName(edlast);
                    reg.setEmailId(edemail);
                    reg.setMobNo(edmobile);
                    reg.setPassword(edpass);
                    db.addregister(reg);

                    Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else{

                    Toast.makeText(getApplicationContext(),"Password doesn't match",Toast.LENGTH_LONG).show();
                    pass.setText("");
                    confpass.setText("");
                }
            }
        });
    }


}


