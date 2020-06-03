package models;


public class Credentials implements CreateTableInterface {


    int id;
    String client_id;
    String oauth_token;

    @Override
    public String create() {
        //this will generate the create table sql for this table
        String createTableSQL = "CREATE TABLE IF NOT EXISTS credentials (\n"
                + "	id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "	client_id VARCHAR(200) NOT NULL,\n"
                + "	oauth_token VARCHAR(200) NOT NULL\n"
                + ");";

        return createTableSQL;
    }
}
