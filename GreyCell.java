public class GreyCell implements DefaultCell {
    public String getColor(){
        return "Grey";
    }
    private int value = (int)(Math.random()*3)+1;

    public String getDisplayValue(){
        return String.valueOf(value);
    }
    public GreyCell(){
        //System.out.println(getDisplayValue());
        getDisplayValue();
    }
}
