# My Personal Project

## Maplestory cubing simulator

About my *cubing simulator*:
- My application will allow users to add equipment and re-roll stats with cubes until a desired outcome
- As a maplestory player myself, I know the satisfaction that comes with cubing and finally achieving the desired legendary stats, but the cost that comes with it is too high. 
- Players can use this simulator to roll cubes without having to spend thousands of dollars.

## User Story

- As a user, I want to be able to obtain equipment that can be enhanced
- As a user, I want to be able to use cubes on my obtained equipment to re-roll for desired stats
- As a user, I want to be able to save my completed equipment with the stats
- As a user, I want to be able to delete old equipment that I don't need anymore
- As a user, I want to be able to save my equipment list to a file
- As a user, I want to be able to load in equipment that I previously saved


## Instructions For Grader

- You can generate the first event by clicking View/Edit equipments List and adding/removing equipments
- You can generate the second event by clicking Edit Equipment Stats to view and generate your desired stats (flame/cube)
- My visual component is the maplestory logo on a pop up
- You can load from json by clicking the button on the other pop up
- You can save your list to a json by clicking the save list button on the main menu

## Sources:
- https://docs.oracle.com/javase/tutorial/uiswing/events/intro.html
- https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
- https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-MenuDemoProject.zip
- http://www.java2s.com/Tutorial/Java/0260__Swing-Event/ListeningtoJListEventswithaListSelectionListener.htm
- https://www.javatpoint.com/Displaying-image-in-swing
- https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#eg

## Phase 4: Task 2
Thu Dec 01 18:06:45 PST 2022\
Loaded List\
Thu Dec 01 18:06:51 PST 2022\
Added Equipment and Cube to list\
Thu Dec 01 18:06:52 PST 2022 \
Removed Equipment and Cube from list\
Thu Dec 01 18:06:55 PST 2022\
Rerolled Cube\
Thu Dec 01 18:06:56 PST 2022\
Flamed Equipment\
Thu Dec 01 18:06:58 PST 2022\
Saved List

## Phase 4: Task 3
There is much refactoring to be worked not seen through the uml design specifically but in the code itself.\
Though looking at the uml the code is more complex than it needs to be as I could've just had combined\
equipment and cubes into a single class instead of a class for each and combined into a List class.
