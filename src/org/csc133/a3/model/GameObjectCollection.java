package org.csc133.a3.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * A data structure to hold GameObjects
 */
public class GameObjectCollection implements Collection<GameObject>
{
    private final List<GameObject> world = new ArrayList<>();

    @Override
    public int size()
    {
        return world.size();
    }

    @Override
    public boolean isEmpty()
    {
        return world.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return world.contains(o);
    }

    @Override
    public Iterator<GameObject> iterator()
    {
        return world.iterator();
    }

    @Override
    public Object[] toArray()
    {
        return world.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return world.toArray(a);
    }

    @Override
    public boolean add(GameObject gameObject)
    {
        return world.add(gameObject);
    }

    @Override
    public boolean remove(Object o)
    {
        return world.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return world.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends GameObject> c)
    {
        return world.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return world.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return world.retainAll(c);
    }

    @Override
    public void clear()
    {
        world.clear();
    }
}
