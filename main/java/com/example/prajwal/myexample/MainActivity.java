package com.example.prajwal.myexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.example.prajwal.myexample.Permissions;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText mURL;
    private Button mGo, mDownload;
    private Context mContext;
    private static final int VERIFY_PERMISSIONS_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mURL = (EditText) findViewById(R.id.input_url);
        mContext = MainActivity.this;
        init();
    }

    /*
     * Check if any field is empty
     */
    private boolean isStringNull(String string){
        Log.d(TAG, "isStringNull: checking string if null.");

        if(string.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * verifiy all the permissions passed to the array
     * @param permissions
     */
    public boolean verifyPermissions(String[] permissions){
        Log.d(TAG, "verifyPermissions: verifying permissions.");

        ActivityCompat.requestPermissions(
                MainActivity.this,
                permissions,
                VERIFY_PERMISSIONS_REQUEST
        );
        return true;
    }

    /**
     * Check an array of permissions
     * @param permissions
     * @return
     */
    public boolean checkPermissionsArray(String[] permissions){
        Log.d(TAG, "checkPermissionsArray: checking permissions array.");

        for(int i = 0; i< permissions.length; i++){
            String check = permissions[i];
            if(!checkPermissions(check)){
                return false;
            }
        }
        return true;
    }

    /**
     * Check a single permission is it has been verified
     * @param permission
     * @return
     */
    public boolean checkPermissions(String permission){
        Log.d(TAG, "checkPermissions: checking permission: " + permission);

        int permissionRequest = ActivityCompat.checkSelfPermission(MainActivity.this, permission);

        if(permissionRequest != PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "checkPermissions: \n Permission was not granted for: " + permission);
            return false;
        }
        else{
            Log.d(TAG, "checkPermissions: \n Permission was granted for: " + permission);
            return true;
        }
    }

    /*
     * initialize fields
     */
    private void init() {

        if(checkPermissionsArray(Permissions.PERMISSIONS)){

        }else{
            verifyPermissions(Permissions.PERMISSIONS);

        }

        mGo = (Button) findViewById(R.id.btn_url);
        mGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: attempting to open URL.");

                String URL = mURL.getText().toString();

                if(isStringNull(URL)){
                    Toast.makeText(mContext, "You must fill out all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d(TAG, "onComplete: success.");
                    Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                    intent.putExtra("URL", URL);
                    startActivity(intent);
                }

            }
        });


        mDownload = (Button) findViewById(R.id.btn_download);
        mDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Downloading a pdf file");

                Toast.makeText(mContext, "Attempting to download", Toast.LENGTH_SHORT).show();

                URL fileurl = null;
                try {
                    fileurl = new URL("http://cs229.stanford.edu/notes/cs229-notes1.pdf");
                }catch (MalformedURLException e) {
                    Log.e(TAG, "OnClickListener: MalformedURLException" + e.getMessage());
                }

                new DownloadFileTask().execute(fileurl);

            }
        });

    }

    //Download
    private class DownloadFileTask extends AsyncTask<URL, Void, Void> {

        URL url = null;
        File filePath = null;

        private Void DownloadFile(URL url) {
            try {

                Log.d(TAG, "creating folder");
                String root = Environment.getExternalStorageDirectory().getPath();
                File myDir = new File(root + "/Download");

                Log.d(TAG, "creating folder complete " + myDir);

                if (!myDir.exists()) {
                    myDir.mkdirs();
                }
                String name = new SimpleDateFormat("yyyyMMddHHmm'.pdf'").format(new Date());
                filePath = new File(myDir + File.separator + name);
                if (filePath.createNewFile()) {
                    try {
                        HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
                        c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
                        c.connect();//connect the URL Connection
                        FileOutputStream fos = new FileOutputStream(filePath);//Get OutputStream for NewFile Location

                        InputStream is = c.getInputStream();//Get InputStream for connection

                        byte[] buffer = new byte[1024];//Set buffer type
                        int len1 = 0;//init length
                        while ((len1 = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, len1);//Write new file
                        }

                        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        Uri contentUri = Uri.fromFile(filePath);
                        mediaScanIntent.setData(contentUri);
                        sendBroadcast(mediaScanIntent);

                        //Close all connection after doing task
                        fos.close();
                        is.close();
                    } catch (Exception e) {
                        //Read exception if something went wrong
                        e.printStackTrace();
                        Log.e(TAG, "Download Error Exception " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                Log.d(TAG, "onClick: IOException " + e.getMessage());
            }
            return null;
        }

        @Override
        protected Void doInBackground(URL... urls) {
            this.url = urls[0];
            DownloadFile(url);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(mContext, "Download Complete.", Toast.LENGTH_SHORT).show();
        }
    }
}
