package com.example.leeser.monsie;

/**
 * Created by xiuwenlu on 8/13/2015.
 */
public class Tuple {
    int first;
    int second;
    public Tuple(int i, int j){
        this.first=i;
        this.second=j;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + first;
        result = prime * result + second;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tuple other = (Tuple) obj;
        if (first != other.first)
            return false;
        if (second != other.second)
            return false;
        return true;
    }
}