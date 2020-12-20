package com.example.aplus.listings;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplus.R;
import com.example.aplus.home.ui.dashboard.DashboardFragment;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class newListing extends AppCompatActivity {
    String sessionUser;
    EditText editTextTitle, editTextColor, editTextSize, editTextGender, editTextitemdesc, editTextQuantity, editTextPrice;
    Button buttonSubmit;

    Button buttonbrowse;
    ImageView img;
    String encodedImageString;
    Bitmap bitmap;

    private static final String url = "https://www.psuwal.com/aplus/uploadconfirm.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newlisting);

        SharedPreferences sharedPreferences = getSharedPreferences("SavedPreferences", Context.MODE_PRIVATE);
        sessionUser = sharedPreferences.getString("Username", "");
        Toast.makeText(this, sessionUser, Toast.LENGTH_SHORT).show();

        editTextTitle = findViewById(R.id.itemtitle);
        editTextTitle.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        editTextColor = findViewById(R.id.itemcolor);
        editTextColor.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        editTextSize = findViewById(R.id.itemsize);
        editTextSize.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        editTextGender = findViewById(R.id.itemgender);
        editTextGender.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        editTextitemdesc = findViewById(R.id.itemdescription);
        editTextitemdesc.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        editTextQuantity = findViewById(R.id.itemquantity);
        editTextQuantity.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        editTextPrice = findViewById(R.id.itemprice);
        editTextPrice.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        img = findViewById(R.id.imgView);
        buttonbrowse = findViewById(R.id.buttonLoadPicture);
        buttonbrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(newListing.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent  = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Browse Image"), 1);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
            }
        });

        buttonSubmit = findViewById(R.id.btnadditem);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title, color, size, gender, description, quantity, price;
                title = editTextTitle.getText().toString();
                color = editTextColor.getText().toString();
                size = editTextSize.getText().toString();
                gender = editTextGender.getText().toString();
                description = editTextitemdesc.getText().toString();
                quantity = editTextQuantity.getText().toString();
                price = editTextPrice.getText().toString();

                if(title.equals("")){
                    editTextTitle.setError("Title can't be EMPTY");
                    editTextTitle.requestFocus();
                }else if(color.equals("")){
                    editTextColor.setError("color can't be EMPTY");
                    editTextColor.requestFocus();
                }else if(size.equals("")){
                    editTextSize.setError("Size can't be EMPTY");
                    editTextSize.requestFocus();
                }else if(gender.equals("")){
                    editTextGender.setError("Gender can't be EMPTY");
                    editTextGender.requestFocus();
                }else if(description.equals("")){
                    editTextitemdesc.setError("Description can't be EMPTY");
                    editTextitemdesc.requestFocus();
                }else if(quantity.equals("")) {
                    editTextQuantity.setError("Quantity can't be EMPTY");
                    editTextQuantity.requestFocus();
                }else if(price.equals("")) {
                    editTextPrice.setError("Price can't be EMPTY");
                    editTextPrice.requestFocus();
                }else{
                    uploadDataToDb(title, color, size, gender, description, quantity, price);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri filepath = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
                encodeBitmapImage(bitmap);
            }catch(Exception ex){

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void encodeBitmapImage(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);

        byte[] bytesOfImage = byteArrayOutputStream.toByteArray();
        encodedImageString = android.util.Base64.encodeToString(bytesOfImage, Base64.DEFAULT);
    }

    private void uploadDataToDb(String title, String color, String size, String gender, String desc, String qty, String price) {
        SharedPreferences sharedPreferences = getSharedPreferences("SavedPreferences", Context.MODE_PRIVATE);
        sessionUser = sharedPreferences.getString("Username", "");

        final String name = sessionUser;


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                editTextTitle.setText("");
                editTextColor.setText("");
                editTextSize.setText("");
                editTextGender.setText("");
                editTextitemdesc.setText("");
                editTextQuantity.setText("");
                editTextPrice.setText("");
                img.setImageResource(R.drawable.ic_image);
                Toast.makeText(newListing.this, response.toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), DashboardFragment.class);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(newListing.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("seller", name);
                map.put("title", title);
                map.put("color", color);
                map.put("size", size);
                map.put("gender", gender);
                map.put("description", desc);
                map.put("quantity", qty);
                map.put("price", price);
                map.put("upload", encodedImageString);
                return map;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                8000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}