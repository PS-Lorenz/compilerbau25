public record Lexem(Token ID, String value) {
    public Lexem(Token ID){
        this(ID, ID.name());
    }
}
