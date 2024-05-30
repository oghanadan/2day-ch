public class GreenCell implements DefaultCell{
    private String value = "G";

    public String getColor(){
        return "Green";
    }
    
    public String getDisplayValue(){
        return value;
    }
    public GreenCell(){
        getDisplayValue();
    }
}   
