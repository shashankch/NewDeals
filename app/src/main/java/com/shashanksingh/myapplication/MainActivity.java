package com.shashanksingh.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText n, e;
    EditText num;
    String name1, mobile1, email1, image1;
    Button bt;
    String imgDecodableString;
    String encodedImage;
    ImageView imgView;
    //private static final int RESULT_LOAD_IMG = 1;
    LinearLayout linearLayout;
     RelativeLayout relativeLayout;
    private ListView mDrawerList;
    private DrawerLayout drawerLayout;
    private ArrayAdapter<String> mAdapter;
 int pos;

    public void clickfun(View view){
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n = (EditText) findViewById(R.id.name);
        e = (EditText) findViewById(R.id.email);
        num = (EditText) findViewById(R.id.number);
        bt = (Button) findViewById(R.id.submit);
        bt.setOnClickListener(this);
        imgView = (ImageView) findViewById(R.id.imageView);
        //relativeLayout=(RelativeLayout)findViewById(R.id.rel);
         linearLayout=(LinearLayout)findViewById(R.id.lin);
       relativeLayout=(RelativeLayout) findViewById(R.id.rel);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);

        mDrawerList = (ListView) findViewById(R.id.navList);
        final String[] osArray = {"Add products by type:","Electronics", "Fashion", "Others","view products", "About us"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerLayout.closeDrawers();
                if(position!=0) {
                    pos=position;
                    n.setText("");
                    e.setText("");
                    num.setText("");
                    imgView.setImageResource(R.drawable.shop);
                    linearLayout.setVisibility(View.VISIBLE);
                    relativeLayout.setVisibility(View.INVISIBLE);
                    getSupportActionBar().setTitle(osArray[position]);


                    if(position==4){
                        linearLayout.setVisibility(View.INVISIBLE);
                        relativeLayout.setVisibility(View.VISIBLE);
                    }
                    if(position==5){
                        Intent intent=new Intent(MainActivity.this,Main23Activity.class);
                        startActivity(intent);
                    }

            }}


        });
    }








    @Override
    public void onClick(View v) {



            register();






    }


    public void register() {

        //validation

         if (n.getText().toString().length() == 0) {
            n.setError("title can not be blanked");
            return;
        }
        else if (num.getText().toString().length() == 0) {
            num.setError("please enter some description");
            return;
        } //
        // else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(e.getText().toString()).matches()) {
        //Validation for Invalid Email Address
        else if (e.getText().toString().length() == 0) {

            e.setError("enter product price");
            return;
        } else {
            Toast.makeText(this, "All Fields Validated", Toast.LENGTH_SHORT).show();
        }




//validation END

        name1 = n.getText().toString().trim();
        mobile1 = num.getText().toString().trim();
        email1 = e.getText().toString().trim();
        Toast.makeText(this, "result" + name1 + " " + mobile1 + " " + email1, Toast.LENGTH_SHORT).show();
        String method = "register";
        if (isOnline()) {

            Toast.makeText(this, "product added successfully!", Toast.LENGTH_SHORT).show();
            linearLayout.setVisibility(View.INVISIBLE);
            relativeLayout.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("Home");

            try {

                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method, name1, mobile1, email1, encodedImage, Integer.toString(pos));
            }
            catch (Exception e){
               e.printStackTrace();
            }}


         else {
            Toast.makeText(this, "Server Connection offline", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }


    public void takePicture(View View) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//MediaStore is type of dtabse whwere image and video storeed.
      /*  imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Test.jpg");//directory path and file name two argument in file
        Toast.makeText(LabourRegistration.this, "Picture Clicked" + imageFile, Toast.LENGTH_SHORT).show();

        //generate path Uri
        Uri value = Uri.fromFile(imageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, value);//Extraoutput show path for saving file
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);//define image quality 0 for low and 1 for high quality
        */
        startActivityForResult(intent, 0);
    }

    public void browseImage(View v) {

// Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        galleryIntent.putExtra("crop", "true");
        galleryIntent.putExtra("outputX", 200);
        galleryIntent.putExtra("outputY", 260);
        galleryIntent.putExtra("aspectX", 1);
        galleryIntent.putExtra("aspectY", 1);
        galleryIntent.putExtra("scale", true);
        galleryIntent.putExtra("return-data", true);
// Start the Intent
        startActivityForResult(galleryIntent, 0);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == 0) {
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // Toast.makeText(this, "Picture saved at " + imageFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                        Toast.makeText(this, "ImageSet", Toast.LENGTH_SHORT).show();
                        imgView.setImageBitmap(thumbnail);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        if (thumbnail != null) {
                            thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object//0 for low quality
                        }
                        byte[] b = baos.toByteArray();
                        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                        Toast.makeText(this, "Wait for moment ....", Toast.LENGTH_SHORT).show();
                        break;

                    case Activity.RESULT_CANCELED:
                        Toast.makeText(this, "Activity.RESULT_CANCELLED", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;


                }

            }//onActivityCamera-END
            if (requestCode == 0 && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                if (cursor != null) {
                    cursor.moveToFirst();
                }

                int columnIndex = 0;
                if (cursor != null) {
                    columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                }
                if (cursor != null) {
                    imgDecodableString = cursor.getString(columnIndex);
                }
                if (cursor != null) {
                    cursor.close();
                }
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));
                //imageUploadSTART

                Bitmap bm = BitmapFactory.decodeFile(imgDecodableString);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object//0 for low quality
                byte[] b = baos.toByteArray();
                encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                Toast.makeText(this, "ImageSet", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Wait for moment ....", Toast.LENGTH_SHORT).show();
                Log.d("error", "images" + encodedImage);
                //close
            }
        } catch (Exception e) {

            e.printStackTrace();
            //Toast.makeText(this, "Problem Detected!", Toast.LENGTH_LONG)
                    //.show();
        }
    }
}


//-onActivityResultclosed-source END



