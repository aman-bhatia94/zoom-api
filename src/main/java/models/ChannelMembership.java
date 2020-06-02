package models;

public class ChannelMembership implements CreateTableInterface {

    @Override
    public String create() {
        //this will generate the create table sql for this table
        String createTableSQL = "CREATE TABLE IF NOT EXISTS channel_membership (\n"
                + "	id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "	channel_id VARCHAR(200) NOT NULL,\n"
                + " member_id  VARCHAR(200) NOT NULL,\n"
                + " first_name VARCHAR(50) NOT NULL,\n"
                + " last_name VARCHAR(50) NOT NULL,\n"
                + "	email VARCHAR(200) NOT NULL,\n"
                + " role VARCHAR(10)  NOT NULL\n"
                + ");";

        return createTableSQL;
    }
}
