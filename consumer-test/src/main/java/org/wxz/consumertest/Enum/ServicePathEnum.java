package org.wxz.consumertest.Enum;

public enum ServicePathEnum {

    PROVIDE_TEST_SERVICE("http://localhost:8081/hello/hello_word"),

    ;
   // private String name;
    private String value;

     ServicePathEnum(String value){
         //this.name=name;
         this.value=value;
     }



     public String getValue(){
         return value;
     }

}
