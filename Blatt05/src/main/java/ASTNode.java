import java.util.List;
import java.util.Objects;

interface Stmt {

    class vardecl implements Stmt {
        private final Type type;
        private final String id;
        private final Expr expr;
        private Scope scope;

        public vardecl(Type type, String id, Expr expr) {
            this.type = type;
            this.id = id;
            this.expr = expr;
            this.scope = null;
        }
        public vardecl(Type type, String id, Expr expr, Scope scope) {
            this.type = type;
            this.id = id;
            this.expr = expr;
            this.scope = scope;
        }

        public void setScope(Scope scope) {
            this.scope = scope;
        }

        public Type type() {
            return type;
        }

        public String id() {
            return id;
        }

        public Expr expr() {
            return expr;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (vardecl) obj;
            return Objects.equals(this.type, that.type) &&
                Objects.equals(this.id, that.id) &&
                Objects.equals(this.expr, that.expr) &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, id, expr, scope);
        }

        @Override
        public String toString() {
            return "vardecl[" +
                "type=" + type + ", " +
                "id=" + id + ", " +
                "expr=" + expr + ", " +
                "scope=" + scope + ']';
        }

        }

    class assign implements Stmt {
        private final String id;
        private final Expr expr;
        private Scope scope;

        public assign(String id, Expr expr) {
            this.id = id;
            this.expr = expr;
            this.scope = null;
        }

        public assign(String id, Expr expr, Scope scope) {
            this.id = id;
            this.expr = expr;
            this.scope = scope;
        }

        public void setScope(Scope scope) {
            this.scope = scope;
        }

        public String id() {
            return id;
        }

        public Expr expr() {
            return expr;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (assign) obj;
            return Objects.equals(this.id, that.id) &&
                Objects.equals(this.expr, that.expr) &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, expr, scope);
        }

        @Override
        public String toString() {
            return "assign[" +
                "id=" + id + ", " +
                "expr=" + expr + ", " +
                "scope=" + scope + ']';
        }

        }

    class fndecl implements Stmt {
        private final Type type;
        private final String id;
        private final params params;
        private final block block;
        private Scope scope;

        public fndecl(Type type, String id, params params, block block) {
            this.type = type;
            this.id = id;
            this.params = params;
            this.block = block;
            this.scope = null;
        }

        public fndecl(Type type, String id, params params, block block, Scope scope) {
            this.type = type;
            this.id = id;
            this.params = params;
            this.block = block;
            this.scope = scope;
        }

        public void setScope(Scope scope) {
            this.scope = scope;
        }

        public Type type() {
            return type;
        }

        public String id() {
            return id;
        }

        public params params() {
            return params;
        }

        public block block() {
            return block;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (fndecl) obj;
            return Objects.equals(this.type, that.type) &&
                Objects.equals(this.id, that.id) &&
                Objects.equals(this.params, that.params) &&
                Objects.equals(this.block, that.block) &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, id, params, block, scope);
        }

        @Override
        public String toString() {
            return "fndecl[" +
                "type=" + type + ", " +
                "id=" + id + ", " +
                "params=" + params + ", " +
                "block=" + block + ", " +
                "scope=" + scope + ']';
        }

        }

    class expr implements Stmt {
        private final Expr expr;
        private Scope scope;

        public expr(Expr expr, Scope scope) {
            this.expr = expr;
            this.scope = scope;
        }
        public expr(Expr expr) {
            this.expr = expr;
            this.scope = null;
        }

        public void setScope(Scope scope) {
            this.scope = scope;
        }

        public Expr expr() {
            return expr;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (expr) obj;
            return Objects.equals(this.expr, that.expr) &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(expr, scope);
        }

        @Override
        public String toString() {
            return "expr[" +
                "expr=" + expr + ", " +
                "scope=" + scope + ']';
        }
    }

    class params implements Stmt {
        private final List<Type> types;
        private final List<String> ids;
        private Scope scope;

        public params(List<Type> types, List<String> ids, Scope scope) {
            this.types = types;
            this.ids = ids;
            this.scope = scope;
        }
        public params(List<Type> types, List<String> ids) {
            this.types = types;
            this.ids = ids;
            this.scope = null;
        }
        public void setScope(Scope scope) {
            this.scope = scope;
        }

        public List<Type> types() {
            return types;
        }

        public List<String> ids() {
            return ids;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (params) obj;
            return Objects.equals(this.types, that.types) &&
                Objects.equals(this.ids, that.ids) &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(types, ids, scope);
        }

        @Override
        public String toString() {
            return "params[" +
                "types=" + types + ", " +
                "ids=" + ids + ", " +
                "scope=" + scope + ']';
        }

        }

    class Return implements Stmt {
        private final Expr expr;
        private Scope scope;

        public Return(Expr expr, Scope scope) {
            this.expr = expr;
            this.scope = scope;
        }
        public  Return(Expr expr) {
            this.expr = expr;
            this.scope = null;
        }

        public void setScope(Scope scope) {
            this.scope = scope;
        }

        public Expr expr() {
            return expr;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Return) obj;
            return Objects.equals(this.expr, that.expr) &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(expr, scope);
        }

        @Override
        public String toString() {
            return "Return[" +
                "expr=" + expr + ", " +
                "scope=" + scope + ']';
        }

        }

    class fncall implements Stmt {
        private final String id;
        private final args args;
        private Scope scope;

        public fncall(String id, args args, Scope scope) {
            this.id = id;
            this.args = args;
            this.scope = scope;
        }
        public fncall(String id,args args) {
            this.id = id;
            this.args = args;
            this.scope = null;
        }

        public void setScope(Scope scope) {
            this.scope = scope;
        }

        public String id() {
            return id;
        }

        public args args() {
            return args;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (fncall) obj;
            return Objects.equals(this.id, that.id) &&
                Objects.equals(this.args, that.args) &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, args, scope);
        }

        @Override
        public String toString() {
            return "fncall[" +
                "id=" + id + ", " +
                "args=" + args + ", " +
                "scope=" + scope + ']';
        }

        }

    class args implements Stmt {
        private final List<Expr> exprs;
        private Scope scope;

        public args(List<Expr> exprs, Scope scope) {
            this.exprs = exprs;
            this.scope = scope;
        }
        public args(List<Expr> exprs) {
            this.exprs = exprs;
            this.scope = null;
        }
        public void setScope(Scope scope) {
            this.scope = scope;
        }

        public List<Expr> exprs() {
            return exprs;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (args) obj;
            return Objects.equals(this.exprs, that.exprs) &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(exprs, scope);
        }

        @Override
        public String toString() {
            return "args[" +
                "exprs=" + exprs + ", " +
                "scope=" + scope + ']';
        }

        }

    class block implements Stmt {
        private final List<Stmt> stmts;
        private Scope scope;

        public block(List<Stmt> stmts, Scope scope) {
            this.stmts = stmts;
            this.scope = scope;
        }
        public block(List<Stmt> stmts) {
            this.stmts = stmts;
            this.scope = null;
        }
        public void setScope(Scope scope) {
            this.scope = scope;
        }

        public List<Stmt> stmts() {
            return stmts;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (block) obj;
            return Objects.equals(this.stmts, that.stmts) &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(stmts, scope);
        }

        @Override
        public String toString() {
            return "block[" +
                "stmts=" + stmts + ", " +
                "scope=" + scope + ']';
        }

        }

    class While implements Stmt {
        private final Expr expr;
        private final block block;
        private Scope scope;

        public While(Expr expr, block block, Scope scope) {
            this.expr = expr;
            this.block = block;
            this.scope = scope;
        }
        public While(Expr expr, block block) {
            this.expr = expr;
            this.block = block;
            this.scope = null;
        }

        public void setScope(Scope scope) {
            this.scope = scope;
        }

        public Expr expr() {
            return expr;
        }

        public block block() {
            return block;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (While) obj;
            return Objects.equals(this.expr, that.expr) &&
                Objects.equals(this.block, that.block) &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(expr, block, scope);
        }

        @Override
        public String toString() {
            return "While[" +
                "expr=" + expr + ", " +
                "block=" + block + ", " +
                "scope=" + scope + ']';
        }

        }

    class cond implements Stmt {
        private final Expr expr;
        private final block block;
        private final block elblock;
        private Scope scope;

        public cond(Expr expr, block block, block elblock, Scope scope) {
            this.expr = expr;
            this.block = block;
            this.elblock = elblock;
            this.scope = scope;
        }
        public cond(Expr expr, block block, block elblock) {
            this.expr = expr;
            this.block = block;
            this.elblock = elblock;
            this.scope = null;
        }
        public void setScope(Scope scope) {
            this.scope = scope;
        }

        public Expr expr() {
            return expr;
        }

        public block block() {
            return block;
        }

        public block elblock() {
            return elblock;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (cond) obj;
            return Objects.equals(this.expr, that.expr) &&
                Objects.equals(this.block, that.block) &&
                Objects.equals(this.elblock, that.elblock) &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(expr, block, elblock, scope);
        }

        @Override
        public String toString() {
            return "cond[" +
                "expr=" + expr + ", " +
                "block=" + block + ", " +
                "elblock=" + elblock + ", " +
                "scope=" + scope + ']';
        }

        }
}

interface Expr {
    class IntLiteral implements Expr {
        private final int value;
        private Scope scope;

        public IntLiteral(int value, Scope scope) {
            this.value = value;
            this.scope = scope;
        }
        public IntLiteral(int value) {
            this.value = value;
            this.scope = null;
        }

        public void setScope(Scope scope) {
            this.scope = scope;
        }

        public int value() {
            return value;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (IntLiteral) obj;
            return this.value == that.value &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, scope);
        }

        @Override
        public String toString() {
            return "IntLiteral[" +
                "value=" + value + ", " +
                "scope=" + scope + ']';
        }
    }

    class StringLiteral implements Expr {
        private final String value;
        private Scope scope;

        public StringLiteral(String value, Scope scope) {
            this.value = value;
            this.scope = scope;
        }
        public StringLiteral(String value) {
            this.value = value;
            this.scope = null;
        }
        public void setScope(Scope scope) {
            this.scope = scope;
        }

        public String value() {
            return value;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (StringLiteral) obj;
            return Objects.equals(this.value, that.value) &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, scope);
        }

        @Override
        public String toString() {
            return "StringLiteral[" +
                "value=" + value + ", " +
                "scope=" + scope + ']';
        }
    }

    class BoolLiteral implements Expr {
        private final boolean value;
        private Scope scope;

        public BoolLiteral(boolean value, Scope scope) {
            this.value = value;
            this.scope = scope;
        }
        public BoolLiteral(boolean value) {
            this.value = value;
            this.scope = null;
        }
        public void setScope(Scope scope) {
            this.scope = scope;
        }

        public boolean value() {
            return value;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (BoolLiteral) obj;
            return this.value == that.value &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, scope);
        }

        @Override
        public String toString() {
            return "BoolLiteral[" +
                "value=" + value + ", " +
                "scope=" + scope + ']';
        }
    }

    class Variable implements Expr {
        private final String id;
        private Scope scope;

        public Variable(String id, Scope scope) {
            this.id = id;
            this.scope = scope;
        }
        public Variable(String id) {
            this.id = id;
            this.scope = null;
        }
        public void setScope(Scope scope) {
            this.scope = scope;
        }


        public String id() {
            return id;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Variable) obj;
            return Objects.equals(this.id, that.id) &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, scope);
        }

        @Override
        public String toString() {
            return "Variable[" +
                "id=" + id + ", " +
                "scope=" + scope + ']';
        }
    }

    class Binary implements Expr {
        private final Expr left;
        private final Operator op;
        private final Expr right;
        private Scope scope;

        public Binary(Expr left, Operator op, Expr right, Scope scope) {
            this.left = left;
            this.op = op;
            this.right = right;
            this.scope = scope;
        }
        public Binary(Expr left, Operator op, Expr right) {
            this.left = left;
            this.op = op;
            this.right = right;
            this.scope = null;
        }
        public void setScope(Scope scope) {
            this.scope = scope;
        }

        public Expr left() {
            return left;
        }

        public Operator op() {
            return op;
        }

        public Expr right() {
            return right;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Binary) obj;
            return Objects.equals(this.left, that.left) &&
                Objects.equals(this.op, that.op) &&
                Objects.equals(this.right, that.right) &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, op, right, scope);
        }

        @Override
        public String toString() {
            return "Binary[" +
                "left=" + left + ", " +
                "op=" + op + ", " +
                "right=" + right + ", " +
                "scope=" + scope + ']';
        }
    }

    class Call implements Expr {
        private final String id;
        private final List<Expr> args;
        private Scope scope;

        public Call(String id, List<Expr> args, Scope scope) {
            this.id = id;
            this.args = args;
            this.scope = scope;
        }
        public Call(String id, List<Expr> args) {
            this.id = id;
            this.args = args;
            this.scope = null;
        }
        public void setScope(Scope scope) {
            this.scope = scope;
        }

        public String id() {
            return id;
        }

        public List<Expr> args() {
            return args;
        }

        public Scope scope() {
            return scope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Call) obj;
            return Objects.equals(this.id, that.id) &&
                Objects.equals(this.args, that.args) &&
                Objects.equals(this.scope, that.scope);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, args, scope);
        }

        @Override
        public String toString() {
            return "Call[" +
                "id=" + id + ", " +
                "args=" + args + ", " +
                "scope=" + scope + ']';
        }
    }
}



