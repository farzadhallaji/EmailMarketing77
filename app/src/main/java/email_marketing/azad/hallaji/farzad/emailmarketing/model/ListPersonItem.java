package email_marketing.azad.hallaji.farzad.emailmarketing.model;

/**
 * Created by farzad on 11/15/2016.
 */
public class ListPersonItem {

    private String ID ;
    private String name;

    public ListPersonItem(String ID , String NAme){
        this.ID=ID;
        this.name=NAme;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
