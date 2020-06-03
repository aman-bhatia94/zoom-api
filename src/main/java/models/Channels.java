package models;

public class Channels implements CreateTableInterface {

    int id;
    String channel_id;
    String channel_name;
    String channel_type;

    @Override
    public String create() {
        //this will generate the create table sql for this table
        String createTableSQL = "CREATE TABLE IF NOT EXISTS channels (\n"
                + "	id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "	channel_id VARCHAR(200) NOT NULL,\n"
                + "	channel_name VARCHAR(200) NOT NULL,\n"
                + " channel_type VARCHAR(10)  NOT NULL\n"
                + ");";

        return createTableSQL;
    }
}
