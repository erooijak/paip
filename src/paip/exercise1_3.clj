(ns paip.exercise1_3
  (:gen-class))

; Exercise 1.3 [m] Write a function that counts the number of atoms in an expression.
; For example: `(count-atoms '(a (b) c)) = 3. Notice that there is something of an ambiguity
; in this: should (a nil c) count as three atoms, or as two, because it is equivalent to
; (a () c)?

(defn count-atoms
  "Counts the number of elements in an expression. nil is considered an atom.
  Clojure does not have true atoms, so better to call this method count-elements."
  [expression]
  (->> expression
       flatten
       count))
