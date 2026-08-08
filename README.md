# Homie Hand

A teamwork-based 2D platformer written in Java, with no dependencies outside the JDK.

**All artwork is done by me.**

There are two players. **Big Player** walks left and right and throws a hammer. **Small Player** can
only jump. Neither can finish a level alone. A level is meant to be complete only when both players
reach the end section together. (The win condition itself isn't wired up yet. See
[Roadmap](#roadmap). Right now it's a sandbox.)

The hammer knocks Small Player around, and that's the point. It's how you get Small Player somewhere
it can't jump to on its own. Big Player can't throw while the two are touching.

## Play

1. Download `HomieHand.jar` from the [latest release](https://github.com/UncleAryan/HomieHand/releases/latest).
2. Double-click it, or run:

```
java -jar HomieHand.jar
```

Needs **Java 17 or newer**. If `java -version` fails, grab a JDK from
[ORACLE](https://www.oracle.com/java/technologies/downloads/).

## Controls

| Key | Action |
| --- | --- |
| `A` / `D` | Move Big Player left / right |
| `W` | Big Player throws the hammer |
| `Space` | Small Player jumps |
| `Esc` | Back to the main menu |

## Build from source

No Maven, no Gradle. Two commands.


**Windows PowerShell:**

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
jar --create --file HomieHand.jar --main-class core.Main -C out . -C res .
```

Then `java -jar HomieHand.jar`.

The `-C res .` copies the contents of `res/` to the root of the jar, which is where the game looks
for its images. See `ImageLoader`.

> **Windows note:** if `javac` works but `jar` reports "not recognized", your PATH is pointing at
> `C:\Program Files\Common Files\Oracle\Java\javapath`, which only provides `java` and `javac`. Call
> the real one directly: `"C:\Program Files\Java\jdk-24\bin\jar" --create ...` (adjust the version to
> match your install).

## Open it in an IDE

Both source folders have to be registered, or the game compiles but can't find its images at runtime.

- **IntelliJ IDEA**: open the project folder, then right-click `src` -> *Mark Directory as* ->
  *Sources Root*, and `res` -> *Mark Directory as* -> *Resources Root*.
- **Eclipse**: File -> New -> Java Project (uncheck *Use default location*, point at this folder), then
  add both `src` and `res` as source folders under Build Path.

Run `core.Main`.

## How it works

Levels are drawn as PNGs. Each pixel of `res/level1.png` is one 32×32 block, and the pixel's color
picks which block it is. `LevelHandler` reads the image, and `BlockType` maps RGB to a column in
`res/block_spritesheet.png`:

| Color (R, G, B) | Block |
| --- | --- |
| 0, 102, 0 | Grass |
| 64, 64, 64 | Dirt |
| 0, 51, 0 | Stone |
| 51, 0, 0 | Lava |
| 0, 0, 51 | Ice |

So building a level means opening a PNG in any pixel editor and painting it. Anything that isn't one
of those colors is empty space.

The rest is plain Swing: `Panel` runs the game loop on its own thread at a configurable frame rate
(set it in the Settings menu) with a fixed 200 tick rate, and `GameObjectHandler` ticks and renders
everything in one list.

## Contributing

This project is open source and contributions are welcome. Bug reports, ideas, and pull requests all
help.

- [Just open an issue](https://github.com/UncleAryan/HomieHand/issues).
  Include your issue/idea. For a bug, what you did right before it
  happened.
- I am going to start the implementation of a level editor to start making this a game and less of a computer science highschool Intro to Java final project (which is where it was started).

There is no build tooling and no test suite, so "tested" means: build the jar, run it, and play
through the part you touched.

I review and merge everything myself, so give me a little time to get to it.

**On the artwork:** all the sprites in `res/` are drawn by me. The MIT license covers them too, but
if you're lifting the art for something unrelated, I'd appreciate a heads up and a credit.

## Roadmap

- The win condition: detect both players in the end section together.
- A scrolling camera. `level1.png` is 256×256, but only the first screen is visible right now.
- Networked co-op, so the two players don't have to share a keyboard.
