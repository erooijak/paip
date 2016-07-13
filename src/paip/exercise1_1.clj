(ns paip.exercise1_1
  (:gen-class))

; Exercise 1.1 [m] Define a version of `last-name` that handles "Rex Morgan Md,"
; "Morton Downey, Jr.," and whatever other cases you can think of.

(def suffixes '("Jr." "Md"))

(defn last-name
  "Select the last name from a name represented as a list."
  [name]
  (if (some #(= (last name) %) suffixes)
    (last-name (drop-last name))
    (last name)))
