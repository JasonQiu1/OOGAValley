# oogasalad

## Team 3

## Jason Qiu (jq48), ...

This project implements an editor and player for 2D farming-type games like Stardew Valley, Harvest
Moon, Farmville, etc.

### Timeline

* Start Date: March 21, 2024

* Finish Date: May 2, 2024

* Hours Spent:
    * Jason: 61 hours

### Attributions

* Resources used for learning (including AI assistance)
    * Jason: Referenced Baeldung, Stackoverflow, and Javadoc.io to learn how to use Gson

* Resources used directly (including AI assistance)
    * Jason: None

### Running the Program

* Main class: `src/main/java/oogasalad/Main.java`

* Data files needed: All found in `data/`.
    * Each folder has a README or FOLDER_PURPOSE document describing its purpose.
    * Broad overview:
        * `data/configurablesstores/`: Contains stores of user-created configurables
        * `data/gameconfigurations/`: Contains user-created game configurations
        * `data/gamesaves/`: Contains saves for particular game configurations
        * `data/images/`: Contains images referenced by configurables
        * `data/templates/`: Contains templates used internally by the model for all the above data
          files.
        * `data/test/`: Contains data files used only for testing.
        * `data/viewconfigurations`: Contains data files used to configure the view.

* Interesting data files:
    * `data/gameconfigurations/SUNFLOWER_GAME.json`
        * The sunflower game created in the final demo created by Jason.
        * Sunflowers can be harvested using hoes, using a scythe on them will turn them into
          foliage, and leaving them alone for too long turns them into boulders.
        * Collect 10 sunflower seeds to win!
        * Load the save `data/gamesaves/sunflower_save.json` to see a fun message!
    * `data/gameconfigurations/Battleship.json`
        * A battleship game created by Jason.
        * Place two targets on your side and cover up your entire side using the water tiles before
          saving to web.
        * Let your friend log in to your account via the web interface, load the game, and set up
          their side of the board as above.
        * Take turns using the torpedo on the other person's side of the board to find their hidden
          targets, saving and loading the game to end each turn.
        * Whoever destroys both targets first wins! (don't cheat!)

* Key/Mouse inputs:
    * In the gameplay view (not including the shop!):
        * Click on items in the bag to select them.
        * Click on tiles on the map to interact with them using the currently selected item.
        * Click on the sleep button to sleep until the configured wake time.
        * Click on the shop icon to open up the shop to buy and sell items.
        * Click on the question mark icon to open up the ChatGPT assistant.
        * Click on the save button to save the current game state to a file to load later.
        * Click on the menu button to play/load a new game configuration or go back to the splash
          screen.
        * Cheat Keys (doesn't work in the shop):
            * A: Gives you one of each item in the currently-loaded configurables store.
            * M: Gives you 1000 monies.
            * N: Advances time by one hour.
            * S: Forces the shop to update its item rotation available for sale.
            * E: Restores energy back to full.
    * In the editor
        * Just click!
        * Click the question mark button for information about using the editor.

### Notes/Assumptions

* Assumptions or Simplifications:
    * Can only play a single game at a time due to design issues with the `GameConfigurablesStore`
      class.
    * Item drops (collectables) on the map only display a single item in its stack.

* Known Bugs:
    * Switching between the editor and game too many times can sometimes cause a heap overflow
      crash.
    * Ending the game using the cheat keys can spawn a ton of results screens, crashing the program.
    * Some game configurations can't be saved/loaded using the web interface due to data errors.
    * There are many exceptions thrown from the model that aren't caught by the view which may
      create many red error messages in the console. This usually only occurs when opening old
      configuration files that are missing new properties.
    * Energy recovery activates a lot when you first load the game

* Features implemented:
    * Playing game configurations using the default state defined in the configuration.
    * Saving and loading game saves.
    * Creating and saving new game configurations in the editor.
    * Extremely configurable system for land and structures!
        * Can look like whatever image you give it!
        * Can transforms into whatever you want after a certain amount of time (if you want)!
        * Can self-destruct (expire) after a certain amount of time (if you want)!
        * Can transform into different things based on the item you use on them, you choose!
        * Can create structures on top of land by specifying what structure is created by the item
          used on the land!
        * Can be destroyed by any item or only those you define!
        * Can drop whatever and however many items you'd like!
    * Configurable item drops (collectables) on the map!
        * Can expire after a configurable amount of time!
    * Extremely configurable items!
        * Can look like whatever you want!
    * Extremely configurable game rules!
        * Can change what happens on zero energy (death or collapse)!
        * Can change how much time passes after collapsing!
        * Can change the frequency and amount of energy recovery (can be negative too)!
        * Can change goal conditions (game over after a certain amount of time or collecting a
          certain number of items)!
        * Change shop properties (number of items on rotation, frequency of rotation, possible items
          to choose for rotation)!
        * Change the hour on which you wake up after sleeping!
        * Change starting items!
        * Change amount of max energy!

* Features unimplemented:
    * Display for game configuration information.
    * Configuration-specific splash screen when playing the configuration.
    * Loading existing configurations into the editor.
    * Configurable toggle for the sleep button.
    * Weather+climate system.
    * Special interactable objects (like a bed to sleep).

* Noteworthy Features:
    * Save/load on web and user accounts.
    * ChatGPT assistant.

### Assignment Impressions

Jason: This assignment taught me some very valuable lessons in working with a larger team. First,
thoroughly planning out the API before doing any coding is critical for all of future development so
that all team members are on the same page and can develop independently. This should be prioritized
above all else at the beginning of the project. Second, pair/team programming, especially with team
members not working on the same part of the codebase as you, is vital to ensure that blockers are
resolved as quickly as possible and also serves as an extremely effective method of communication.
This promotes very efficient development, resolves any issues that the API planning may not have
covered, and again keeps team members on the same page all throughout development..