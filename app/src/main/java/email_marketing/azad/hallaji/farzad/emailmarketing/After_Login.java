package email_marketing.azad.hallaji.farzad.emailmarketing;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import email_marketing.azad.hallaji.farzad.emailmarketing.internet.HttpManager;
import email_marketing.azad.hallaji.farzad.emailmarketing.internet.RequestPackage;

public class After_Login extends AppCompatActivity {

    Button logout_btn;
    Button send_email;
    Button observe_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afrter_login);

        send_email=(Button)findViewById(R.id.sendemailbutton);
        observe_btn=(Button)findViewById(R.id.reportbutton);
        logout_btn = (Button) findViewById(R.id.logoutbtn);


        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(After_Login.this,Make_Email.class);
                startActivity(intent);
            }
        });
        observe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(After_Login.this,Report.class);
                startActivity(intent);
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isOnline()){
                    RequestPackage p = new RequestPackage();
                    p.setMethod("POST");
                    p.setUri(MainActivity.addresehttp);
                    p.setParam("type","6");
                    p.setParam("uid",MainActivity.user.getID());
                    LogoutAsyncTask logoutAsyncTask =new LogoutAsyncTask();
                    logoutAsyncTask.execute(p);
                }else {
                    Toast.makeText(getApplicationContext(),"Network isn't available",Toast.LENGTH_LONG).show();
                }
            }
        });



    }
    private class LogoutAsyncTask extends AsyncTask<RequestPackage, String, String> {

        protected String doInBackground(RequestPackage... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(After_Login.this,MainActivity.class);
            startActivity(intent);
        }
    }
    private boolean isOnline() {
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo !=null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        return false;
    }
}
