import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.*;

@ManagedBean(name = "treeBasicView")
@SessionScoped
public class TreeBasicView implements Serializable {


    private TreeNode selectedNode;
    private TreeNode root = new DefaultTreeNode("Root", null);
    private TreeNode node0 = new DefaultTreeNode("/", root);
    private int ID = 0;
    private int counterNewFolders = 0;

    public void updateDB(){
        db(root);
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }


    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void addNode() {
            new DefaultTreeNode("new folder" + counterNewFolders++, selectedNode);
    }

    public void setRename(String name) {
        ((DefaultTreeNode) selectedNode).setData(name);
    }


    public String getRename() {
        return (String) selectedNode.getData();
    }


    public void delete() {
        if (selectedNode.getParent() == root && root.getChildCount() == 1)
            return;
        selectedNode.getChildren().clear();
        selectedNode.getParent().getChildren().remove(selectedNode);
        selectedNode.setParent(null);
        selectedNode = null;
    }
    public void db(TreeNode root) {
        ID=0;
        String url = "jdbc:mysql://localhost:3306/Test_schema?&serverTimezone=UTC";
        String user = "user";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS `TREE`");
            statement.execute("CREATE TABLE  `TREE` (" +
                    "`id` INT NOT NULL," +
                    "`name` CHAR(100) NOT NULL," +
                    "`parent` INT ," +
                    "PRIMARY KEY(`id`)" +
                    ")");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `TREE` VALUES (?,?,?)");

            recursiveMethod(root,ID,preparedStatement);
            preparedStatement.executeBatch();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void recursiveMethod(TreeNode parent, int PID, PreparedStatement statement) throws SQLException {

        statement.setInt(1,++ID);
        statement.setString(2,(String) parent.getData());
        statement.setInt(3,PID);
        statement.addBatch();
        int i = ID;
        for (TreeNode node : parent.getChildren())
            recursiveMethod(node,i,statement);



    }
}
