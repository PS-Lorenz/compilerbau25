public class Parser {
    Lexer lex;
    Lexem lookahead;

    public Parser(Lexer lex) {
        this.lex = lex;
        lookahead = lex.nextToken();
    }

    public void program(){
        do{
            expr();
        }while(lookahead.ID() != Token.EOF);
    }

    private void expr(){

        switch(lookahead.ID()){
            //literal/symbol
            case NUMBER:
                match(Token.NUMBER);
                break;
            case STRING:
                match(Token.STRING);
                break;
            case ID:
                match(Token.ID);
                break;
            case BOOL:
                match(Token.BOOL);
                break;
            case LBRAK:
                //an open bracket
                openBrak();
                break;
            default:
                System.out.println(lookahead);
                break;
        }
    }

    private void openBrak(){
        match(Token.LBRAK);
        switch(lookahead.ID()){
            case DEF:
                match(Token.DEF);
                def();
                break;
            case DEFN:
                match(Token.DEFN);
                defn();
                break;
            case LET:
                match(Token.LET);
                let();
                break;
            case IF:
                match(Token.IF);
                ifb();
                break;
            case DO:
                match(Token.DO);
                dob();
                break;
            case LIST:
                match(Token.LIST);
                list();
                break;
            case HEAD:
                match(Token.HEAD);
                head();
                break;
            case TAIL:
                match(Token.TAIL);
                tail();
                break;
            case NTH:
                match(Token.NTH);
                nth();
                break;
            case STR:
                match(Token.STR);
                str();
                break;
            case PRINT:
                match(Token.PRINT);
                print();
                break;
            case OP:
                op();
                break;
            case ID:
                funccall();
                break;
        }
        match(Token.RBRAK);
    }

    private void def(){
        match(Token.ID);
        expr();
    }

    private void defn(){
        match(Token.ID);
        match(Token.LBRAK);
        while(lookahead.ID() != Token.RBRAK){
            match(Token.ID);
        }
        match(Token.RBRAK);
        expr();
    }

    private void let(){
        match(Token.LBRAK);
        bindings();
        match(Token.RBRAK);
        expr();
    }

    private void ifb(){
        expr();
        expr();
        if(lookahead.ID() == Token.LBRAK || lookahead.ID() == Token.NUMBER || lookahead.ID() == Token.STRING || lookahead.ID() == Token.ID || lookahead.ID() == Token.BOOL){
            expr();
        }
    }

    private void dob(){
        while(lookahead.ID() != Token.RBRAK){
            expr();
        }
    }

    private void list(){
        while(lookahead.ID() != Token.RBRAK){
            expr();
        }
    }

    private void head(){
        match(Token.LBRAK);
        match(Token.LIST);
        list();
        match(Token.RBRAK);
    }

    private void tail(){
        match(Token.LBRAK);
        match(Token.LIST);
        list();
        match(Token.RBRAK);
    }

    private void nth(){
        match(Token.LBRAK);
        match(Token.LIST);
        list();
        match(Token.RBRAK);
        expr();
    }

    private void str(){
        while(lookahead.ID() != Token.RBRAK){
            expr();
        }
    }

    private void print(){
        expr();
    }

    private void op(){
        match(Token.OP);
        while(lookahead.ID() != Token.RBRAK){
            expr();
        }
    }

    private void bindings(){
        while(lookahead.ID() != Token.RBRAK){
            match(Token.ID);
            expr();
        }
    }

    private void funccall(){
        match(Token.ID);
        expr();
    }

    private void match(Token token){
        if (lookahead.ID() == token){
            System.out.println("MATCHED " + lookahead);
            consume();
        } else {
            System.out.println("ERROR: expected " + token + " found " + lookahead);
            throw new Error("ERROR: expected " + token + " found " + lookahead);
        }
    }

    private void consume(){
        lookahead = lex.nextToken();
        if (lookahead.ID() == Token.COMMENT){
            System.out.println("COMMENT found " + lookahead);
            consume();
        }
    }
}
