package com.autobots.automanager.model;

import java.util.List;

public interface LinkAdder<T> {
    public void AddLink(List<T> list);
    public void AddLink(T object);
}
