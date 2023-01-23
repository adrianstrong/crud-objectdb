module csur.adrian.crudfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires lombok;
    requires java.sql;


    opens csur.adrian.crudobjectdb to javafx.fxml, org.hibernate.orm.core, java.sql;
    opens models;
    exports csur.adrian.crudobjectdb;
}
