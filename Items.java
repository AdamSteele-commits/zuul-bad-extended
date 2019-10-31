/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class contains all the relevbant code regarding the items within our game
 * 
 */
 
 public class Items 
 {


    private String itemDescription;
    private int itemWeight;
 /**
     * Add an item to the Room
     * @param description The description of the item
     * @param weight The item's weight
     */
    public void addItem(String description, int weight) 
	{
        itemDescription = description;
        itemWeight = weight;               
    }
    
    /**
     * Does the room contain an item
     * @param description the item
     * @ return the item's weight or 0 if none
     */
    public int containsItem(String description) 
	{
        if (itemDescription.equals(description)) 
		{
            return itemWeight;
		}
        else
		{
			return 0;
		}
    }
    
    /**
     * Remove an item from the Room
     */
    public String removeItem(String description) 
	{
        if (itemDescription.equals(description)) 
		{
            String tmp = itemDescription;
            itemDescription = null;
            return tmp;
        }
        else 
		{
            System.out.println("This room does not contain" + description);
            return null;
        }
    }



    public String getItemDescription() 
    {
        return this.itemDescription;
    }

    public void setItemDescription(String itemDescription) 
    {
        this.itemDescription = itemDescription;
    }
    
    public int getItemWeight() 
    {
        return this.itemWeight;
    }

    public void setItemWeight(int itemWeight) 
    {
        this.itemWeight = itemWeight;
    }

    public String getCharacter() 
    {
        return this.character;
    }

    public void setCharacter(String character) 
    {
        this.character = character;
    }
}
