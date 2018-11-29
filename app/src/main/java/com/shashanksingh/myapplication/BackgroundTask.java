package com.shashanksingh.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;




/**
 * Created by Adboss on 11/22/2016.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;
    public BackgroundTask(Context ctx)
    {
        this.ctx=ctx;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected String doInBackground(String... params) {



        String reg_url="";
        //reg_url = "C:/wamp/www/details/customerRegistration-db.php";
       //String reg_url="http://192.168.0.4/details/customerRegistration-db.php";

        String method=params[0];
        if (method.equals("register"))
        {
            String Customer_name=params[1];
            String Customer_mobile=params[2];
            String Customer_email=params[3];
            String Customer_image=params[4];

            String Customer_type=params[5];
            if(Customer_type.equals("1")) {
                reg_url = "http://192.168.0.102/details/customer.php";
            }
            else if(Customer_type.equals("2")) {
                reg_url = "http://192.168.0.102/details/customer2.php";
            }
            else if(Customer_type.equals("3")){
                reg_url = "http://192.168.0.102/details/customer3.php";

            }

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                //encode data before send it
                //no space permiteted in equals sign
                String data= URLEncoder.encode("Customer_name","UTF-8")+"="+ URLEncoder.encode(Customer_name,"UTF-8")+"&"+
                        URLEncoder.encode("Customer_mobile","UTF-8")+"="+ URLEncoder.encode(Customer_mobile,"UTF-8")+"&"+
                        URLEncoder.encode("Customer_email","UTF-8")+"="+ URLEncoder.encode(Customer_email,"UTF-8")+"&"+
                        URLEncoder.encode("Customer_image","UTF-8")+"="+ URLEncoder.encode(Customer_image,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                //get response from server
                InputStream is=httpURLConnection.getInputStream();
                is.close();
                return "registration.....success";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }

        }


        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);


        if (result.equals("Login Success...Welcome"))
        {
            Toast.makeText(ctx, "Welcome", Toast.LENGTH_SHORT).show();


        }


    }
}
