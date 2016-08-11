(ns paip.exercise2_4
  (:require paip.simple)
  (:gen-class))

; Exercise 2.4 [m] One way of describing `combine-all` is that it calculates the
; cross-product of the function `append` on the argument lists.
; Write the higher-order function `cross-product`, and define `combine-all` in
; terms of it.
; The moral is to make your code as general as possible, because you never know
; what you may want to do with it next.

(defn cross-product
  "Calculates the cross product."
  [f coll1 coll2]
  (if (empty? coll1)
    '(())
    (for [x coll1
          y coll2]
      (f x y))))

(defn combine-all
  [xlist ylist]
  (cross-product list xlist ylist))
