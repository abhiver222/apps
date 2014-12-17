package com.example.mylastnight;



        import com.google.i18n.phonenumbers.Phonemetadata.NumberFormat;
        import com.google.i18n.phonenumbers.Phonemetadata.PhoneMetadata;
        import com.google.i18n.phonenumbers.Phonemetadata.PhoneMetadataCollection;
        import com.google.i18n.phonenumbers.Phonemetadata.PhoneNumberDesc;
        import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
        import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber.CountryCodeSource;
        import java.util.*;
public class EmergencyContact {

    private TreeMap<String, PhoneNumber> listofcontacts;

    public EmergencyContact()
    {
        listofcontacts = new TreeMap<String, PhoneNumber>();

    }
    public void addContact(String name, long number)
    {
        String modi = name.replaceAll(name, "\\s").toLowerCase();
        PhoneNumber toadd = new PhoneNumber();
        toadd.setRawInput("" + number);
        listofcontacts.put(modi, toadd);
    }

    public PhoneNumber getContacts(String name)
    {
        String modi = name.replaceAll(name, "\\s").toLowerCase();
        return (PhoneNumber)listofcontacts.get(modi);
    }

    public void deleteContact(String name)
    {
        String modi = name.replaceAll(name, "\\s").toLowerCase();
        listofcontacts.remove(modi);
    }

    public void editContact(String name, long number)
    {
        PhoneNumber newnum = new PhoneNumber();
        newnum.setRawInput("" + number);
        String modi = name.replaceAll(name, "\\s").toLowerCase();
        listofcontacts.put(modi, newnum);
    }

    public String toString()
    {
        Iterator it = listofcontacts.keySet().iterator();
        String returning="";
        while(it.hasNext())
        {
            String title = (String)it.next();
            returning += title + ":" + listofcontacts.get(title) + "\n";
        }
        return returning;
    }
}
