package com.shashanksingh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main22Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);


        BackgroundTask3 backgroundTask2=new BackgroundTask3(this);
        backgroundTask2.execute();
    }public class BackgroundTask3 extends AsyncTask<Void,Void,String> {
        String reg_url;
        String response;
        Context ctx;
        StringBuilder stringBuilder;
        String temp="";
        String path="";


        public BackgroundTask3(Context ctx)
        {
            this.ctx=ctx;

        }



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Intent in=getIntent();
            int pp=in.getIntExtra("type",-1);
            if(pp==1) {
                reg_url = "http://192.168.0.102/details/fetch.php";
            }
            else if(pp==2){
                reg_url = "http://192.168.0.102/details/fetch2.php";
            }
            else if(pp==3){
                reg_url = "http://192.168.0.102/details/fetch3.php";
            }


        }

        @Override
        protected String doInBackground(Void... params) {

            //String reg_url;
            //reg_url = "C:/wamp/www/details/customerRegistration-db.php";
            // String reg_url="http://10.0.2.2/details/customerRegistration-db.php";


            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                //httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setDoOutput(true);
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                //encode data before send it
                //no space permiteted in equals sign
                //String data= URLEncoder.encode("Customer_name","UTF-8")+"="+ URLEncoder.encode(Customer_name,"UTF-8")+"&"+
                // URLEncoder.encode("Customer_mobile","UTF-8")+"="+ URLEncoder.encode(Customer_mobile,"UTF-8")+"&"+
                //URLEncoder.encode("Customer_email","UTF-8")+"="+ URLEncoder.encode(Customer_email,"UTF-8")+"&"+
                //URLEncoder.encode("Customer_image","UTF-8")+"="+ URLEncoder.encode(Customer_image,"UTF-8");

                //get response from server
                stringBuilder=new StringBuilder();
                while((response=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(response+"\n");
                }
                inputStream.close();
                bufferedReader.close();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
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
            Intent i=getIntent();
            TextView textView=(TextView)findViewById(R.id.data);
            ImageView imageView=(ImageView)findViewById(R.id.img);

            try {
                JSONArray jsonArray=new JSONArray(result);

                int pos=i.getIntExtra("position",-1);

                    JSONObject jsonObject=jsonArray.getJSONObject(pos);
                    temp+="Product:"+"\n"+"              ";
                    temp+=jsonObject.getString("name")+"\n";

                    temp+="price:"+"\n"+"         ";
                temp+=jsonObject.getString("mobile")+"\n";
                temp+="specs:"+"\n"+"           ";
                temp+=jsonObject.getString("email");



                path+=jsonObject.getString("image");

                //String [] arr=path.split("image//");
                //String lp=arr[arr.length-1];
                //arr=path.split("http://192.168.0.4/details/image//");

                byte[] decodedString = Base64.decode(path , Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);


                imageView.setImageBitmap(decodedByte);
             //imageView.setImageURI(Uri.parse(new File(path).toString()));
                textView.setText(temp);

                } catch (JSONException e1) {
                e1.printStackTrace();
            }





            }


        }

    public void buy (View view){

        Intent intent=new Intent(this,Main23Activity.class);
        startActivity(intent);

    }
    }




