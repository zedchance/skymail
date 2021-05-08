# SkyMail 3000

A graphical game where you play as a Helicopter flying between sky scrapers delivering mail. Created for CS133 during
Spring 2021.

# The object of the game

The player controls a helicopter and attempts to fly to each sky scraper in order, while maintaining a balance between
fuel and damage. Other helicopters compete against the player and attempt to do the same, or do directly damage the
player. The player must navigate between other moving objects such as birds, and must make decisions on when to deviate
from the flight path to refuel.

The player starts with 3 lives, and loses a life when their helicopter runs out of fuel, or if they take too much
damage. If the player loses all 3 lives, or if the opponent helicopter finishes the flight path before the player, it is
game over and the player loses. If the player can successfully land at each sky scraper in order, while both maintaining
adequate fuel and damage levels, the player wins. If the player wins, the elapsed time that it took to win is
essentially the score at the end of the game.

# How to play

Use the flight controls below to control the helicopter.

Keybind | Description
--- | ---
`a` or up arrow | accelerate
`b` or down arrow | decelerate
`l` or left arrow | turn left
`r` or right arrow | turn right

Fly over sky scrapers to land at them. You must land at each SkyScraper in order.

Fly over refueling blimps to refuel your helicopter.

If your helicopter becomes too damaged, you will lose a life and the game will restart. If you lose all 3 lives, it is
game over.

# Controller

## Game

The top level `Form` is `Game`, which is a `BorderLayout`. This is the main view that holds 3 smaller
views: `GlassCockpit` in the north, `MapView` in the center, and a `ButtonControls` in the south.
`Game` "has-a" `GameWorld`, and also handles adding the key listeners to specific key bindings to play the game
(See Commands below).

## Game's run method

`Game` implements Java's built in `Runnable` interface. The CN1 `UITimer` class controls when the `run()` method is
called, and is continually being refreshed every 15 ms. On the very iteration of this being called, the objects in the
world are spawned, and the reason for this is to let the views called their `laidOut()` method so the objects can be
spawned randomly with the `MapView`'s width and height as the bounds. The `run()` method checks if the world needs to be
reset, and if the game is over. If the game is over, the `GameWorld`'s `exit()` method is called, and if not then
the `GameWorld`'s `tick()` method is called.

# Model

## GameWorld

`GameWorld` handles all the objects in the world, and the interactions that these objects have with one another.
`GameWorld` "has-a" `GameObjectCollection`, which is a data structure containing all the objects in the world. When the
world is initialized, the objects are spawned and added to the collection. The starting `SkyScraper` and the
player's `Helicopter` are both spawned at an x,y location of (100, 100). The rest of the `SkyScraper`s are spawned
randomly within the bounds of the map. 2 `RefuelingBlimp`s are spawned in random locations, and 2 `Bird`s are spawned in
random locations, with random heading and speed. 3 `NonPlayerHelicopter`s are spawned in random locations as well.
The `GameWorld` exposes the world so that the views can observe the changes happening to the world.
`GameWorld` loops through the world and calls each `Movable` objects `move()` method for each tick in the game (
controlled by the `UITimer` described above).

### Commands

`GameWorld` is exposed to the commands that are bound to the key listeners created in `Game`. Each command calls methods
that interact with the world, most of which having to do with the player's helicopter. These commands consist of
accelerating, braking, turning left/right, refueling, and simulating collisions. Currently, the collisions still need to
be simulated as no automatic collisions detection is occuring.

These commands all implement an `actionPerformed()` method, as is defined by CN1's `Command` interface.

## Movable objects

`Movable` is an abstract class defining a game object that has the ability to change its location on each tick of the
game. These objects have both a speed and a heading attribute, and define a `move()` method that describes the behavior
of the object when it changes location.

### Bird

One of the concrete `Movable` objects is the `Bird`.
`Bird`s simply fly around the map randomly. They spawn in random locations, with random heading and speed. When
a `Bird`'s `move()` method is called, a random change to heading (between -5 and +5 degrees) happens every 10 game
ticks. If the player collides with a `Bird`, then the bird is despawned and a new one is spawned in a new random
location.

### Helicopter

The `Helicopter` is the game object that the player controls.
`Helicopter`s have specific attributes that have to do with the playability of the game, for example:
maximum speed, fuel consumption, and damage level. The player has to find a balance between not consuming too much fuel
and not damaging the helicopter while attempting to fly the entire flight path.

The speed of the helicopter is determined by its maximum speed attribute, its current fuel (lighter helicopters fly
faster), and its damage level (more damaged helicopters fly slower).

`Helicopter` implements the `ISteerable` interface, which defines a method called `changeDirection()`. This allows the
heading of the helicopter to be changed, allowing the object to be steered around the map.

When a helicopter contacts the wall (the side of the map), it takes some damage and its speed is set to 0.

### NonPlayerHelicopter

The `NonPlayerHelicopter` extends `Helicopter`, and is controlled by the computer rather than the player. They spawn in
random locations inside the map's bounds, with random heading. All `NonPlayerHelicopter`s fly the same speed, and do not
accelerate or decelerate. The reason for this is so that the player can effectively out run a `NonPlayerHelicopter` that
is on an imminent collision course. These helicopters have specific flight strategies, and do not fly randomly.

#### Strategies

##### Attack strategy

1 of the `NonPlayerHelicopter`s uses the attack strategy. This strategy calculates a heading that flies directly toward
the player, in an attempt to collide and damage the player's helicopter. Since `NonPlayerHelicopter`s fly slower than
the player, the player must balance out running the chasing attacker, and not consuming too much fuel and wasting time
while attempting to win.

##### Fly to sky scraper strategy

1 of the `NonPlayerHelicopter`s uses this strategy, and attempts to fly the entire flight path of sky scrapers in order.
This is the helicopter that the player is essentially playing against, as it is trying to complete the flight path
before the player can do so.

##### Circle strategy

1 of the `NonPlayerHelicopter`s uses the circle strategy. This strategy simply flies in a large circle in an attempt to
occasionally collide with the player. This strategy can be thought of as defending a portion of the map, as the player
colliding too much can mean a game over and a loss of a life.

## Fixed objects

Fixed objects are game objects that do not move during game play.

### SkyScraper

The `SkyScraper` is the checkpoint that the player must reach. Each `SkyScraper` has a sequence number associated with
the order in which the player must reach them. If the player attempts to land at a sky scraper out of order, the landing
request is denied. If the player lands at the correct sky scraper, the player's last reached sky scraper attributed is
incremented.

### RefuelingBlimp

The `RefuelingBlimp` is a fixed object that allows the player's helicopter to refuel. When the player is low on fuel,
colliding with a `RefuelingBlimp` can refuel the helicopter and allow the player to continue flying.

The `RefuelingBlimp` has a randomly generated fuel capacity, as a proportion of the size of the blimp. When
a `RefuelingBlimp` has unloaded all of its fuel, a new blimp is randomly spawned in the world.

# View

As is described above, the `Game` is the main `Form` that holds all the views. These views have a reference to
the `GameWorld`, and effectively observe the world.

## GlassCockpit

The `GlassCockpit` is the player's dashboard inside the helicopter. This dashboard consists of 6 elements: elapsed game
time, fuel level, damage level, remaining lives, last checkpoint reached, and the current heading of the helicopter as
compass degrees.

The `GlassCockpit` is self animating and gets all the dash elements from the `GameWorld` when it is repainted.

### GameClockComponent

The `GameClockComponent` is a self-animating component that displays the elapsed time as minutes seconds and
milliseconds. This allows the player to keep an eye on how much in-game time has passed. When the clocks reaches 10
minutes, the digits turn from green to red.

## MapView

The `MapView` is a top-down view on the world. Each time the `MapView` repaints itself, it loops through the world and
calls each `draw()` method for the objects. Since each game object knows how to draw itself, the `MapView` will not have
to be changed if new game objects are added.

When the `MapView`'s `laidOut()` method is called, it sets the width and height of the map so that the game objects can
spawn randomly within the bounds of the map. A wall is drawn around the outside of the map to indicate the edge of the
map, and if objects reach this point they respond according to the object's wall behavior.

# Assets

The assets in game are images that represent each images. These images 
