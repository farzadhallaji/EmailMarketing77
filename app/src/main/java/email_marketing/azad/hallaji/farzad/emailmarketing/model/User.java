package email_marketing.azad.hallaji.farzad.emailmarketing.model;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import email_marketing.azad.hallaji.farzad.emailmarketing.internet.HttpManager;
import email_marketing.azad.hallaji.farzad.emailmarketing.internet.RequestPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by farzad on 10/10/2016...
 *
 */
public class User {

    private String UserName="";
    private String PassWord="";
    private String ID;
    private String UT;
    private String Lt;
    private String Fn;
    private String LIP;


    public User(String userName, String passWord) {
        UserName = userName;
        PassWord = passWord;
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUT() {
        return UT;
    }
    public void setUT(String UT) {
        this.UT = UT;
    }

    public String getLt() {
        return Lt;
    }
    public void setLt(String lt) {
        Lt = lt;
    }

    public String getLIP() {
        return LIP;
    }
    public void setLIP(String LIP) {
        this.LIP = LIP;
    }

    public String getFn() {
        return Fn;
    }
    public void setFn(String fn) {
        Fn = fn;
    }

    public String getUserName() {
        return UserName;
    }
    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }
    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    /*
    private List<ListPersonItem> listcontacts ;
    private List<String > listtemplate;

    public List<ListPersonItem> getListcontacts() {
        return listcontacts;
    }

    public void setListcontacts(List<ListPersonItem> listcontacts) {
        this.listcontacts = listcontacts;
    }

    public List<String> getListtemplate() {
        return listtemplate;
    }

    public void setListtemplate(List<String> listtemplate) {
        this.listtemplate = listtemplate;
    }

    public List<EmailObj> email_Obj_list=new ArrayList<>();
    public List<List<Person>> mashmulinemail=new ArrayList<>();
    public List<Person> ListeMoxatebin=new ArrayList<>();


    public List<List<Person>> getMashmulinemail() {
        return mashmulinemail;
    }

    public void setMashmulinemail(List<List<Person>> mashmulinemail) {
        this.mashmulinemail = mashmulinemail;
    }

    public void addMashmulinemail(List<Person> asad){
        this.mashmulinemail.add(asad);
    }

    public List<EmailObj> getEmail_Obj_list() {
        return email_Obj_list;
    }

    public void setEmail_Obj_list(List<EmailObj> email_Obj_list) {
        this.email_Obj_list = email_Obj_list;
    }

    public List<Person> getListeMoxatebin() {
        return ListeMoxatebin;
    }

    public void setListeMoxatebin(List<Person> listeMoxatebin) {
        ListeMoxatebin = listeMoxatebin;
    }
    public boolean addToListeMoxatebin(Person Moxateb) {
        if (ListeMoxatebin.add(Moxateb)) return true;
        else return false;
    }

    public boolean addToListeEmail(EmailObj Moxateb) {
        if (email_Obj_list.add(Moxateb)) return true;
        else return false;
    }

    public boolean removeFromListeMoxatebin(Person Moxateb) {
        if (ListeMoxatebin.remove(Moxateb)) return true;
        else return false;
    }
    public void tayideNahayiSend(){

        RequestPackage p = new RequestPackage();
        p.setMethod("POST");
        p.setUri("comtech http ");

        p.setParam("UserName",UserName);
        p.setParam("PassWord", PassWord);

        MyTask task = new MyTask();
        task.execute(p);

    }

    public JSONObject getJsonObject() {
        //TODO tabdile user be json (Last object)
        return null;
    }

    private class MyTask extends AsyncTask<RequestPackage, String, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(RequestPackage... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {

            //output.setText(result+"1");

            if(result.contains("")){     //moshkel*****


            }else{
                //updateDisplay(result);

                Log.i("TaG","0"+result+"1");
            }
        }
    }*/
}
