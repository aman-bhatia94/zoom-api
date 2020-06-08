package services;

import models.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataDDLService {

    public void createAllTables(Connection connection) {

        //Creating all tables
        Channels channels = new Channels();
        ChannelMembership channelMembership = new ChannelMembership();
        Credentials credentials = new Credentials();
        Messages messages = new Messages();

        ArrayList<Object> tables = new ArrayList<>();
        tables.add(credentials);
        tables.add(channels);
        tables.add(channelMembership);
        tables.add(messages);
        GenericTableCreator genericTableCreator = new GenericTableCreator();
        for (Object table : tables) {
            String sql = genericTableCreator.create(table);
            try {
                Statement statement = connection.createStatement();
                statement.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
