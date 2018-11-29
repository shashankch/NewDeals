package com.shashanksingh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {
    TextView editText;
    ListView lv;
    public void clickfun(View view){

        int pos=1;
        BackgroundTask2 backgroundTask2=new BackgroundTask2(this,pos);
        backgroundTask2.execute();


    }

    public void onClick2(View view){
        int pos=2;
        BackgroundTask2 background=new BackgroundTask2(this,pos);
        background.execute();
    }
    public void onClick3(View view){
        int pos=3;
        BackgroundTask2 background=new BackgroundTask2(this,pos);
        background.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lv=(ListView)findViewById(R.id.list);
    }


    public class BackgroundTask2 extends AsyncTask<Void,Void,String> {
        String reg_url;
        String response;
        Context ctx;
        StringBuilder stringBuilder;
        String temp="";
        String[]arr;
        int posit;
        public BackgroundTask2(Context ctx,int p)
        {
            this.ctx=ctx;
            posit=p;

        }



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(posit==1){
            reg_url = "http://192.168.0.102/details/fetch.php";

        } else if(posit==2){
                reg_url = "http://192.168.0.102/details/fetch2.php";
        }
        else if(posit==3){
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

          try {
              JSONArray jsonArray=new JSONArray(result);
              int count=jsonArray.length();
              for(int i=0;i<count;i++){
                  JSONObject jsonObject=jsonArray.getJSONObject(i);
                  temp+=jsonObject.getString("name")+":";
              }
              arr=temp.split(":");
              lv.setAdapter(new ArrayAdapter<String>(Main2Activity.this,android.R.layout.simple_list_item_1,arr));
              lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  @Override
                  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                      Intent intent=new Intent(Main2Activity.this,Main22Activity.class);
                      intent.putExtra("position",position);
                      intent.putExtra("type",posit);
                      startActivity(intent);
                  }
              });
          }
          catch (Exception e){

          }


        }
    }




}
