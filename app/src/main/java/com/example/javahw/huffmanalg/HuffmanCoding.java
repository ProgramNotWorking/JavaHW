package com.example.javahw.huffmanalg;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * <p><b>Пример использования:</b></p>
 * <pre>{@code
 * String original = "some string";
 * HuffmanCoding huffmanCoding = new HuffmanCoding();
 * Map<Character, Integer> freqMap = huffmanCoding.buildFrequencyMap(original);
 * HuffmanNode root = huffmanCoding.buildHuffmanTree(freqMap);
 * String decoded = huffmanCoding.decode(encoded, root);
 * }</pre>
 *
 * @see #buildFrequencyMap(String)
 * @see #buildFrequencyMap(String)
 * @see #decode(String, HuffmanNode)
 * */
public class HuffmanCoding {

    /**
     * Подсчет частот символов
     * */
    public static Map<Character, Integer> buildFrequencyMap(String data) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : data.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        return freqMap;
    }

    /**
     * Построение дерева Хаффмана
     * */
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            minHeap.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (minHeap.size() > 1) {
            HuffmanNode left = minHeap.poll();
            HuffmanNode right = minHeap.poll();
            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, left, right);
            minHeap.add(parent);
        }

        return minHeap.poll();
    }

    /**
     * Генерация кодов
     * */
    private static void buildCodeTable(
            HuffmanNode node, String code, Map<Character, String> codeTable
    ) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            codeTable.put(node.character, code);
            return;
        }
        buildCodeTable(node.left, code + "0", codeTable);
        buildCodeTable(node.right, code + "1", codeTable);
    }

    /**
     * Кодирование строки
     * */
    public static String encode(String data) {
        if (data.isEmpty()) return "";

        Map<Character, Integer> freqMap = buildFrequencyMap(data);
        HuffmanNode root = buildHuffmanTree(freqMap);
        Map<Character, String> codeTable = new HashMap<>();
        buildCodeTable(root, "", codeTable);

        StringBuilder encoded = new StringBuilder();
        for (char c : data.toCharArray()) {
            encoded.append(codeTable.get(c));
        }

        return encoded.toString();
    }

    /**
     * Декодирование
     * */
    public static String decode(String encodedData, HuffmanNode root) {
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedData.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;
            if (current.left == null && current.right == null) {
                decoded.append(current.character);
                current = root;
            }
        }

        return decoded.toString();
    }

}
