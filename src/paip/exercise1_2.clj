(ns paip.exercise1_2
  (:gen-class))

; Exercise 1.2 [m] Write a function to exponentiate, or raise a number to an integer power.
; For example: (power 3 2) = 3^2 = 9.

(defn power
  "Exponentiate a base to a power"
  [base power]
  (loop [b base
         n power
         result 1]
    (if (= 0 n)
      result
      (recur b (dec n) (* b result)))))
