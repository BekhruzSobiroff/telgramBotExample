package org.company.exceptions;

public class RequestNotFoundException extends Throwable{
 RequestNotFoundException(){
    System.out.println(RequestNotFoundException.class.getName()+" sssss");
    super.fillInStackTrace();
}
}
