import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
  static void main(String... args) {
      Path fileName = Path.of("C:\\Users\\itsch\\IdeaProjects\\Blatt05\\src\\main\\resources\\test3.txt");
      String str = null;
      try {
          str = Files.readString(fileName);
      } catch (IOException e) {
          throw new RuntimeException(e);
      }

      CharStream input = CharStreams.fromString(str);
      minicLexer lexer = new minicLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      minicParser parser = new minicParser(tokens);

      minicParser.ProgramContext tree = parser.program();
      List<Stmt> ast = ASTBuilder.toAst(tree);
      ASTWalker walker = new ASTWalker(ast);
      walker.walk();
      IO.println(ast);
  }
}
