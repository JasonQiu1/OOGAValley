# DESIGN Document for PROJECT_NAME

### Team 3 Farm Game

### Names Jason Qiu, Spencer Katz, Ryan Teachman, Yinuo Guo, Beilong Tang, Saad Hakim

## Team Roles and Responsibilities

* Team Member Jason Qiu
    * Model Api
    * Data saving and loading

* Team Member Spencer Katz
    * Model Api
    * Interaction and logic

* Team Member Ryan Teachman
    * Authoring Environment view

* Team Member Yinuo Guo
    * Shop UI
    * Firebase

* Team Member Beilong Tang
    * Playing Page UI
    * Integration with model api

* Saad Hakim
    * Reflection and reformatting

## Design Goals

* Goal #1

* Goal #2

* Goal View-model separate
    * The UI only uses the data and method API from the model to do the view stu

#### How were Specific Features Made Easy to Add

* Feature #1: Make Game Object Easy to add
    * In our configuration, all game objects has its own property. In order to create a new game
      object,
      let's say a bed for the user to sleep, we need to
      // TODO: finish this

* Feature #2

* Feature #3

## High-level Design

#### Core Classes and Abstractions, their Responsibilities and Collaborators

* Class #1

* Class #2

* Class #3 PlayingPageView.java
    * This class is the core class for the playing part of the game. It is responsible for
      displaying
      the data from the model and creating the game loop that keeps updating the game. Its
      collaborators is
      `GameInterface.java`, which is the model api interface and the `ShoppingPageView.java` for
      opening
      the shopping page, and `BagView.java` for displaying items in the bag.

* Class #4

## Assumptions or Simplifications

* Decision #1

* Decision #2

* Decision #3 No Game Character and No Map Adjustment
    * In our game, there is no game character that moves around. All actions are done by clicking
      with
      the objects. This assumption lowers the burden from the view team. Also, the map has a fixed
      size and not scrollable once specified in the authoring environment. This also lowers the
      burden
      for view team.

* Decision #4

## Changes from the Original Plan

* Change #1

* Change #2

* Change #3 Plant Model Changed
    * Initially, we have a plant model that can get the progress of plants. It is deprecated
      now, called `PlantModelInterface.java`. This will help the view to show the progress of the
      plants.
      This is changed base on the difficulty of generalizing it with other game objects in the
      model. Instead, we have each
      plant
      as a unique object and the growing phase of the plants are also game object. In other words,
      we can have
      immature wheat, and wheat as two separate game objects instead of single one.

* Change #4

## How to Add New Features

#### Features Designed to be Easy to Add

* Feature #1

* Feature #2

* Feature #3

* Feature #4

#### Features Not Yet Done

* Feature #1 Add Building
    * In our implementation now, there is no building. In order to add a building, we need to ...
      // TODO: finish this

* Feature #2

* Feature #3

* Feature #4