package lesson4;

import java.util.*;

import kotlin.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Префиксное дерево для строк
 */
public class Trie extends AbstractSet<String> implements Set<String> {

    private static class Node {
        Map<Character, Node> children = new LinkedHashMap<>();
    }

    private Node root = new Node();

    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root.children.clear();
        size = 0;
    }

    private String withZero(String initial) {
        return initial + (char) 0;
    }

    @Nullable
    private Node findNode(String element) {
        Node current = root;
        for (char character : element.toCharArray()) {
            if (current == null) return null;
            current = current.children.get(character);
        }
        return current;
    }

    @Override
    public boolean contains(Object o) {
        String element = (String) o;
        return findNode(withZero(element)) != null;
    }

    @Override
    public boolean add(String element) {
        Node current = root;
        boolean modified = false;
        for (char character : withZero(element).toCharArray()) {
            Node child = current.children.get(character);
            if (child != null) {
                current = child;
            } else {
                modified = true;
                Node newChild = new Node();
                current.children.put(character, newChild);
                current = newChild;
            }
        }
        if (modified) {
            size++;
        }
        return modified;
    }

    @Override
    public boolean remove(Object o) {
        String element = (String) o;
        Node current = findNode(element);
        if (current == null) return false;
        if (current.children.remove((char) 0) != null) {
            size--;
            return true;
        }
        return false;
    }

    /**
     * Итератор для префиксного дерева
     *
     * Спецификация: {@link Iterator} (Ctrl+Click по Iterator)
     *
     * Сложная
     */
    @NotNull
    @Override
    public Iterator<String> iterator() {
        return new TrieIterator();
    }

    public class TrieIterator implements Iterator<String> {
        private String actual;
        private final ArrayDeque<String> stack = new ArrayDeque<>();

        //Трудоемкость: O(N)
        //Память: O(M)
        //Где N - количество букв в дереве, M - количество букв в словах
        private TrieIterator() {
            if (!root.children.isEmpty()) {
                add(root, "");
            }
        }
        private void add(Node root, String w) {
            if (root.children.containsKey((char) 0))
                stack.add(w);
            root.children.forEach((key, value) -> {
                if (key != (char) 0) add(value, w + key);
            });
        }

        //Трудоемкость: O(1)
        //Память: O(1)
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        //Трудоемкость: O(1)
        //Память: O(1)
        @Override
        public String next() {
            if (stack.isEmpty()) throw new NoSuchElementException();
            actual = stack.removeLast();
            return actual;
        }

        //Трудоемкость: O(1)
        //Память: O(1)
        @Override
        public void remove() {
            if (actual == null) throw new IllegalStateException();
            Trie.this.remove(actual);
            actual = null;
        }
    }
}