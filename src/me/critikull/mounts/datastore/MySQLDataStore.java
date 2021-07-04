package me.critikull.mounts.datastore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import me.critikull.mounts.mount.Mount;
import me.critikull.mounts.mount.Mounts;
import me.critikull.mounts.mount.types.MountType;
import me.critikull.mounts.mount.types.MountTypes;

public class MySQLDataStore implements IDataStore {
    private final Connection conn;

    public MySQLDataStore(String host, int port, String database, String user, String password) throws SQLException {
        this.conn = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", new Object[] { host, Integer.valueOf(port), database }), user, password);
        createTables();
    }

    private void createTables() throws SQLException {
        Statement stmt = this.conn.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS mounts_mount_types (id int NOT NULL AUTO_INCREMENT,type varchar(50) NOT NULL,icon varchar(50) NOT NULL,name varchar(50) NOT NULL,speed double NOT NULL,jump double NOT NULL,PRIMARY KEY (id));");
    }

    public void save(Mount type) {}

    public void save(MountType type) {}

    public Mounts loadMounts() {
        return null;
    }

    public MountTypes loadMountTypes() {
        return null;
    }

    public void remove(Mount mount) {}
}
