import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DSMain {

    public static void main(String[] args) {
        DBInfo.DBProvider dbp = DBInfo.DBProvider.ORA;
        String hostName       = "oracle" ;
        
        DBUser dbUser = new DBUser("jmedina","jmedina");
        DBInfo dbInfo = new DBInfo(hostName,dbp.getPortNumber(),dbp.getDataBaseName(),dbp);
        
        MyDataSource ds = MyDataSource.getInstance(dbUser, dbInfo);
        
        try {
            Connection cnx = ds.getDataSource().getConnection();
            
            System.out.println("Conexión abierta: "+!cnx.isClosed());
            
        } catch (SQLException ex) {
            Logger.getLogger(DSMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
