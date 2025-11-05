import org.antlr.v4.runtime.tree.ParseTree;
import java.util.List;
import java.util.Objects;

public class PrettyPrinter extends HelloBaseVisitor<String> {

    private int indent = 0;

    private String indent(){
        //creates 3 spaces per indent count
        return "   ".repeat(indent);
    }

    public String visitStart(HelloParser.StartContext ctx) {
        StringBuilder out = new StringBuilder();
        for (ParseTree ch : ctx.children) {
            if (ch instanceof HelloParser.StmtContext s) {
                out.append(visit(s));
            }
        }
        IO.println("PrettyPrinting: ");
        IO.println(out);
        return out.toString();
    }

    @Override
    public String visitASSIGN(HelloParser.ASSIGNContext ctx) {
        //ID ':=' expr NL   #ASSIGN
        String out = indent();
        out = ctx.children.get(0).toString();
        out += " := ";
        out += visit(ctx.children.get(2));
        out += "\n";
        return out;
    }
    @Override
    public String visitEX(HelloParser.EXContext ctx) {
        //expr NL           #EX
        String out = indent();
        out = visit(ctx.children.get(0));
        out += "\n";
        return out;
    }
    public String visitLOOP(HelloParser.LOOPContext ctx) {
        //while             #LOOP
        String out = visit(ctx.children.get(0));
        return out;
    }

    public String visitCHECK(HelloParser.CHECKContext ctx) {
        //if                #CHECK
        String out = visit(ctx.children.get(0));
        return out;
    }

    public String visitDIV(HelloParser.DIVContext ctx) {
        String out = visit(ctx.children.get(0));
        out += " \\ " + visit(ctx.children.get(2));
        return out;
    }

    public String visitMULT(HelloParser.MULTContext ctx) {
        String out = visit(ctx.children.get(0));
        out += " * " + visit(ctx.children.get(2));
        return out;
    }

    public String visitSUB(HelloParser.SUBContext ctx) {
        String out = visit(ctx.children.get(0));
        out += " - " + visit(ctx.children.get(2));
        return out;
    }

    public String visitADD(HelloParser.ADDContext ctx) {
        String out = visit(ctx.children.get(0));
        out += " + " + visit(ctx.children.get(2));
        return out;
    }

    @Override
    public String visitLT(HelloParser.LTContext ctx) {
        String out = visit(ctx.children.get(0));
        out += " < " + visit(ctx.children.get(2));
        return out;
    }

    @Override
    public String visitGT(HelloParser.GTContext ctx) {
        String out = visit(ctx.children.get(0));
        out += " > " + visit(ctx.children.get(2));
        return out;
    }

    @Override
    public String visitEQ(HelloParser.EQContext ctx) {
        String out = visit(ctx.children.get(0));
        out += " == " + visit(ctx.children.get(2));
        return out;
    }

    @Override
    public String visitNEQ(HelloParser.NEQContext ctx) {
        String out = visit(ctx.children.get(0));
        out += " != " + visit(ctx.children.get(2));
        return out;
    }

    public String visitVAR(HelloParser.VARContext ctx) {
        return ctx.children.getFirst().getText();
    }

    @Override
    public String visitNUM(HelloParser.NUMContext ctx) {
        return ctx.children.getFirst().getText();
    }

    @Override
    public String visitTXT(HelloParser.TXTContext ctx) {
        return ctx.children.getFirst().getText();
    }

    @Override
    public String visitWhile(HelloParser.WhileContext ctx) {
        //'while' expr 'do' NL stmt* 'end' NL
        List ch = ctx.children;
        String out = "";
        out += "while ";
        out += visit(ctx.children.get(1));
        out += " do \n";
        indent++;
        for (int i = 4; i < ch.size()-2; i++){
            out += indent() + visit(ctx.children.get(i));
        }
        indent--;
        out += indent() +"end \n";
        return out;
    }

    public String visitSIF(HelloParser.SIFContext ctx) {
        //: 'if' expr 'do' NL stmt* 'end' NL
        List ch = ctx.children;
        String out = "";
        out += "if ";
        out += visit(ctx.children.get(1));
        out += " do \n";
        indent++;
        for (int i = 4; i < ch.size()-2; i++){
            out += indent() + visit(ctx.children.get(i));
        }
        indent--;
        out += indent() +"end \n";
        return out;
    }

    public String visitLIF(HelloParser.LIFContext ctx) {
        //'if' expr 'do' NL stmt* 'else' 'do' NL stmt* 'end' NL
        List ch = ctx.children;
        String out = "";
        out += "if ";
        out += visit(ctx.children.get(1));
        out += " do \n";
        indent++;
        int i = 4;
        while (!Objects.equals(ch.get(i).toString(), "else")){
            out += indent() + visit(ctx.children.get(i));
            i++;
        }
        indent--;
        out += indent() +"else do \n";
        indent++;
        for (int j = i+3; j < ch.size()-2; j++){
            out += indent() + visit(ctx.children.get(j));
        }
        indent--;
        out += indent() +"end \n";
        return out;
    }


}
