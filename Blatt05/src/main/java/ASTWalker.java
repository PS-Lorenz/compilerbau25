import java.util.ArrayList;
import java.util.List;

public class ASTWalker {

    Scope globalScope;
    Scope currentScope;
    int currentDepth = 0;
    List<Stmt> AST ;
    public ASTWalker(List<Stmt> AST){
        this.AST = AST;
        this.globalScope = new Scope(null,0);
        this.currentScope = globalScope;
        System.out.println("Scope begins     : " +currentDepth + " " + currentScope);
    }

    public void walk(){
        visitStmtL(AST);
    }

    public void visitStmt(Stmt stmt){
        switch (stmt) {
            case Stmt.vardecl v -> {
                v.setScope(currentScope);
                Symbol sym = new Symbol(v.id(),v.type(),v.scope());
                if (!isInThisScope(v.id(),v.scope())){
                    bind(sym);
                } else {
                    System.out.println(" ERR :Trying to declare variable "+sym.name+ " but already exists in scope " + sym.scope);
                }
                visitExpr(v.expr());
            }
            case Stmt.assign v -> {
                v.setScope(currentScope);
                Symbol sym = resolve(v.id(),v.scope());
                if (sym != null){
                    bind(sym);
                } else {
                    System.out.println(" ERR :Trying to assign variable "+sym.name+ " but does not exists in scope " + sym.scope);
                }
                visitExpr(v.expr());
            }
            case Stmt.fndecl v -> {
                v.setScope(currentScope);
                Symbol sym = new Symbol(v.id(),v.type(),v.scope());
                if (!isInThisScope(v.id(),v.scope())){
                    bind(sym);
                } else {
                    System.out.println(" ERR :Trying to declare function "+sym.name+ " but already exists in scope " + sym.scope);
                }
                if (v.params() != null){
                    currentDepth++;
                    currentScope = new Scope(currentScope,currentDepth);
                    System.out.println("Scope goes deeper: "+currentDepth + " " + currentScope);
                    visitStmt(v.params());
                    currentScope = currentScope.parent;
                    currentDepth--;
                    System.out.println("Scope returns    : "+currentDepth + " " + currentScope);
                }
                visitStmt(v.block());
            }
            case Stmt.expr v -> {
                v.setScope(currentScope);
                visitExpr(v.expr());
            }
            case Stmt.params v -> {
                v.setScope(currentScope);
                int i = 0;
                for (String id : v.ids()){
                    if (resolve(id,v.scope())!=null) {
                        System.out.println(" ERR :Trying to declare variable " + id + " but already exists in scope " + v.scope());
                    } else {
                        bind(new Symbol(id,v.types().get(i),v.scope()));
                    }
                    i++;
                }
            }
            case Stmt.Return v -> {
                v.setScope(currentScope);
                visitExpr(v.expr());
            }
            case Stmt.fncall v -> {
                v.setScope(currentScope);
                Symbol sym = resolve(v.id(),v.scope());
                System.out.println(sym);
                if (sym == null) {
                    System.out.println(" ERR :Trying to call function "+v.id()+ " but does not exists in scope " + v.scope());
                }
                visitStmt(v.args());
            }
            case Stmt.args v -> {
                v.setScope(currentScope);
                visitExprL(v.exprs());
            }
            case Stmt.block v -> {
                currentDepth++;
                currentScope = new Scope(currentScope,currentDepth);
                v.setScope(currentScope);
                System.out.println("Scope goes deeper: "+currentDepth + " " + currentScope);
                visitStmtL(v.stmts());
                currentScope = currentScope.parent;
                currentDepth--;
                System.out.println("Scope returns    : "+currentDepth + " " + currentScope);
            }
            case Stmt.While v -> {
                v.setScope(currentScope);
                visitExpr(v.expr());
                visitStmt(v.block());
            }
            case Stmt.cond v -> {
                v.setScope(currentScope);
                visitExpr(v.expr());
                visitStmt(v.block());
                if (v.elblock() != null) {
                    visitStmt(v.elblock());
                }
            }
            default -> throw new IllegalStateException("Unexpected state");
        };
    }
    public void visitStmtL(List<Stmt> stmts){
        for (Stmt stmt : stmts) {
            visitStmt(stmt);
        }
    }

    public void visitExpr(Expr expr){
        switch (expr) {
            case Expr.Call e -> {
                e.setScope(currentScope);
                Symbol sym = resolve(e.id(),e.scope());
                if (sym == null) {
                    System.out.println(" ERR :Trying to call function "+e.id()+ " but does not exists in scope " + e.scope());
                }
                visitExprL(e.args());
            }
            case Expr.Binary e -> {
                e.setScope(currentScope);
            }
            case Expr.IntLiteral e -> {
                e.setScope(currentScope);
            }
            case Expr.StringLiteral e -> {
                e.setScope(currentScope);
            }
            case Expr.BoolLiteral e -> {
                e.setScope(currentScope);
            }
            case Expr.Variable e -> {
                e.setScope(currentScope);
                resolve(e.id(), e.scope());
            }
            default -> throw new IllegalStateException("Unexpected state");
        }
    }

    public void visitExprL(List<Expr> exprs){
        for (Expr expr : exprs) {
            visitExpr(expr);
        }
    }

    public void bind(Symbol symbol){
        currentScope.symbols.add(symbol);
    }

    public Symbol resolve(String name,Scope scope){
        for (Symbol symbol : scope.symbols) {
            if (symbol.name.equals(name)) {
                return symbol;
            }
        }
        if (scope.parent != null) {
            return resolve(name, scope.parent);
        }
        return null;
    }
    private boolean isInThisScope(String name, Scope scope){
        for (Symbol symbol : scope.symbols) {
            if (symbol.name.equals(name)) {
                return true;
            }
        }
        return  false;
    }

}
