public enum CommandWord {

    HELP("help"), 
    GO("go"), 
    QUIT("quit"), 
    UNKNOWN("unknown"), 
    LOOK("look"),
    TAKE("take"),
    DROP("drop"),
    GIVE("give"),
    TEST("test");

    private String command;

    CommandWord(String command) {
        this.command = command;
    }

    public String toString() {
        return command;
    }
}