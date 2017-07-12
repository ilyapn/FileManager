import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@ManagedBean(name="treeBasicView")
@SessionScoped
public class TreeBasicView implements Serializable {


    private TreeNode next ;
    private TreeNode selectedNode;
    private String testSt = "is test string";
    Boolean isLoad = false;
    private final TreeNode root = new DefaultTreeNode("Root", null);
    private final TreeNode node0 = new DefaultTreeNode("/", root);
    private int i = 0;
    private String rename = "test";
    private TreeNode dragNode;
    private TreeNode dropNode;
    private int count;


    public TreeNode getDragNode() {
        return dragNode;
    }

    public void setDragNode(TreeNode dragNode) {
        this.dragNode = dragNode;
    }

    public TreeNode getDropNode() {
        return dropNode;
    }

    public void setDropNode(TreeNode dropNode) {
        this.dropNode = dropNode;
    }



    public String getTestSt() {
        return testSt;
    }

    public void setTestSt(String testSt) {
        this.testSt = testSt;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        //this.selectedNode
        this.selectedNode = selectedNode;
    }



    {
        TreeNode node00 = new DefaultTreeNode("Node00",node0);
        next = node0;
    }

    public TreeNode getRoot() {
        return root;
    }
    public void addNode(){
       next = new DefaultTreeNode("new folder"+i++,selectedNode == null ? root : selectedNode);
       db();
    }
    public void setRename(String name){
       // ((Document)selectedNode.getData()).setName(name);
        ((DefaultTreeNode)selectedNode).setData(name);
    }
    public String getRename(){
        return  "new name";//selectedNode == null ? "new name" :(String)selectedNode.getData();
    }
    public void onDragDrop(TreeDragDropEvent event) {
        dragNode = event.getDragNode();
        dropNode = event.getDropNode();
        int dropIndex = event.getDropIndex();
    }
    public void listener(ActionEvent event){

    }
    public void delete(){
        selectedNode.getChildren().clear();
        selectedNode.getParent().getChildren().remove(selectedNode);
        selectedNode.setParent(null);
        selectedNode = null;
    }

    public void db(){
        String url = "jdbc:mysql://localhost:3306/Test_schema?&serverTimezone=UTC";
        String user = "user";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS `TREE`");
            statement.execute("CREATE TABLE  `TREE` (" +
                    "`id` INT NOT NULL AUTO_INCREMENT," +
                    "`name` CHAR(100) NOT NULL," +
                    "`parent` INT ," +
                    "FOREIGN KEY (`parent`) REFERENCES TREE(`id`),"+
                    "PRIMARY KEY(`id`)" +
                    ")");

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public int getCount() {
        return root.getChildCount();
    }

    public void setCount(int count) {
        this.count = count;
    }
}
