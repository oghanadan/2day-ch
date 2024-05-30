public class BlackCell implements DefaultCell{
    public String getColor(){
        return "Black";
    }
    private String value = "B";
    public String getDisplayValue(){
        return value;
    }
    public BlackCell(){
        getDisplayValue();
        //System.out.println(getDisplayValue());
    }
}   