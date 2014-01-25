package com.example.sms;

public class ProviderSpinner {
    private String providername;
   
    public ProviderSpinner(String name)
    {
       providername = name;
     
    }
    
    public String getproviderName()
    {
        return providername;
    }

   
    public String toString()
    {
        return providername;
    }
}