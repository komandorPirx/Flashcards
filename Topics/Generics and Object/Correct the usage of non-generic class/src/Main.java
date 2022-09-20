class Holder {
    private Object value;

    public void set(Object value) {
        this.value = value;
    }

    public Object get() {
        return value;
    }
}

class Main {
    static final int NB = 256;
    
    public static void main(String... args) {
        
        Holder holder = new Holder();
        holder.set(NB);

        // correct the line to make the code compile
        Integer value = (int) holder.get();

        // do not change
        System.out.println(value);
    }
}
