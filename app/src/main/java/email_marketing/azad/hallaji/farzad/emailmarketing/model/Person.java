package email_marketing.azad.hallaji.farzad.emailmarketing.model;

/**
 * Created by farzad on 10/4/2016.
 */
public class Person {

    private String Name;
    private String Email_Address;

    public Person(String name, String email_Address) {
        Name = name;
        Email_Address = email_Address;
    }

    public String getEmail_Address() {
        return Email_Address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail_Address(String email_Address) {
        Email_Address = email_Address;
    }
}
