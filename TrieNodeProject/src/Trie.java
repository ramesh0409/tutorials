import java.util.Map;
import java.util.function.Function;

public class Trie {

    TrieNode root = new TrieNode();

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("Test");
        trie.insert("Tests");
        trie.insert("Play");
        trie.insert("PlayFull");
        trie.insert("PlayRight");
        trie.insert("Program");
        trie.insert("play");
        System.out.println("Display All Strings");
        trie.display();
        System.out.println("Search And Display");
        trie.searchAndDisplay("PlayFull");
        trie.searchAndDisplay("Pla");

        //OnlyFull should be removed from trie not Play
        trie.delete("PlayFull");
        System.out.println("After deleting");
        trie.display();

    }


    public void insert(String word) {
        TrieNode tmp = root;
        for (int i = 0; i < word.length(); i++) {
            tmp=tmp.getChildrens().computeIfAbsent(word.substring(i, i + 1),key -> new TrieNode(key));
//            tmp=insert(word.substring(i, i + 1),tmp);
        }
        tmp.setLast(true);

    }

    public void display() {
        for (Map.Entry<String, TrieNode> entrySet : root.getChildrens().entrySet()) {
            display(entrySet.getValue(), entrySet.getKey());
        }
    }

    private void display(TrieNode currentNode, String displayString) {
        if(currentNode.isLast()){
            System.out.println(displayString);
        }
        if (currentNode.getChildrens().isEmpty()) {
            return;
        } else {
            for (Map.Entry<String, TrieNode> entrySet : currentNode.getChildrens().entrySet()) {
                display(entrySet.getValue(), displayString + entrySet.getKey());

            }
        }
    }

    public void searchAndDisplay(String searchString){

        TrieNode child=root.getChildrens().get(searchString.substring(0,1));
        if(child == null){
            System.out.println("Not found");
            return;
        }
        searchAndDisplay(child,searchString,0);

    }

    private void searchAndDisplay(TrieNode child, String searchString, int index) {

        if(child.isLast()){
            //One of the SearchString partial Match Found or Full Match Found
            //SearchString PalyFull
            //Partial Match Paly Full Match PalyFull
            System.out.println(searchString.substring(0,index+1));
        }
        if(child.getChildrens().isEmpty() || searchString.length()<=index+1){
            return;
        }

        index=index+1;
        child=child.getChildrens().get(searchString.substring(index,index+1));
        if(child == null)
            return;
        else{
            searchAndDisplay(child,searchString,index);
        }

    }


    private TrieNode insert(String ch, TrieNode currentNode) {
        if (currentNode.getChildrens().containsKey(ch)) {
            return currentNode.getChildrens().get(ch);
        } else {
            TrieNode newNode = new TrieNode(ch);
            currentNode.getChildrens().put(ch, newNode);
            return newNode;

        }
    }

    public void delete(String deleteString){
        delete(root,0,deleteString);
    }

    private boolean delete(TrieNode currentNode, int index, String deleteString) {

        if(index == deleteString.length()){
            currentNode.setLast(false);
            return currentNode.getChildrens().isEmpty();
        }
        TrieNode childNode=currentNode.getChildrens().get(deleteString.substring(index,index+1));
        if (childNode == null)
            return false;
        index++;
        boolean deleteChild=delete(childNode,index,deleteString);
        //Parent node
        //Delete the child only if the child has no childrens
        if(deleteChild){
            currentNode.getChildrens().remove(deleteString.substring(index-1,index));
            //After removing the child check if the current node has any more childres
            //If the current node has no childrens then delete the current nod =e also
            return currentNode.getChildrens().isEmpty();
        }
        return false;


    }

}
