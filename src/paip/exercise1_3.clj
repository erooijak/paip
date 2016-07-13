(ns paip.exercise1_3
  (:gen-class))

; Exercise 1.2 [m] Write a function to exponentiate, or raise a number to an integer power.
; For example: (power 3 2) = 3^2 = 9.

(defn count-atoms
  "Count the symbols in an expression. nil is considered an atom."
  [expression]
  (if (symbol? expression) 1
      (+ (count-atoms (first expression))
         (count-atoms (rest expression)))))
