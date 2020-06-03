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

        ArrayList<CreateTableInterface> tables = new ArrayList<>();
        tables.add(credentials);
        tables.add(channels);
        tables.add(channelMembership);
        //tables.add(messages);
        GenericTableCreator genericTableCreator = new GenericTableCreator();
        for(int i = 0; i < tables.size(); i++){
            String sql = genericTableCreator.create(tables.get(i));
            try {
                Statement statement = connection.createStatement();
                System.out.println(sql);
                statement.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DataDDLService c = new DataDDLService();
        Connection conn = DatabaseConnectionService.connect();
        c.createAllTables(conn);
    }

}
