import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import my.pkg.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
  static void main(String... args) throws IOException, URISyntaxException {

    // Einlesen über Konsole/Prompt

    StringBuilder sb = new StringBuilder();
    while (true) {
        String line = IO.readln("lines ?> ");
        if (line == null || line.isBlank()) break;
        sb.append(line).append('\n');
    }
    String input = sb.toString();

    HelloLexer lexer = new HelloLexer(CharStreams.fromString(input));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    HelloParser parser = new HelloParser(tokens);

    ParseTree tree = parser.start(); // Start-Regel
    IO.println(tree.toStringTree(parser));
    PrettyPrinter eval = new PrettyPrinter();
    eval.visit(tree);

    //ASTBuilder eval2 = new ASTBuilder();
    //Node node = eval2.visit(tree);
    //IO.println(node.toString());
  }
}
