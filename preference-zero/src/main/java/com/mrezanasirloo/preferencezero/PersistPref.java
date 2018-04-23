package com.mrezanasirloo.preferencezero;

/**
 * @author : Pedramrn@gmail.com
 *         Created on: 2016-01-05
 */
public interface PersistPref<T> {
    void save(T pref);
    T load();
}
