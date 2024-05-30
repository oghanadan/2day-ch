public class GreenCell implements DefaultCell{
    public String getColor(){
        return "Green";
    }
    private String value = "G";
    public String getDisplayValue(){
        return value;
    }
    public GreenCell(){
        getDisplayValue();
        //System.out.println(getDisplayValue());
    }
}   
