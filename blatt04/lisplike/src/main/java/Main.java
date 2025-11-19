import java.util.Objects;

public class Main {
    static void main(String... args){
        Lexer lex = new Lexer ("(def x (+ 2 (/ 6 2))) ;; rechne 2+6/2 = x\n" +
                "(print x)");
        Lexer lex2 = new Lexer("(def x 4)\n" +
                "(if (< x 5) \n" +
                "    (do (print \"A\")(print \"X\")) \n" +
                "    (do (print \"B\")(print \"Y\")))\n" +
                "(defn hello (n) (str \"hello \" n))\n" +
                "(print(hello \"Dietmar\"))");
        Lexer lex3 = new Lexer ("(def x 4)\n" +
                "(if (< x 5) \n" +
                "    (do (print \"A)(print \"X\")) \n" +
                "    (do (print \"B\")(print \"Y\")))\n" +
                "(defn hello (n) (str \"hello \" n))\n" +
                "(print(hello \"Dietmar\"))");

        Parser par =  new Parser(lex);
        par.program();

        Parser par2 = new Parser(lex2);
        par2.program();

        //Parser par3 = new Parser(lex3);
        //par3.program();
    }
}
