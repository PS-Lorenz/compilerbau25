public class Lexer {
    char peek;
    String input;
    int start = 0;

    Lexer(String input) {
        this.input = input;
        consume();
    }

    public Lexem nextToken(){
        while(start <= input.length()) {
            switch (peek) {
                case ' ', '\t', '\n', '\r':
                    consume();
                    WS();
                    break;
                case '(':
                    consume();
                    return new Lexem(Token.LBRAK);
                case ')':
                    consume();
                    return new Lexem(Token.RBRAK);

                case '*':
                    consume();
                    return new Lexem(Token.OP,"*");
                case '/':
                    consume();
                    return new Lexem(Token.OP,"/");
                case '+':
                    consume();
                    return new Lexem(Token.OP,"+");
                case '-':
                    consume();
                    return new Lexem(Token.OP,"-");
                case '<':
                    consume();
                    return new Lexem(Token.OP,"<");
                case '>':
                    consume();
                    return new Lexem(Token.OP,">");
                case '=':
                    consume();
                    return new Lexem(Token.OP,"=");

                case 'd':
                    //matches "do","def" and "defn"
                    if (match('o')) {
                        consume();
                        return new Lexem(Token.DO);
                    }
                    if (match('e')) {
                        if (match('f')){
                            if (match('n')) {
                                consume();
                                return new Lexem(Token.DEFN);
                            } else {
                                consume();
                                return new Lexem(Token.DEF);
                            }
                        } else {
                            rollBack();
                        }
                    }
                    break;

                case 'l':
                    //matches "let" and "list"
                    if (match('e')){
                        if (match('t')) {
                            consume();
                            return new Lexem(Token.LET);
                        } else {
                            rollBack();
                        }
                    }
                    if (match('i')){
                        if (match('s')){
                            if (match('t')){
                                consume();
                                return new Lexem(Token.LIST);
                            } else {
                                rollBack(2);
                            }
                        } else {
                            rollBack();
                        }
                    }
                    break;
                case 'h':
                    if (match('e')){
                        if (match('a')){
                            if (match('d')){
                                consume();
                                return new Lexem(Token.HEAD);
                            } else {
                                rollBack(2);
                            }
                        } else {
                            rollBack();
                        }
                    }
                    break;
                case 't':
                    if (match('a')){
                        if (match('i')){
                            if (match('l')){
                                consume();
                                return new Lexem(Token.TAIL);
                            } else {
                                rollBack(2);
                            }
                        } else {
                            rollBack();
                        }
                    }
                    if (match('r')){
                        if (match('u')){
                            if (match('e')){
                                consume();
                                return new Lexem(Token.BOOL,"true");
                            } else {
                                rollBack(2);
                            }
                        } else {
                            rollBack();
                        }
                    }
                    break;
                case 'f':
                    if (match('a')){
                        if (match('l')){
                            if (match('s')){
                                if (match('e')){
                                    consume();
                                    return new Lexem(Token.BOOL,"false");
                                } else {
                                    rollBack(3);
                                }
                            } else {
                                rollBack(2);
                            }
                        } else {
                            rollBack();
                        }
                    }
                    break;

                case 'n':
                    if (match('t')){
                        if (match('h')){
                            consume();
                            return new Lexem(Token.NTH);
                        } else {
                            rollBack();
                        }
                    }
                    break;
                case 's':
                    if (match('t')){
                        if (match('r')){
                            consume();
                            return new Lexem(Token.STR);
                        } else {
                            rollBack();
                        }
                    }
                    break;
                case 'p':
                    if (match('r')){
                        if (match('i')){
                            if (match('n')){
                                if (match('t')){
                                    consume();
                                    return new Lexem(Token.PRINT);
                                } else {
                                    rollBack(3);
                                }
                            } else {
                                rollBack(2);
                            }
                        } else {
                            rollBack();
                        }
                    }
                    break;
                case 'i':
                    if (match('f')){
                        consume();
                        return new Lexem(Token.IF);
                    }
                    break;

                case ';':
                    if (match(';')){
                        consume();
                        String value = tillEndOfLine();
                        return new Lexem(Token.COMMENT,value);
                    } else {
                        System.out.println("ERROR: expected ; found "+peek);
                    }
                    break;
                case '"':
                    consume();
                    return STRING();



                default:
                    if (isLetter(peek)) {
                        return NAME();
                    } else if (isDigit(peek)) {
                        return NUMBER();
                    } else {
                        System.out.println("ERROR: invalid character "+peek);
                    }
            }
            if (isLetter(peek)) {
                return NAME();
            } else if (isDigit(peek)) {
                return NUMBER();
            }
        }
        System.out.println("end of file");
        return new Lexem(Token.EOF);
    }

    private void WS(){
        while (peek == ' ') {
            consume();
        }
    }

    private boolean consume(){
        //places next symbol in peek, returns false if the entire string has already been consumed
        if (start >= input.length()){
            start = start+1;
            return false;
        } else {
            peek = input.charAt(start);
            start = start+1;
            return true;
        }
    }

    private void rollBack(){
        start = start-1;
        peek = input.charAt(start-1);
    }
    private void rollBack(int n){
        start = start - n;
        peek = input.charAt(start-1);
    }

    private boolean match(char ch){
        if (!consume()){
            System.out.println("ERROR: expected " + ch + " but input ended ");
            return false;
        }
        if (peek == ch){
            return true;
        } else {
            //System.out.println("trying to match " + ch + " but found " + peek);
            rollBack();
            return false;
        }
    }

    private boolean isLetter(char ch){
        if (ch >= 'a' && ch <= 'z'){
            return true;
        }
        return ch >= 'A' && ch <= 'Z';
    }
    private boolean isDigit(char ch){
        return ch >= '0' && ch <= '9';
    }
    private boolean isLetterOrDigit(char ch){
        return isLetter(ch) || isDigit(ch);
    }

    private String tillEndOfLine(){
        StringBuilder sb = new StringBuilder();
        if (peek == '\n'){
            return null;
        }
        while(peek != '\n'){
            sb.append(peek);
            consume();
            if (start >= input.length()){
                sb.append(peek);
                break;
            }
        }
        return sb.toString();
    }

    private String tillEndOfWord(){
        StringBuilder sb = new StringBuilder();
        while (isLetterOrDigit(peek)){
            sb.append(peek);
            consume();
        }
        return sb.toString();
    }

    private String tillEndOfNumber(){
        StringBuilder sb = new StringBuilder();
        while (isDigit(peek)){
            sb.append(peek);
            consume();
        }
        return sb.toString();
    }

    private String tillEndOfString(){
        StringBuilder sb = new StringBuilder();
        while (peek != '"'){
            sb.append(peek);
            consume();
        }
        consume();
        return sb.toString();
    }

    private Lexem STRING(){
        return new Lexem(Token.STRING, tillEndOfString());
    }
    private Lexem NUMBER(){
        return new Lexem(Token.NUMBER, tillEndOfNumber());
    }
    private Lexem NAME(){
        return new Lexem(Token.ID, tillEndOfWord());
    }
}
