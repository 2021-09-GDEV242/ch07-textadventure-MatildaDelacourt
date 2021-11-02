/**
 * 
 *  This class is the main class of the "Maze University" application. 
 *  "Maze of University" is a very simple, text based adventure game.  Users 
 *  can walk around the university and look around what is there in each room. 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Matilda Delacourt
 * @version 2021.11.01
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together
     * Also creates items and their description with weight for
     * each room.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office, gym, musicRoom, artsRoom, 
        bathroom, basement, studyRoom, library, cafeteria, teachersLounge, secretGarden;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        gym = new Room("in the gym");
        musicRoom = new Room("in the music room");
        artsRoom = new Room("in the arts room");
        bathroom = new Room("in the school's bathroom");
        basement = new Room("in the basement");
        studyRoom = new Room("in the study room");
        library = new Room("in the library");
        cafeteria = new Room("in the cafeteria");
        teachersLounge = new Room("in the teachers lounge");
        secretGarden = new Room("in the secret garden");
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        theater.setExit("west", outside);
        pub.setExit("east", outside);
        lab.setExit("north", outside);
        lab.setExit("east", office);
        office.setExit("west", lab);
        office.setExit("north", library);
        office.setExit("south", teachersLounge);
        library.setExit("south", office);
        library.setExit("east", studyRoom);
        library.setExit("west", cafeteria);
        library.setExit("north", secretGarden);
        teachersLounge.setExit("north", office);
        teachersLounge.setExit("south", basement);
        teachersLounge.setExit("east", artsRoom);
        teachersLounge.setExit("west", musicRoom);
        artsRoom.setExit("west", teachersLounge);
        artsRoom.setExit("south", gym);
        artsRoom.setExit("north", cafeteria);
        artsRoom.setExit("east", basement);
        cafeteria.setExit("south", artsRoom);
        cafeteria.setExit("east", library);
        cafeteria.setExit("west", bathroom);
        gym.setExit("south", secretGarden);
        gym.setExit("east", bathroom);
        gym.setExit("north", artsRoom);
        musicRoom.setExit("east", teachersLounge);
        basement.setExit("north", teachersLounge);
        basement.setExit("west", artsRoom);
        secretGarden.setExit("south", library);
        secretGarden.setExit("north", gym);
        bathroom.setExit("east", cafeteria);
        bathroom.setExit("west", gym);
        studyRoom.setExit("west", library);
    
        currentRoom = outside;  // start game outside
        
        // Making the items and their description.   
        Item pencil, eraser, flashlight, watch, soda, candybar, chips;
        Item keys, book, flowers, boxofchalk, sandwich;
        Item toiletpaper, paint, ruler, juicebox, paintbrush, ball, jumpingrope, flute, toolbox, whiskey, vodka, chemicalvials, candles, pizza, skateboard;
      
        // creates items
        pencil = new Item("a pencil that you can write with", 3);
        eraser = new Item("a eraser you can erase with", 3);
        flashlight = new Item("a flashlight for dark areas", 10);
        watch = new Item("a watch to tell you the time", 5);
        soda = new Item("a soda pop in case you're thirsty", 10); 
        chips = new Item("some chips in case you're hungry", 3);
        keys = new Item("a key to somewhere", 5);
        book = new Item ("a notebook for you to write on",5);
        flowers = new Item("flowers from the garden", 5); 
        boxofchalk = new Item("a box of chalk", 7);
        sandwich = new Item("a sandwich in case you're hungry", 6);
        toiletpaper = new Item("a toilet paper roll", 3);
        paint = new Item("a can of paint", 15);
        paintbrush = new Item("a paintbrush", 3);
        candybar = new Item("a candy bar in case you're hungry", 3);
        ruler = new Item("a ruler", 2);
        juicebox = new Item("a juice box in case you're thirsty", 4);
        ball = new Item("a ball to play with", 7);
        jumpingrope = new Item("a jumping rope", 4);
        flute = new Item("a flute", 5);
        toolbox = new Item("a small toolbox", 13);
        whiskey = new Item("a whiskey bottle", 10);
        vodka = new Item("a vodka bottle", 10);
        chemicalvials = new Item("a chemical vial", 2);
        candles = new Item("a candle", 2) ;
        pizza = new Item("a pizza slice", 3);
        skateboard = new Item("a skateboard", 25);
         
        outside.addItem(new Item[] {juicebox, candybar});
        theater.addItem(new Item[] {watch, flashlight});
        pub.addItem(new Item[] {whiskey, vodka});
        lab.addItem(new Item[] {boxofchalk, chemicalvials});
        office.addItem(new Item[] {keys, pencil});
        library.addItem(new Item[] {book, candles});
        teachersLounge.addItem(new Item[] {chips, book});
        artsRoom.addItem(new Item[] {paintbrush, paint});
        cafeteria.addItem(new Item[] {sandwich, juicebox, soda, pizza});
        gym.addItem(new Item[] {ball, jumpingrope});
        musicRoom.addItem(new Item[] {flute, boxofchalk});
        basement.addItem(new Item[] {toiletpaper, toolbox});
        secretGarden.addItem(new Item[] {flowers, skateboard});
        bathroom.addItem(new Item[] {toiletpaper, juicebox});
        studyRoom.addItem(new Item[] {book, pencil, eraser, chips, ruler});
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Maze University!");
        System.out.println("Maze University, is a basic adventure game where you have to explore a University and see what you can find");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());

        System.out.println(currentRoom.getItem());
        

    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;
            
            case LOOK:
                System.out.println(currentRoom.getLongDescription());
                break;
                
            case EAT:
                System.out.println("You have eaten now and you are not hungry any more");
                break;

            case HELP:
                printHelp();
                break;
            
            case GO:
                goRoom(command);
                break;
                
            case BACK:
                goBack();
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
        
    }
    
    // implementations of user commands:
    /**
     * Print out some help information.
     * Here we print a message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * 
     * @param command is recieving whats typed in.
     *
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            System.out.println(currentRoom.getItem());
        }
    }
    /**
     * "back" was entered. Allows user to go back one room.
     */
    private void goBack(){
        if (previousRoom == null) {
            System.out.println("No room to go back to!");

        }
        else{
            currentRoom = previousRoom;
            previousRoom = null;
            System.out.println(currentRoom.getLongDescription());
            System.out.println(currentRoom.getItem());
        }
    }
      /** 
     * "look" was entered. Check the rest of the command to see
     * whether we are observing the room we're in.
     */
    private void look()
    {
      System.out.println(currentRoom.getLongDescription());
    }
    
    public static void main(String[] args){
       Game game = new Game();
       game.play(); 
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @param command getting keyboard input.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
