import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TrieNode {

    private String value;
    private Map<String,TrieNode> childrens;
    private boolean last;

    public TrieNode(){

        this.childrens=new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        last=false;
    }
    public TrieNode(String value){
        this.value=value;
        childrens=new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        this.last=false;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<String, TrieNode> getChildrens() {
        return childrens;
    }

    public void setChildrens(Map<String, TrieNode> childrens) {
        this.childrens = childrens;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
