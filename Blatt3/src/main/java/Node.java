import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node {
    private Node parent;
    private List<Node> children;
    private String value;
    Node(){
    }

    Node(Node p){
        parent = p;
    }

    Node(String v){
        value = v;
    }

    public void addChild(Node c){
        children.add(c);
        c.setParent(this);
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setValue(String value){
        this.value = value;
    }
    public void addValue(String value){
        this.value += value;
    }
    public String getValue(){
        return this.value;
    }
    public Node getParent() {
        return parent;
    }
    private void setParent(Node parent) {
        this.parent = parent;
    }


    public String toString() {
        StringBuilder buffer = new StringBuilder();
        print(buffer, "", "");
        return buffer.toString();
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(getValue());
        buffer.append('\n');
        for (Iterator<Node> it = children.iterator(); it.hasNext();) {
            Node next = it.next();
            if (it.hasNext()) {
                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }
}
