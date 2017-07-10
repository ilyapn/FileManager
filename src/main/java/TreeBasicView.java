import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;

@ManagedBean(name="treeBasicView")
@SessionScoped
public class TreeBasicView implements Serializable {

    private TreeNode root ;
    private TreeNode next ;
    private TreeNode selectedNode;
//    private  String selectNodeName = selectedNode.getData().toString();
//    public String getSelectNodeName() {
//        return selectNodeName;
//    }
//
//    public void setSelectNodeName(String selectNodeName) {
//        this.selectNodeName = selectNodeName;
//    }


    //private String rename;
    private String testSt = "is test string";
    Boolean isLoad = false;

//    public  String getSelectFolder() {
//        return selectFolder;
//    }

    //public void setSelectFolder(String selectFolder) {
    //}

   // private String selectFolder = "select folder";

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
        this.selectedNode = selectedNode;
    }


    int i = 0;
    {
        root = new DefaultTreeNode("Root", null);
        TreeNode node0 = new DefaultTreeNode("/", root);
        TreeNode node00 = new DefaultTreeNode("Node00",node0);
        next = node0;
    }

    public TreeNode getRoot() {
        return root;
    }
    public void addNode(){
       next = new DefaultTreeNode("Work"+(i++),selectedNode == null ? root : selectedNode);
    }
    public void setRename(String name){
        ((Document)selectedNode.getData()).setName(name);
    }
    public String getRename(){
        return "test";
    }
    public void onDragDrop(TreeDragDropEvent event) {
        TreeNode dragNode = event.getDragNode();
        TreeNode dropNode = event.getDropNode();
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

}
