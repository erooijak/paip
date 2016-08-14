(ns paip.exercise3_10)

; Exercise 3.10 [m] Write a version of 1ength using the function reduce.

(defn length
  "Finds the length of coll using the function reduce."
  [coll]
  (reduce (fn [acc e] (inc acc)) coll))
