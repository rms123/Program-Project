final class MyResult {
    private final int choiceX;
    private final int choiceY;
    private final int choiceZ;
    
    public MyResult(int first, int second, int third) {
        this.choiceX = first;
        this.choiceY = second;
        this.choiceZ = third;
    }

    public int getChoiceX(){
    	return choiceX;
    }

    public int getChoiceY(){
    	return choiceY;
    }
    
    public int getChoiceZ(){
    	return choiceZ;
    }
 }
    //public static MyResult something() {
      //  int number1 = 1;
       // int number2 = 2;
        //int number3 = 3;

        //return new MyResult(number1, number2, number3);
    //}

    //public static void main(String[] args) {
      //  MyResult result = something();
       // System.out.println(result.getChoiceX() + result.getChoiceY()+ result.getChoiceZ());
    //}
//}