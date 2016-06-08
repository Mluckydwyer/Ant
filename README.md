# Ant
Final AP Computer Science Project Program To Create A Program To Generate Langston's Ant Art.

<span style="overflow: hidden; display: inline-block; margin: 0.00px 0.00px; border: 0.00px solid #000000; transform: rotate(0.00rad) translateZ(0px); -webkit-transform: rotate(0.00rad) translateZ(0px); width: 249.33px; height: 249.33px;">![](images/image00.gif)</span>

<span>Ant Art Readme</span>

<span class="c9">[http://Github.com/Mluckydwyer/Ant](https://www.google.com/url?q=http://github.com/Mluckydwyer/Ant&sa=D&ust=1465411811035000&usg=AFQjCNEnBZQeSavBo-kpGEvrmtOepuB5GA)</span>

## <span>Purpose</span>

<span>The purpose of this program is it allow the user to create and play with colorful and unique patterns. One of the main strengths of the program is how robust it is. It allows the user to switch between modes seamlessly while also dynamically providing on screen information to aid the user in understanding what is happening on screen. This was created independently by one man, Mluckydwyer (Matt Dwyer) over the course of a 4 week time period. This program includes a n Automatic mode for display and showcase purposes. It also includes a manual mode where the user can draw and paint preset or random patterns on a blank canvas. This allows for a very diverse program that can be put on display, or used as an easy creative tool. It is also a very intuitive program, meaning it is easy to figure of for a person never using it before. This allows almost anybody to pick up and use the program with no prior knowledge; mainly due to the to the easy to read onscreen controls.</span>

<span></span>

## <span>Difficulties</span>

<span>During those 4 short weeks, many problems were encountered. One such problem was that sometimes a intricate design would be randomly generated. If the user wanted to replicate that design, it was impossible until each individual cell was changed to hold the last pattern that manipulated it. Allowing the user to go back and dynamically move from automatic mode to manual mode seamlessly. This creates a smooth, flowing, enjoyable experience for the user. Another problem that arose was during the creation of the graphical user control window. This part was abandoned due to lack of time to complete it to the quality necessary and opted instead for an on screen Heads Up Display (HUD) that could be toggled and interacted with using the mouse buttons and the keyboard.</span>

## <span>Code</span>

<span>This piece of code is the main piece that calculates the generations for each ant and is located in the Ant Class:</span>

<span></span>

<span>// renderNext calculates the next generation of an ant</span>

<span>public void renderNext(Render render) {  </span>

<span>// This would zoom the window out if it was enabled          </span>

<span>            if (getX() >= render.cells.getCells().length || getX() < 0 || getY() >= render.cells.getCells()[1].length || getY() < 0)</span>

<span>                    render.zoom(this);</span>

<span></span>

<span>            Point prevousPosition = new Point(getX(), getY());</span>

<span>            Color nextColor = new Color(255);</span>

<span></span>

<span>// Checks to see if the ant is outside the cell grid, if so delete it</span>

<span>            if (getX() >= render.cells.getCells().length || getY() >= render.cells.getCells()[0].length)</span>

<span>                    terminate(render);</span>

<span>           </span>

<span>// if this ant hasn’t been deleted, move the ant, and get the color to set the last cell to</span>

<span>            if(render.containsAnt(this)) {</span>

<span>                    nextColor = next(render.cells.getCell(getX(), getY()).getColor());</span>

<span>                   </span>

<span>// if no error is run into and the color isn’t null, set the old cell to the new color</span>

<span>                    if (nextColor != null) render.cells.setCell(new Cell(nextColor, getPattern()), (int) prevousPosition.getX(), (int) prevousPosition.getY());</span>

<span>            }</span>

<span></span>

<span>// and finally if the ant is in the same spot as it started the last generation, delete it.</span>

<span>            if (prevousPosition.equals(new Point(getX(), getY()))) {</span>

<span>                    terminate(render);</span>

<span>            }</span>

<span></span>

<span>    }</span>
