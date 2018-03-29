package trie.autocomplete;
import trie.Trie;
import trie.TrieNode;
import java.util.Queue;
import java.util.*;
import java.util.ArrayDeque;

public class KauchickAutocomplete extends Trie<List<String>> implements IAutocomplete<List<String>>
        {

    private List<String> ArrayList;

    /**
     * @param prefix the prefix of candidate words to return.
     * @return the list of candidate words for the specific prefix.
     */
    public List<String> getCandidates(String prefix){

        TrieNode<List<String>> prefixNode = getRoot();
        prefixNode = find(prefix);

        if (prefixNode == null){
            return new ArrayList<String>();
        }
        List<String> Candidates = new ArrayList<String>();
        //todo check value properly for a stored list.
        if (prefixNode.getValue() != null){
            Candidates = prefixNode.getValue() ;
        }

        prefixNode.setValue(Candidates);
        if(Candidates.size() == 20 ){
            return Candidates;
        }


        Queue<TrieNode<List<String>>> tNQ = new ArrayDeque<TrieNode<List<String>>>();

        tNQ.add(prefixNode);
        char array[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        while (!tNQ.isEmpty()){
            TrieNode<List<String>> node = tNQ.remove();
                if(node.isEndState()){
                    Candidates.add(getString(node));
                            if(Candidates.size() >= 20 ) {
                                return Candidates;
                            }
                            else if(Candidates.size() <20){
                                for(int i = 0; i <= 25; i++){
                                    if(node.getChild(array[i]) != null ){
                                        tNQ.add(node.getChild(array[i]));
                                    }
                                }
                            }
                }
        }
       // ArrayList<TrieNode<List<String>>> t = new ArrayList<>(n)




        return Candidates;

    }
    /*
    This function calls the curcsive getMapString function and reverses each sting so it is in the correct order.
     */
        public String getString(TrieNode<List<String>> child){
        String x = getMapString(child);

            char[] in = x.toCharArray();
            int begin=0;
            int end=in.length-1;
            char temp;
            while(end>begin){
                temp = in[begin];
                in[begin]=in[end];
                in[end] = temp;
                end--;
                begin++;
            }
           x = new String(in);
        return x;
        }
        /*
        recursive function that starts at an endstate in the trie and works back through the parents concatenating all of the characters.
         */
        public String getMapString(TrieNode<List<String>> child){
            if(child.getParent() == null){
                return "";
            }
            return child.getKey() + getMapString(child.getParent());
        }

        public TrieNode<List<String>> addToTrie(String prefix) {
            char[] array = prefix.toCharArray();
            int i, len = prefix.length();
            TrieNode<List<String>> node = getRoot();

            for (i = 0; i < len; i++) {
                if (node.getChild(array[i]) == null) {
                    node.addChild(array[i]);
                }


            }
            node.setEndState(false);
            return node;
        }

        public void addCandidate(List List, String Candidate){
            if(List.contains(Candidate)){
                return;
            }
            if(List.size() ==20){
                List.remove(19);
            }
            TrieNode<List<String>> node = find(Candidate);
            if(node == null){
                addToTrie(Candidate);
            }
            List.add(0,Candidate);
        }
    /**
     * Memorize the specific candidate word for the specific prefix.
     * @param prefix the prefix.
     * @param candidate the selected candidate for the prefix.
     */
    public void pickCandidate(String prefix, String candidate){
        TrieNode<List<String>> node = find(prefix);
        if(node == null){
            node = addToTrie(prefix);
        }
        List<String> val = node.getValue();
        if(val == null){
            val = new ArrayList<String>();
            node.setValue(val);
        }
        addCandidate(val, candidate);

    }

}
