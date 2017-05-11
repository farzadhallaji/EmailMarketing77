package email_marketing.azad.hallaji.farzad.emailmarketing;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import email_marketing.azad.hallaji.farzad.emailmarketing.helper.TableItemListViewAdapter;
import email_marketing.azad.hallaji.farzad.emailmarketing.helper.TableItemListViewAdapter2;
import email_marketing.azad.hallaji.farzad.emailmarketing.internet.HttpManager;
import email_marketing.azad.hallaji.farzad.emailmarketing.internet.RequestPackage;
import email_marketing.azad.hallaji.farzad.emailmarketing.model.ListPersonItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Make_Email extends AppCompatActivity {


    public static int selectedList_ListItem =-1;
    public static int selectedTemplate_ListItem =-1;
    int mohtavasijorsanan=1;
    int form=1;
    int idlistpos=0;

    ////////////////////

    LinearLayout mohtavaye1;
    ListView templatelistview;

    RelativeLayout mohtavaye2;
    ListView listViewcontacts;

    RelativeLayout mohtavaye3;
    ProgressBar progressbarsendemailobject;
    TextView textViewresult;

    RelativeLayout asad;
    TextView headertext;

    Button backBtn;
    Button nextBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_email);

        initialview();
        TemplateRecv();
        navigationintentbutton();

    }

    private void navigationintentbutton() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mohtavasijorsanan==1){
                    Intent intent = intent = new Intent(Make_Email.this, After_Login.class);
                    startActivity(intent);
                }
                else if(mohtavasijorsanan==2){
                    TemplateRecv();
                    nextBtn.setText("Continue");
                    mohtavaye1.setVisibility(View.VISIBLE);
                    mohtavaye2.setVisibility(View.GONE);
                    mohtavaye3.setVisibility(View.GONE);
                    headertext.setText("Choose your favorit template");
                }else if (mohtavasijorsanan==3){
                    mohtavaye1.setVisibility(View.GONE);
                    mohtavaye2.setVisibility(View.VISIBLE);
                    mohtavaye3.setVisibility(View.GONE);
                    nextBtn.setText("Continue");
                    headertext.setText("Choose your List");
                    UpdateRecieveListPerson();
                }
                mohtavasijorsanan--;
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mohtavasijorsanan==1 &&  isOnline()){
                    mohtavaye1.setVisibility(View.GONE);
                    mohtavaye2.setVisibility(View.VISIBLE);
                    mohtavaye3.setVisibility(View.GONE);
                    backBtn.setVisibility(View.VISIBLE);
                    headertext.setText("Choose your List");
                    UpdateRecieveListPerson();
                    nextBtn.setText("Continue");
                }else if(mohtavasijorsanan==1 && !isOnline()){
                    Toast.makeText(getApplicationContext(), "Network isn't available", Toast.LENGTH_LONG).show();
                    mohtavasijorsanan--;
                }else if(mohtavasijorsanan==2){
                    mohtavaye1.setVisibility(View.GONE);
                    mohtavaye2.setVisibility(View.GONE);
                    mohtavaye3.setVisibility(View.VISIBLE);
                    backBtn.setVisibility(View.GONE);
                    nextBtn.setText("Home");
                    headertext.setText("");
                    progressbarsendemailobject.setVisibility(View.VISIBLE);
                    //textViewresult.setText("test shod");
                    Log.i("asad manam", "man asadam1");
                    //TODO asyncTask send Object
                    if (isOnline()) {
                        SendEmailVaFormVaRecievers(MainActivity.addresehttp);
                    } else {
                        Toast.makeText(getApplicationContext(), "Network isn't available", Toast.LENGTH_LONG).show();
                        mohtavasijorsanan--;
                    }
                    Log.i("SendEmail","clicked");
                }else if(mohtavasijorsanan==3){
                    Log.i("asad manam", "man asadam2");
                    Intent intent = new Intent(Make_Email.this,After_Login.class);
                    startActivity(intent);
                }
                mohtavasijorsanan++;
            }
        });
    }

    private void UpdateRecieveListPerson() {

        RequestPackage request =new RequestPackage();

        request.setMethod("POST");
        request.setUri(MainActivity.addresehttp);

        request.setParam("type","3");
        request.setParam("uid",MainActivity.user.getID());
        request.setParam("lid",MainActivity.user.getID());
        request.setParam("tid",MainActivity.user.getID());

        ReciveListAsyncktas task = new ReciveListAsyncktas();
        task.execute(request);

    }

    private void TemplateRecv() {

        RequestPackage request =new RequestPackage();

        request.setMethod("POST");
        request.setUri(MainActivity.addresehttp);

        request.setParam("type","4");
        request.setParam("uid",MainActivity.user.getID());

        ReciveTemplateListAsyncktas task = new ReciveTemplateListAsyncktas();
        task.execute(request);
    }

    private void SendEmailVaFormVaRecievers(String uri) {

        RequestPackage request =new RequestPackage();

        request.setMethod("POST");
        request.setUri(uri);
        request.setParam("type","2");
        request.setParam("uid",MainActivity.user.getID());
        request.setParam("tid",String.valueOf(form));
        request.setParam("lid",String.valueOf(idlistpos));

        SendEmailTask task = new SendEmailTask();
        task.execute(request);
    }

    private boolean isOnline() {
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

        if(networkInfo !=null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        return false;
    }

    private void initialview() {


        backBtn=(Button)findViewById(R.id.backbutton);
        nextBtn=(Button)findViewById(R.id.nextbutton);

        mohtavaye1=(LinearLayout) findViewById(R.id.mohtavaye1);
        templatelistview=(ListView)findViewById(R.id.templatelistview);
        mohtavaye2=(RelativeLayout) findViewById(R.id.mohtavaye2);
        mohtavaye3=(RelativeLayout) findViewById(R.id.mohtavaye3);
        asad=(RelativeLayout)findViewById(R.id.asad);

        headertext=(TextView)findViewById(R.id.headertext);

        listViewcontacts=(ListView)findViewById(R.id.contactslistview);
        progressbarsendemailobject=(ProgressBar)findViewById(R.id.progressbarsendemailobject);
        textViewresult=(TextView)findViewById(R.id.resultsendemailobjtextview);

        mohtavaye1.setVisibility(View.VISIBLE);
        mohtavaye2.setVisibility(View.GONE);
        mohtavaye3.setVisibility(View.GONE);

    }


    private class SendEmailTask extends AsyncTask<RequestPackage, String, String> {

        @Override
        protected String doInBackground(RequestPackage... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {

            progressbarsendemailobject.setVisibility(View.INVISIBLE);
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            try {
                JSONObject jsonObject = new JSONObject(result);
                //textViewresult.setText("Result : "+ jsonObject.get("res")+"\n"+"Total : "+ jsonObject.get("tot"));
/*
                AlertDialog dialog=new AlertDialog.Builder(Make_Email.this)
                        .setTitle("  Getting :) ")
                        .setMessage("\tTotal Send Email : "+jsonObject.get("res")+
                                "\nSuccess Recieved Email : "+jsonObject.get("tot")+"\n")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue
                            }
                        }).show();
*/
                AlertDialog.Builder builder = new AlertDialog.Builder(Make_Email.this, R.style.MyAlertDialogStyle);
                builder.setTitle("\t\t\t\t\t\tGetting :)");
                builder.setMessage("\t\t\t\tTotal Send Email : "+jsonObject.get("res")+
                        "\n\t\t\t\tSuccess Recieved Email : "+jsonObject.get("tot")+"\n");
                builder.setPositiveButton("OK", null);
                builder.show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
             selectedList_ListItem =-1;
             selectedTemplate_ListItem =-1;
             form=1;
             idlistpos=0;
        }

    }


    private class ReciveListAsyncktas extends AsyncTask<RequestPackage, String, String> {

        @Override
        protected void onPostExecute(String result) {

            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();


            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray idsobjson = jsonObject.getJSONArray("ids");
                JSONArray namesobjson = jsonObject.getJSONArray("names");


                List<ListPersonItem> templist=new ArrayList<>();
                ListPersonItem te = new ListPersonItem("ID","Name");
                templist.add(te);
                for(int i = 0 ; i < idsobjson.length() ; i++){

                    ListPersonItem temp = new ListPersonItem(idsobjson.get(i).toString(),namesobjson.get(i).toString());
                    templist.add(temp);
                }

                //MainActivity.user.setListcontacts(templist);
                final TableItemListViewAdapter tableItemListViewAdapter =
                        new TableItemListViewAdapter(Make_Email.this,templist);

                listViewcontacts.setAdapter(tableItemListViewAdapter);
                listViewcontacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(position!=0) {
                            selectedList_ListItem = position;
                            listViewcontacts.setAdapter(tableItemListViewAdapter);
                            idlistpos = position - 1;
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        protected String doInBackground(RequestPackage... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }
    }
    private class ReciveTemplateListAsyncktas extends AsyncTask<RequestPackage, String, String> {

        @Override
        protected String doInBackground(RequestPackage... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray idsobjson = jsonObject.getJSONArray("ids");
                JSONArray namesobjson = jsonObject.getJSONArray("names");


                List<ListPersonItem> templist=new ArrayList<>();
                ListPersonItem te = new ListPersonItem("ID","Name");
                templist.add(te);

                for(int i = 0 ; i < idsobjson.length() ; i++){

                    ListPersonItem temp = new ListPersonItem(idsobjson.get(i).toString(),namesobjson.get(i).toString());
                    templist.add(temp);
                }

                final TableItemListViewAdapter2 tableItemListViewAdapter =
                        new TableItemListViewAdapter2(Make_Email.this,templist);

                templatelistview.setAdapter(tableItemListViewAdapter);
                templatelistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(position!=0){
                            selectedTemplate_ListItem=position;
                            templatelistview.setAdapter(tableItemListViewAdapter);
                            form= position-1;
                        }
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
