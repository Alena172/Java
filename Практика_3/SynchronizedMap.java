package Практика_3;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SynchronizedMap<K, V> implements Map<K, V> {
    private final Map<K, V> map;

    public SynchronizedMap() {
        this.map = new HashMap<>();
    }

    // Следующие методы синхронизированы для обеспечения потокобезопасности

    // Возвращает размер map
    public synchronized int size() {
        return map.size();
    }
    // Проверяет, пуст ли map
    public synchronized boolean isEmpty() {
        return map.isEmpty();
    }
    // Проверяет, содержит ли map указанный ключ
    public synchronized boolean containsKey(Object key) {
        return map.containsKey(key);
    }
    // Проверяет, содержит ли map указанное значение
    public synchronized boolean containsValue(Object value) {
        return map.containsValue(value);
    }
    // Получает значение по ключу из map
    public synchronized V get(Object key) {
        return map.get(key);
    }
    // Добавляет элемент в map
    public synchronized V put(K key, V value) {
        return map.put(key, value);
    }
    // Удаляет элемент из map по ключу
    public synchronized V remove(Object key) {
        return map.remove(key);
    }
    // Добавляет все элементы из другой map в текущую map
    public synchronized void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }
    // Очищает map
    public synchronized void clear() {
        map.clear();
    }
    // Возвращает множество ключей из map
    public synchronized Set<K> keySet() {
        return map.keySet();
    }
    // Возвращает коллекцию значений из map
    public synchronized Collection<V> values() {
        return map.values();
    }
    // Возвращает множество пар ключ-значение из map
    public synchronized Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }
}