package utils;
//This class is in a package utils since the class was present in python version of utils.py
//Ask a question for this design decision
public class ApiClient {

    String baseUri;
    int timeout;
    public ApiClient(String baseUri){
        this.baseUri = baseUri;
        this.timeout = 15;

        //TODO kwargs
    }

    //get timeout
    public int getTimeout(){
        return timeout;
    }

    //set timeout
    public void setTimeout(int value){

        //TODO check for timeout

        this.timeout = value;
    }

    //get base_uri
    public String getBaseUri(){

        return this.baseUri;
    }

    //setting the base uri
    public void setBaseUri(String value){

        if(value != null){
            if(value.endsWith("/")){
                value = value.substring(0,value.length()-1);
            }
        }
        this.baseUri = value;
    }

    //TODO endpoint,requests


}
