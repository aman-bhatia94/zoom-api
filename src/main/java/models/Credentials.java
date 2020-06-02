package models;


public class Credentials implements CreateTableInterface {


    @Override
    public String create() {
        //this will generate the create table sql for this table
        String createTableSQL = "CREATE TABLE IF NOT EXISTS credentials (\n"
                + "	id integer PRIMARY KEY AUTO INCREMENT,\n"
                + "	client_id VARCHAR(200) NOT NULL,\n"
                + "	oauth_token VARCHAR(200) NOT NULL\n"
                + ");";

        return createTableSQL;
    }
}
