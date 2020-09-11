package datastructure.trietree;

public interface TrieTree {

    void insert(String word);

    int search(String word);

    int prefixNumber(String prefix);

    void delete(String word);
}
