// Palindrome Checker Using Stacks

import java.util.Stack;

class Main {

    static public boolean checker(String x){

        Stack<String> s = new Stack<>();
        
        boolean addToStack = true;
        String y[] = x.split("");

        for (String letter : y) {

            if(letter.equals("C")){
                addToStack = false;
                continue;
            }

            if(addToStack == true){
                s.add(letter);
            }else if(addToStack == false){
                if(!s.pop().equals(letter)){
                    return false;
                }
            }
        }

        return true;

    }

    public static void main(String[] args) {
        
        String x = "ABBABCBABBA";
        System.out.println(checker(x));

    }

}
