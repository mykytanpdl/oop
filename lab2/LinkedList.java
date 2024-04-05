public class LinkedList<T>
{
    private Node<T> head;
    private int size;

    public LinkedList()
    {
        head = null;
        size = 0;
    }

    public Node<T> getHead()
    {
        return head;
    }

    public void add(T data)
    {
        Node<T> New = new Node<>(data);
        size++;
        if (head == null)
        {
            head = New;
            return;
        }
        Node<T> current = head;
        while (current.next != null)
            current = current.next;
        current.next = New;
    }

    public void add(int index, T data) throws IndexOutOfBoundsException
    {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index out of bounds");
        Node<T> New = new Node<>(data);
        if (index == 0)
        {
            New.next = head;
            head = New;
            size++;
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++)
            current = current.next;
        New.next = current.next;
        current.next = New;
        size++;
    }

    public void clear()
    {
        head = null;
        size = 0;
    }

    public boolean contains(T data)
    {
        Node<T> current = head;
        while (current != null)
        {
            if (current.data.equals(data))
                return true;
            current = current.next;
        }
        return false;
    }

    public T get(int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");
        Node<T> current = head;
        for (int i = 0; i < index; i++)
            current = current.next;
        return current.data;
    }

    public int indexOf(T data)
    {
        Node<T> current = head;
        int index = 0;
        while (current != null)
        {
            if (current.data.equals(data))
                return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void removeFirst()
    {
        if (head == null)
            return;
        head = head.next;
        size--;
    }

    public void removeLast()
    {
        if (head == null)
            return;
        if (head.next == null)
        {
            head = null;
            size--;
            return;
        }
        Node<T> current = head;
        while (current.next.next != null)
            current = current.next;
        current.next = null;
        size--;
    }

    public void remove(int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");
        if (index == 0)
        {
            head = head.next;
            size--;
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++)
            current = current.next;
        current.next = current.next.next;
        size--;
    }

    public int size()
    {
        return size;
    }

    public void print()
    {
        Node<T> current = head;
        while (current != null)
        {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void set(int index, T data) throws IndexOutOfBoundsException
    {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");
        Node<T> current = head;
        for (int i = 0; i < index; i++)
            current = current.next;
        current.data = data;
    }

    public T[] toArray()
    {
        T[] array = (T[]) new Object[size];
        Node<T> current = head;
        for (int i = 0; i < size; i++)
        {
            array[i] = current.data;
            current = current.next;
        }
        return array;
    }
}