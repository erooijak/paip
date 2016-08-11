(ns paip.exercise2_1
  (:require paip.simple)
  (:gen-class))

; Exercise 2.1 [m] Write a version of `generate` that uses `cond` but avoids calling
; `rewrites` twice.

; Instead of checking it the rewrites are non-empty, in our way of defining the grammar
; (using the native Clojure map) we can perform the same check by checking if the phrase is
; a keyword.

(defn generate-without-calling-rewrites-twice
  "Generate a random sentence or phrase"
  [phrase]
  (cond (vector? phrase) (mapcat generate-without-calling-rewrites-twice phrase)
        (keyword? phrase) (generate-without-calling-rewrites-twice (rand-nth (paip.simple/rewrites phrase)))
        :else (list phrase)))
