(ns paip.exercise3_1
  (:gen-class))

(let [x 6
      y (* x x)]
  (+ x y)) ; => 42

; Exercise 3.1 [m] Show a `lambda` expression that is equivalent to the
; above `let*` expression. You may need more than one `lambda`.*

((fn [x]
   ((fn [y] (+ x y))
    (* x x)))
 6) ; => 42
