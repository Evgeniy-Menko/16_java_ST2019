package by.menko.finalproject.dao.pool;

import java.util.ResourceBundle;

class MySqlResources {

    private String driverClass;
    private String url;
    private String user;
    private String password;
    private int maxSize;
    private int startSize;
    private int checkConnectionTimeout;

    MySqlResources() {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        this.driverClass = resource.getString("db.driver_class");
        this.url = resource.getString("db.url");
        this.user = resource.getString("db.user");
        this.password = resource.getString("db.password");
        this.startSize = Integer.parseInt(resource.getString("db.startSize"));
        this.maxSize = Integer.parseInt(resource.getString("db.max_size"));
        this.checkConnectionTimeout = Integer.parseInt(resource.getString("db.check_connection_timeout"));
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getStartSize() {
        return startSize;
    }

    public int getCheckConnectionTimeout() {
        return checkConnectionTimeout;
    }
}
