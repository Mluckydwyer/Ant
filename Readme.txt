Ant Art Readme
http://Github.com/Mluckydwyer/Ant
Purpose
The purpose of this program is it allow the user to create and play with colorful and unique patterns. One of the main strengths of the program is how robust it is. It allows the user to switch between modes seamlessly while also dynamically providing on screen information to aid the user in understanding what is happening on screen. This was created independently by one man, Mluckydwyer (Matt Dwyer) over the course of a 4 week time period. This program includes a n Automatic mode for display and showcase purposes. It also includes a manual mode where the user can draw and paint preset or random patterns on a blank canvas. This allows for a very diverse program that can be put on display, or used as an easy creative tool. It is also a very intuitive program, meaning it is easy to figure of for a person never using it before. This allows almost anybody to pick up and use the program with no prior knowledge; mainly due to the to the easy to read onscreen controls.


Difficulties
During those 4 short weeks, many problems were encountered. One such problem was that sometimes a intricate design would be randomly generated. If the user wanted to replicate that design, it was impossible until each individual cell was changed to hold the last pattern that manipulated it. Allowing the user to go back and dynamically move from automatic mode to manual mode seamlessly. This creates a smooth, flowing, enjoyable experience for the user. Another problem that arose was during the creation of the graphical user control window. This part was abandoned due to lack of time to complete it to the quality necessary and opted instead for an on screen Heads Up Display (HUD) that could be toggled and interacted with using the mouse buttons and the keyboard.
Code
This piece of code is the main piece that calculates the generations for each ant and is located in the Ant Class:


// renderNext calculates the next generation of an ant
public void renderNext(Render render) {  
// This would zoom the window out if it was enabled          
            if (getX() >= render.cells.getCells().length || getX() < 0 || getY() >= render.cells.getCells()[1].length || getY() < 0)
                    render.zoom(this);


            Point prevousPosition = new Point(getX(), getY());
            Color nextColor = new Color(255);


// Checks to see if the ant is outside the cell grid, if so delete it
            if (getX() >= render.cells.getCells().length || getY() >= render.cells.getCells()[0].length)
                    terminate(render);
            
// if this ant hasn’t been deleted, move the ant, and get the color to set the last cell to
            if(render.containsAnt(this)) {
                    nextColor = next(render.cells.getCell(getX(), getY()).getColor());
                    
// if no error is run into and the color isn’t null, set the old cell to the new color
                    if (nextColor != null) render.cells.setCell(new Cell(nextColor, getPattern()), (int) prevousPosition.getX(), (int) prevousPosition.getY());
            }


// and finally if the ant is in the same spot as it started the last generation, delete it.
            if (prevousPosition.equals(new Point(getX(), getY()))) {
                    terminate(render);
            }


    }