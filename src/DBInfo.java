public class DBInfo {
    private String dbHostName;      //Nombre o IP del servidor.
    private String dbPortNumber;    //Puerto donde escucha el servidor
    private String dbServiceName;   //Servicio/Nombre de Base de Datos 
    private DBProvider dbProvider;  //Proveedor de Base de Datos [ORA, MySQL, MySSQL]
    
    private String dbUrl;

    public DBInfo(String hostName, String portNumber, String servicename, DBProvider provider) {
        this.dbHostName     = hostName;
        this.dbPortNumber   = portNumber;
        this.dbServiceName  = servicename;
        this.dbProvider     = provider;
    }
    
    private void createDBurl(){
        if (this.dbProvider == null) {
            this.dbUrl = "";
        } else {
            switch (this.dbProvider) {
                case ORA:
                    this.dbUrl = "jdbc:oracle:thin:@" + this.dbHostName + ":" + this.dbPortNumber + ":" + this.dbServiceName;
                    //Ejemplo ORAcle: jdbc:oracle:thin:@hostname:1521:servicename
                    break;
                case MYSQL:
                    this.dbUrl = "jdbc:mysql://" + this.dbHostName + ":" + this.dbPortNumber + "/" + this.dbServiceName + "?useSSL=false&zeroDateTimeBehavior=convertToNull";
                    //Ejemplo MySQL:  jdbc:mysql://localhost:3306/biblioteca?zeroDateTimeBehavior=convertToNull
                    break;
                case MSSQL:
                    this.dbUrl = "jdbc:sqlserver://" + this.dbHostName + ":" + this.dbPortNumber + ";" + this.dbServiceName + "?zeroDateTimeBehavior=convertToNull";
                    //Ejemplo Microsoft SQL:???
                    break;
                default:
                    break;
            }
        }
    }

    public String getDBurl() {
        this.createDBurl();
        return(this.dbUrl);
    }
    
    public String getDBHostName() {
        return(this.dbHostName);
    }
    
    public void setDBHostName(String hostName) {
        this.dbHostName = hostName;
    }
    
    public String getDBPortNumber() {
        return(this.dbPortNumber);
    }
    
    public void setDBPortNumber(String portNumber) {
        this.dbPortNumber = portNumber;
    }
    
    public String getDBServiceName() {
        return(this.dbServiceName);
    }
    
    public void setDBServiceName(String serviceName) {
        this.dbServiceName = serviceName;
    }
    
    public DBProvider getDBProvider() {
        return(this.dbProvider);
    }
    
    public void setDBProvider(DBProvider provider) {
        this.dbProvider = provider;
    }

    public enum DBProvider  {
        /** Proveedor de Base de Datos Oracle.*/
        ORA     ("ORA",     "oracle.jdbc.driver.OracleDriver",              "XE",   "1521"), 
        /** Proveedor de Base de Datos MySQL.*/
        MYSQL   ("MYSQL",   "com.mysql.jdbc.Driver",                        "MyDataBase",  "3306"),
        /**Proveedor de Base de Datos Microsoft SQL Server.*/
        MSSQL   ("MSSQL",   "com.microsoft.sqlserver.jdbc.SQLServerDriver", "MyDataBase",  "0000");
        
        private DBProvider (String value,String classNameJDBC, String dbName, String defaultPort) {
            this.miValue         = value;
            this.miName          = this.name();
            this.miDBName        = dbName;      //Reemplaza el nombre por el de tu Base de Datos.
            this.miClassNameJDBC = classNameJDBC;
            this.miPortNumber    = defaultPort;
        }
        public String getValue() {
            return this.miValue;
        }
        public String getName(){
            return(this.miName);
        }
        public String getJDBCClassName(){
            return(this.miClassNameJDBC);
        }
        public String getDataBaseName(){
            return(this.miDBName);
        }
        public String getPortNumber(){
            return (this.miPortNumber);
        }
        private final String miValue, miName, miClassNameJDBC, miDBName,miPortNumber;
    }
    
}