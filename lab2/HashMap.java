public class HashMap<K, V> {
    private LinkedList<Pair<K, V>>[] buckets;
    private static final int INITIAL_CAPACITY = 16;
    private int size = 0;

    public HashMap()
    {
        this(INITIAL_CAPACITY);
    }

    public HashMap(int capacity)
    {
        this.buckets = (LinkedList<Pair<K, V>>[]) new LinkedList[capacity];
    }

    public V get(K key)
    {
        int bucketIndex = getBucketIndex(key);
        LinkedList<Pair<K, V>> bucket = buckets[bucketIndex];
        if (bucket == null)
        {
            return null;
        }
        for (int i = 0; i < bucket.size(); i++)
        {
            Pair<K, V> pair = bucket.get(i);
            if (pair.getKey().equals(key))
            {
                return pair.getValue();
            }
        }
        return null;
    }

    public void put(K key, V value)
    {
        if (++size > buckets.length)
        {
            resize();
        }
        int bucketIndex = getBucketIndex(key);
        LinkedList<Pair<K, V>> bucket = buckets[bucketIndex];
        if (bucket == null)
        {
            bucket = new LinkedList<>();
            buckets[bucketIndex] = bucket;
        }
        for (int i = 0; i < bucket.size(); i++)
        {
            Pair<K, V> pair = bucket.get(i);
            if (pair.getKey().equals(key))
            {
                pair.setValue(value);
                return;
            }
        }
        bucket.add(new Pair<>(key, value));
    }

    public V remove(K key)
    {
        int bucketIndex = getBucketIndex(key);
        LinkedList<Pair<K, V>> bucket = buckets[bucketIndex];
        if (bucket == null)
        {
            return null;
        }
        for (int i = 0; i < bucket.size(); i++)
        {
            Pair<K, V> pair = bucket.get(i);
            if (pair.getKey().equals(key))
            {
                V value = pair.getValue();
                bucket.remove(i);
                size--;
                return value;
            }
        }
        return null;
    }

    public int size()
    {
        return size;
    }

    private void resize()
    {
        LinkedList<Pair<K, V>>[] newBuckets = (LinkedList<Pair<K, V>>[]) new LinkedList[buckets.length * 2];
        for (LinkedList<Pair<K, V>> bucket : buckets)
        {
            if (bucket != null)
            {
                for (int i = 0; i < bucket.size(); i++)
                {
                    Pair<K, V> pair = bucket.get(i);
                    int newBucketIndex = pair.getKey().hashCode() % newBuckets.length;
                    LinkedList<Pair<K, V>> newBucket = newBuckets[newBucketIndex];
                    if (newBucket == null)
                    {
                        newBucket = new LinkedList<>();
                        newBuckets[newBucketIndex] = newBucket;
                    }
                    newBucket.add(pair);
                }
            }
        }
        buckets = newBuckets;
    }

    private int getBucketIndex(K key)
    {
        return key.hashCode() % buckets.length;
    }
}