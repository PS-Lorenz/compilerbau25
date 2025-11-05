import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;
import java.util.Objects;

public class ASTBuilder extends HelloBaseVisitor<Node>{

    public Node visitStart(HelloParser.StartContext ctx) {
        Node startNode = new Node("start");
        for (ParseTree ch : ctx.children) {
            if (ch instanceof HelloParser.StmtContext s) {
                startNode.addChild(visit(s));
            }
        }
        return startNode;
    }

    @Override
    public Node visitASSIGN(HelloParser.ASSIGNContext ctx) {
        //ID ':=' expr NL   #ASSIGN
        Node myNode = new Node(ctx.children.getFirst().getText());
        myNode.addValue(" := ");
        myNode.addChild(visit(ctx.children.get(2)));
        return myNode;
    }
    @Override
    public Node visitEX(HelloParser.EXContext ctx) {
        //expr NL           #EX
        return visit(ctx.children.get(0));
    }
    public Node visitLOOP(HelloParser.LOOPContext ctx) {
        //while             #LOOP
        return visit(ctx.children.get(0));
    }

    public Node visitCHECK(HelloParser.CHECKContext ctx) {
        //if                #CHECK
        return visit(ctx.children.get(0));
    }

    public Node visitDIV(HelloParser.DIVContext ctx) {
        //expr '*' expr
        Node myNode = new Node("/");
        myNode.addChild(visit(ctx.children.get(0)));
        myNode.addChild(visit(ctx.children.get(2)));
        return myNode;
    }

    public Node visitMULT(HelloParser.MULTContext ctx) {
        Node myNode = new Node("*");
        myNode.addChild(visit(ctx.children.get(0)));
        myNode.addChild(visit(ctx.children.get(2)));
        return myNode;
    }

    public Node visitSUB(HelloParser.SUBContext ctx) {
        Node myNode = new Node("-");
        myNode.addChild(visit(ctx.children.get(0)));
        myNode.addChild(visit(ctx.children.get(2)));
        return myNode;
    }

    public Node visitADD(HelloParser.ADDContext ctx) {
        Node myNode = new Node("+");
        myNode.addChild(visit(ctx.children.get(0)));
        myNode.addChild(visit(ctx.children.get(2)));
        return myNode;
    }

    @Override
    public Node visitLT(HelloParser.LTContext ctx) {
        Node myNode = new Node("<");
        myNode.addChild(visit(ctx.children.get(0)));
        myNode.addChild(visit(ctx.children.get(2)));
        return myNode;
    }

    @Override
    public Node visitGT(HelloParser.GTContext ctx) {
        Node myNode = new Node(">");
        myNode.addChild(visit(ctx.children.get(0)));
        myNode.addChild(visit(ctx.children.get(2)));
        return myNode;
    }

    @Override
    public Node visitEQ(HelloParser.EQContext ctx) {
        Node myNode = new Node("==");
        myNode.addChild(visit(ctx.children.get(0)));
        myNode.addChild(visit(ctx.children.get(2)));
        return myNode;
    }

    @Override
    public Node visitNEQ(HelloParser.NEQContext ctx) {
        Node myNode = new Node("!=");
        myNode.addChild(visit(ctx.children.get(0)));
        myNode.addChild(visit(ctx.children.get(2)));
        return myNode;
    }

    public Node visitVAR(HelloParser.VARContext ctx) {
        return new Node(ctx.children.getFirst().getText());
    }

    @Override
    public Node visitNUM(HelloParser.NUMContext ctx) {
        return new Node(ctx.children.getFirst().getText());
    }

    @Override
    public Node visitTXT(HelloParser.TXTContext ctx) {
        return new Node(ctx.children.getFirst().getText());
    }

    @Override
    public Node visitWhile(HelloParser.WhileContext ctx) {
        //'while' expr 'do' NL stmt* 'end' NL
        Node myNode = new Node("while");
        myNode.addChild(visit(ctx.children.get(1)));
        for (int i = 4; i < ctx.children.size()-2; i++){
            myNode.addChild(visit(ctx.children.get(i)));
        }
        return myNode;
    }

    public Node visitSIF(HelloParser.SIFContext ctx) {
        //: 'if' expr 'do' NL stmt* 'end' NL
        Node myNode = new Node("sif");
        myNode.addChild(visit(ctx.children.get(1)));

        for (int i = 4; i < ctx.children.size()-2; i++){
            myNode.addChild(visit(ctx.children.get(i)));
        }
        return myNode;
    }

    public Node visitLIF(HelloParser.LIFContext ctx) {
        //'if' expr 'do' NL stmt* 'else' 'do' NL stmt* 'end' NL
        Node myNode = new Node("lif");
        myNode.addChild(visit(ctx.children.get(1)));
        int i = 4;
        while (!Objects.equals(ctx.children.get(i).toString(), "else")){
            myNode.addChild(visit(ctx.children.get(i)));
            i++;
        }
        myNode.addChild(new Node("else"));
        for (int j = i+3; j < ctx.children.size()-2; j++){
            myNode.addChild(visit(ctx.children.get(j)));
        }
        return myNode;
    }


}

