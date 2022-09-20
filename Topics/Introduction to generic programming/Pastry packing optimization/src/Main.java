
class Box<T> {
    
    private T pack;
    
    public void put(T pack) {
        this.pack = pack;
    }
    
    public T get() {
        return this.pack;
    }
}

// Don't change classes below
class Cake { }

class Pie { }

class Tart { }
