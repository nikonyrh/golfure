# golfure

A quickly hacked together Lisp "language", which actually just a few Clojure wrappers
which replaces long function names like `partition` and `partition-by` by 1 or 2 character
long aliases like `P` and `Pb`. Just kidding, they are `T` and `Tb` because I already used
`P` for `apply` :D

I intend to use this for Stackexchange's [Codegolf](https://codegolf.stackexchange.com/)
problems because although you can get quite succint solutions with Clojure the long function
names are really costly in terms of byte count.

Note that these symbols are currently "aliased": `s`, `n`, `t`, `m`, `f`, `e`, `o` and `p`
(obviously in no particular order). But you can actualy re-introduce them to your scope if
you use special forms like `let` or `for`.

## Examples

Source file: [examples.clj](src/golfure/examples.clj)

There are a few `assert`s within the [source code](src/golfure/core.clj) so have a peek there if you are curious.

## How to run a Golfure script

Loading the source code to the current namespace:

```(-> "https://raw.githubusercontent.com/nikonyrh/golfure/v0.1.1/src/golfure/core.clj" slurp (subs 39) load-string)```

Then simply evaluate the code by using the `golfure-eval` macro.
