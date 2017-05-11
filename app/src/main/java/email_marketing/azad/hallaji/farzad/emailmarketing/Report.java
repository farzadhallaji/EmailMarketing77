package email_marketing.azad.hallaji.farzad.emailmarketing;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import email_marketing.azad.hallaji.farzad.emailmarketing.helper.report_listshow;
import email_marketing.azad.hallaji.farzad.emailmarketing.internet.HttpManager;
import email_marketing.azad.hallaji.farzad.emailmarketing.internet.RequestPackage;
import email_marketing.azad.hallaji.farzad.emailmarketing.model.ReportItem;

public class Report extends AppCompatActivity {

    public static int selected_item_report=-1;
    String from, to;
    private int year, month, day;
    LinearLayout laylingereftanedate;
    TextView from_textView;
    TextView to_textView;
    Button reportbutton;

    LinearLayout layrellistviewreport;
    Button Homebuttonfromreport;
    ListView listviewreport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        initialviews();

    }

    private void initialviews() {
        laylingereftanedate = (LinearLayout) findViewById(R.id.laylingereftanedate);
        from_textView = (TextView) findViewById(R.id.fromTextView);
        to_textView = (TextView) findViewById(R.id.toTextView);

        reportbutton = (Button) findViewById(R.id.reportbuttoninreport);
        Homebuttonfromreport = (Button) findViewById(R.id.Homebuttonfromreport);
        listviewreport = (ListView) findViewById(R.id.listviewreport);
        layrellistviewreport = (LinearLayout) findViewById(R.id.layrellistviewreport);

        laylingereftanedate.setVisibility(View.VISIBLE);
        layrellistviewreport.setVisibility(View.GONE);


        gereftaneDate();

        reportbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendreportrequest();
                laylingereftanedate.setVisibility(View.GONE);
                layrellistviewreport.setVisibility(View.VISIBLE);

            }
        });

        Homebuttonfromreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Report.this, After_Login.class);
                startActivity(intent);
            }
        });
    }
    @SuppressWarnings("deprecation")
    private void gereftaneDate() {

        to_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(998);

            }
        });
        from_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(999);

            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    FromDateListener, year, month, day);
        }else if(id == 998){

            return new DatePickerDialog(this,
                    ToDateListener, year, month, day);
        }

        return null;
    }

    private DatePickerDialog.OnDateSetListener FromDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // arg1 = year
                    from=String.valueOf(new StringBuilder(String.valueOf(arg1) + "/"+String.valueOf(arg2)+"/"+String.valueOf(arg3)));
                    from_textView.setText(from);

                }
            };
    private DatePickerDialog.OnDateSetListener ToDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // arg1 = year
                    to= String.valueOf(new StringBuilder(String.valueOf(arg1) + "/"+String.valueOf(arg2)+"/"+String.valueOf(arg3)));
                    to_textView.setText(to);

                }
            };


    private void sendreportrequest() {

        if(isOnline()){
            PostDataDate(MainActivity.addresehttp);
        }else{
            Toast.makeText(getApplicationContext(), "Network isn't available", Toast.LENGTH_LONG).show();
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

    public void PostDataDate(String uri) {

        RequestPackage request =new RequestPackage();


        request.setMethod("POST");
        request.setUri(uri);

        request.setParam("type","55");
        request.setParam("uid",MainActivity.user.getID());
        request.setParam("start",from);
        request.setParam("end",to);


        PostTask task = new PostTask();
        task.execute(request);
    }


    private class PostTask extends AsyncTask<RequestPackage, String, String> {


        @Override
        protected String doInBackground(RequestPackage... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }
        @Override
        protected void onPostExecute(String result) {

            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(result);
                JSONArray timesobjson = jsonObject.getJSONArray("time");
                JSONArray listsobjson = jsonObject.getJSONArray("list");
                JSONArray templatesobjson = jsonObject.getJSONArray("template");
                JSONArray Ssobjson = jsonObject.getJSONArray("s");
                JSONArray Rsobjson = jsonObject.getJSONArray("r");
                JSONArray Csobjson = jsonObject.getJSONArray("c");
                JSONArray Tsobjson = jsonObject.getJSONArray("t");


                final List<ReportItem> templist=new ArrayList<>();
                for(int i = 0 ; i < timesobjson.length() ; i++){

                    ReportItem temp = new ReportItem(timesobjson.get(i).toString()
                            ,listsobjson.get(i).toString(),templatesobjson.get(i).toString()
                            ,Ssobjson.get(i).toString(),Rsobjson.get(i).toString()
                            ,Csobjson.get(i).toString(),Tsobjson.get(i).toString());
                    templist.add(temp);
                }

                listviewreport.setAdapter(new report_listshow(Report.this,templist));

                listviewreport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        selected_item_report=position;
                        listviewreport.setAdapter(new report_listshow(Report.this,templist));


                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }
}
