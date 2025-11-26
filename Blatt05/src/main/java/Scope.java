import java.util.ArrayList;
import java.util.List;

class Scope {
    Scope parent;
    List<Symbol> symbols;
    int depth;
    Scope (Scope parent,int depth) {
        this.parent = parent;
        symbols = new ArrayList<Symbol>();
        this.depth = depth;
    }
    public List<Symbol> getSymbols(){
        return symbols;
    }
}
class Symbol{
    String name;
    Type type;
    Scope scope;
    Symbol(String name,Type type,Scope scope){
        this.name = name;
        this.type = type;
        this.scope = scope;
    }
}

