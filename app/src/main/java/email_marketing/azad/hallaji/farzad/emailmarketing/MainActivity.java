package email_marketing.azad.hallaji.farzad.emailmarketing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import email_marketing.azad.hallaji.farzad.emailmarketing.internet.HttpManager;
import email_marketing.azad.hallaji.farzad.emailmarketing.internet.RequestPackage;
import email_marketing.azad.hallaji.farzad.emailmarketing.model.User;

public class MainActivity extends Activity {

    public static User user=new User(" "," ");
    public static String addresehttp="http://fpn.fpwork.ir/test.php";

    LinearLayout VurudiLoginLineLay;
    EditText user_edit ;
    EditText pass_edit;
    Button login_btn;

    LinearLayout resultLinelay;
    TextView resultTextView;

    LinearLayout LoginXuruji;
    TextView WelcomTextView;
    Button MenuButton;
    Button LogoutButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewinitial();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline()) {
                    user =new User(user_edit.getText().toString(),pass_edit.getText().toString());
                    requestData(addresehttp);

                } else {
                    Toast.makeText(getApplicationContext(), "Network isn't available", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean isOnline() {
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo !=null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        return false;
    }

    private void viewinitial() {

        VurudiLoginLineLay=(LinearLayout)findViewById(R.id.VurudiLoginLineLay);
        user_edit=(EditText)findViewById(R.id.username_edittext) ;
        pass_edit=(EditText)findViewById(R.id.password_edittext);
        login_btn=(Button)findViewById(R.id.loginbutton);

        resultLinelay=(LinearLayout)findViewById(R.id.resultLinelay);
        resultTextView=(TextView)findViewById(R.id.resultTextView) ;

        LoginXuruji=(LinearLayout)findViewById(R.id.LoginXuruji) ;
        WelcomTextView=(TextView)findViewById(R.id.WelcomTextView) ;
        MenuButton=(Button)findViewById(R.id.MenuButton) ;
        LogoutButton=(Button)findViewById(R.id.LogoutButton) ;

        VurudiLoginLineLay.setVisibility(View.VISIBLE);
        LoginXuruji.setVisibility(View.GONE);
        resultLinelay.setVisibility(View.GONE);

    }

    private void requestData(String uri) {

        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri(uri);
        p.setParam("user", user.getUserName());
        p.setParam("pass", user.getPassWord());
        p.setParam("type","1");

        LoginAsyncTask task = new LoginAsyncTask();
        task.execute(p);

    }


    private class LoginAsyncTask extends AsyncTask<RequestPackage, String, String> {


        @Override
        protected String doInBackground(RequestPackage... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {

            JSONObject jsonObject = null;
            String d= " ";
            try {
                jsonObject = new JSONObject(result);
                d=jsonObject.getString("res");
                if(d.equals("1")){
                    String uid =jsonObject.getString("id");
                    user.setID(uid);
                    user.setFn((String)jsonObject.get("fn"));
                    user.setLIP(jsonObject.getString("lip"));
                    user.setLt(jsonObject.getString("lt"));
                    user.setUT(jsonObject.getString("ut"));
                    //Log.i("AAAAAAAAAAAAAreslogin",uid);

                    String date="";
                    Long aLong ;
                    String dd=jsonObject.getString("lt").trim();
                    if (!dd.equals("")){
                        aLong=Long.parseLong(dd);
                        date=new java.text.SimpleDateFormat("MM/dd/yy")
                                .format(new java.util.Date (aLong*1000));
                    }

                    resultTextView.setText("Your IP is : " + user.getLIP() +
                                    "\n"+"Last online time : "+ date);
                    WelcomTextView.setText("Welcome "+ user.getFn());

                    VurudiLoginLineLay.setVisibility(View.GONE);
                    LoginXuruji.setVisibility(View.VISIBLE);
                    resultLinelay.setVisibility(View.VISIBLE);

                    MenuButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this,After_Login.class);
                            startActivity(intent);
                        }
                    });

                    LogoutButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RequestPackage p = new RequestPackage();
                            p.setMethod("POST");
                            p.setUri(addresehttp);
                            p.setParam("type","6");
                            p.setParam("uid",MainActivity.user.getID());

                            LogoutAsyncTask logoutAsyncTask =new LogoutAsyncTask();
                            logoutAsyncTask.execute(p);
                        }
                    });

                }else{
                    resultTextView.setText("Try again !!!");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private class LogoutAsyncTask extends AsyncTask<RequestPackage, String, String> {

        protected String doInBackground(RequestPackage... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            viewinitial();
        }
    }
}
