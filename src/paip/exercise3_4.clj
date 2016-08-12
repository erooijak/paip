(ns paip.exercise3_4
  (:gen-class))

; Exercise 3.4 [m] ### Exercise 3.4 [m] Write a function that, like the regular `
; print` function, will print an expression in dotted pair notation when necessary
; but will use normal list notation when possible.

; It is impossible to create an improper list with native Clojure data structures.

(cons 2 3) ; => IllegalArgumentException (because 3 is not a sequence)

; This makes it impossible to write a test.
