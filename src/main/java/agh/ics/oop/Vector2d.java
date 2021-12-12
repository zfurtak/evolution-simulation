package agh.ics.oop;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;
    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
    public String to_String(){ return "(" + this.x + "," + this.y + ")";}
    public boolean precedes(Vector2d vec){ return vec.x >= this.x && vec.y >= this.y; }
    public boolean follows(Vector2d vec){ return vec.x <= this.x && vec.y <= this.y; }
    public Vector2d upperRight(Vector2d vec){
        int a = this.x;
        int b = this.y;
        if(vec.x >= this.x)
            a = vec.x;
        if (vec.y >= this.y)
            b = vec.y;
        return new Vector2d(a, b);
    }
    public Vector2d lowerLeft(Vector2d vec){
        int a = this.x;
        int b = this.y;
        if(vec.x <= this.x)
            a = vec.x;
        if (vec.y <= this.y)
            b = vec.y;
        return new Vector2d(a, b);
    }
    public Vector2d add(Vector2d vec){
        return new Vector2d(this.x+vec.x, this.y+vec.y);
    }

    public Vector2d subtract(Vector2d vec){
        return new Vector2d(this.x-vec.x, this.y-vec.y);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x && y == vector2d.y;
    }
    public Vector2d opposite(){
        return new Vector2d((-1)*this.x, (-1)*this.y);
    }
}

