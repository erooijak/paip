(ns paip.exercise3_2
  (:gen-class))

; Exercise 3.2 [m] The function `cons` can be seen as a special case of one
; of the other functions listed previously. Which one?

(list* 1 '(2 3 4)) ; => (cons 1 '(2 3 4))
