package Dao;


import Database.DB;
import org.junit.rules.ExternalResource;
import org.sql2o.Sql2o;
import org.sql2o.Connection;

public class DatabaseRule extends ExternalResource{
    @Override
    public  void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/student_system_test", "", "" );
    }

    @Override
    public void after(){
        try(Connection con = DB.sql2o.open() ) {
            String sql = "DELETE FROM students *;" ;
            con.createQuery(sql).executeUpdate();
        }
    }

}