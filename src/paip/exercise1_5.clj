(ns paip.exercise1_5
  (:gen-class))

;; Exercise 1.5 [m] Write a function to compute the dot product of two sequences of numbers,
;; represented as lists. The dot product is computed by multiplying corresponding elements
;; and then adding up the resulting products.

(defn dot-product
  "Calculates the dot product. Assuming vectors are of even length and consist of numbers."
  [coll1 coll2]
  (if (empty? coll1) 0
    (+ (* (first coll1) (first coll2))
       (dot-product (rest coll1) (rest coll2)))))

;(defn dot-product2
;  "Calculates the dot product. Assuming vectors are of even length and consist of numbers."
;  [coll1 coll2]
;  (apply + (mapcat #(* %1 %2) coll1 coll2)))
