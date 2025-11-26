import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ASTBuilder{

    static List<Stmt> toAst(minicParser.ProgramContext s) {
        return s.stmt().stream()
            .map(ASTBuilder::toAst)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    static Stmt toAst(minicParser.StmtContext s) {
        return switch (s) {
            case minicParser.VARDECLSContext c -> new Stmt.vardecl(Type.fromString(c.vardecl().type().getText()), c.vardecl().ID().getText(), toAst(c.vardecl().expr()));
            case minicParser.ASSIGNSContext a -> new Stmt.assign(a.assign().ID().getText(),toAst(a.assign().expr()));
            case minicParser.FNDECLSContext fnd -> new Stmt.fndecl(Type.fromString(fnd.fndecl().type().getText()),fnd.fndecl().ID().getText(),toAst(fnd.fndecl().params()),toAst(fnd.fndecl().block()));
            case minicParser.EXPRSContext ex -> new Stmt.expr(toAst(ex.expr()));
            case minicParser.BLOCKSContext b -> toAst(b.block());
            case minicParser.WHILESContext w -> new Stmt.While(toAst(w.while_().expr()),toAst(w.while_().block()));
            case minicParser.CONDSContext c -> new Stmt.cond(toAst(c.cond().expr()),toAst(c.cond().block(0)),toAst(c.cond().block(1)));
            case minicParser.RETURNSContext r -> new Stmt.Return(toAst(r.return_().expr()));
            default -> throw new IllegalStateException();
        };
    }

    private static Stmt.block toAst(minicParser.BlockContext block) {
        if (block != null) {
            return new Stmt.block(toAst(block.stmt()));
        } else {
            return null;
        }
    }

    private static List<Stmt> toAst(List<minicParser.StmtContext> stmt) {
        List<Stmt> stmts = new ArrayList<>();
        for (minicParser.StmtContext s : stmt) {
            stmts.add(toAst(s));
        }
        return stmts;
    }

    private static Stmt.params toAst(minicParser.ParamsContext params) {
        List<Type> types = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < params.type().size(); i++) {
            types.add(Type.fromString(params.type(i).getText()));
            ids.add(params.ID(i).getText());
        }
        return new Stmt.params(types,ids,null);
    }

    static Expr toAst(minicParser.ExprContext e) {
        return switch (e) {
            case minicParser.CALLContext c -> new Expr.Call(c.fncall().ID().getText(),toAst(c.fncall().args()));
            case minicParser.MDContext md -> new Expr.Binary(toAst(md.expr(0)),Operator.fromSymbol(md.getChild(1).getText()),toAst(md.expr(1)));
            case minicParser.PMContext pm ->  new Expr.Binary(toAst(pm.expr(0)),Operator.fromSymbol(pm.getChild(1).getText()),toAst(pm.expr(1)));
            case minicParser.GLContext gl -> new Expr.Binary(toAst(gl.expr(0)),Operator.fromSymbol(gl.getChild(1).getText()),toAst(gl.expr(1)));
            case minicParser.EQNQContext eq -> new Expr.Binary(toAst(eq.expr(0)),Operator.fromSymbol(eq.getChild(1).getText()),toAst(eq.expr(1)));
            case minicParser.IDENTContext ident -> new Expr.StringLiteral(ident.ID().getText());
            case minicParser.NUMContext num -> new Expr.IntLiteral(Integer.parseInt(num.NUMBER().getText()));
            case minicParser.STRContext str -> new Expr.StringLiteral(str.STRING().getText());
            case minicParser.BTContext bt -> new Expr.BoolLiteral(true);
            case minicParser.BFContext bf -> new Expr.BoolLiteral(false);
            case minicParser.BRAKContext brak -> toAst(brak.expr());
            default -> throw new IllegalStateException("Unexpected value: " + e);
        };
    }

    private static List<Expr> toAst(minicParser.ArgsContext args) {
        List<Expr> exprs = new ArrayList<>();
        for (int i = 0; i < args.expr().size(); i++) {
            exprs.add(toAst(args.expr(i)));
        }
        return exprs;
    }

}



enum Operator{
    EQ("=="),
    NEQ("!="),
    PLUS("+"),
    MINUS("-"),
    MUL("*"),
    DIV("/"),
    LT("<"),
    GT(">");

    private final String symbol;
    Operator(String symbol) {
        this.symbol = symbol;
    }
    public String getSymbol() {
        return symbol;
    }
    public static Operator fromSymbol(String symbol) {
        for (Operator op : Operator.values()) {
            if (op.symbol.equals(symbol)) {
                return op;
            }
        }
        return null;
    }
}

enum Type{
    INT("int"),
    STRING("string"),
    BOOL("bool");
    private final String type;
    Type(String type) {
        this.type = type;
    }
    public static Type fromString(String string) {
        for (Type t : Type.values()) {
            if (t.type.equals(string)) {
                return t;
            }
        }
        return null;
    }
}
