import java.util.*;

/**
 * This class is part of the "Maze University" application. 
 * "Maze University" is a basic, text based adventure game.  
 * 
 * 
 * An "item" represents every item you can find in this game, for each item there 
 * is a description and its weight.
 * Here you are printing the items description and weight.
 *
 * @author Matilda Delacourt
 * @version 2021.11.01
 */
public class Item
{
    // instance variables 
    private String itemDescription;
    private int weight;
    
    /**
     * Constructor for objects of class Item
     *@param description, the description of the item.
     *@param weight, the weight of the item.
     *
     */
    public Item(String description, int weight)
    {
        this.itemDescription = description;
        this.weight = weight;
    }

    /**
     * When you find an item tells you the description.
     * @return String a string description.
     */
    public String itemDescription()
    {
        return "You have found " + itemDescription + " and the weight is " + this.weight +".\n";
    }
    }

