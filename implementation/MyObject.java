package implementation;

public class MyObject{

  private String key;
  private Integer  value;


  // constructors
      public MyObject(){
        key = null;
        value = null;
      }
      public  MyObject(String key, Integer value){
          this.key = key;
          this.value = value;
       }

      // methods
      public void setValueForKey(String key, Integer value){
        this.key = key;
        this.value = value;
      }


    public void setValue( Integer value){

        this.value = value;
      }

      public Integer getValue(){

        return this.value;

      }

      public String getKey(){
        return this.key;
      }


}
