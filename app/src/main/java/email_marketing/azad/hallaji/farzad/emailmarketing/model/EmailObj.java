package email_marketing.azad.hallaji.farzad.emailmarketing.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by farzad on 10/4/2016.
 */
public class EmailObj {

    private List<Person> receivers=new ArrayList<Person>();
    private String matn_text=" ";
    private int form =0;

    public EmailObj() {
    }

    public EmailObj(List<Person> receivers, String matn_text, int form) {
        this.receivers = receivers;
        this.matn_text = matn_text;
        this.form = form;
    }

    public List<Person> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<Person> receivers) {
        this.receivers = receivers;
    }

    public String getMatn_text() {
        return matn_text;
    }

    public void setMatn_text(String matn_text) {
        this.matn_text = matn_text;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public boolean addReceiver( Person person){
        return receivers.add(person);
    }
    public boolean removReceiver(Person person){
        return receivers.remove(person);
    }
    public boolean Send_atLast(){

        return true;
    }
}
