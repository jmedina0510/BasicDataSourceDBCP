import org.apache.commons.dbcp2.BasicDataSource;

public class MyDataSource{
    
    private static MyDataSource     myDataSource;      //Instancia Singleton.
    private BasicDataSource         basicDataSource;

    //---Constructores - Singleton---
    //******************************************************************************************************************
    private MyDataSource(DBUser dbUser, DBInfo dbInfo){
        this.initializeDataSource(dbUser, dbInfo);
    }

    //---Método Público para obtener la instancia Singleton---
    //******************************************************************************************************************
    public  synchronized static MyDataSource getInstance(DBUser dbUser, DBInfo dbInfo )   {
        if(myDataSource == null) {
            myDataSource   = new MyDataSource(dbUser,dbInfo);
        }
        return(myDataSource);
    }
    
    public BasicDataSource getDataSource(){
        return(this.basicDataSource);
    }
    
    //---Inicializar el DataSource---
    //******************************************************************************************************************
    private void        initializeDataSource(DBUser dbUser, DBInfo dbInfo){
        this.basicDataSource = new BasicDataSource();
        
        this.basicDataSource.setDriverClassName(dbInfo.getDBProvider().getJDBCClassName()); //Nombre del Class Driver JDBC.
        this.basicDataSource.setUsername(dbUser.getUserName());     //Usuario o login.
        this.basicDataSource.setPassword(dbUser.getPassword());     //Contraseña del usuario.
        this.basicDataSource.setUrl(dbInfo.getDBurl());
        
        this.basicDataSource.setMaxTotal(10);           //10 Conexiones máximo.
        this.basicDataSource.setInitialSize(2);         //El pool inicia con 2 conexiones abiertas.
        this.basicDataSource.setMaxIdle(2);             //2 conexiones desocupadas máximo.
        this.basicDataSource.setMinIdle(1);             //1 conexión desocupada mínimo.
        this.basicDataSource.setMaxWaitMillis(5000);    //5 segundos de espera máximo para obtener una conexión del pool.
    }
}
