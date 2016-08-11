(ns paip.exercise2_2
  (:require paip.simple)
  (:gen-class))

; Exercise 2.2 [m] Write a version of `generate` that explicitly
; differentiates between terminal symbols (those with no rewrite rules) and
; non-terminal symbols.

; We can rename a predicate:

(defn non-terminal?
  "Determines if a phrase is non terminal."
  [phrase]
  (keyword? phrase))

(defn generate-non-terminal
  "Generate a random sentence or phrase"
  [phrase]
  (cond (vector? phrase) (mapcat generate-non-terminal phrase)
        (non-terminal? phrase) (generate-non-terminal (rand-nth (paip.simple/rewrites phrase)))
        :else (list phrase)))
