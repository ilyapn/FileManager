/**
 * Created by ilyaP on 10.07.2017.
 */
public class DBConnector {
    private String url;
    private String user;
    private String password;
    private String rootNodeName;
    private String tableName;

    private DBConnector(){}

    public DBConnector(String url, String user, String password,String tableName ,String rootNodeName) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.rootNodeName = rootNodeName;
        this.tableName = tableName;


    }

    public DBConnector(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private void init(){

    }

    public void creatTable(String tableName) {

    }

    public void addNode(String parentNode,String Name){

    }

    public void deleteNode(){

    }



}
